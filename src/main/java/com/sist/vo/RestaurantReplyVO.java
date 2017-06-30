package com.sist.vo;
import java.util.*;

//�떇�떦_�뙎湲� 
public class RestaurantReplyVO {

 // PK 
 private Integer id;

 // �쉶�썝id 
 private Integer userId;

 // �떇�떦id 
 private Integer restaurantId;

 // �뙎湲� 
 private String reply;

 // �젏�닔 
 private Integer score;

 // �벑濡앹씪 
 private Date regdate;

 // 洹몃９�븘�씠�뵒 
 private Integer groupId;

 // 洹몃９�뒪�꺆 
 private Integer groupStep;

 // 洹몃９�꺆 
 private Integer groupTab;

 // 猷⑦듃 
 private Integer root;

 // 源딆씠 
 private Integer depth;

 public Integer getId() {
     return id;
 }

 public void setId(Integer id) {
     this.id = id;
 }

 public Integer getUserId() {
     return userId;
 }

 public void setUserId(Integer userId) {
     this.userId = userId;
 }

 public Integer getRestaurantId() {
     return restaurantId;
 }

 public void setRestaurantId(Integer restaurantId) {
     this.restaurantId = restaurantId;
 }

 public String getReply() {
     return reply;
 }

 public void setReply(String reply) {
     this.reply = reply;
 }

 public Integer getScore() {
     return score;
 }

 public void setScore(Integer score) {
     this.score = score;
 }

 public Date getRegdate() {
     return regdate;
 }

 public void setRegdate(Date regdate) {
     this.regdate = regdate;
 }

 public Integer getGroupId() {
     return groupId;
 }

 public void setGroupId(Integer groupId) {
     this.groupId = groupId;
 }

 public Integer getGroupStep() {
     return groupStep;
 }

 public void setGroupStep(Integer groupStep) {
     this.groupStep = groupStep;
 }

 public Integer getGroupTab() {
     return groupTab;
 }

 public void setGroupTab(Integer groupTab) {
     this.groupTab = groupTab;
 }

 public Integer getRoot() {
     return root;
 }

 public void setRoot(Integer root) {
     this.root = root;
 }

 public Integer getDepth() {
     return depth;
 }

 public void setDepth(Integer depth) {
     this.depth = depth;
 }

 // RestaurantReply 紐⑤뜽 蹂듭궗
 public void CopyData(RestaurantReplyVO param)
 {
     this.id = param.getId();
     this.userId = param.getUserId();
     this.restaurantId = param.getRestaurantId();
     this.reply = param.getReply();
     this.score = param.getScore();
     this.regdate = param.getRegdate();
     this.groupId = param.getGroupId();
     this.groupStep = param.getGroupStep();
     this.groupTab = param.getGroupTab();
     this.root = param.getRoot();
     this.depth = param.getDepth();
 }
}