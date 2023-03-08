package com.soulsoft.globalrestobar.model.runningtable;

import com.soulsoft.globalrestobar.model.existingkot.ExistingKotBO;

import java.util.ArrayList;

public class RunningOrderResponse {
    public ArrayList<RunningOrderBO> Table;

    public ArrayList<RunningOrderBO> getTable() {
        if(this.Table==null){
            this.Table=new ArrayList<RunningOrderBO>();
        }
        return Table;
    }

    public void setTable(ArrayList<RunningOrderBO> table) {
        Table = table;
    }

}
