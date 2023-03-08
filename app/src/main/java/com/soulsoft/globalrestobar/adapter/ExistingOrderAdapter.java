package com.soulsoft.globalrestobar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
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

import java.util.ArrayList;

public class ExistingOrderAdapter extends RecyclerView.Adapter<ExistingOrderAdapter.TakeOrderViewHolder> {

    Context context;
    ArrayList<ExistingKotBO> existingKotBOArrayList;
    float total,sum=0.0f;
    private static final String TAG="TakeOrderAdapter";
    private TakeOrderListener takeOrderListener;
    //private final int[] backgroundColors = {R.color.yellow_50,R.color.yellow_100};

    public ExistingOrderAdapter(Context context, ArrayList<ExistingKotBO> ExistingKotBOArrayList) {
        this.context = context;
        this.existingKotBOArrayList = ExistingKotBOArrayList;
    }

    @NonNull
    @Override
    public TakeOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_takeorder_row, parent, false);
        return new TakeOrderViewHolder(view,takeOrderListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TakeOrderViewHolder holder, final int position) {
        holder.ivDeleteMenu.setVisibility(View.GONE);
        holder.tvCode.setText(existingKotBOArrayList.get(position).getIID());
        holder.tvMenu.setText(existingKotBOArrayList.get(position).getQTY()+" * "
                +existingKotBOArrayList.get(position).getMENUNAME());
        //holder.tvQuantity.setText("QTY: "+takeMenuOrderArrayList.get(position).getQTY());
        //holder.tvServesIn.setText(takeMenuOrderArrayList.get(position).getUnitname());
        holder.tvAmount.setText("₹."+existingKotBOArrayList.get(position).getAMOUNT());
    /*    int bgColor = ContextCompat.getColor(context, backgroundColors[position % 2]);
        holder.cardView.setCardBackgroundColor(bgColor);*/
        holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.yellow_50));
    }

    @Override
    public int getItemCount() {
        return existingKotBOArrayList.size();
    }

    public class TakeOrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView cardView;
        private TextView tvCode, tvMenu, tvQuantity, tvServesIn, tvAmount;
        private ImageView ivDeleteMenu;
        private TakeOrderListener takeOrderListener;

        public TakeOrderViewHolder(@NonNull View itemView, TakeOrderListener takeOrderListener) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardView);
            tvCode = itemView.findViewById(R.id.tv_code);
            tvMenu = itemView.findViewById(R.id.tv_menu);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
           // tvServesIn = itemView.findViewById(R.id.tv_servesin);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            ivDeleteMenu = itemView.findViewById(R.id.ivDelete);
            ivDeleteMenu.setOnClickListener(this);
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
