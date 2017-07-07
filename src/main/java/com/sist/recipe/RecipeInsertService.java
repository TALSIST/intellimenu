package com.sist.recipe;

import java.io.File;
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
public class RecipeInsertService {
	@Autowired
	private RecipeInsertDAO recipeInsertDAO;
	@Autowired
	private FileManager fileManager;
	
	@Transactional
	public int recipeInsert(RecipeVO recipe,
			String tags,MultipartFile mainFile,int user_id) {
		int id=0;
		System.out.println("테그는:"+tags);
			
	
		
		List<String> stepContent=recipe.getContent();
		List<MultipartFile> fileinfo=recipe.getStepsFile();
		
		List<String> ingrg=recipe.getIngrg(); //중량
		List<Integer> ingrv=recipe.getIngrv(); //값
		
	
		String  main_nuw=null;
		try {
			
			main_nuw =fileManager.insertFile(mainFile, "recipe");
		
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		recipe.setUser_id(user_id);
		recipe.setImg_new(main_nuw);	//파일 바꾼것
		recipe.setImg_ori(mainFile.getOriginalFilename()); //파일원래이름 기억
		

		recipeInsertDAO.insertRecipe(recipe);
		id=recipeInsertDAO.recipeMId();
	
			
	//재료저장
		for (int i = 0; i < ingrg.size(); i++) {
		//	System.out.println("재료"+i+"번째:"+ingrv.get(i));
		//	System.out.println("재료량"+i+"번째:"+ingrg.get(i));
		//	recipeInsertDAO.insert_RecipeIngr(ingrg.get(i), ingrv.get(i));
			if(ingrv.get(i)!=null){
				
				IngrRecipeVO vo=new IngrRecipeVO();
				vo.setRecipe_id(id);
				vo.setQuantity(ingrg.get(i));
				vo.setIngredient_id(ingrv.get(i));
				//System.out.println(vo.getIngredient_id()+" "+vo.getRecipe_id()+" "+vo.getQuantity());
				recipeInsertDAO.insert_RecipeIngr(vo);
			}
		}
		
		
		//요리순서 저장
		List<String> newStepFnames=null;
		try {
			newStepFnames = fileManager.insertFile(fileinfo,"recipe_content");
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i=0;i<stepContent.size();i++){
			
			RecipeContentVO vo=new RecipeContentVO();
			vo.setContent(stepContent.get(i));
			vo.setImg_ori(fileinfo.get(i).getOriginalFilename());
			vo.setImg_new(newStepFnames.get(i));
			vo.setRecipe_id(id);
			vo.setStep(i);
			
			recipeInsertDAO.insertRecipeContent(vo);
			
		}
		
		//태그 db 저장
		
		if(!(tags.isEmpty())){
			List<String> tag=StringManager.stringToList(tags);
			for (String v :tag) {
				RecipeTagVO vo=new RecipeTagVO();
				vo.setRecipe_id(id);
				vo.setName(v);
				recipeInsertDAO.insertRecipeTag(vo);
			}
		
		}
		return id;
	}
}
