package com.soulsoft.globalrestobar.model.existingkot;

import java.util.ArrayList;

public class ExistingDetailsResponse {
    public ArrayList<ExistingKotBO> Table;

    public ArrayList<ExistingKotBO> getTable() {
        if(this.Table==null){
            this.Table=new ArrayList<ExistingKotBO>();
        }
        return Table;
    }

    public void setTable(ArrayList<ExistingKotBO> table) {
        Table = table;
    }
}
