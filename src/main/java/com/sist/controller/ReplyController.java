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
        System.out.println("vo="+vo);
        System.out.println("vo..getId()="+vo.getId());
        System.out.println("vo..getRestaurantid()="+vo.getRestaurantid());
        System.out.println("vo.getReply()="+vo.getReply());
        System.out.println("vo..getScore()="+vo.getScore());
        try {
        	String userId = (String) session.getAttribute("userId");

        	dao.insert(vo);
            // 댓글입력이 성공하면 성공메시지 저장
            entity = new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // 댓글입력이 실패하면 실패메시지 저장
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        // 입력 처리 HTTP 상태 메시지 리턴
        return entity;
    }
    

  // 댓글 목록(@RestController Json방식으로 처리 : 데이터를 리턴)
    @RequestMapping("reply/listJson")
    @ResponseBody // 리턴데이터를 json으로 변환(생략가능)
    public List<RestaurantReplyVO> listJson(@RequestParam int restaurantid, @RequestParam(defaultValue="1") int curPage, HttpSession session){
        int count = 10;
        //BoardPager pager = new BoardPager(count, curPage);
        //int start = pager.getPageBegin();
        //int end = pager.getPageEnd();
        int start=1;
        int end=100;
        List<RestaurantReplyVO> list = dao.list(restaurantid, start, end, session);
        return list;
    }
}