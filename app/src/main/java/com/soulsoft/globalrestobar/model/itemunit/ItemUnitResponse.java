package com.soulsoft.globalrestobar.model.itemunit;

import java.util.ArrayList;

public class ItemUnitResponse {

    public ArrayList<ItemUnitBO> Table;

    public ArrayList<ItemUnitBO> getTable() {
        if(this.Table==null){
            this.Table=new ArrayList<>();
        }
        return Table;
    }

    public void setTable(ArrayList<ItemUnitBO> table) {
        Table = table;
    }

}
