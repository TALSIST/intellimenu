package com.sist.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.RestaurantReplyVO;
import com.sist.vo.RestaurantVO;

import java.util.*;

import javax.servlet.http.HttpSession;
@Repository
public class ReplyDAO {
	@Autowired
    private ResreplyMapper rrMapper;


	// 댓글 목록
	public List<RestaurantReplyVO> list(int restaurant_id, int start, int end, HttpSession session) {
        return rrMapper.list(restaurant_id);
    }

    // 댓글 입력
	 public void insert(RestaurantReplyVO vo) {
		 rrMapper.insert(vo);
	  }
}
