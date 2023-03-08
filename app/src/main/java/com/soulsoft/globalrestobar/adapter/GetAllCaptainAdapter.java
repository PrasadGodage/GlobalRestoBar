package com.soulsoft.globalrestobar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.soulsoft.globalrestobar.R;
import com.soulsoft.globalrestobar.model.getcaptain.GetAllCaptainBO;

import java.util.ArrayList;

public class GetAllCaptainAdapter extends BaseAdapter implements Filterable {

    Context context;
    ArrayList<GetAllCaptainBO> getAllCaptainBOS;

    private ArrayList<GetAllCaptainBO> suggestions = new ArrayList<>();
    private Filter filter = new CustomFilter();

    public GetAllCaptainAdapter(Context context, ArrayList<GetAllCaptainBO> getAllCaptainBOS) {
        this.context = context;
        this.getAllCaptainBOS = getAllCaptainBOS;
    }

    @Override
    public int getCount() {
        return getAllCaptainBOS.size();
    }

    @Override
    public Object getItem(int position) {
        return getAllCaptainBOS.get(position).getEMPNAME();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_getallcaptain_row, parent, false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text1.setText(getAllCaptainBOS.get(position).getEMPNAME());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    public static class ViewHolder{

        TextView text1;
        ViewHolder(View view){
            text1=view.findViewById(R.id.autoText);
        }
    }

    public void updateData(Context context, ArrayList<GetAllCaptainBO> data) {
        this.context=context;
        this.getAllCaptainBOS = data;
        notifyDataSetChanged();
    }

    /**
     * Our Custom Filter Class.
     */
    private class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            suggestions.clear();

            if (getAllCaptainBOS != null && constraint != null) { // Check if the Original List and Constraint aren't null.
                for (int i = 0; i < getAllCaptainBOS.size(); i++) {
                    if (getAllCaptainBOS.get(i).getEMPNAME().toLowerCase().contains(constraint)|| getAllCaptainBOS.get(i).getEMPNAME().toUpperCase().contains(constraint) ) { // Compare item in original list if it contains constraints.
                        suggestions.add(getAllCaptainBOS.get(i)); // If TRUE add item in Suggestions.
                    }
                }
            }
            FilterResults results = new FilterResults(); // Create new Filter Results and return this to publishResults;
            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}

