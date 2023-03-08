package com.soulsoft.globalrestobar.model.menucard;

import java.util.ArrayList;

public class MenuCardResponse {

    private ArrayList<MenuDataBO> Table;

    public ArrayList<MenuDataBO> getTable( ) {
        if(this.Table==null){
            this.Table=new ArrayList<>();
        }
        return Table;
    }

    public void setTable(ArrayList<MenuDataBO> table) {
        Table = table;
    }
}
