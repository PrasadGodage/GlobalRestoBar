package com.soulsoft.globalrestobar.model.table;

public class GetTableDataBO {

    private String TID;
    private String TABLENO;
    private String SID;
    private String ORDERTYPE;
    private String SECTIONNAME;
    private String FOODCGST;
    private String FOODSGST;
    private String FOODSERVICETAX;
    private String FOODSECTIONCHARGE;
    private String FOODOTHERCHARGE;
    private String BARVAT;
    private String BARSECTIONCHARGE;
    private String BARSERVICETAX;
    private String BAROTHERCHARGE;
    private String ISALLOWPARCELCHARGE;
    private String BILLPRINTCOUNT;
    private String PARCELCHARGE;

    public String getTID( ) {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public String getTABLENO( ) {
        return TABLENO;
    }

    public void setTABLENO(String TABLENO) {
        this.TABLENO = TABLENO;
    }

    public String getSID( ) {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getORDERTYPE( ) {
        return ORDERTYPE;
    }

    public void setORDERTYPE(String ORDERTYPE) {
        this.ORDERTYPE = ORDERTYPE;
    }

    public String getSECTIONNAME( ) {
        return SECTIONNAME;
    }

    public void setSECTIONNAME(String SECTIONNAME) {
        this.SECTIONNAME = SECTIONNAME;
    }

    public String getFOODCGST( ) {
        return FOODCGST;
    }

    public void setFOODCGST(String FOODCGST) {
        this.FOODCGST = FOODCGST;
    }

    public String getFOODSGST( ) {
        return FOODSGST;
    }

    public void setFOODSGST(String FOODSGST) {
        this.FOODSGST = FOODSGST;
    }

    public String getFOODSERVICETAX( ) {
        return FOODSERVICETAX;
    }

    public void setFOODSERVICETAX(String FOODSERVICETAX) {
        this.FOODSERVICETAX = FOODSERVICETAX;
    }

    public String getFOODSECTIONCHARGE( ) {
        return FOODSECTIONCHARGE;
    }

    public void setFOODSECTIONCHARGE(String FOODSECTIONCHARGE) {
        this.FOODSECTIONCHARGE = FOODSECTIONCHARGE;
    }

    public String getFOODOTHERCHARGE( ) {
        return FOODOTHERCHARGE;
    }

    public void setFOODOTHERCHARGE(String FOODOTHERCHARGE) {
        this.FOODOTHERCHARGE = FOODOTHERCHARGE;
    }

    public String getBARVAT( ) {
        return BARVAT;
    }

    public void setBARVAT(String BARVAT) {
        this.BARVAT = BARVAT;
    }

    public String getBARSECTIONCHARGE( ) {
        return BARSECTIONCHARGE;
    }

    public void setBARSECTIONCHARGE(String BARSECTIONCHARGE) {
        this.BARSECTIONCHARGE = BARSECTIONCHARGE;
    }

    public String getBARSERVICETAX( ) {
        return BARSERVICETAX;
    }

    public void setBARSERVICETAX(String BARSERVICETAX) {
        this.BARSERVICETAX = BARSERVICETAX;
    }

    public String getBAROTHERCHARGE( ) {
        return BAROTHERCHARGE;
    }

    public void setBAROTHERCHARGE(String BAROTHERCHARGE) {
        this.BAROTHERCHARGE = BAROTHERCHARGE;
    }

    public String getISALLOWPARCELCHARGE( ) {
        return ISALLOWPARCELCHARGE;
    }

    public void setISALLOWPARCELCHARGE(String ISALLOWPARCELCHARGE) {
        this.ISALLOWPARCELCHARGE = ISALLOWPARCELCHARGE;
    }

    public String getBILLPRINTCOUNT( ) {
        return BILLPRINTCOUNT;
    }

    public void setBILLPRINTCOUNT(String BILLPRINTCOUNT) {
        this.BILLPRINTCOUNT = BILLPRINTCOUNT;
    }

    public String getPARCELCHARGE( ) {
        return PARCELCHARGE;
    }

    public void setPARCELCHARGE(String PARCELCHARGE) {
        this.PARCELCHARGE = PARCELCHARGE;
    }

    @Override
    public String toString( ) {
        return "GetTableDataBO{" +
                "TID='" + TID + '\'' +
                ", TABLENO='" + TABLENO + '\'' +
                ", SID='" + SID + '\'' +
                ", ORDERTYPE='" + ORDERTYPE + '\'' +
                ", SECTIONNAME='" + SECTIONNAME + '\'' +
                ", FOODCGST='" + FOODCGST + '\'' +
                ", FOODSGST='" + FOODSGST + '\'' +
                ", FOODSERVICETAX='" + FOODSERVICETAX + '\'' +
                ", FOODSECTIONCHARGE='" + FOODSECTIONCHARGE + '\'' +
                ", FOODOTHERCHARGE='" + FOODOTHERCHARGE + '\'' +
                ", BARVAT='" + BARVAT + '\'' +
                ", BARSECTIONCHARGE='" + BARSECTIONCHARGE + '\'' +
                ", BARSERVICETAX='" + BARSERVICETAX + '\'' +
                ", BAROTHERCHARGE='" + BAROTHERCHARGE + '\'' +
                ", ISALLOWPARCELCHARGE='" + ISALLOWPARCELCHARGE + '\'' +
                ", BILLPRINTCOUNT='" + BILLPRINTCOUNT + '\'' +
                ", PARCELCHARGE='" + PARCELCHARGE + '\'' +
                '}';
    }
}
