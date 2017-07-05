package com.sist.vo;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UsersVO {
	/* 기본 정보 */
	private int id;
	
	@Pattern(regexp="[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[_0-9a-zA-Z-]+)*$",message="올바른 주소 형식이 아닙니다")
	private String email;
	
	@Size(min=6,max=100,message="비밀번호는 6글자 이상 입력해주세요")
	private String pwd;
	
	@Size(min=2,max=25,message="이름은 2글자에서 25글자 사이로 입력가능합니다")
	@Pattern(regexp="^[가-힣a-zA-Z]+$",message="이름은 한글이나 알파벳으로 입력해주세요")
	private String name;
	
	@Size(min=2,max=25,message="닉네임은 2글자에서 25글자 사이로 입력가능합니다")
	@Pattern(regexp="^[가-힣a-zA-Z0-9]+$",message="닉네임은 한글이나 알파벳, 숫자로 입력해주세요")
	private String nickname;
	
	private Date regdate;
	private Date moddate;
	
	/* 추가 정보 */
	private String img_ori;
	private String img_new;
	private int religion;
	private int vegeterain;
	private String gender;
	private int address1;
	private String address2;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date ragdate) {
		this.regdate = ragdate;
	}
	public Date getModdate() {
		return moddate;
	}
	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}
	
	public String getImg_ori() {
		return img_ori;
	}
	public void setImg_ori(String img_ori) {
		this.img_ori = img_ori;
	}
	public String getImg_new() {
		return img_new;
	}
	public void setImg_new(String img_new) {
		this.img_new = img_new;
	}
	public int getReligion() {
		return religion;
	}
	public void setReligion(int religion) {
		this.religion = religion;
	}
	public int getVegeterain() {
		return vegeterain;
	}
	public void setVegeterain(int vegeterain) {
		this.vegeterain = vegeterain;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAddress1() {
		return address1;
	}
	public void setAddress1(int address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
}
