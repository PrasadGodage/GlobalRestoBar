package com.soulsoft.globalrestobar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.soulsoft.globalrestobar.R;
import com.soulsoft.globalrestobar.activity.CancelOrderActivity;
import com.soulsoft.globalrestobar.model.cancelkot.CancelKotBO;
import com.soulsoft.globalrestobar.model.existingkot.ExistingKotBO;

import java.util.ArrayList;

public class CancelOrderDetailsAdapter extends RecyclerView.Adapter<CancelOrderDetailsAdapter.TakeOrderViewHolder> {

    Context context;
    ArrayList<CancelKotBO> existingKotBOArrayList;
    private TakeOrderListener takeOrderListener;
    private CancelOrderActivity cancelOrderActivity;
    //private final int[] backgroundColors = {R.color.yellow_50,R.color.yellow_100};

    /*public TakeOrderAdapter(Context baseContext, ArrayList<ExistingKotBO> takeOrderArrayList) {
        this.context = baseContext;
        this.ExistingKotBOArrayList = takeOrderArrayList;
    }*/

    public CancelOrderDetailsAdapter(Context context, ArrayList<CancelKotBO> ExistingKotBOArrayList,CancelOrderActivity cancelOrderActivity) {
        this.context = context;
        this.existingKotBOArrayList = ExistingKotBOArrayList;
        this.cancelOrderActivity=cancelOrderActivity;
    }

    @NonNull
    @Override
    public TakeOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cancel_running_order_details, parent, false);
        return new TakeOrderViewHolder(view,takeOrderListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TakeOrderViewHolder holder, final int position) {
        //int bgColor = ContextCompat.getColor(context, backgroundColors[position % 2]);
        //holder.cardView.setCardBackgroundColor(bgColor);
        holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.yellow_50));
        //  int bgColor1 = ContextCompat.getColor(context, backgroundColors[position % 2]);
        //holder.cardView2.setCardBackgroundColor(bgColor1);
        holder.tvCode.setText(existingKotBOArrayList.get(position).getIID());
        holder.tvMenu.setText(existingKotBOArrayList.get(position).getQTY()+" * "+existingKotBOArrayList.get(position).getITEM());
        //holder.tvQuantity.setText("QTY: "+existingKotBOArrayList.get(position).getQTY());
        //holder.tvServesIn.setText(ExistingKotBOArrayList.get(position).getUnitname());
        holder.tvAmount.setText("₹."+existingKotBOArrayList.get(position).getRATE());
        holder.etReturnQty.setText(existingKotBOArrayList.get(position).getQTY());

        holder.etReturnQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    if(Double.parseDouble(s.toString())>Double.parseDouble(existingKotBOArrayList.get(position).getQTY())){
                        holder.etReturnQty.setText(existingKotBOArrayList.get(position).getQTY());
                    }
                    cancelOrderActivity.cancelKotList.remove(position);
                    cancelOrderActivity.cancelKotList.add(new CancelKotBO(existingKotBOArrayList.get(position).getIID(),
                            existingKotBOArrayList.get(position).getITEM(),
                            existingKotBOArrayList.get(position).getITEMTYPE(),
                            existingKotBOArrayList.get(position).getQTY(),
                            existingKotBOArrayList.get(position).getUNITID(),
                            existingKotBOArrayList.get(position).getUNIT(),
                            existingKotBOArrayList.get(position).getRATE(),
                            holder.etReason.getText().toString(),
                            holder.etReturnQty.getText().toString()));
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        holder.etReason.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    if(cancelOrderActivity.cancelKotList.size()==0){
                        cancelOrderActivity.cancelKotList.add(new CancelKotBO(existingKotBOArrayList.get(position).getIID(),
                                existingKotBOArrayList.get(position).getITEM(),
                                existingKotBOArrayList.get(position).getITEMTYPE(),
                                existingKotBOArrayList.get(position).getQTY(),
                                existingKotBOArrayList.get(position).getUNITID(),
                                existingKotBOArrayList.get(position).getUNIT(),
                                existingKotBOArrayList.get(position).getRATE(),
                                holder.etReason.getText().toString(),
                                holder.etReturnQty.getText().toString()));
                    }else{
                        cancelOrderActivity.cancelKotList.remove(position);

                        cancelOrderActivity.cancelKotList.add(new CancelKotBO(existingKotBOArrayList.get(position).getIID(),
                                existingKotBOArrayList.get(position).getITEM(),
                                existingKotBOArrayList.get(position).getITEMTYPE(),
                                existingKotBOArrayList.get(position).getQTY(),
                                existingKotBOArrayList.get(position).getUNITID(),
                                existingKotBOArrayList.get(position).getUNIT(),
                                existingKotBOArrayList.get(position).getRATE(),
                                holder.etReason.getText().toString(),
                                holder.etReturnQty.getText().toString()));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            //set your object's last status
            if(isChecked){
                if(cancelOrderActivity.cancelKotList.size()==0){
                    cancelOrderActivity.cancelKotList.add(new CancelKotBO(existingKotBOArrayList.get(position).getIID(),
                            existingKotBOArrayList.get(position).getITEM(),
                            existingKotBOArrayList.get(position).getITEMTYPE(),
                            existingKotBOArrayList.get(position).getQTY(),
                            existingKotBOArrayList.get(position).getUNITID(),
                            existingKotBOArrayList.get(position).getUNIT(),
                            existingKotBOArrayList.get(position).getRATE(),
                            holder.etReason.getText().toString(),
                            holder.etReturnQty.getText().toString()));
                }else{
                    cancelOrderActivity.cancelKotList.remove(position);

                    cancelOrderActivity.cancelKotList.add(new CancelKotBO(existingKotBOArrayList.get(position).getIID(),
                            existingKotBOArrayList.get(position).getITEM(),
                            existingKotBOArrayList.get(position).getITEMTYPE(),
                            existingKotBOArrayList.get(position).getQTY(),
                            existingKotBOArrayList.get(position).getUNITID(),
                            existingKotBOArrayList.get(position).getUNIT(),
                            existingKotBOArrayList.get(position).getRATE(),
                            holder.etReason.getText().toString(),
                            holder.etReturnQty.getText().toString()));
                }
            }else{
                cancelOrderActivity.cancelKotList.remove(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return existingKotBOArrayList.size();
    }

    public class TakeOrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView cardView;
        private TextView tvCode, tvMenu, tvQuantity, tvServesIn, tvAmount;
        private TakeOrderListener takeOrderListener;
        private EditText etReturnQty,etReason;
        private CheckBox checkBox;

        public TakeOrderViewHolder(@NonNull View itemView, TakeOrderListener takeOrderListener) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardView);
            tvCode = itemView.findViewById(R.id.tv_code);
            tvMenu = itemView.findViewById(R.id.tv_menu);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
            //tvServesIn = itemView.findViewById(R.id.tv_servesin);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            etReason=itemView.findViewById(R.id.et_reason);
            etReturnQty=itemView.findViewById(R.id.et_return_qty);
            checkBox=itemView.findViewById(R.id.cb_checbox);
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

    public void updateData(Context context,ArrayList<CancelKotBO> data) {
        this.context=context;
        this.existingKotBOArrayList = data;
        notifyDataSetChanged();
    }
}
