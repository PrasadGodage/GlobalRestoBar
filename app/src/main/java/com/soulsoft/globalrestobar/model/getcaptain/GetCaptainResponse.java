package com.soulsoft.globalrestobar.model.getcaptain;

import java.util.ArrayList;

public class GetCaptainResponse {

    public ArrayList<GetAllCaptainBO> Table;

    public ArrayList<GetAllCaptainBO> getTable() {
        if(this.Table==null){
            this.Table=new ArrayList<>();
        }
        return Table;
    }

    public void setTable(ArrayList<GetAllCaptainBO> table) {
        Table = table;
    }
}
