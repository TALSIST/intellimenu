package com.sist.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

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
    @ResponseBody
    @RequestMapping(value="/upload/uploadAjax", method=RequestMethod.POST, produces="text/plain;charset=utf-8")
    public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
    	//파일 매니져로 서버에 파일 업로드하고 
    	//객체화된 파일이름과 HttpStatus 신호를  Ajax로 보내  success :function을 실행하게함
         return new ResponseEntity<String>(fm.insertFile(file, "restaurant"), HttpStatus.OK);
    }
    
    //파일 썸네일 생성
    @ResponseBody 
    @RequestMapping("/upload/displayFile")
    public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
    	InputStream is=null;
    	ResponseEntity<byte[]> entity=null;	
    	try {
    		//is=new FileInputStream("resources/restaurant/2017/"+fileName);
    	 	is=new FileInputStream(fm.getFinalPath()+fileName);
		} catch (Exception e) {
			e.printStackTrace();
			is.close();
		} 
    	//썸네일용 이미지만들기 위해 방금 톰캣서버에 올라간 파일을 읽는 스트림을 만들어 Ajax에 전달
    	return new ResponseEntity<byte[]>(IOUtils.toByteArray(is), HttpStatus.OK);

    }
    
    //파일 삭제 
    @ResponseBody
    @RequestMapping(value = "/upload/deleteFile", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFile(String fileName) {
        // 파일메니져로서버에서 파일 삭제
    	fm.deleteFile(fileName, "restaurant");
        // 데이터와 http 상태 코드 전송해 View에서도 이미지를 지우도록 함
        return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }

}
