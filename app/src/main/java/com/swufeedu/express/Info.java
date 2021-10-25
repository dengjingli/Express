package com.swufeedu.express;

public class Info {
    private String state;
    private String num;
    private String companyImgID;

    public Info(String state, String num, String companyImgID) {
        this.state = state;
        this.num = num;
        this.companyImgID = companyImgID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCompanyImgID() {
        return companyImgID;
    }

    public void setCompanyImgID(String companyImgID) {
        this.companyImgID = companyImgID;
    }
}
