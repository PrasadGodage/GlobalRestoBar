package com.soulsoft.globalrestobar.model.itemunit;

public class ItemUnitBO {

    private String UNITID;
    private String UNITNAME;
    private String RATE;

    public ItemUnitBO(){

    }

    public ItemUnitBO(String UNITID, String UNITNAME, String RATE) {
        this.UNITID = UNITID;
        this.UNITNAME = UNITNAME;
        this.RATE = RATE;
    }

    public String getUNITID( ) {
        return UNITID;
    }

    public void setUNITID(String UNITID) {
        this.UNITID = UNITID;
    }

    public String getUNITNAME( ) {
        return UNITNAME;
    }

    public void setUNITNAME(String UNITNAME) {
        this.UNITNAME = UNITNAME;
    }

    public String getRATE( ) {
        return RATE;
    }

    public void setRATE(String RATE) {
        this.RATE = RATE;
    }
}
