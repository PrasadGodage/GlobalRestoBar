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
import com.soulsoft.globalrestobar.model.menucard.MenuDataBO;

import java.util.ArrayList;

public class GetMenuAdapter extends ArrayAdapter<MenuDataBO> {

    Context context;
    private final ArrayList<MenuDataBO> menuDataBOArrayList;
    private final ArrayList<MenuDataBO> suggestions;
    private final ArrayList<MenuDataBO> tempItems;
    private LayoutInflater inflater = null;

    public GetMenuAdapter(@NonNull Context context, @NonNull ArrayList<MenuDataBO> objects) {
        super(context, 0,objects);
        this.context=context;
        this.menuDataBOArrayList =objects;
        tempItems = new ArrayList<>(menuDataBOArrayList);
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
        return menuDataBOArrayList.size();
    }

    @Nullable
    @Override
    public MenuDataBO getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(@Nullable MenuDataBO item) {
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

        MenuDataBO menuDataBO = getItem(position);

        //patientPosition=suggestions.size();

       /* try{
                ((AllInOneActivity) context).showAnimalInEditext(suggestions.size(),tempItems.size());
        }catch (NullPointerException e){
            e.printStackTrace();
        }
*/
        if (menuDataBO != null) {
            textViewName.setText(menuDataBO.getITEMNAME());
        }
        return convertView;
    }

    private final Filter fruitFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            MenuDataBO menuDataBO = (MenuDataBO) resultValue;
            return menuDataBO.getITEMNAME();
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            if (charSequence != null) {
                suggestions.clear();
                for (MenuDataBO menuDataBO: tempItems) {
                    if (menuDataBO.getITEMNAME().toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                        suggestions.add(menuDataBO);
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
                ArrayList<MenuDataBO> tempValues = (ArrayList<MenuDataBO>) filterResults.values;
                if (filterResults != null && filterResults.count > 0) {
                    clear();
                    for (MenuDataBO fruitObj : tempValues) {
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