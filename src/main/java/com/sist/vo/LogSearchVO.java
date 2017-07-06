package com.sist.vo;

import java.sql.Date;

public class LogSearchVO {

    // PK 
    private int id;

    // 검색어 
    private String keyword;

    // 검색일 
    private Date regdate;
    
    private int hit;//조회수
    
    private int num;//랭킹

    
    
    public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

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
    public void CopyData(LogSearchVO param)
    {
        this.id = param.getId();
        this.keyword = param.getKeyword();
        this.regdate = param.getRegdate();
    }
}
