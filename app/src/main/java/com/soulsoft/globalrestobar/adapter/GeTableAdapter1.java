package com.soulsoft.globalrestobar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.soulsoft.globalrestobar.R;
import com.soulsoft.globalrestobar.model.table.GetTableDataBO;

import java.util.ArrayList;

public class GeTableAdapter1 extends ArrayAdapter<GetTableDataBO> {

    Context context;
    private final ArrayList<GetTableDataBO> getTableDataBOArrayList;
    private final ArrayList<GetTableDataBO> suggestions;
    private final ArrayList<GetTableDataBO> tempItems;
    private LayoutInflater inflater = null;

    public GeTableAdapter1(@NonNull Context context, @NonNull ArrayList<GetTableDataBO> objects) {
        super(context, 0,objects);
        this.context=context;
        this.getTableDataBOArrayList =objects;
        tempItems = new ArrayList<>(getTableDataBOArrayList);
        suggestions=new ArrayList<>();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return fruitFilter;
    }

    @Override
    public int getCount() {
        /*return (ownerDataArrayList != null && ownerDataArrayList.size() > 0)
                ? ownerDataArrayList.size() : 0;*/
        return getTableDataBOArrayList.size();
    }

    @Nullable
    @Override
    public GetTableDataBO getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(@Nullable GetTableDataBO item) {
        return super.getPosition(item);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.custom_get_owner_row, parent, false
            );
        }

        TextView textViewName = convertView.findViewById(R.id.autoText);

        GetTableDataBO getTableDataBO = getItem(position);

        //patientPosition=suggestions.size();

       /* try{
                ((AllInOneActivity) context).showAnimalInEditext(suggestions.size(),tempItems.size());
        }catch (NullPointerException e){
            e.printStackTrace();
        }
*/
        if (getTableDataBO != null) {
            textViewName.setText(getTableDataBO.getTABLENO());
        }
        return convertView;
    }

    private final Filter fruitFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            GetTableDataBO getTableDataBO = (GetTableDataBO) resultValue;
            return getTableDataBO.getTABLENO();
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            if (charSequence != null) {
                suggestions.clear();
                for (GetTableDataBO getTableDataBO: tempItems) {
                    if (getTableDataBO.getTABLENO().toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                        suggestions.add(getTableDataBO);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            try{
                ArrayList<GetTableDataBO> tempValues = (ArrayList<GetTableDataBO>) filterResults.values;
                if (filterResults != null && filterResults.count > 0) {
                    clear();
                    for (GetTableDataBO fruitObj : tempValues) {
                        add(fruitObj);
                    }
                    notifyDataSetChanged();
                } else {
                    clear();
                    notifyDataSetChanged();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };
}