package com.soulsoft.globalrestobar.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.soulsoft.globalrestobar.BaseFragment;
import com.soulsoft.globalrestobar.R;
import com.soulsoft.globalrestobar.activity.DashBoardActivity;
import com.soulsoft.globalrestobar.adapter.ExistingOrderAdapter;
import com.soulsoft.globalrestobar.adapter.GeTableAdapter1;
import com.soulsoft.globalrestobar.adapter.GetMenuAdapter;
import com.soulsoft.globalrestobar.adapter.ServesInSpinnerAdapter;
import com.soulsoft.globalrestobar.adapter.TakeOrderAdapter;
import com.soulsoft.globalrestobar.model.CommonResponse;
import com.soulsoft.globalrestobar.model.TakeMenuOrder;
import com.soulsoft.globalrestobar.model.existingkot.ExistingDetailsResponse;
import com.soulsoft.globalrestobar.model.itemunit.ItemUnitBO;
import com.soulsoft.globalrestobar.model.itemunit.ItemUnitResponse;
import com.soulsoft.globalrestobar.model.menucard.MenuDataBO;
import com.soulsoft.globalrestobar.model.table.GetTableDataBO;
import com.soulsoft.globalrestobar.utility.AllKeys;
import com.soulsoft.globalrestobar.utility.CommonMethods;
import com.soulsoft.globalrestobar.utility.ConfigUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import io.paperdb.Paper;

public class TakeOrderFragment extends BaseFragment implements View.OnClickListener, TakeOrderAdapter.TakeOrderListener {

    @BindView(R.id.act_tableno)
    AutoCompleteTextView actTableNo;
    @BindView(R.id.act_menu)
    AutoCompleteTextView actMenu;
    @BindView(R.id.spin_ServesIn)
    Spinner acsSpinServesIn;
    @BindView(R.id.et_rate)
    TextInputEditText etRate;
    @BindView(R.id.et_amount)
    TextInputEditText etAmount;
    @BindView(R.id.et_qty)
    EditText etQuantity;
    @BindView(R.id.btn_clear)
    AppCompatButton btnClear;
    @BindView(R.id.btn_add)
    AppCompatButton btnAdd;
    @BindView(R.id.btn_exit)
    AppCompatButton btnExit;
    @BindView(R.id.btn_takeorder)
    AppCompatButton btnTakeOrder;
    @BindView(R.id.et_splrequest)
    EditText etSpecialRequest;
    @BindView(R.id.rv_menu)
    RecyclerView rvMenu;
    @BindView(R.id.rv_existingorder)
    RecyclerView rvExistingOrder;
    @BindView(R.id.ll_existingorder)
    LinearLayout llExistingOrder;
    @BindView(R.id.tv_total_amount)
    TextView tvTotalAmount;

    private final ArrayList<MenuDataBO> menuDataBOArrayList = new ArrayList<>();
    private final ArrayList<GetTableDataBO> getTableDataBOArrayList = new ArrayList<>();
    private MenuDataBO menuDataBO = null;
    private GetTableDataBO getTableDataBO = null;
    private static final String TAG = "TakeOrderFragment";
    private ItemUnitResponse itemUnitResponse;
    private String stServesInId, stSectionId, stGoodsCode, stServesInName, stOrderId="0", stItemType;
    private double stRate;
    private float sum = 0.0f, total;
    private final ArrayList<TakeMenuOrder> takeOrderArrayList=new ArrayList<>();
    private TakeMenuOrder takeMenuOrder;
    private TakeOrderAdapter takeOrderAdapter;
    private static int MY_SOCKET_TIMEOUT_MS = 20000;

    public TakeOrderFragment( ) {
        // Required empty public constructor
    }

    @Override
    protected int getActivityLayout( ) {
        return R.layout.fragment_take_order;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //basic intialisation...
        initViews();

        //attach data to table and menu
        setData();
    }

    private void initViews( ) {
        DashBoardActivity.tvTitle.setText(getResources().getString(R.string.take_order1));
        //setLayoutManager for Menu recyler..
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext){
            @Override
            public boolean canScrollVertically( ) {
                return false;
            }
        };

        rvMenu.setLayoutManager(linearLayoutManager);

        //setLayoutManager for Existing order..
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(mContext){
            @Override
            public boolean canScrollVertically( ) {
                return false;
            }
        };
        rvExistingOrder.setLayoutManager(linearLayoutManager1);

            ArrayList<ItemUnitBO> itemUnitBOS=new ArrayList<>();
        itemUnitBOS.add(new ItemUnitBO("","Select",""));
        ServesInSpinnerAdapter servesInSpinnerAdapter = new ServesInSpinnerAdapter(getContext(),itemUnitBOS);
        acsSpinServesIn.setAdapter(servesInSpinnerAdapter);

