package com.soulsoft.globalrestobar.model.getcaptain;

import android.os.Parcel;
import android.os.Parcelable;

public class GetAllCaptainBO implements Parcelable {

    private String EMPID;
    private String EMPNAME;
    private String DESIGNATIONID;
    private String SALARY;
    private String EMPADDRESS;
    private String CONTACT;
    private String EMPBDATE;
    private String EMPJOINDATE;
    private String REFERNCEBY;
    private String LOGINPASSWORD;
    private String ISALLOWKOT;
    private String ISALLOWCANCELKOT;
    private String ISALLOWSHIFTTABLE;
    private String ISALLOWBILL;
    private String ISALLOWDISCOUNTONBILL;
    private String ACTIVESTATUS;
    private String CREBY;
    private String CREON;
    private String UPDBY;
    private String UPDON;

    protected GetAllCaptainBO(Parcel in) {
        EMPID = in.readString();
        EMPNAME = in.readString();
        DESIGNATIONID = in.readString();
        SALARY = in.readString();
        EMPADDRESS = in.readString();
        CONTACT = in.readString();
        EMPBDATE = in.readString();
        EMPJOINDATE = in.readString();
        REFERNCEBY = in.readString();
        LOGINPASSWORD = in.readString();
        ISALLOWKOT = in.readString();
        ISALLOWCANCELKOT = in.readString();
        ISALLOWSHIFTTABLE = in.readString();
        ISALLOWBILL = in.readString();
        ISALLOWDISCOUNTONBILL = in.readString();
        ACTIVESTATUS = in.readString();
        CREBY = in.readString();
        CREON = in.readString();
        UPDBY = in.readString();
        UPDON = in.readString();
    }

    public static final Creator<GetAllCaptainBO> CREATOR = new Creator<GetAllCaptainBO>() {
        @Override
        public GetAllCaptainBO createFromParcel(Parcel in) {
            return new GetAllCaptainBO(in);
        }

        @Override
        public GetAllCaptainBO[] newArray(int size) {
            return new GetAllCaptainBO[size];
        }
    };

    public String getEMPID( ) {
        return EMPID;
    }

    public void setEMPID(String EMPID) {
        this.EMPID = EMPID;
    }

    public String getEMPNAME( ) {
        return EMPNAME;
    }

    public void setEMPNAME(String EMPNAME) {
        this.EMPNAME = EMPNAME;
    }

    public String getDESIGNATIONID( ) {
        return DESIGNATIONID;
    }

    public void setDESIGNATIONID(String DESIGNATIONID) {
        this.DESIGNATIONID = DESIGNATIONID;
    }

    public String getSALARY( ) {
        return SALARY;
    }

    public void setSALARY(String SALARY) {
        this.SALARY = SALARY;
    }

    public String getEMPADDRESS( ) {
        return EMPADDRESS;
    }

    public void setEMPADDRESS(String EMPADDRESS) {
        this.EMPADDRESS = EMPADDRESS;
    }

    public String getCONTACT( ) {
        return CONTACT;
    }

    public void setCONTACT(String CONTACT) {
        this.CONTACT = CONTACT;
    }

    public String getEMPBDATE( ) {
        return EMPBDATE;
    }

    public void setEMPBDATE(String EMPBDATE) {
        this.EMPBDATE = EMPBDATE;
    }

    public String getEMPJOINDATE( ) {
        return EMPJOINDATE;
    }

    public void setEMPJOINDATE(String EMPJOINDATE) {
        this.EMPJOINDATE = EMPJOINDATE;
    }

    public String getREFERNCEBY( ) {
        return REFERNCEBY;
    }

    public void setREFERNCEBY(String REFERNCEBY) {
        this.REFERNCEBY = REFERNCEBY;
    }

    public String getLOGINPASSWORD( ) {
        return LOGINPASSWORD;
    }

    public void setLOGINPASSWORD(String LOGINPASSWORD) {
        this.LOGINPASSWORD = LOGINPASSWORD;
    }

    public String getISALLOWKOT( ) {
        return ISALLOWKOT;
    }

    public void setISALLOWKOT(String ISALLOWKOT) {
        this.ISALLOWKOT = ISALLOWKOT;
    }

    public String getISALLOWCANCELKOT( ) {
        return ISALLOWCANCELKOT;
    }

