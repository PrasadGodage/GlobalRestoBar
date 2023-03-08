package com.soulsoft.globalrestobar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.soulsoft.globalrestobar.R;
import com.soulsoft.globalrestobar.model.itemunit.ItemUnitBO;

import java.util.ArrayList;

public class ServesInSpinnerAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<ItemUnitBO> itemUnitBOArrayList =null;

    public ServesInSpinnerAdapter(Context context, ArrayList<ItemUnitBO> itemUnitBOArrayList) {
        this.context = context;
        this.itemUnitBOArrayList = itemUnitBOArrayList;
    }

    @Override
    public int getCount() {
            return itemUnitBOArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemUnitBOArrayList.get(position).getUNITID();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ServesInViewHolder servesInViewHolder;
        LayoutInflater inflater = LayoutInflater.from(context);
        if(convertView==null){
            convertView = inflater.inflate(R.layout.custom_get_owner_row, parent, false);
            servesInViewHolder = new ServesInViewHolder(convertView);
            convertView.setTag(servesInViewHolder);
        } else {
            servesInViewHolder = (ServesInViewHolder) convertView.getTag();
        }
        servesInViewHolder.text1.setText(itemUnitBOArrayList.get(position).getUNITNAME());
        return convertView;
    }

    public static class ServesInViewHolder {

        TextView text1;

        public ServesInViewHolder(View view) {
            //imageView = (ImageView) view.findViewById(R.id.image);
            text1 = view.findViewById(R.id.autoText);
        }
    }
}
