package com.jeong.covidinfo;
// 코로나 OpenApi를 사용하기 위한 Item
public class Item {
    String decideCnt;
    String examCnt;
    String clearCnt;
    String deathCnt;

    public Item() {}

    public Item (String decideCnt, String examCnt, String clearCnt, String deathCnt){
        this.decideCnt = decideCnt;
        this.examCnt = examCnt;
        this.clearCnt = clearCnt;
        this.decideCnt = deathCnt;
    }
    public void setDecideCnt(String decideCnt) { this.decideCnt = decideCnt; }
    public void setExamCnt(String examCnt) {
        this.examCnt = examCnt;
    }
    public void setClearCnt(String clearCnt) {
        this.clearCnt = clearCnt;
    }
    public void setDeathCnt(String deathCnt) {
        this.deathCnt = deathCnt;
    }


}
