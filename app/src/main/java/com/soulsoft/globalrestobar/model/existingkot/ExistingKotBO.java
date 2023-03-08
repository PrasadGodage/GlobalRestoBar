package com.soulsoft.globalrestobar.model.existingkot;

import com.google.gson.annotations.SerializedName;

public class ExistingKotBO {

    @SerializedName("MENU NAME")
    private String MENUNAME;
    private String QTY;
    @SerializedName("UNIT NAME")
    private String UNITNAME;
    private String RATE;
    private String AMOUNT;
    private String IID;
    private String UNITID;
    private String ITEMTYPE;

    public String getMENUNAME( ) {
        return MENUNAME;
    }

    public void setMENUNAME(String MENUNAME) {
        this.MENUNAME = MENUNAME;
    }

    public String getQTY( ) {
        return QTY;
    }

    public void setQTY(String QTY) {
        this.QTY = QTY;
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

    public String getAMOUNT( ) {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getIID( ) {
        return IID;
    }

    public void setIID(String IID) {
        this.IID = IID;
    }

    public String getUNITID( ) {
        return UNITID;
    }

    public void setUNITID(String UNITID) {
        this.UNITID = UNITID;
    }

    public String getITEMTYPE( ) {
        return ITEMTYPE;
    }

    public void setITEMTYPE(String ITEMTYPE) {
        this.ITEMTYPE = ITEMTYPE;
    }
}
