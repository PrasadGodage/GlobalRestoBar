package com.soulsoft.globalrestobar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.soulsoft.globalrestobar.R;
import com.soulsoft.globalrestobar.activity.CancelOrderActivity;
import com.soulsoft.globalrestobar.activity.RunningOrderDetailsActivity;
import com.soulsoft.globalrestobar.model.runningtable.RunningOrderBO;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RunningTableAdapter extends RecyclerView.Adapter<RunningTableAdapter.RunningTableViewHolder> {

    private Context context;
    private ArrayList<RunningOrderBO> runningOrderBOArrayList;
    //private final int[] backgroundColors = {R.color.yellow_50,R.color.yellow_100};
    private String type = "";

    public RunningTableAdapter(Context context, ArrayList<RunningOrderBO> runningOrderBOArrayList, String type) {
        this.context = context;
        this.runningOrderBOArrayList = runningOrderBOArrayList;
        this.type = type;
    }

    @NonNull
    @Override
    public RunningTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_running_table, parent, false);
        return new RunningTableViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RunningTableViewHolder holder, int position) {
        holder.tvTableNo.setText(runningOrderBOArrayList.get(position).getTABLE());
        holder.tvTableAmount.setText("₹."+runningOrderBOArrayList.get(position).getAMOUNT());
        // holder.tvEmpId.setText("Emp ID:- "+runningOrderBOArrayList.get(position).getEMPID());

        //int bgColor = ContextCompat.getColor(context, backgroundColors[position % 2]);
        holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.yellow_50));

        holder.cardView.setOnClickListener(v -> {
            if (type.equalsIgnoreCase("running")) {
                Intent intent = new Intent(context, RunningOrderDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("tableno", runningOrderBOArrayList.get(position).getTABLE());
                context.startActivity(intent);
            } else if (type.equalsIgnoreCase("cancel")) {
                Intent intent = new Intent(context, CancelOrderActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("table", runningOrderBOArrayList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount( ) {
        return runningOrderBOArrayList.size();
    }

    public class RunningTableViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_table_no)
        TextView tvTableNo;
        @BindView(R.id.cardView)
        CardView cardView;
        @BindView(R.id.tv_table_amount)
        TextView tvTableAmount;

        public RunningTableViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void updateData(Context context, ArrayList<RunningOrderBO> data) {
        this.context = context;
        this.runningOrderBOArrayList = data;
        notifyDataSetChanged();
    }

}
