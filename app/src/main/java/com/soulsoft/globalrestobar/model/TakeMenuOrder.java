package com.soulsoft.globalrestobar.model;

public class TakeMenuOrder {

    public String IID;
    public String ITEMNAME;
    public String ITEMTYPE;
    public String ITEMNARRATION;
    public String UID;
    public String RATE;
    public String QTY;
    public String TOTAL;
    public String UNITNAME;

    public TakeMenuOrder(){

    }

    public TakeMenuOrder(String IID, String ITEMNAME, String ITEMTYPE, String ITEMNARRATION, String UID, String RATE, String QTY, String TOTAL, String UNITNAME) {
        this.IID = IID;
        this.ITEMNAME = ITEMNAME;
        this.ITEMTYPE = ITEMTYPE;
        this.ITEMNARRATION = ITEMNARRATION;
        this.UID = UID;
        this.RATE = RATE;
        this.QTY = QTY;
        this.TOTAL = TOTAL;
        this.UNITNAME = UNITNAME;
    }

    public String getIID( ) {
        return IID;
    }

    public void setIID(String IID) {
        this.IID = IID;
    }

    public String getITEMNAME( ) {
        return ITEMNAME;
    }

    public void setITEMNAME(String ITEMNAME) {
        this.ITEMNAME = ITEMNAME;
    }

    public String getITEMTYPE( ) {
        return ITEMTYPE;
    }

    public void setITEMTYPE(String ITEMTYPE) {
        this.ITEMTYPE = ITEMTYPE;
    }

    public String getITEMNARRATION( ) {
        return ITEMNARRATION;
    }

    public void setITEMNARRATION(String ITEMNARRATION) {
        this.ITEMNARRATION = ITEMNARRATION;
    }

    public String getUID( ) {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getRATE( ) {
        return RATE;
    }

    public void setRATE(String RATE) {
        this.RATE = RATE;
    }

    public String getQTY( ) {
        return QTY;
    }

    public void setQTY(String QTY) {
        this.QTY = QTY;
    }

    public String getTOTAL( ) {
        return TOTAL;
    }

    public void setTOTAL(String TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String getUNITNAME( ) {
        return UNITNAME;
    }

    public void setUNITNAME(String UNITNAME) {
        this.UNITNAME = UNITNAME;
    }
}
