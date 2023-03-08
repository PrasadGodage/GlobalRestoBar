package com.soulsoft.globalrestobar.model.runningtable;

import android.os.Parcel;
import android.os.Parcelable;

public class RunningOrderBO implements Parcelable {

    private String TID;
    private String TABLE;
    private String AMOUNT;
    private String EMPLOYEE;
    private String EMPID;
    private String SID;
    private String KTYPE;

    protected RunningOrderBO(Parcel in) {
        TID = in.readString();
        TABLE = in.readString();
        AMOUNT = in.readString();
        EMPLOYEE = in.readString();
        EMPID = in.readString();
        SID = in.readString();
        KTYPE = in.readString();
    }

    public static final Creator<RunningOrderBO> CREATOR = new Creator<RunningOrderBO>() {
        @Override
        public RunningOrderBO createFromParcel(Parcel in) {
            return new RunningOrderBO(in);
        }

        @Override
        public RunningOrderBO[] newArray(int size) {
            return new RunningOrderBO[size];
        }
    };

    public String getTID( ) {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public String getTABLE( ) {
        return TABLE;
    }

    public void setTABLE(String TABLE) {
        this.TABLE = TABLE;
    }

    public String getAMOUNT( ) {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getEMPLOYEE( ) {
        return EMPLOYEE;
    }

    public void setEMPLOYEE(String EMPLOYEE) {
        this.EMPLOYEE = EMPLOYEE;
    }

    public String getEMPID( ) {
        return EMPID;
    }

    public void setEMPID(String EMPID) {
        this.EMPID = EMPID;
    }

    public String getSID( ) {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getORDERTYPE( ) {
        return KTYPE;
    }

    public void setORDERTYPE(String ORDERTYPE) {
        this.KTYPE = ORDERTYPE;
    }

    public String getKTYPE( ) {
        return KTYPE;
    }

    public void setKTYPE(String KTYPE) {
        this.KTYPE = KTYPE;
    }

    @Override
    public int describeContents( ) {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(TID);
        parcel.writeString(TABLE);
        parcel.writeString(AMOUNT);
        parcel.writeString(EMPLOYEE);
        parcel.writeString(EMPID);
        parcel.writeString(SID);
        parcel.writeString(KTYPE);
    }
}
