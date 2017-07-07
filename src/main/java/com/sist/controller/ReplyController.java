package com.sist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sist.restaurant.ReplyDAO;
import com.sist.util.FileManager;
import com.sist.util.PagingManager;
import com.sist.vo.RestaurantReplyVO;


@RestController
public class ReplyController {
    @Autowired
    ReplyDAO dao;
    
    // 댓글입력 (Rest방식으로 json전달하여 글쓰기)
    // @ResponseEntity : 데이터 + http status code
    // @ResponseBody : 객체를 json으로 (json - String)
    // @RequestBody : json을 객체로
    @RequestMapping(value="/reply/insertRest", method=RequestMethod.POST)
    public ResponseEntity<String> insertRest(@RequestBody RestaurantReplyVO vo, HttpSession session){
        ResponseEntity<String> entity = null;
        try {
        	//레스토랑에서 userid넘겨받는데 나중에 잘 연동안되면 session으로 받아야함
        	//String userId = (String) session.getAttribute("userId");
        	dao.insert(vo);
            entity = new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity; // 입력 처리 HTTP 상태 메시지 리턴
    }

    @RequestMapping("/reply/listJson")
    @ResponseBody // 리턴데이터를 json으로 변환(생략가능)
    public List<RestaurantReplyVO> listJson(@RequestParam int restaurant_id, PagingManager pm, HttpSession session){
        int totalRecord=dao.totalRecord(restaurant_id);
        Map<String, Integer> pageCal=pm.calcPage(totalRecord);
        int totalPage=dao.totalPage(restaurant_id);
		Map map=new HashMap();
		map.put("totalRecord", totalRecord);
		map.put("totalPage", totalPage);		
		map.put("start", pageCal.get("start"));
		map.put("end", pageCal.get("end"));
        
        
        int start=1;
        int end=100;
        List<RestaurantReplyVO> list = dao.list(restaurant_id, start, end, session);
        for(RestaurantReplyVO vo : list){
       	    String names =vo.getImg_new();
          	StringBuffer sb=new StringBuffer();
          	if(names!=null){//사진파일을 올렸을 경우에만 동작 
	          	String[] imgs=names.split(","); //이미지가 복수인 경우 , 를 기준으로 자름   	
	          	for(String str : imgs){ //이미지이름을 화면에 띄울수 있는 태그로 만들어 ajax에 보냄
	          		sb.append("<img src=\"../resources/restaurant/2017/"+str+"\"hspace=\"5\"");
	          		sb.append("style=\"width:100px;height:100px;cursor:pointer\" onclick=\"onClick(this)\" class=\"w3-hover-opacity\">");
	          	}
          	}else{
          		sb.append("<p></p>");
          	}
          	vo.setImg_new(sb.toString());	
        }  
        return list;	
    }
    
    @RequestMapping("/reply/report")
    public void report(@RequestParam int id){
    	System.out.println("신고된 아이디는="+id);
    	dao.report(id);
    }
}