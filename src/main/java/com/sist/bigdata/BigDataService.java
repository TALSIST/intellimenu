package com.sist.bigdata;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	   Calendar cal = Calendar.getInstance();
	   cal.setTime(new Date());
	   int month = cal.get(Calendar.MONTH)+1;
	   int today = cal.get(Calendar.DAY_OF_MONTH);

		List<MartVO> todaylist_vegi = martDAO.selectItem(today, 0);
		List<MartVO> todaylist_fish = martDAO.selectItem(today, 1);

		//lineChart의 컬럼명이 되는 day를 검색해서 제이슨으로 보내기
 		StringBuffer sb=new StringBuffer();
 		List<String> days=martDAO.selectDays(todaylist_vegi.get(0).getItem());
 		for(int i=0;i<days.size();i++){
 			sb.append("\""+days.get(i)+"\"");
 			if(i!=days.size()-1){
 				sb.append(", ");
 			}
 		}
 		String categories="["+sb.toString()+"]";
 		
		model.addAttribute("today", month+"월 "+today+"일");
		model.addAttribute("categories", categories);
		model.addAttribute("series_vegi", getLineChartJson(todaylist_vegi));
		model.addAttribute("series_fish", getLineChartJson(todaylist_fish));
		model.addAttribute("todayHitItem_vegi",todaylist_vegi.get(0).getItem());
		model.addAttribute("todayHitItem_fish",todaylist_fish.get(0).getItem());
		model.addAttribute("wordData_vegi", getWordCloudJson(todaylist_vegi, 0));
		model.addAttribute("wordData_fish", getWordCloudJson(todaylist_fish, 1));
		model.addAttribute("randomMartList_vegi", getRandomMartList(todaylist_vegi));
		model.addAttribute("randomMartList_fish", getRandomMartList(todaylist_fish));
	}

	public String getWordCloudJson(List<MartVO> todaylist, int cate){
		//워드클라우드를 위한 json만들기
		StringBuffer word=new StringBuffer();
		for(int j=0;j<todaylist.size();j++){
			word.append("{");
			word.append("\"word\":\""+todaylist.get(j).getItem());
			word.append("\",\"freq\":"+todaylist.get(j).getHit()*10);
			word.append("},");
		};
		//워드클라우드비주얼업그레이를 위해 다른 날짜들의 재료들을 검색해와 붙이기
		List<MartVO> vegilist=martDAO.selectItem(cate);
		for(int j=0;j<vegilist.size();j++){
			word.append("{");
			word.append("\"word\":\""+vegilist.get(j).getItem());
			word.append("\",\"freq\":"+vegilist.get(j).getHit()*8);
			if(j!=vegilist.size()-1){
				word.append("},");
			}else{
				word.append("}");
			}
		};
		return "["+word.toString()+"]";
	}
	
	public String getLineChartJson(List<MartVO> todaylist){	
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
		return "["+line.toString()+"]";
	}	
	
	public List<RecipeVO> getRandomMartList(List<MartVO> todaylist){
		String todayHitItem=todaylist.get(0).getItem();
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
		return randomMartList;
	}
}
