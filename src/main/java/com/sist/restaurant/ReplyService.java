package com.sist.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.vo.RestaurantVO;

import java.util.*;
//JSP (�슂泥�) => board_content.do 
//DispatcherServlet ==> DataBoardController
//�삤�씪�겢�뿉 �엳�뒗 �뜲�씠�꽣 ===> DataBoardController 
//Model ==> JSP(異쒕젰)

@Service
public class ReplyService {

	@Autowired
    private ResreplyMapper resreplyMapper;


	
}