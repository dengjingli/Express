package com.swufeedu.express.db;


public class InfoItem {
    private int id;
    private String curName;
    private String curNum;
    private String curState;
    

    public InfoItem() {
        super();
        curName = "";
        curNum = "";
        curState="";
    }
    public InfoItem(String curName, String curNum,String curState) {
        super();
        this.curName = curName;
        this.curNum = curNum;
        this.curState=curState;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCurName() {
        return curName;
    }
    public void setCurName(String curName) {
        this.curName = curName;
    }
    public String getCurNum() {
        return curNum;
    }
    public void setCurNum(String curNum) {
        this.curNum = curNum;
    }
    public String getCurState() {
        return curState;
    }
    public void setCurState(String curState) {
        this.curState = curState;
    }
}