    public void setISALLOWCANCELKOT(String ISALLOWCANCELKOT) {
        this.ISALLOWCANCELKOT = ISALLOWCANCELKOT;
    }

    public String getISALLOWSHIFTTABLE( ) {
        return ISALLOWSHIFTTABLE;
    }

    public void setISALLOWSHIFTTABLE(String ISALLOWSHIFTTABLE) {
        this.ISALLOWSHIFTTABLE = ISALLOWSHIFTTABLE;
    }

    public String getISALLOWBILL( ) {
        return ISALLOWBILL;
    }

    public void setISALLOWBILL(String ISALLOWBILL) {
        this.ISALLOWBILL = ISALLOWBILL;
    }

    public String getISALLOWDISCOUNTONBILL( ) {
        return ISALLOWDISCOUNTONBILL;
    }

    public void setISALLOWDISCOUNTONBILL(String ISALLOWDISCOUNTONBILL) {
        this.ISALLOWDISCOUNTONBILL = ISALLOWDISCOUNTONBILL;
    }

    public String getACTIVESTATUS( ) {
        return ACTIVESTATUS;
    }

    public void setACTIVESTATUS(String ACTIVESTATUS) {
        this.ACTIVESTATUS = ACTIVESTATUS;
    }

    public String getCREBY( ) {
        return CREBY;
    }

    public void setCREBY(String CREBY) {
        this.CREBY = CREBY;
    }

    public String getCREON( ) {
        return CREON;
    }

    public void setCREON(String CREON) {
        this.CREON = CREON;
    }

    public String getUPDBY( ) {
        return UPDBY;
    }

    public void setUPDBY(String UPDBY) {
        this.UPDBY = UPDBY;
    }

    public String getUPDON( ) {
        return UPDON;
    }

    public void setUPDON(String UPDON) {
        this.UPDON = UPDON;
    }

    @Override
    public String toString( ) {
        return "\n GetAllCaptainBO{" +
                "EMPID='" + EMPID + '\'' +
                ", EMPNAME='" + EMPNAME + '\'' +
                ", DESIGNATIONID='" + DESIGNATIONID + '\'' +
                ", SALARY='" + SALARY + '\'' +
                ", EMPADDRESS='" + EMPADDRESS + '\'' +
                ", CONTACT='" + CONTACT + '\'' +
                ", EMPBDATE='" + EMPBDATE + '\'' +
                ", EMPJOINDATE='" + EMPJOINDATE + '\'' +
                ", REFERNCEBY='" + REFERNCEBY + '\'' +
                ", LOGINPASSWORD='" + LOGINPASSWORD + '\'' +
                ", ISALLOWKOT='" + ISALLOWKOT + '\'' +
                ", ISALLOWCANCELKOT='" + ISALLOWCANCELKOT + '\'' +
                ", ISALLOWSHIFTTABLE='" + ISALLOWSHIFTTABLE + '\'' +
                ", ISALLOWBILL='" + ISALLOWBILL + '\'' +
                ", ISALLOWDISCOUNTONBILL='" + ISALLOWDISCOUNTONBILL + '\'' +
                ", ACTIVESTATUS='" + ACTIVESTATUS + '\'' +
                ", CREBY='" + CREBY + '\'' +
                ", CREON='" + CREON + '\'' +
                ", UPDBY='" + UPDBY + '\'' +
                ", UPDON='" + UPDON + '\'' +
                '}';
    }

    @Override
    public int describeContents( ) {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(EMPID);
        dest.writeString(EMPNAME);
        dest.writeString(DESIGNATIONID);
        dest.writeString(SALARY);
        dest.writeString(EMPADDRESS);
        dest.writeString(CONTACT);
        dest.writeString(EMPBDATE);
        dest.writeString(EMPJOINDATE);
        dest.writeString(REFERNCEBY);
        dest.writeString(LOGINPASSWORD);
        dest.writeString(ISALLOWKOT);
        dest.writeString(ISALLOWCANCELKOT);
        dest.writeString(ISALLOWSHIFTTABLE);
        dest.writeString(ISALLOWBILL);
        dest.writeString(ISALLOWDISCOUNTONBILL);
        dest.writeString(ACTIVESTATUS);
        dest.writeString(CREBY);
        dest.writeString(CREON);
        dest.writeString(UPDBY);
        dest.writeString(UPDON);
    }
}