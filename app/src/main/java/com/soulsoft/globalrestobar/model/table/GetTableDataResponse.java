package com.soulsoft.globalrestobar.model.table;

import java.util.ArrayList;

public class GetTableDataResponse {

    private ArrayList<GetTableDataBO> Table;

    public ArrayList<GetTableDataBO> getTable( ) {
        if (this.Table==null){
            this.Table=new ArrayList<GetTableDataBO>();
        }
        return Table;
    }

    public void setTable(ArrayList<GetTableDataBO> table) {
        Table = table;
    }
}
