package com.sist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.restaurant.ReplyService;


@Controller
public class ReplyController {
    
    @Autowired
    ReplyService replyService;
    
    @RequestMapping("restaurant/res_detail")
    public String main(){
    	return "restaurant/res_detail";
    }
    
}
