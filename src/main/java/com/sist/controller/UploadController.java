package com.sist.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sist.util.FileManager;

@Controller
public class UploadController {
	
	@Autowired
	FileManager fm;
		
	//Get방식으로 페이지 메핑
    @RequestMapping(value="/upload/uploadAjax", method=RequestMethod.GET)
    public void uploadAjax(){
        // uploadAjax.jsp로 포워딩
    }

    //post로 요청받은 내용을 Ajax로 처리  //produces="text/plain;charset=utf-8" : 파일 한글처리
    //파일 업로드
    //public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
    @ResponseBody
    @RequestMapping(value="/upload/uploadAjax",method=RequestMethod.POST, produces="application/json;charset=utf-8" )
    public List<String> uploadAjax(MultipartHttpServletRequest request) throws Exception {
  
    	 System.out.println("작동");
    	 Iterator<String> itr =  request.getFileNames();
         MultipartFile mpf = null;
         List<String> list=new ArrayList<String>();
         StringBuffer sb=new StringBuffer();
         while(itr.hasNext()){
        	 mpf = request.getFile(itr.next()); 
        	 System.out.println(mpf.getOriginalFilename());
        	 String newName=fm.insertFile(mpf, "restaurant");
        	 list.add(newName);
        	 System.out.println(newName);
        	 //sb.append("'newName':'"+newName+"'");
         }
        		 
/*    	System.out.println("map="+map.get(0).getName());
    	//map.get(0).getName()
    	System.out.println("map="+map.size());
*/
    //파일 매니져로 서버에 파일 업로드하고 
    	//객체화된 파일이름과 HttpStatus 신호를  Ajax로 보내  success :function을 실행하게함
         //return new ResponseEntity<String>(fm.insertFile(file, "restaurant"), HttpStatus.OK);
    	return list;
    }
    
    //파일 삭제 
/*    @ResponseBody
    @RequestMapping(value = "/upload/deleteFile", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFile(String fileName) {
        // 파일메니져로서버에서 파일 삭제
    	fm.deleteFile(fileName, "restaurant");
        // 데이터와 http 상태 코드 전송해 View에서도 이미지를 지우도록 함
        return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }*/

}
