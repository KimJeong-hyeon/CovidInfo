package com.jeong.covidinfo;

public class ClinicData {
    String sido;
    String sigun;
    String clinic;
    String adress;
    String time_1;
    String time_2;
    String time_3;
    String tel;

    public ClinicData(String sido, String sigun, String clinic, String adress, String time_1, String time_2,
                      String time_3, String tel){
        this.sido = sido;
        this.sigun = sigun;
        this.clinic = clinic;
        this.adress = adress;
        this.time_1 = time_1;
        this.time_2 = time_2;
        this.time_3 = time_3;
        this.tel = tel;
    }

    public String getSido() {
        return sido;
    }

    public void setSido(String sido) {
        this.sido = sido;
    }

    public String getSigun() {
        return sigun;
    }

    public void setSigun(String sigun) {
        this.sigun = sigun;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTime_1() {
        return time_1;
    }

    public void setTime_1(String time_1) {
        this.time_1 = time_1;
    }

    public String getTime_2() {
        return time_2;
    }

    public void setTime_2(String time_2) {
        this.time_2 = time_2;
    }

    public String getTime_3() {
        return time_3;
    }

    public void setTime_3(String time_3) {
        this.time_3 = time_3;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
