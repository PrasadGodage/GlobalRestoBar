package com.soulsoft.globalrestobar.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.soulsoft.globalrestobar.BaseActivity;
import com.soulsoft.globalrestobar.R;
import com.soulsoft.globalrestobar.adapter.RunningOrderDetailsAdapter;
import com.soulsoft.globalrestobar.model.existingkot.ExistingDetailsResponse;
import com.soulsoft.globalrestobar.model.existingkot.ExistingKotBO;
import com.soulsoft.globalrestobar.utility.AllKeys;
import com.soulsoft.globalrestobar.utility.CommonMethods;
import com.soulsoft.globalrestobar.utility.ConfigUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class RunningOrderDetailsActivity extends BaseActivity {

    private static final String TAG = "RunningOrderDetailsActi";
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv_running_order_details)
    RecyclerView rvRunningOrderDetails;

    private ArrayList<ExistingKotBO> existingKotBOArrayList=new ArrayList<>();
    RunningOrderDetailsAdapter runningOrderDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //basic intialisation...
        initViews();

        if(getIntent().getStringExtra("tableno")!=null){
            //get order details of corresponding table no...
            if(CommonMethods.isNetworkAvailable(mContext)){
                getOrderDetailsOfTable(getIntent().getStringExtra("tableno"));
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
        return R.layout.activity_running_order_details;
    }

    private void initViews(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
        rvRunningOrderDetails.setLayoutManager(linearLayoutManager);
    }

    private void getOrderDetailsOfTable(String tableNo){
        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait...");
        progressDialog.setTitle("Fetching data");
        progressDialog.show();
        progressDialog.setCancelable(false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, CommonMethods.getPrefrence(mContext, AllKeys.BASE_URL).concat(ConfigUrl.BOOKEDTABLE_KOTDETAILS), response -> {
            Log.e(TAG, "onResponse: " + response);
            Gson gson = new Gson();

            ExistingDetailsResponse existingDetailsResponse = gson.fromJson(response, ExistingDetailsResponse.class);
            progressDialog.dismiss();
            if(existingDetailsResponse.getTable().size()>0){
                existingKotBOArrayList.addAll(existingDetailsResponse.getTable());
               // llExistingOrder.setVisibility(View.VISIBLE);
                runningOrderDetailsAdapter=new RunningOrderDetailsAdapter(mContext,existingKotBOArrayList);
                rvRunningOrderDetails.setAdapter(runningOrderDetailsAdapter);
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
        if(existingKotBOArrayList !=null && existingKotBOArrayList.size() > 0) {
            ArrayList<ExistingKotBO> filteredList1 = new ArrayList<>();
            for (ExistingKotBO item : existingKotBOArrayList) {
                if (item.getMENUNAME().toLowerCase().startsWith(text)
                        || item.getRATE().toLowerCase().startsWith(text)
                        || item.getQTY().toLowerCase().startsWith(text)) {
                    filteredList1.add(item);
                }
            }
            //Log.e(TAG, "filter: size" + filteredList1.size());
            // Log.e(TAG, "filter: List" + filteredList1.toString());
            runningOrderDetailsAdapter.updateData(mContext, filteredList1);
        }
    }
}