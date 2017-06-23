package com.sist.vo;

//대분류 
public class Cat_topVO {

 // PK 
 private int id;

 // 대분류이름 
 private String name;

 public int getId() {
     return id;
 }

 public void setId(int id) {
     this.id = id;
 }

 public String getName() {
     return name;
 }

 public void setName(String name) {
     this.name = name;
 }

 // CatTop 모델 복사
 public void CopyData(Cat_topVO param)
 {
     this.id = param.getId();
     this.name = param.getName();
 }
}