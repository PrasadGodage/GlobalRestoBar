package com.soulsoft.globalrestobar.model.settings;

import java.util.ArrayList;

public class SettingsResponse {

    public ArrayList<SettingsBO> Table;

    public ArrayList<SettingsBO> getTable() {
        if(this.Table==null){
            this.Table=new ArrayList<SettingsBO>();
        }
        return Table;
    }

    public void setTable(ArrayList<SettingsBO> table) {
        Table = table;
    }
}
