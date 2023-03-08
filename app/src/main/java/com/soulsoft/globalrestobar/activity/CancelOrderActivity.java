package com.soulsoft.globalrestobar.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.soulsoft.globalrestobar.BaseActivity;
import com.soulsoft.globalrestobar.R;
import com.soulsoft.globalrestobar.adapter.CancelOrderDetailsAdapter;
import com.soulsoft.globalrestobar.adapter.RunningOrderDetailsAdapter;
import com.soulsoft.globalrestobar.model.CommonResponse;
import com.soulsoft.globalrestobar.model.cancelkot.CancelKotBO;
import com.soulsoft.globalrestobar.model.cancelkot.CancelResponse;
import com.soulsoft.globalrestobar.model.existingkot.ExistingDetailsResponse;
import com.soulsoft.globalrestobar.model.existingkot.ExistingKotBO;
import com.soulsoft.globalrestobar.model.runningtable.RunningOrderBO;
import com.soulsoft.globalrestobar.utility.AllKeys;
import com.soulsoft.globalrestobar.utility.CommonMethods;
import com.soulsoft.globalrestobar.utility.ConfigUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;

public class CancelOrderActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "CancelOrderActivity";
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv_running_order_details)
    RecyclerView rvRunningOrderDetails;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_exit)
    AppCompatButton btnExit;
    @BindView(R.id.btn_cancel_order)
    AppCompatButton btnCancelOrder;

    private ArrayList<CancelKotBO> cancelKotBOArrayList=new ArrayList<>();
    public ArrayList<CancelKotBO> cancelKotList=new ArrayList<>();
    CancelOrderDetailsAdapter cancelOrderDetailsAdapter;
    private String stTableNo,stSectionId,stKtype;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //basic intialisation...
        initViews();

        if(getIntent().getParcelableExtra("table")!=null){
            RunningOrderBO runningOrderBO=getIntent().getParcelableExtra("table");
            //get order details of corresponding table no...
            stTableNo=runningOrderBO.getTABLE();
            stSectionId=runningOrderBO.getSID();
            stKtype=runningOrderBO.getKTYPE();
            if(CommonMethods.isNetworkAvailable(mContext)){
                getOrderDetailsOfTable(stTableNo);
            }
        }

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filterTable(s.toString());
            }
        });
    }

    @Override
    protected int getActivityLayout( ) {
        return R.layout.activity_cancel_order;
    }

    private void initViews(){
        //Setting text of toolbar..
        tvTitle.setText(getResources().getString(R.string.cancel_kot));

        //Listerners...
        btnCancelOrder.setOnClickListener(this);
        btnExit.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
        rvRunningOrderDetails.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_exit:
                finish();
                break;

            case R.id.btn_cancel_order:
                if(cancelKotList.size()==0){
                    showDialogWindow("Warning","Please cancel atleast on item!");
                }else{
                    saveCanceKOT();
                }
                Log.e(TAG, "onClick: cancelKotList"+cancelKotList.size() );
                Log.e(TAG, "onClick: cancelKotList"+cancelKotList );
                break;
        }
    }

    private void saveCanceKOT(){
        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait...");
        progressDialog.setTitle("Cancelling Order");
        progressDialog.show();
        progressDialog.setCancelable(false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, CommonMethods.getPrefrence(mContext, AllKeys.BASE_URL).concat(ConfigUrl.SAVE_CANCEL_KOT), response -> {
            Log.e(TAG, "onResponse: " + response);
            Gson gson = new Gson();

            CommonResponse commonResponse = gson.fromJson(response, CommonResponse.class);
            progressDialog.dismiss();
            if(commonResponse.getDATA().size()>0){
                Toast.makeText(mContext, "" + commonResponse.getDATA().get(0).getSTATUSMESSAGE(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

                //Call after successful kot created...
                Intent intent = new Intent(mContext, DashBoardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }, error -> {
            progressDialog.dismiss();
            Log.e(TAG, "onErrorResponse: " + error);
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("TableNo",stTableNo);
                params.put("EMPID",CommonMethods.getPrefrence(mContext,AllKeys.EMPCODE));
                params.put("SID",stSectionId);
                String data = new Gson().toJson(cancelKotList);
                params.put("STRCANCELKOTDTLS",data);
                params.put("KTYPE",stKtype);//from menu we will get
                params.put("ISPRINTCANCELKOT",CommonMethods.getPrefrence(mContext,AllKeys.ISPRINT_CANCEL_ORDER));
                params.put("ISPRINTFOOD",CommonMethods.getPrefrence(mContext,AllKeys.ISPRINT_CANCELRESTO_ORDER));
                params.put("ISPRINTBAR",CommonMethods.getPrefrence(mContext,AllKeys.ISPRINT_CANCELBAR_ORDER));
                params.put("ISPRINTRESTOBAR",CommonMethods.getPrefrence(mContext,AllKeys.ISPRINT_CANCELRESTOBAR_ORDER));
                Log.e(TAG, "getParams: " + params);
                return params;
            }
        };
        RequestQueue mQueue = Volley.newRequestQueue(mContext);
        mQueue.add(stringRequest);
    }

    private void getOrderDetailsOfTable(String tableNo){
        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait...");
        progressDialog.setTitle("Fetching data");
        progressDialog.show();
        progressDialog.setCancelable(false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, CommonMethods.getPrefrence(mContext, AllKeys.BASE_URL).concat(ConfigUrl.CANCEL_KOT_DETAILS), response -> {
            Log.e(TAG, "onResponse: " + response);
            Gson gson = new Gson();

            CancelResponse cancelResponse = gson.fromJson(response, CancelResponse.class);
            progressDialog.dismiss();
            if(cancelResponse.getTable().size()>0){
                cancelKotBOArrayList.addAll(cancelResponse.getTable());
                // llExistingOrder.setVisibility(View.VISIBLE);
                cancelOrderDetailsAdapter=new CancelOrderDetailsAdapter(mContext,cancelKotBOArrayList,CancelOrderActivity.this);
                rvRunningOrderDetails.setAdapter(cancelOrderDetailsAdapter);
            }else{
                // llExistingOrder.setVisibility(View.GONE);
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
        RequestQueue mQueue = Volley.newRequestQueue(mContext);
        mQueue.add(stringRequest);
    }

    private void filterTable(String text) {
        if(cancelKotBOArrayList !=null && cancelKotBOArrayList.size() > 0) {
            ArrayList<CancelKotBO> filteredList1 = new ArrayList<>();
            for (CancelKotBO item : cancelKotBOArrayList) {
                if (item.getITEM().toLowerCase().startsWith(text)
                        || item.getRATE().toLowerCase().startsWith(text)
                        || item.getIID().toLowerCase().startsWith(text)
                        || item.getQTY().toLowerCase().startsWith(text)) {
                    filteredList1.add(item);
                }
            }
            //Log.e(TAG, "filter: size" + filteredList1.size());
            // Log.e(TAG, "filter: List" + filteredList1.toString());
            cancelOrderDetailsAdapter.updateData(mContext, filteredList1);
        }
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
}