package com.sist.recipe;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sist.util.FileManager;
import com.sist.util.StringManager;
import com.sist.vo.IngrRecipeVO;
import com.sist.vo.RecipeContentVO;
import com.sist.vo.RecipeTagVO;
import com.sist.vo.RecipeVO;

@Service
public class RecipeUpdateService {

	@Autowired
	private RecipeDAO recipeDAO;
	@Autowired
	private RecipeUpdateDAO recipeUpdateDAO;
	@Autowired
	private RecipeInsertDAO recipeInsertDAO;	
	@Autowired
	private FileManager fm;
	
	@Transactional
	public void updateRecipe(RecipeVO recipe,
			String tags,MultipartFile mainFile,int rid){
		String fileName=null;
		RecipeVO recipeVO=recipeDAO.recipeDetail(rid);
		recipeVO.setTitle(recipe.getTitle());
		recipeVO.setLvl(recipe.getLvl());
		recipeVO.setReqmember(recipe.getReqmember());
		recipeVO.setCat_sub_id(recipe.getCat_sub_id());
		recipeVO.setTime(recipe.getTime());
		recipeVO.setUpdateid(rid);
			if (mainFile.getSize()!=0) {
				try {
					fm.deleteFile(recipeVO.getImg_new(),"recipe");
					fileName=fm.insertFile(mainFile, "recipe");
					recipeVO.setImg_ori(mainFile.getOriginalFilename());
					recipeVO.setImg_new(fileName);
					
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		recipeUpdateDAO.updateRecipe(recipeVO);
		
		
		
			
		//2단계 재료 재료는 지우고 다시 삽입
		List<String> ingrg=recipe.getIngrg(); //중량
		List<Integer> ingrv=recipe.getIngrv(); //값
		recipeUpdateDAO.deleteIngrR(rid);
		for (int i = 0; i < ingrg.size(); i++) {
			//	System.out.println("재료"+i+"번째:"+ingrv.get(i));
			//	System.out.println("재료량"+i+"번째:"+ingrg.get(i));
			//	recipeInsertDAO.insert_RecipeIngr(ingrg.get(i), ingrv.get(i));
				if(ingrv.get(i)!=null){
					
					IngrRecipeVO vo=new IngrRecipeVO();
					vo.setRecipe_id(rid);
					vo.setQuantity(ingrg.get(i));
					vo.setIngredient_id(ingrv.get(i));
					//System.out.println(vo.getIngredient_id()+" "+vo.getRecipe_id()+" "+vo.getQuantity());
					recipeInsertDAO.insert_RecipeIngr(vo);
				}
			}
		
			
			
				//3단계 순서id 값 다받아와서 처리 한다
				//조회하는 쿼리 필요
				List<String> stepContent=recipe.getContent();		//전달받은것
				List<MultipartFile> fileinfo=recipe.getStepsFile();   //전달받은것 
				List<RecipeContentVO> stepRecipe=recipeDAO.recipeDetailContent(rid);
				int j=0;
				for (int i=0;i<stepContent.size();i++){
					RecipeContentVO vo =new RecipeContentVO();
					
					if(stepRecipe.size()<stepContent.size()){
						try {
							vo.setRecipe_id(rid);
							vo.setStep(i);
							vo.setContent(stepContent.get(i));
							vo.setImg_ori(fileinfo.get(i).getOriginalFilename());
							vo.setImg_new(fm.insertFile(fileinfo.get(i),"recipe_content"));
						
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("여기"+vo.getContent());
						System.out.println("여기2"+vo.getStep());
						System.out.println("여기3"+vo.getImg_ori());
						System.out.println("여기4"+vo.getImg_new());
						recipeInsertDAO.insertRecipeContent(vo);
						
					}
					else{
						
						vo.setContent(stepContent.get(i));
						vo.setId(stepRecipe.get(i).getId());
						vo.setRecipe_id(stepRecipe.get(i).getRecipe_id());
						vo.setStep(i);
						
						
						
						if(!(fileinfo.get(i).getOriginalFilename().isEmpty())){
							
							try {
								String stepname=fm.insertFile(fileinfo.get(i),"recipe_content");
								if(stepRecipe.get(i).getImg_new()!=null){
								fm.deleteFile(stepRecipe.get(i).getImg_new(), "recipe_cotent");
								}
								vo.setImg_new(stepname);
								vo.setImg_ori(fileinfo.get(i).getOriginalFilename());
							
							} catch (IllegalStateException e) {
								
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}else{
							if(stepRecipe.get(i).getImg_new()==null){
								vo.setImg_new("");
								vo.setImg_ori("");
							}else{
								
								vo.setImg_new(stepRecipe.get(i).getImg_new());
								vo.setImg_ori(stepRecipe.get(i).getImg_ori());
							}
						}
						System.out.println("vo.getContent()"+vo.getContent()+"vo.getImg_new()"+vo.getImg_new()+vo.getStep());
						recipeUpdateDAO.updateStep(vo);
					}
					//System.out.println("vo.getContent()"+vo.getContent()+"vo.getImg_new()"+vo.getImg_new()+vo.getStep());
				}
				for(int i=stepContent.size();i<stepRecipe.size();i++){
					recipeUpdateDAO.deleteStep(stepRecipe.get(i).getId());
				}
			
				
				
				
		System.out.println("없어2");
					recipeUpdateDAO.deleteTag(rid);
					if(!(tags.isEmpty())){
						List<String> tag=StringManager.stringToList(tags);
						for (String v :tag) {
							RecipeTagVO vo=new RecipeTagVO();
							vo.setRecipe_id(rid);
							vo.setName(v);
							recipeInsertDAO.insertRecipeTag(vo);
						}
					
					}
		
		
	}
}
