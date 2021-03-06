package com.sist.vo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.web.multipart.MultipartFile;

import com.sist.util.FileManager;

public class RecipeVO {
	private int id;
	private int user_id;
	private int cat_sub_id;
	private Date regdate;
	private int hit;
	private String title;
	private String summary;
	private int reqmember;
	private String time;
	private String lvl;
	private String img_ori;
	private String img_new;
	private String img;//img_ori와 new중 사용할 이미지 

	private int updateid;//update id;
	private String nickname;//user_id로 검색한 nickname
	private String subCategoryName; //cat_sub_id 로 검색한 결과	
	private List<MultipartFile> stepsFile=new ArrayList<MultipartFile>();					//파일배열
	private List<String> content=new ArrayList<String>();								//순서내용
	private List<Integer> ingrv=new ArrayList<Integer>();									//재료목록
	private  List<String> ingrg=new ArrayList<String>();								//재료중량
	
	private List<RecipeContentVO> contentList=new ArrayList<RecipeContentVO>();
	private List<RecipeTagVO> tagList=new ArrayList<RecipeTagVO>();
	private List<IngredientVO> ingredientList=new ArrayList<IngredientVO>();
	
	private int favorite_id;
	
	public int getFavorite_id() {
		return favorite_id;
	}
	public void setFavorite_id(int favorite_id) {
		this.favorite_id = favorite_id;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	
	public int getUpdateid() {
		return updateid;
	}
	public void setUpdateid(int updateid) {
		this.updateid = updateid;
	}
		
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public List<MultipartFile> getStepsFile() {
		return stepsFile;
	}
	public void setStepsFile(List<MultipartFile> stepsFile) {
		this.stepsFile = stepsFile;
	}
	public List<String> getContent() {
		return content;
	}
	public void setContent(List<String> content) {
		this.content = content;
	}
	public List<Integer> getIngrv() {
		return ingrv;
	}
	public void setIngrv(List<Integer> ingrv) {
		this.ingrv = ingrv;
	}
	public List<String> getIngrg() {
		return ingrg;
	}
	public void setIngrg(List<String> ingrg) {
		this.ingrg = ingrg;
	}
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public void setImgAuto() {
		/*if (vo.getImg_new().equals("imgfromweb")) {
			vo.setImg(vo.getImg_ori());
		}else{
			vo.setImg(vo.getImg_new());				
		}*/
		
		
		if (img_new.equals("imgfromweb")) {
			img=img_ori;
		}else{
			img="/resources/recipe/2017/"+img_new;
			
		}
		
	}
	
	
	
	public List<RecipeContentVO> getContentList() {
		return contentList;
	}
	public void setContentList(List<RecipeContentVO> contentList) {
		this.contentList = contentList;
	}
	public List<RecipeTagVO> getTagList() {
		return tagList;
	}
	public void setTagList(List<RecipeTagVO> tagList) {
		this.tagList = tagList;
	}
	public List<IngredientVO> getIngredientList() {
		return ingredientList;
	}
	public void setIngredientList(List<IngredientVO> ingredientList) {
		this.ingredientList = ingredientList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getReqmember() {
		return reqmember;
	}
	public void setReqmember(int reqmember) {
		this.reqmember = reqmember;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLvl() {
		return lvl;
	}
	public void setLvl(String lvl) {
		this.lvl = lvl;
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
	
	public int getCat_sub_id() {
		return cat_sub_id;
	}
	public void setCat_sub_id(int cat_sub_id) {
		this.cat_sub_id = cat_sub_id;
	}
	
}
