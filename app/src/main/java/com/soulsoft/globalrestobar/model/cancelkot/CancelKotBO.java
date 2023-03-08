package com.soulsoft.globalrestobar.model.cancelkot;

import java.util.Objects;

public class CancelKotBO {

    private String IID;
    private String ITEM;
    private String ITEMTYPE;
    private String QTY;
    private String UNITID;
    private String UNIT;
    private String RATE;
    private String CANCELREASON;
    private String RETURNQTY;

    public CancelKotBO(String IID, String ITEM, String ITEMTYPE, String QTY, String UNITID, String UNIT, String RATE, String CANCELREASON, String RETURNQTY) {
        this.IID = IID;
        this.ITEM = ITEM;
        this.ITEMTYPE = ITEMTYPE;
        this.QTY = QTY;
        this.UNITID = UNITID;
        this.UNIT = UNIT;
        this.RATE = RATE;
        this.CANCELREASON = CANCELREASON;
        this.RETURNQTY = RETURNQTY;
    }

    public String getIID( ) {
        return IID;
    }

    public void setIID(String IID) {
        this.IID = IID;
    }

    public String getITEM( ) {
        return ITEM;
    }

    public void setITEM(String ITEM) {
        this.ITEM = ITEM;
    }

    public String getITEMTYPE( ) {
        return ITEMTYPE;
    }

    public void setITEMTYPE(String ITEMTYPE) {
        this.ITEMTYPE = ITEMTYPE;
    }

    public String getQTY( ) {
        return QTY;
    }

    public void setQTY(String QTY) {
        this.QTY = QTY;
    }

    public String getUNITID( ) {
        return UNITID;
    }

    public void setUNITID(String UNITID) {
        this.UNITID = UNITID;
    }

    public String getUNIT( ) {
        return UNIT;
    }

    public void setUNIT(String UNIT) {
        this.UNIT = UNIT;
    }

    public String getRATE( ) {
        return RATE;
    }

    public void setRATE(String RATE) {
        this.RATE = RATE;
    }

    public String getCANCELREASON( ) {
        return CANCELREASON;
    }

    public void setCANCELREASON(String CANCELREASON) {
        this.CANCELREASON = CANCELREASON;
    }

    public String getRETURNQTY( ) {
        return RETURNQTY;
    }

    public void setRETURNQTY(String RETURNQTY) {
        this.RETURNQTY = RETURNQTY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CancelKotBO)) return false;
        CancelKotBO that = (CancelKotBO) o;
        return IID.equals(that.IID);
    }

    @Override
    public int hashCode( ) {
        return Objects.hash(IID);
    }

    @Override
    public String toString( ) {
        return "CancelKotBO{" +
                "IID='" + IID + '\'' +
                ", ITEM='" + ITEM + '\'' +
                ", ITEMTYPE='" + ITEMTYPE + '\'' +
                ", QTY='" + QTY + '\'' +
                ", UNITID='" + UNITID + '\'' +
                ", UNIT='" + UNIT + '\'' +
                ", RATE='" + RATE + '\'' +
                ", CANCELREASON='" + CANCELREASON + '\'' +
                ", RETURNQTY='" + RETURNQTY + '\'' +
                '}';
    }
}

