package com.sist.analysis;

import java.io.File;
import java.io.FileWriter;
import java.net.URLEncoder;

import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class IngredientCrawler {
	
	public static void main(String[] args) {
		IngredientCrawler crw = new IngredientCrawler();
		for (int i=0; i<10; i++) {
			crw.searchIngredient("돼지고기 고추장찌개", i);
		}
	}
	
	public void searchIngredient(String title, int page) {
		try {
			Document docu = Jsoup.connect("https://www.google.co.kr/search?q="
							+ title+"&start="+page*10).get();
			
			Elements elms = docu.select("cite._Rm");
			
			File file = new File("./ingredient.txt");
			if (file.exists()) { file.delete(); }
			
			FileWriter fw = new FileWriter("./ingredient.txt",true);
			for (Element elm : elms) {
				String url = elm.text();
				if (url.startsWith("https://")) { continue; }
				Document txt = Jsoup.connect("http://"+url).get();
				fw.write(txt.toString());
			}
			fw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
