package com.soulsoft.globalrestobar.model;

import com.soulsoft.globalrestobar.model.getcaptain.GetAllCaptainBO;

import java.util.ArrayList;

public class LoginResponse {

    private ArrayList<GetAllCaptainBO> Table;

    public ArrayList<GetAllCaptainBO> getTable( ) {
        if(this.Table==null){
            this.Table=new ArrayList<GetAllCaptainBO>();
        }
        return Table;
    }

    public void setTable(ArrayList<GetAllCaptainBO> table) {
        Table = table;
    }
}
