package com.soulsoft.globalrestobar.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.soulsoft.globalrestobar.BaseFragment;
import com.soulsoft.globalrestobar.R;
import com.soulsoft.globalrestobar.activity.DashBoardActivity;
import com.soulsoft.globalrestobar.adapter.RunningTableAdapter;
import com.soulsoft.globalrestobar.model.runningtable.RunningOrderBO;
import com.soulsoft.globalrestobar.model.runningtable.RunningOrderResponse;
import com.soulsoft.globalrestobar.utility.AllKeys;
import com.soulsoft.globalrestobar.utility.CommonMethods;
import com.soulsoft.globalrestobar.utility.ConfigUrl;
import java.util.ArrayList;
import butterknife.BindView;

public class RunningTableFragment extends BaseFragment {

    private static final String TAG = "ReceptionFragment";
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv_running_order)
    RecyclerView rvRunningOrder;
    private final ArrayList<RunningOrderBO> runningOrderBOArrayList=new ArrayList<>();
    private RunningTableAdapter runningTableAdapter;
    private String type="";

    public RunningTableFragment( ) {
        // Required empty public constructor
    }

    @Override
    protected int getActivityLayout( ) {
        return R.layout.fragment_running_table;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //basic intialisation...
        initViews();

        if (getArguments().getString("type") != null) {
            type=getArguments().getString("type");
        }

        //get ongoing table details..
        if(CommonMethods.isNetworkAvailable(mContext)){
            getRunningTables();
        }else{
            Toast.makeText(mContext, AllKeys.NO_INTERNET_AVAILABLE, Toast.LENGTH_LONG).show();
        }
    }

    private void initViews(){
        DashBoardActivity.tvTitle.setText(getResources().getString(R.string.running_order));

        GridLayoutManager gridLayoutManager=new GridLayoutManager(mContext,2);
        rvRunningOrder.setLayoutManager(gridLayoutManager);

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

    private void getRunningTables(){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.setTitle("Fetching data");
        progressDialog.show();
        progressDialog.setCancelable(false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, CommonMethods.getPrefrence(mContext, AllKeys.BASE_URL).concat(ConfigUrl.BOOKEDTABLES_LIST), response -> {
            Log.e(TAG, "onResponse: " + response);
            Gson gson = new Gson();

            RunningOrderResponse runningOrderResponse = gson.fromJson(response, RunningOrderResponse.class);
            progressDialog.dismiss();
            if(runningOrderResponse.getTable().size()>0){
                runningOrderBOArrayList.addAll(runningOrderResponse.getTable());
                //llExistingOrder.setVisibility(View.VISIBLE);
                runningTableAdapter=new RunningTableAdapter(mContext,runningOrderBOArrayList,type);
                rvRunningOrder.setAdapter(runningTableAdapter);
            }else{
                //llExistingOrder.setVisibility(View.GONE);
            }
        }, error -> {
            progressDialog.dismiss();
            Log.e(TAG, "onErrorResponse: " + error);
        });
        RequestQueue mQueue = Volley.newRequestQueue(getContext());
        mQueue.add(stringRequest);
    }

    private void filterTable(String text) {
        if(runningOrderBOArrayList !=null && runningOrderBOArrayList.size() > 0) {
            ArrayList<RunningOrderBO> filteredList1 = new ArrayList<>();
            for (RunningOrderBO item : runningOrderBOArrayList) {
                if (item.getTABLE().toLowerCase().startsWith(text)
                        || item.getAMOUNT().toLowerCase().startsWith(text)
                        || item.getEMPID().toLowerCase().startsWith(text)) {
                    filteredList1.add(item);
                }
            }
            //Log.e(TAG, "filter: size" + filteredList1.size());
            // Log.e(TAG, "filter: List" + filteredList1.toString());
            runningTableAdapter.updateData(mContext, filteredList1);
        }
    }
}