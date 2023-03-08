package com.soulsoft.globalrestobar.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.soulsoft.globalrestobar.BaseActivity;
import com.soulsoft.globalrestobar.R;
import com.soulsoft.globalrestobar.adapter.GetAllCaptainAdapter;
import com.soulsoft.globalrestobar.model.LoginResponse;
import com.soulsoft.globalrestobar.model.getcaptain.GetAllCaptainBO;
import com.soulsoft.globalrestobar.model.menucard.MenuCardResponse;
import com.soulsoft.globalrestobar.model.settings.SettingsResponse;
import com.soulsoft.globalrestobar.model.table.GetTableDataResponse;
import com.soulsoft.globalrestobar.utility.AllKeys;
import com.soulsoft.globalrestobar.utility.CommonMethods;
import com.soulsoft.globalrestobar.utility.ConfigUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import io.paperdb.Paper;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.act_waitername)
    AutoCompleteTextView actWaiterName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_submit)
    AppCompatButton btnSubmit;

    GetAllCaptainAdapter getAllCaptainAdapter;

    private ArrayList<GetAllCaptainBO> getAllCaptainBOArrayList =new ArrayList<>();
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent().getParcelableArrayListExtra("captainlist")!=null){
            getAllCaptainBOArrayList.clear();
            getAllCaptainBOArrayList.addAll(getIntent().getParcelableArrayListExtra("captainlist"));
        }

        //Basic intialisation...
        initViews();

        if(CommonMethods.isNetworkAvailable(mContext)){
            //getAllMenuData...
            getMenuCardData();

            //getTableData...
            getTableData();

            //getAllSettings of restaurant.....
            getAllSettings();
        }

        etPassword.setOnEditorActionListener((v, actionId, event) -> {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                if(isValidated()){
                    //login captain, employee
                    autheticationUser();
                }
            }
            return false;
        });
    }

    @Override
    protected int getActivityLayout( ) {
        return R.layout.activity_login;
    }

    private void initViews(){
        btnSubmit.setOnClickListener(this);

        getAllCaptainAdapter = new GetAllCaptainAdapter(LoginActivity.this, getAllCaptainBOArrayList);
        actWaiterName.setAdapter(getAllCaptainAdapter);
        actWaiterName.setThreshold(1);

        actWaiterName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_submit){
            if(isValidated()){
                //login captain, employee
                autheticationUser();
            }
        }
    }

    private void autheticationUser(){
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setTitle("Logging In...");
        progressDialog.show();
        progressDialog.setCancelable(false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, CommonMethods.getPrefrence(mContext, AllKeys.BASE_URL).concat(ConfigUrl.LOGIN_CAPTAIN), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Log.e(TAG, "Login onResponse: " + response);
                LoginResponse loginResponse = gson.fromJson(response, LoginResponse.class);

                try {
                    if (!loginResponse.getTable().isEmpty() || loginResponse.getTable().size()!=0) {
                        for (int i = 0; i < loginResponse.getTable().size(); i++) {
                            progressDialog.dismiss();
                            CommonMethods.setPreference(LoginActivity.this, AllKeys.EMPCODE, loginResponse.getTable().get(i).getEMPID());
                            CommonMethods.setPreference(LoginActivity.this, AllKeys.EMPNAME, loginResponse.getTable().get(i).getEMPNAME());
                            CommonMethods.setPreference(LoginActivity.this, AllKeys.IsEnableKOT, loginResponse.getTable().get(i).getISALLOWKOT());
                            CommonMethods.setPreference(LoginActivity.this, AllKeys.IsEnableCancelKOT, loginResponse.getTable().get(i).getISALLOWCANCELKOT());
                            CommonMethods.setPreference(LoginActivity.this, AllKeys.IsEnableShiftTable, loginResponse.getTable().get(i).getISALLOWSHIFTTABLE());
                            CommonMethods.setPreference(LoginActivity.this, AllKeys.IsEnableInvoice, loginResponse.getTable().get(i).getISALLOWBILL());
                            CommonMethods.setPreference(LoginActivity.this, AllKeys.IsAllowDiscOnBill, loginResponse.getTable().get(i).getISALLOWDISCOUNTONBILL());
                        }
                        Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    }else{
                        progressDialog.dismiss();
                        showDialogWindow("Error","Invalid Login!");
                    }
                }catch(NullPointerException e){
                    e.printStackTrace();
                }
            }
        }, error -> {
            Log.e(TAG, "Login onErrorResponse: " + error);
            progressDialog.dismiss();
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("EmpName", actWaiterName.getText().toString());
                params.put("EmpPassword", etPassword.getText().toString());

                return params;
            }
        };
        RequestQueue mQueue = Volley.newRequestQueue(LoginActivity.this);
        mQueue.add(stringRequest);
    }

    private void filter(String text) {
        ArrayList<GetAllCaptainBO> filteredList = new ArrayList<>();
        for (GetAllCaptainBO item : getAllCaptainBOArrayList) {
            if (item.getEMPNAME().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        getAllCaptainAdapter.updateData(LoginActivity.this, filteredList);
    }

    public boolean isValidated() {
        if (actWaiterName.getText().toString().isEmpty()) {
            /*actWaiterName.setError("Please enter name!");
            actWaiterName.requestFocus();
            actWaiterName.setFocusable(true);*/
            showDialogWindow("Warning","Please enter name!");
            return false;
        }
        if (etPassword.getText().toString().isEmpty()) {
            /*etPassword.setError("Please enter password!");
            etPassword.requestFocus();
            etPassword.setFocusable(true);*/
            showDialogWindow("Warning","Please enter password!");
            return false;
        }
        return true;
    }

    private void showDialogWindow(String title,String message) {
        MaterialDialog mDialog = new MaterialDialog.Builder(LoginActivity.this)
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

    private void getMenuCardData(){
        ProgressDialog progress = new ProgressDialog(mContext);
        progress.setMessage("Downloading....");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
        progress.setCancelable(false);
        //BASE_URL.concat(ConfigUrl.SYS_CAPTAINLIST)
        StringRequest stringRequest=new StringRequest(Request.Method.POST,CommonMethods.getPrefrence(mContext,AllKeys.BASE_URL).concat(ConfigUrl.ITEM_MENUCARD), response -> {
            Log.e(TAG, "onResponse:  "+response );
            if(response != null) {
                progress.dismiss();
                Gson gson=new Gson();
                MenuCardResponse menuCardResponse=gson.fromJson(response,MenuCardResponse.class);
                if(menuCardResponse.getTable().size()>0){
                    //Log.e(TAG, "onResponse:IPAddress "+CommonMethods.getPrefrence(IPAddressActivity.this, AllKeys.IPAddress) );
                    Log.e(TAG, "getAllCaptainData: "+menuCardResponse.getTable());
                    Paper.book().write("menucard",menuCardResponse.getTable());
                    Log.e(TAG, "getMenuCardData: "+Paper.book().read("menucard") );
                }
            }
        }, error -> {
            progress.dismiss();
            Log.e(TAG, "onErrorResponse: "+error);
        });
        RequestQueue mQueue= Volley.newRequestQueue(mContext);
        mQueue.add(stringRequest);
    }

    private void getTableData(){
        ProgressDialog progress = new ProgressDialog(mContext);
        progress.setMessage("Downloading....");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
        progress.setCancelable(false);
        //BASE_URL.concat(ConfigUrl.SYS_CAPTAINLIST)
        StringRequest stringRequest=new StringRequest(Request.Method.POST,CommonMethods.getPrefrence(mContext,AllKeys.BASE_URL).concat(ConfigUrl.GET_SYS_TABLE), response -> {
            Log.e(TAG, "onResponse:  "+response );
            if(response != null) {
                progress.dismiss();
                Gson gson=new Gson();
                GetTableDataResponse getTableDataResponse=gson.fromJson(response,GetTableDataResponse.class);
                if(getTableDataResponse.getTable().size()>0){
                    //Log.e(TAG, "onResponse:IPAddress "+CommonMethods.getPrefrence(IPAddressActivity.this, AllKeys.IPAddress) );
                   // Log.e(TAG, "getAllCaptainData: "+getTableDataResponse.getTable());
                    Paper.book().write("tabledata",getTableDataResponse.getTable());
                    Log.e(TAG, "getTableData: "+Paper.book().read("tabledata") );
                }
            }
        }, error -> {
            progress.dismiss();
            Log.e(TAG, "onErrorResponse: "+error);
        });
        RequestQueue mQueue= Volley.newRequestQueue(mContext);
        mQueue.add(stringRequest);
    }

    private void getAllSettings(){
        ProgressDialog progress = new ProgressDialog(mContext);
        progress.setMessage("Downloading....");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
        progress.setCancelable(false);
        StringRequest stringRequest=new StringRequest(Request.Method.POST,CommonMethods.getPrefrence(mContext,AllKeys.BASE_URL).concat(ConfigUrl.SYS_BILLINGSETTING), response -> {
            Log.e(TAG, "onResponse:  "+response );
            if(response != null) {
                progress.dismiss();
                Gson gson=new Gson();
                SettingsResponse settingsResponse=gson.fromJson(response,SettingsResponse.class);
                if(settingsResponse.getTable().size()>0){
                   CommonMethods.setPreference(mContext,AllKeys.ISPRINTORDER,settingsResponse.getTable().get(0).getISPRINTORDER());
                   CommonMethods.setPreference(mContext,AllKeys.ISPRINT_REST_ORDER,settingsResponse.getTable().get(0).getISPRINT_REST_ORDER());
                   CommonMethods.setPreference(mContext,AllKeys.ISPRINT_BAR_ORDER,settingsResponse.getTable().get(0).getISPRINT_BAR_ORDER());
                   CommonMethods.setPreference(mContext,AllKeys.ISPRINT_RESTOBAR_ORDER,settingsResponse.getTable().get(0).getISPRINT_RESTOBAR_ORDER());
                   CommonMethods.setPreference(mContext,AllKeys.ISALLOW_REPRINT_ORDER,settingsResponse.getTable().get(0).getISALLOW_REPRINT_ORDER());
                   CommonMethods.setPreference(mContext,AllKeys.ISPRINT_CANCEL_ORDER,settingsResponse.getTable().get(0).getISPRINT_CANCEL_ORDER());
                   CommonMethods.setPreference(mContext,AllKeys.ISPRINT_CANCEL_ORDER,settingsResponse.getTable().get(0).getISPRINT_CANCEL_ORDER());
                   CommonMethods.setPreference(mContext,AllKeys.ISPRINT_CANCELRESTO_ORDER,settingsResponse.getTable().get(0).getISPRINT_CANCELRESTO_ORDER());
                   CommonMethods.setPreference(mContext,AllKeys.ISPRINT_CANCELRESTO_ORDER,settingsResponse.getTable().get(0).getISPRINT_CANCELRESTO_ORDER());
                   CommonMethods.setPreference(mContext,AllKeys.ISPRINT_CANCELBAR_ORDER,settingsResponse.getTable().get(0).getISPRINT_CANCELBAR_ORDER());
                   CommonMethods.setPreference(mContext,AllKeys.ISPRINT_CANCELRESTOBAR_ORDER,settingsResponse.getTable().get(0).getISPRINT_CANCELRESTOBAR_ORDER());

                }
            }
        }, error -> {
            progress.dismiss();
            Log.e(TAG, "onErrorResponse: "+error);
        });
        RequestQueue mQueue= Volley.newRequestQueue(mContext);
        mQueue.add(stringRequest);
    }
}