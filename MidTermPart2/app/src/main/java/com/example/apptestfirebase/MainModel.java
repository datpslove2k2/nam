package com.example.apptestfirebase;

public class MainModel {
    private String nameKH, nameTG, dacTinh, mauLa, congDung, duocTinh, chuY, turl;

    MainModel() {

    }

    public MainModel(String nameKH, String nameTG, String dacTinh, String mauLa, String congDung, String duocTinh, String chuY, String turl) {
        this.nameKH = nameKH;
        this.nameTG = nameTG;
        this.dacTinh = dacTinh;
        this.mauLa = mauLa;
        this.congDung = congDung;
        this.duocTinh = duocTinh;
        this.chuY = chuY;
        this.turl = turl;
    }

    public String getNameKH() {
        return nameKH;
    }

    public void setNameKH(String nameKH) {
        this.nameKH = nameKH;
    }

    public String getNameTG() {
        return nameTG;
    }

    public void setNameTG(String nameTG) {
        this.nameTG = nameTG;
    }

    public String getDacTinh() {
        return dacTinh;
    }

    public void setDacTinh(String dacTinh) {
        this.dacTinh = dacTinh;
    }

    public String getMauLa() {
        return mauLa;
    }

    public void setMauLa(String mauLa) {
        this.mauLa = mauLa;
    }

    public String getCongDung() {
        return congDung;
    }

    public void setCongDung(String congDung) {
        this.congDung = congDung;
    }

    public String getDuocTinh() {
        return duocTinh;
    }

    public void setDuocTinh(String duocTinh) {
        this.duocTinh = duocTinh;
    }

    public String getChuY() {
        return chuY;
    }

    public void setChuY(String chuY) {
        this.chuY = chuY;
    }

    public String getTurl() {
        return turl;
    }

    public void setTurl(String turl) {
        this.turl = turl;
    }
}
