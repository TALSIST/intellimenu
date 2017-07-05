package com.sist.controller;

import java.util.List;

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
import com.sist.vo.RestaurantReplyVO;


@RestController
public class ReplyController {
    
    @Autowired
    ReplyDAO dao;
    
    @Autowired
    FileManager fm;
    
    // 댓글입력 (Rest방식으로 json전달하여 글쓰기)
    // @ResponseEntity : 데이터 + http status code
    // @ResponseBody : 객체를 json으로 (json - String)
    // @RequestBody : json을 객체로
    @RequestMapping(value="/reply/insertRest", method=RequestMethod.POST)
    public ResponseEntity<String> insertRest(@RequestBody RestaurantReplyVO vo, HttpSession session){
        ResponseEntity<String> entity = null;
/*    System.out.println("vo="+vo);
        System.out.println("vo.getId()="+vo.getId());
        System.out.println("vo.getRestaurantid()="+vo.getRestaurant_id());
        System.out.println("vo.getReply()="+vo.getReply());
        System.out.println("vo.getScore()="+vo.getScore());
        System.out.println("vo.getImgOri()="+vo.getImg_ori());
        System.out.println("vo.getImgNew()="+vo.getImg_new());*/
        try {
        	//레스토랑에서 userid넘겨받는데 나중에 잘 연동안되면 session으로 받아야함
        	//String userId = (String) session.getAttribute("userId");
        	dao.insert(vo);
        	//파일저장
            // 댓글입력이 성공하면 성공메시지 저장
            entity = new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        // 입력 처리 HTTP 상태 메시지 리턴
        return entity;
    }

    @RequestMapping("/reply/listJson")
    @ResponseBody // 리턴데이터를 json으로 변환(생략가능)
    public List<RestaurantReplyVO> listJson(@RequestParam int restaurant_id, @RequestParam(defaultValue="1") int curPage, HttpSession session){
        int count = 10;
        //BoardPager pager = new BoardPager(count, curPage);
        //int start = pager.getPageBegin();
        //int end = pager.getPageEnd();
        int start=1;
        int end=100;
        List<RestaurantReplyVO> list = dao.list(restaurant_id, start, end, session);
        for(RestaurantReplyVO vo : list){
         //아무리 시도해도 select * from restaurant_reply 결과가 담기는 
         // vo에  img_new가 null 값으로 구해와서 ㅠㅠㅠ img_new만 구하는 셀렉트문을 따로 만들어 구해옴 ;; 뭐가 문제인지 모르겠음         	
        	System.out.println("변경전 이미지이름:"+vo.getImg_new());
    	    String names =vo.getImg_new();
          	StringBuffer sb=new StringBuffer();
          	if(names!=null){//사진파일을 올렸을 경우에만 동작 
	          	String[] imgs=names.split(","); //이미지가 복수인 경우 , 를 기준으로 자름   	
	          	for(String str : imgs){ //이미지이름을 화면에 띄울수 있는 태그로 만들어 ajax에 보냄
	          		//src=\"/resources/restaurant/2017/"
	          		//sb.append("<img src=\""+fm.getFinalPath()+str+"\" width=100px>");
	          		sb.append("<img src=\"../resources/restaurant/2017/"+str+"\" width=100px hspace='5'>");
	          	}
          	}else{
          		sb.append("<p></p>");
          	}
          	vo.setImg_new(sb.toString());
          	System.out.println("변경후 이미지이름:"+vo.getImg_new());
        }  
        return list;	
    }
    
    @RequestMapping("/reply/report")
    public void report(@RequestParam int id){
    	System.out.println("신고된 아이디는="+id);
    	dao.report(id);
    }
}