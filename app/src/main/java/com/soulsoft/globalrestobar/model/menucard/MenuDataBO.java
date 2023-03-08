package com.soulsoft.globalrestobar.model.menucard;

public class MenuDataBO {

    private String ITEMSHORTCODE;
    private String ITEMCODE;
    private String ITEMNAME;
    private String ITEMTYPE;
    private String CID;
    private String ITEMUID;
    private String RATEONKOT;

    public String getITEMSHORTCODE( ) {
        return ITEMSHORTCODE;
    }

    public void setITEMSHORTCODE(String ITEMSHORTCODE) {
        this.ITEMSHORTCODE = ITEMSHORTCODE;
    }

    public String getITEMCODE( ) {
        return ITEMCODE;
    }

    public void setITEMCODE(String ITEMCODE) {
        this.ITEMCODE = ITEMCODE;
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

    public String getCID( ) {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getITEMUID( ) {
        return ITEMUID;
    }

    public void setITEMUID(String ITEMUID) {
        this.ITEMUID = ITEMUID;
    }

    public String getRATEONKOT( ) {
        return RATEONKOT;
    }

    public void setRATEONKOT(String RATEONKOT) {
        this.RATEONKOT = RATEONKOT;
    }

    @Override
    public String toString( ) {
        return "MenuDataBO{" +
                "ITEMSHORTCODE='" + ITEMSHORTCODE + '\'' +
                ", ITEMCODE='" + ITEMCODE + '\'' +
                ", ITEMNAME='" + ITEMNAME + '\'' +
                ", ITEMTYPE='" + ITEMTYPE + '\'' +
                ", CID='" + CID + '\'' +
                ", ITEMUID='" + ITEMUID + '\'' +
                ", RATEONKOT='" + RATEONKOT + '\'' +
                '}';
    }
}