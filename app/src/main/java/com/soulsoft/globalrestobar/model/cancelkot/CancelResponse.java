package com.soulsoft.globalrestobar.model.cancelkot;


import java.util.ArrayList;

public class CancelResponse {

    public ArrayList<CancelKotBO> Table;

    public ArrayList<CancelKotBO> getTable() {
        if(this.Table==null){
            this.Table=new ArrayList<CancelKotBO>();
        }
        return Table;
    }

    public void setTable(ArrayList<CancelKotBO> table) {
        Table = table;
    }
}
