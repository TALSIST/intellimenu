package com.sist.weather;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherManager {
	//@Autowired
	//private WeatherDAO weatherDAO;
	
	String[] weather={
			"맑은 날",
			"흐린 날",
			"비오는 날",
			"눈오는 날",
			"비눈 오는 날",
			"황사있는 날"
	};
	String[][] weatherDictionary={
		{"맑음", "구름조금"}
		,{"구름많음", "흐림", "연무", "안개", "박무"}	
		,{"비", "가끔 비", "한때 비", "천둥번개"}
		,{"눈", "가끔 눈", "한때 눈"}
		,{"비 또는 눈", "가끔 비 또는 눈", "한때 비 또는 눈", "눈 또는 비", "가끔 눈 또는 비", "한때 눈 또는 비"}
		,{"황사"}
	};
	
	
	//기상청에서 날씨 가져오기
	public List<WeatherVO> todayGetWeather(){
		List<WeatherVO> list = new ArrayList<WeatherVO>();
		try{
			Document doc = Jsoup.connect("http://www.kma.go.kr/index.jsp").get();
			Elements dates = doc.select("dl.region_weather_g dd");
			Elements locations = doc.select("dl.region_weather_e dt a");
			Elements images = doc.select("dl.region_weather_e dd a img");
			
			String date = dates.get(0).text();
			for(int i = 0; i<locations.size();i++){
				String location = locations.get(i).text();
				String image = images.get(i).attr("src");
				String weather = images.get(i).attr("alt");
				
				WeatherVO vo = new WeatherVO();
				vo.setDate(date);
				vo.setLocation(location);
				vo.setImage(image);
				vo.setWeather(weather);
				list.add(vo);
			}
		}catch(Exception e){
			
		}
		return list;
	}
	
	public String getWeather(){
		String result="";
		List<WeatherVO> list=todayGetWeather();
		String now=list.get(0).getWeather();
		
		int i=0;
		for (String[] weatherArr : weatherDictionary) {
			for (String s : weatherArr) {
				if (s.equals(now.trim())) {
					result=weather[i];
					
				}
			}
			i++;
			
		}
		
		//return weatherDAO.weahterFind();
		return result;
		
	}
	
	/*public static void main(String[] args) {
		WeatherManager wm = new WeatherManager();
		
		
		System.out.println(wm.getWeather());
		Calendar cal=Calendar.getInstance();
		Date datex=new Date(cal.get(Calendar.DATE));
		System.out.println(datex);
		
	}*/
}
