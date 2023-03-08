package com.soulsoft.globalrestobar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.soulsoft.globalrestobar.R;
import com.soulsoft.globalrestobar.model.existingkot.ExistingKotBO;
import com.soulsoft.globalrestobar.model.runningtable.RunningOrderBO;

import java.util.ArrayList;

public class RunningOrderDetailsAdapter extends RecyclerView.Adapter<RunningOrderDetailsAdapter.TakeOrderViewHolder> {

    Context context;
    ArrayList<ExistingKotBO> existingKotBOArrayList;
    private TakeOrderListener takeOrderListener;
    private final int[] backgroundColors = {R.color.yellow_50,R.color.yellow_100};

    /*public TakeOrderAdapter(Context baseContext, ArrayList<ExistingKotBO> takeOrderArrayList) {
        this.context = baseContext;
        this.ExistingKotBOArrayList = takeOrderArrayList;
    }*/

    public RunningOrderDetailsAdapter(Context context, ArrayList<ExistingKotBO> ExistingKotBOArrayList) {
        this.context = context;
        this.existingKotBOArrayList = ExistingKotBOArrayList;
    }

    @NonNull
    @Override
    public TakeOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_running_order_details, parent, false);
        return new TakeOrderViewHolder(view,takeOrderListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TakeOrderViewHolder holder, final int position) {

        int bgColor = ContextCompat.getColor(context, backgroundColors[position % 2]);
        holder.cardView.setCardBackgroundColor(bgColor);

        holder.tvCode.setText(existingKotBOArrayList.get(position).getIID());
        holder.tvMenu.setText(existingKotBOArrayList.get(position).getMENUNAME());
        holder.tvQuantity.setText("QTY: "+existingKotBOArrayList.get(position).getQTY());
        //holder.tvServesIn.setText(ExistingKotBOArrayList.get(position).getUnitname());
        holder.tvAmount.setText("AMT: "+existingKotBOArrayList.get(position).getAMOUNT());
    }

    @Override
    public int getItemCount() {
        return existingKotBOArrayList.size();
    }

    public class TakeOrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView cardView;
        private TextView tvCode, tvMenu, tvQuantity, tvServesIn, tvAmount;
        private TakeOrderListener takeOrderListener;

        public TakeOrderViewHolder(@NonNull View itemView, TakeOrderListener takeOrderListener) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardView);
            tvCode = itemView.findViewById(R.id.tv_code);
            tvMenu = itemView.findViewById(R.id.tv_menu);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
           // tvServesIn = itemView.findViewById(R.id.tv_servesin);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            this.takeOrderListener=takeOrderListener;
        }

        @Override
        public void onClick(View v) {
            takeOrderListener.onTakeOrderClick(getAdapterPosition());
        }
    }

    public interface TakeOrderListener{
        void onTakeOrderClick(int position);
    }

    public void updateData(Context context,ArrayList<ExistingKotBO> data) {
        this.context=context;
        this.existingKotBOArrayList = data;
        notifyDataSetChanged();
    }
}
