package com.sist.bigdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sist.naver.NaverManager;
import com.sist.naver.Item;
import com.sist.recipe.RecipeDAO;
import com.sist.search.IngrSearchService;
import com.sist.search.SearchDAO;
import com.sist.search.TitleSearchService;
import com.sist.spark.IngrRankDAO;
import com.sist.spark.IngrRankVO;

import com.sist.users.UsersService;
import com.sist.vo.RecipeVO;
import com.sist.weather.WeatherManager;

@Service
public class BigDataService {
	@Autowired
	private WeatherManager weatherManager;
	
	@Autowired
	private NaverManager naverManager;
	
	@Autowired
	private MartMapper martMapper;
	
	@Autowired
	private MartDAO martDAO;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private IngrRankDAO ingrRankDAO;
	
	@Autowired
	private RecipeDAO recipeDAO;
	
	@Autowired
	private IngrSearchService ingrSearchService;
	
	
	public void helloExample(Model model){
		
		model.addAttribute("hello", "안녕");
	}
	
	public void getWeather(Model model){	
		String weather=weatherManager.getWeather();
		ingrRankDAO.setCollection(weather);
		
		List<IngrRankVO> weatherIngrlist=ingrRankDAO.ingr3Data();
		
		System.out.println("날씨추천 재료크기"+weatherIngrlist.size());
		
		for (IngrRankVO vo : weatherIngrlist) {
			System.out.println("날씨추천 재료"+vo.getName());
			
		}
		Map search =new HashMap();
		
		search.put("start", 1);
		search.put("end", 3);
		search.put("searchKeyword", weatherIngrlist.get(0).getName());
		List<RecipeVO> RecipeListOnWeahter=ingrSearchService.keywordSearch(search);
		for (RecipeVO vo : RecipeListOnWeahter) {
			vo.setImgAuto();
			vo.setNickname(usersService.selectNickName(vo.getUser_id()));
		}
		
		model.addAttribute("RecipeListOnWeahter", RecipeListOnWeahter);
		model.addAttribute("weather", weather);
	}
	
	public void getMart(Model model){

		//몽고db에서 불러오기
		int today=21;
		List<MartVO> todaylist_ori = martDAO.selectDay(today);
		//채소 인기재료만 가져오기
		List<MartVO> todaylist=new ArrayList<MartVO>();
		for(int i=0;i<todaylist_ori.size();i++){
			if(todaylist_ori.get(i).getCate()==0 ){
				if(todaylist.size()<=10){
					todaylist.add(todaylist_ori.get(i));
				}
			}
		}
		System.out.println("todaylist.size()"+todaylist.size());
 		String todayHitItem=todaylist.get(0).getItem();

 		StringBuffer line = new StringBuffer();
 		String[] colors={"#9DC91D","#F49821","#F24630","#7959AB","#F6C208"};
		for(int j = 0; j < 5; j++) {
			MartVO vo = todaylist.get(j);// todayitem
			//List<MartVO> thisItemList = martDAO.selectHitItem(vo.getItem(),2);
			List<MartVO> thisItemList = martDAO.selectItem(vo.getItem());
			line.append("{");
			line.append("name: '" + vo.getItem() + "', ");
			line.append("data: [");
			for (int i = 0; i < thisItemList.size(); i++) {
				if (i != thisItemList.size() - 1) {
					line.append(thisItemList.get(i).getHit()+", ");
				} else {
					line.append(thisItemList.get(i).getHit());
				}
			}
			line.append("],");
			line.append("color: '"+colors[j]+"', ");
		   line.append("stack: 'participants'");
			if (j != todaylist.size() - 1) {
				line.append("},");
			} else {
				line.append("}");
			}
		}
		String series="["+line.toString()+"]";
		
		//워드클라우드를 위한 json만들기
		StringBuffer word=new StringBuffer();
		for(int j=0;j<todaylist.size();j++){
			word.append("{");
			word.append("\"word\":\""+todaylist.get(j).getItem());
			word.append("\",\"freq\":"+todaylist.get(j).getHit()*10);
			if(j!=todaylist.size()-1){
				word.append("},");
			}else{
				word.append("}");
			}
		};
		String wordData="["+word.toString()+"]";
		System.out.println(wordData);
		
		//오늘 최고 인기재료 이름으로 레시피 목록을 구하고 랜덤함수로 만들기
		List<RecipeVO> recipeList=martMapper.RecipeBymartHitIngr(todayHitItem); //오늘 최고 인기재료 이름 구하기
		for (RecipeVO vo : recipeList) {
			vo.setImgAuto();
			vo.setNickname(usersService.selectNickName(vo.getUser_id()));
		}
		//레시피를 위한 랜덤값 얻어오기
		int[] random=new int[5];
		for (int a = 0; a < random.length; a++) {
			int x=(int)(Math.random()*recipeList.size());			
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
		List<RecipeVO> randomMartList=new ArrayList<RecipeVO>();
		for (int i = 0; i < 3; i++) {
			randomMartList.add(recipeList.get(random[i]));
		}
		model.addAttribute("series", series);
		model.addAttribute("todayHitItem",todayHitItem);
		model.addAttribute("wordData", wordData);
		model.addAttribute("randomMartList", randomMartList);
	}

}
