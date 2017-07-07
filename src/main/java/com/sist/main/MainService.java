package com.sist.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.recipe.IngredientDAO;
import com.sist.recipe.RecipeDAO;
import com.sist.search.IngrSearchService;
import com.sist.search.SearchDAO;
import com.sist.search.TitleSearchService;
import com.sist.users.UsersService;
import com.sist.vo.IngredientVO;
import com.sist.vo.LogSearch;
import com.sist.vo.RecipeVO;
import com.sist.vo.UsersVO;

@Service
public class MainService {
	
	@Autowired
	private SearchDAO searchDAO;
	
	@Autowired
	private TitleSearchService titleSearchService;
	
	@Autowired
	private IngrSearchService ingrSearchService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private IngredientDAO ingredientDAO;
	
	@Autowired
	private RecipeDAO recipeDAO;
	
	public Map<String, List<RecipeVO>> homeMain(Map map){
		Map result=new HashMap();
		
		LogSearch first=new LogSearch();
		List<LogSearch> logSearchRankList=searchDAO.getLogSearchRankList();
		for (LogSearch l : logSearchRankList) {
			if (l.getNum()==1) {
				first=l;
			}
			
		}
		
		Map search =new HashMap();
		search.put("searchKeyword", first.getKeyword());
		search.put("start", 1);
		search.put("end", 3);		
		List<RecipeVO> logSearchRankRecipeList=titleSearchService.keywordSearch(search);
		for (RecipeVO recipeVO : logSearchRankRecipeList) {
			recipeVO.setImgAuto();
			recipeVO.setNickname(usersService.selectNickName(recipeVO.getUser_id()));
		}
		
		Calendar calendar=Calendar.getInstance();
		int nowMonth=calendar.get(Calendar.MONTH);
		
		List<IngredientVO> ingrListOnNowMonth=ingredientDAO.selectIngrListByMonth(nowMonth+1);
		
		int total=ingrListOnNowMonth.size();
		int[] random=new int[5];
		//중복되지 않는 랜덤값 5개얻기
		
		System.out.println("total="+total);
		System.out.println("여기까지 되나?");
		for (int a = 0; a < random.length; a++) {
			int x=(int)(Math.random()*total);			
			random[a]=x;
			
			for (int b = 0; b < a; b++) {
				if (random[a]==random[b]) {//새로얻은 값이 이전에 얻은 값과 같다면
					/*x=(int)(Math.random()*total);			
					random[a]=x;무한루프걸리네?*/
					
					a=a-1;//다시 
					break;
				}
					
			}		
			
		}
		
		List<IngredientVO> randomIngrListOnNowMonth=new ArrayList<IngredientVO>();
		for (int i = 0; i < random.length; i++) {
			randomIngrListOnNowMonth.add(ingrListOnNowMonth.get(random[i]));
			
		}
		
		//첫번째 randomIngr로 recipe3개 가져오기
		search.put("searchKeyword", ingrListOnNowMonth.get(random[0]).getName());
		List<RecipeVO> randomRecipeListOnNowMonth=ingrSearchService.keywordSearch(search);
		for (RecipeVO vo : randomRecipeListOnNowMonth) {
			vo.setImgAuto();
			vo.setNickname(usersService.selectNickName(vo.getUser_id()));
		}
		
		//10위까지 유저랭킹 가져오기
		List<UsersVO> userRankList=usersService.selectUserRank();
		//랜덤3명 추출
		int[] randomRankUser=new int[3];
		for (int a = 0; a < randomRankUser.length; a++) {
			randomRankUser[a]=(int)(Math.random()*userRankList.size());

			for (int b = 0; b < a; b++) {
				if (randomRankUser[a]==randomRankUser[b]) {//새로얻은 값이 이전에 얻은 값과 같다면
					a=a-1;//다시 
					break;
				}
					
			}		
		}
		List<UsersVO> randomUserRankList=new ArrayList<UsersVO>();
		for (int i : randomRankUser) {
			randomUserRankList.add(userRankList.get(i));
		}
		
		List<RecipeVO> randomUserRankRecipeList=new ArrayList<RecipeVO>();
		//위 유저의 레시피를 한개씩 가져오기
		search.put("end", 1);
		for (UsersVO userVO : randomUserRankList) {
			search.put("nickname", userVO.getNickname());
			List<RecipeVO> list=recipeDAO.getRecipeListByNick(search);
			RecipeVO recipeVO=list.get(0);
			recipeVO.setImgAuto();
			recipeVO.setNickname(userVO.getNickname());
			randomUserRankRecipeList.add(recipeVO);			
		}
		
		
		result.put("logSearchRankList", logSearchRankList);
		result.put("logSearchRankRecipeList", logSearchRankRecipeList);
		result.put("randomIngrListOnNowMonth", randomIngrListOnNowMonth);
		result.put("randomRecipeListOnNowMonth", randomRecipeListOnNowMonth);
		result.put("nowMonth", nowMonth+1);
		result.put("randomUserRankList", randomUserRankList);
		result.put("randomUserRankRecipeList", randomUserRankRecipeList);
		return result;
	}
	
}
