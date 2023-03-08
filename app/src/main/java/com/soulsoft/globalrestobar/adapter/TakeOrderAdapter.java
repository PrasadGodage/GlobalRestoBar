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
import com.soulsoft.globalrestobar.model.TakeMenuOrder;

import java.util.ArrayList;

public class TakeOrderAdapter extends RecyclerView.Adapter<TakeOrderAdapter.TakeOrderViewHolder> {

    Context context;
    ArrayList<TakeMenuOrder> takeMenuOrderArrayList;
    float total,sum=0.0f;
    private static final String TAG="TakeOrderAdapter";
    private TakeOrderListener takeOrderListener;
   // private final int[] backgroundColors = {R.color.yellow_50,R.color.yellow_100};

    /*public TakeOrderAdapter(Context baseContext, ArrayList<TakeMenuOrder> takeOrderArrayList) {
        this.context = baseContext;
        this.takeMenuOrderArrayList = takeOrderArrayList;
    }*/

    public TakeOrderAdapter(Context context, ArrayList<TakeMenuOrder> takeMenuOrderArrayList, TakeOrderListener takeOrderListener) {
        this.context = context;
        this.takeMenuOrderArrayList = takeMenuOrderArrayList;
        this.takeOrderListener = takeOrderListener;
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
        holder.tvCode.setText(takeMenuOrderArrayList.get(position).getIID());
        holder.tvMenu.setText(takeMenuOrderArrayList.get(position).getQTY()+" * "
                +takeMenuOrderArrayList.get(position).getITEMNAME());
        //holder.tvQuantity.setText("QTY: "+takeMenuOrderArrayList.get(position).getQTY());
        //holder.tvServesIn.setText(takeMenuOrderArrayList.get(position).getUnitname());
        holder.tvAmount.setText("₹."+takeMenuOrderArrayList.get(position).getTOTAL());

      /*  int bgColor = ContextCompat.getColor(context, backgroundColors[position % 2]);
        holder.cardView.setCardBackgroundColor(bgColor);*/
        holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.yellow_100));

        for (int i = 0; i < takeMenuOrderArrayList.size(); i++) {
            Log.e(TAG, "onClick: " + takeMenuOrderArrayList.get(i).getTOTAL());
            total = Float.parseFloat((takeMenuOrderArrayList.get(i).getTOTAL()));
        }
        sum += total;
       // CommonMethods.setPreference(context,AllKeys.SUM1, String.valueOf(sum));

       /* holder.ivDeleteMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonMethods.setPreference(context, AllKeys.TOTAL, takeMenuOrderArrayList.get(position).getTotal());
                takeMenuOrderArrayList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, takeMenuOrderArrayList.size());

                for (int i = 0; i < takeMenuOrderArrayList.size(); i++) {
                    Log.e(TAG, "onClick: " + takeMenuOrderArrayList.get(i).getTotal());
                    total = Float.parseFloat((takeMenuOrderArrayList.get(i).getTotal()));
                }
                Log.e(TAG, "onClick: " + total);
                sum+=total;
                //CommonMethods.setPreference(context,AllKeys.SUM1,String.valueOf(sum));
                Toast.makeText(context, "When delete sum"+total, Toast.LENGTH_SHORT).show();

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return takeMenuOrderArrayList.size();
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
}