        //Click listeners..
        btnClear.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        btnTakeOrder.setOnClickListener(this);

        //Back pressed event for fragment..
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed( ) {
                // Handle the back button event
                AlertDialog alertbox = new AlertDialog.Builder(getContext())
                        .setMessage("Do you want to exit application?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            // do something when the button is clicked
                            public void onClick(DialogInterface arg0, int arg1) {
                                getActivity().finish();
                                //close();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            // do something when the button is clicked
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        })
                        .show();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clear:
                //clear all fields....
                clearFields();
                break;

            case R.id.btn_add:
                if (isValidatedTable()) {
                    addFood();

                    for (int i = 0; i < takeOrderArrayList.size(); i++) {
                        Log.e(TAG, "onClick: " + takeOrderArrayList.get(i).getTOTAL());
                        total = Float.parseFloat((takeOrderArrayList.get(i).getTOTAL()));
                    }
                    sum += total;
                    tvTotalAmount.setVisibility(View.VISIBLE);
                    btnTakeOrder.setText("Total: "+sum);
                    tvTotalAmount.setText("Total(Qty * Rate) "+sum);
                    // sum has the value for the food menu total.
                }
                break;

            case R.id.btn_exit:
                loadFragment(new TakeOrderFragment());
                break;

            case R.id.btn_takeorder:
                //Take order which customer had ordered
                takeOrder();
                break;
        }
    }

    //Take order which customer had ordered
    public void takeOrder() {
        if (takeOrderArrayList.size() != 0) {
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Please wait...");
            progressDialog.setTitle("Order Generating...");
            progressDialog.show();
            progressDialog.setCancelable(false);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, CommonMethods.getPrefrence(mContext,AllKeys.BASE_URL).concat(ConfigUrl.SAVE_KOT), response -> {

                Gson gson = new Gson();

                CommonResponse commonResponse = gson.fromJson(response, CommonResponse.class);

                if (commonResponse.getDATA().get(0).getSTATUSCODE().equals("1")) {
                    /*if (llSpecialRequest.getVisibility() == View.GONE) {
                        llSpecialRequest.setVisibility(View.VISIBLE);
                    } else {
                        llSpecialRequest.setVisibility(View.GONE);
                    }*/
                    Toast.makeText(getContext(), "" + commonResponse.getDATA().get(0).getSTATUSMESSAGE(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                    //Call after successful kot created...
                    Intent intent = new Intent(mContext, DashBoardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    Objects.requireNonNull(getActivity()).finish();
                } else {
                    /*if (llSpecialRequest.getVisibility() == View.GONE) {
                        llSpecialRequest.setVisibility(View.VISIBLE);
                    } else {
                        llSpecialRequest.setVisibility(View.GONE);
                    }*/
                    etSpecialRequest.getText().clear();
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "" + commonResponse.getDATA().get(0).getSTATUSMESSAGE(), Toast.LENGTH_SHORT).show();
                }
            }, error -> {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();
                Log.e(TAG, "OnErrorResponse" + error.toString());
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("TABLENO", actTableNo.getText().toString());
                    params.put("EMPID", CommonMethods.getPrefrence(mContext, AllKeys.EMPCODE));
                    params.put("SID", stSectionId);
                    params.put("KTYPE", stOrderId);
                    params.put("KOTNARRATION", String.valueOf(0));
                    params.put("USERID", String.valueOf(1));
                    String data = new Gson().toJson(takeOrderArrayList);
                    params.put("STRING_KOTLIST", data);
                    params.put("IS_PRINTKOT", CommonMethods.getPrefrence(getContext(), AllKeys.ISPRINTORDER));
                    params.put("ISPRINT_RESTAURANTKOT",CommonMethods.getPrefrence(mContext,AllKeys.ISPRINT_REST_ORDER));
                    params.put("ISPRINT_BARKOT",CommonMethods.getPrefrence(mContext,AllKeys.ISPRINT_BAR_ORDER));
                    params.put("ISPRINT_RESTOBARKOT", CommonMethods.getPrefrence(mContext,AllKeys.ISPRINT_RESTOBAR_ORDER));
                    params.put("ISPRINT_RESTAURANTCOUNTERKOT",CommonMethods.getPrefrence(mContext,AllKeys.ISALLOW_REPRINT_ORDER));
                    Log.e(TAG, "getParams: " + params);
                    return params;
                }
            };
            RequestQueue mQueue = Volley.newRequestQueue(getContext());
            mQueue.add(stringRequest);

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    MY_SOCKET_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        } else {
            Toast.makeText(getContext(), "Please order Atleast one food!", Toast.LENGTH_SHORT).show();
        }
    }

    private void addFood( ) {
        takeMenuOrder = new TakeMenuOrder(
                stGoodsCode,
                actMenu.getText().toString(),
                stItemType,
                etSpecialRequest.getText().toString(),
                stServesInId,
                etRate.getText().toString(),
                etQuantity.getText().toString(),
                etAmount.getText().toString(),
                stServesInName);

        takeOrderArrayList.add(takeMenuOrder);

        takeOrderAdapter = new TakeOrderAdapter(getActivity().getBaseContext(), takeOrderArrayList, this);
        takeOrderAdapter.notifyDataSetChanged();
        rvMenu.setAdapter(takeOrderAdapter);
        // llSpecialRequest.setVisibility(View.GONE);

        //hide keyboard..
        hideSoftKeyboard(etQuantity);

        //clear all fields....
        clearFields();
    }

    private void setData( ) {
        if (Paper.book().read("menucard") != null) {
            menuDataBOArrayList.addAll(Paper.book().read("menucard"));
            GetMenuAdapter getMenuAdapter = new GetMenuAdapter(mContext, menuDataBOArrayList);
            actMenu.setAdapter(getMenuAdapter);
            actMenu.setThreshold(1);
        } else {
            showDialogWindow("Error", "Kindly login again!");
        }

        if (Paper.book().read("tabledata") != null) {
            getTableDataBOArrayList.addAll(Paper.book().read("tabledata"));
            GeTableAdapter1 getTableAdapter = new GeTableAdapter1(mContext, getTableDataBOArrayList);
            actTableNo.setAdapter(getTableAdapter);
            actTableNo.setThreshold(1);
        } else {
            showDialogWindow("Error", "Kindly login again!");
        }

        actMenu.setOnItemClickListener((adapterView, view, i, l) -> {
            //Log.e(TAG, "onItemClick: actownername " + adapterView.getItemAtPosition(i));
            menuDataBO = (MenuDataBO) adapterView.getItemAtPosition(i);
            stGoodsCode = menuDataBO.getITEMCODE();
            stItemType = menuDataBO.getITEMTYPE();
            Log.e(TAG, "onItemClick:menuDataBO " + menuDataBO);
            etQuantity.setText("1");

            //hide keyboard
            hideSoftKeyboard(actMenu);

            if (!stGoodsCode.isEmpty()) {
                loadServesInData();
            }

            //As quantity input amount will be displayed
            calculateAmount();
        });

        actTableNo.setOnItemClickListener((adapterView, view, i, l) -> {
            //Log.e(TAG, "onItemClick: actownername " + adapterView.getItemAtPosition(i));
            getTableDataBO = (GetTableDataBO) adapterView.getItemAtPosition(i);
            Log.e(TAG, "onItemClick: getTableData " + getTableDataBO);
            stSectionId = getTableDataBO.getSID();
            stOrderId=getTableDataBO.getORDERTYPE();

            //hide keyboard
            hideSoftKeyboard(actTableNo);

            //load existing order details....
            if (!actTableNo.getText().toString().isEmpty()) {
                loadExistingOrderData(getTableDataBO.getTABLENO());
            }
        });
    }

    //load ServesIndata like Plate,180ml, 90ml etc
    public void loadServesInData( ) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, CommonMethods.getPrefrence(mContext, AllKeys.BASE_URL).concat(ConfigUrl.ITEM_UNITCARD), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                Gson gson = new Gson();
                itemUnitResponse = gson.fromJson(response, ItemUnitResponse.class);
                if (itemUnitResponse.getTable().size() != 0) {
                    ServesInSpinnerAdapter servesInSpinnerAdapter = new ServesInSpinnerAdapter(getContext(), itemUnitResponse.getTable());
                    acsSpinServesIn.setAdapter(servesInSpinnerAdapter);
                } else {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        }, error -> Log.e(TAG, "onErrorResponse: " + error)) {
            protected Map<String, String> getParams( ) throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("SectionId", stSectionId);
                params.put("GoodsCode", stGoodsCode);

                Log.e(TAG, "getParams: " + params);
                return params;
            }
        };
        RequestQueue mQueue = Volley.newRequestQueue(mContext);
        mQueue.add(stringRequest);

        acsSpinServesIn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stServesInId = itemUnitResponse.getTable().get(position).getUNITID();
                stServesInName = itemUnitResponse.getTable().get(position).getUNITNAME();
                stRate = Double.parseDouble(itemUnitResponse.getTable().get(position).getRATE());

                etRate.setText(String.valueOf(stRate));

                try {

                    if (Objects.requireNonNull(etQuantity.getText()).toString().length() == 0) {
                        Objects.requireNonNull(etAmount.getText()).clear();
                    } else {
                        double result = ((Integer.parseInt(etQuantity.getText().toString()) * stRate));
                        etAmount.setText(String.valueOf(result));
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //As quantity input amount will be displayed
    private void calculateAmount( ) {
        etQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    if (etQuantity.getText().toString().length() == 0) {
                        etAmount.getText().clear();
                    } else {
                        double result = ((Integer.parseInt(etQuantity.getText().toString()) * stRate));
                        //Toast.makeText(getContext(), "result" + result, Toast.LENGTH_SHORT).show();
                        etAmount.setText(String.valueOf(result));
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showDialogWindow(String title, String message) {
        MaterialDialog mDialog = new MaterialDialog.Builder((Activity) mContext)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok",
                        (dialogInterface, which) -> {
                            // Delete Operation
                            dialogInterface.dismiss();
                        })
                //.setNegativeButton("Cancel", (dialogInterface, which) -> dialogInterface.dismiss())
                .build();

        // Show Dialog
        mDialog.show();
    }

    //load existindetails order
    public void loadExistingOrderData(String tableNo) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.setTitle("Fetching data");
        progressDialog.show();
        progressDialog.setCancelable(false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, CommonMethods.getPrefrence(mContext,AllKeys.BASE_URL).concat(ConfigUrl.BOOKEDTABLE_KOTDETAILS), response -> {
            Log.e(TAG, "onResponse: " + response);
            Gson gson = new Gson();

            ExistingDetailsResponse existingDetailsResponse = gson.fromJson(response, ExistingDetailsResponse.class);
            progressDialog.dismiss();
            if(existingDetailsResponse.getTable().size()>0){
                llExistingOrder.setVisibility(View.VISIBLE);
                ExistingOrderAdapter orderDetailsAdapter = new ExistingOrderAdapter(getContext(), existingDetailsResponse.getTable());
                rvExistingOrder.setAdapter(orderDetailsAdapter);
            }else{
                llExistingOrder.setVisibility(View.GONE);
            }
        }, error -> {
            progressDialog.dismiss();
            Log.e(TAG, "onErrorResponse: " + error);
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("TableNo",tableNo);
                Log.e(TAG, "getParams: " + params);
                return params;
            }
        };
        RequestQueue mQueue = Volley.newRequestQueue(getContext());
        mQueue.add(stringRequest);
    }


    //validations....
    public boolean isValidatedTable( ) {
        if (actTableNo.getText().toString().isEmpty()) {
            showDialogWindow("Error", "Please select table!");
            return false;
        }

        if (actMenu.getText().toString().isEmpty()) {
            showDialogWindow("Error", "Please select menu!");
            return false;
        }

        if (etQuantity.getText().toString().isEmpty()) {
            showDialogWindow("Error", "Quantity required!");
            return false;
        }

        if (etRate.getText().toString().isEmpty()) {
            showDialogWindow("Error", "Rate cannot be empty!");
            return false;
        }

        if (etAmount.getText().toString().isEmpty()) {
            showDialogWindow("Error", "Amount cannot be empty!");
            return false;
        }
        return true;
    }


    //clear all fields....
    private void clearFields( ) {
        acsSpinServesIn.setAdapter(null);
        actMenu.getText().clear();
        etQuantity.getText().clear();
        etRate.getText().clear();
        etAmount.getText().clear();
        etSpecialRequest.getText().clear();
    }

    //On Delete button operations...
    @Override
    public void onTakeOrderClick(int position) {
        takeOrderArrayList.remove(position);
        takeOrderAdapter.notifyItemRemoved(position);
        takeOrderAdapter.notifyItemRangeChanged(position, takeOrderArrayList.size());
        /*Log.e("delete", "Array List size when deleteing data" + takeOrderArrayList.size());
        Log.e("delete", "Array List when deleteing data " + takeOrderArrayList);*/
        Float total = 0.0f;
        for (int i = 0; i < takeOrderArrayList.size(); i++) {
            Log.e("delete", "onClick: " + takeOrderArrayList.get(i).getTOTAL());
            total += Float.parseFloat((takeOrderArrayList.get(i).getTOTAL()));
        }
        sum = total;
        if(takeOrderArrayList.size()==0){
            tvTotalAmount.setVisibility(View.GONE);
            btnTakeOrder.setText("Take Order");
        }else {
            tvTotalAmount.setVisibility(View.VISIBLE);
            btnTakeOrder.setText("Total: "+sum);
            tvTotalAmount.setText("Total(Qty * Rate) "+sum);
        }
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    protected void hideSoftKeyboard(View input) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
    }

}