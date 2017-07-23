package com.sist.analysis;

import java.io.FileWriter;
import java.net.URLEncoder;

import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NaverBlogCrawler {
	
	public static void main(String[] args) {
		NaverBlogCrawler crw = new NaverBlogCrawler();
		for (int i=0; i<10; i++) {
			crw.searchNaverBlog("돼지고기 고추장찌개", i);
		}
	}
	
	public void searchNaverBlog(String title, int page) {
		try {
			Document docu = Jsoup.connect("https://section.blog.naver.com/sub/SearchBlog.nhn?type=post&option.keyword="
							+ URLEncoder.encode(title,"utf-8")
							+ "&term=&option.startDate=&option.endDate=&option.page.currentPage="+page+"&option.orderBy=sim").get();
			
			Elements elms = docu.select("ul.search_list li.add_img h5 a");
			
			FileWriter fw = new FileWriter("./ingredient.txt",true);
			for (Element elm : elms) {
				String url = elm.attr("href");
				Document txt = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(0).referrer("https://section.blog.naver.com").method(Method.GET).get();;
				fw.write(txt.toString());
			}
			fw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
