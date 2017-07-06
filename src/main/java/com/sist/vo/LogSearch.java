package com.sist.vo;

import java.sql.Date;

public class LogSearch {

    // PK 
    private int id;

    // 검색어 
    private String keyword;

    // 검색일 
    private Date regdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    // LogSearch 모델 복사
    public void CopyData(LogSearch param)
    {
        this.id = param.getId();
        this.keyword = param.getKeyword();
        this.regdate = param.getRegdate();
    }
}
