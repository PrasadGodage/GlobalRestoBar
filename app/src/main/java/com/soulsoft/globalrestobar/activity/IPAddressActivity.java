package com.soulsoft.globalrestobar.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.soulsoft.globalrestobar.BaseActivity;
import com.soulsoft.globalrestobar.R;
import com.soulsoft.globalrestobar.model.getcaptain.GetCaptainResponse;
import com.soulsoft.globalrestobar.utility.AllKeys;
import com.soulsoft.globalrestobar.utility.CommonMethods;
import com.soulsoft.globalrestobar.utility.ConfigUrl;

import butterknife.BindView;

public class IPAddressActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.et_ipaddress)
    EditText etIpAddress;
    @BindView(R.id.btn_validateIP)
    AppCompatButton btnValidateIp;
    private static final String TAG = "IPAddressActivity";
    public static String IP;
    public static String BASE_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //basic intialisation..
        initViews();

        etIpAddress.setOnEditorActionListener((v, actionId, event) -> {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                //Get all captain data..
                getAllCaptainData();
            }
            return false;
        });
    }

    @Override
    protected int getActivityLayout( ) {
        return R.layout.activity_i_p_address;
    }

    private void initViews(){
        btnValidateIp.setOnClickListener(this);

        if(!CommonMethods.getPrefrence(IPAddressActivity.this,AllKeys.IPAddress).equals(AllKeys.DNF)){
            etIpAddress.setText(CommonMethods.getPrefrence(IPAddressActivity.this,AllKeys.IPAddress));
            if(isIPValidated()){
                //Get all captain data..
                getAllCaptainData();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_validateIP){
            if(isIPValidated()){
                //getAllCaptain Data...
                getAllCaptainData();

                /*Intent intent = new Intent(mContext, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);*/
            }
        }
    }

    private void getAllCaptainData(){
        CommonMethods.setPreference(IPAddressActivity.this, AllKeys.IPAddress,etIpAddress.getText().toString());
        IP=CommonMethods.getPrefrence(IPAddressActivity.this, AllKeys.IPAddress);
        BASE_URL="http://"+IP+"/GRB/GRB.asmx/";

        ProgressDialog progress = new ProgressDialog(mContext);
        progress.setMessage("Connectivity to server...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
        progress.setCancelable(false);
        //BASE_URL.concat(ConfigUrl.SYS_CAPTAINLIST)
        StringRequest stringRequest=new StringRequest(Request.Method.POST,BASE_URL.concat(ConfigUrl.SYS_CAPTAINLIST), response -> {
            Log.e(TAG, "onResponse:  "+response );
            if(response != null) {
                progress.dismiss();
                Gson gson=new Gson();
                GetCaptainResponse getCaptainResponse=gson.fromJson(response,GetCaptainResponse.class);
                Toast.makeText(IPAddressActivity.this, "Connection Successful", Toast.LENGTH_LONG).show();
                CommonMethods.setPreference(mContext,AllKeys.BASE_URL,BASE_URL);
                if(getCaptainResponse.getTable().size()>0){
                    //Log.e(TAG, "onResponse:IPAddress "+CommonMethods.getPrefrence(IPAddressActivity.this, AllKeys.IPAddress) );
                    Log.e(TAG, "getAllCaptainData: "+getCaptainResponse.getTable());
                    Intent intent = new Intent(IPAddressActivity.this, LoginActivity.class);
                    intent.putParcelableArrayListExtra("captainlist",getCaptainResponse.getTable());
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(IPAddressActivity.this, "Please contact your manager!", Toast.LENGTH_LONG).show();
                    showDialogWindow("Warning","Please contact your manager!");
                    finish();
                }
            }
        }, error -> {
            progress.dismiss();
            CommonMethods.setPreference(IPAddressActivity.this, AllKeys.IPAddress, AllKeys.DNF);
            showDialogWindow("Warning","Error while connecting!");
            Log.e(TAG, "onResponse:IPAddress "+CommonMethods.getPrefrence(IPAddressActivity.this, AllKeys.IPAddress) );
            Log.e(TAG, "onErrorResponse: "+error);
        });
        RequestQueue mQueue= Volley.newRequestQueue(IPAddressActivity.this);
        mQueue.add(stringRequest);
    }

    public boolean isIPValidated(){
        if(etIpAddress.getText().toString().equals("")){
            etIpAddress.setError("Please enter IPAddress!");
            etIpAddress.requestFocus();
            etIpAddress.setFocusable(true);
            showDialogWindow("Warning","Please enter IPAddress!");
            return false;
        }

        else if(!CommonMethods.IPAdressValidator(etIpAddress.getText().toString())){
            etIpAddress.setError("Invalid IPAddress!");
            etIpAddress.requestFocus();
            etIpAddress.setFocusable(true);
            showDialogWindow("Warning","Invalid IPAddress!");
            return false;
        }
        return true;
    }

    private void showDialogWindow(String title,String message) {
        MaterialDialog mDialog = new MaterialDialog.Builder(IPAddressActivity.this)
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