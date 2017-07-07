package com.sist.restaurant;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sist.util.FileManager;
import com.sist.vo.RestaurantVO;
import com.sist.vo.UsersVO;

@Service
public class RestaurantService {
	@Autowired
	private FileManager fileManager;
	@Autowired
	private RestaurantDAO restDAO;
	
	@Transactional
	public void restaurantInsert(RestaurantVO vo,MultipartFile mainFile,HttpSession session){

		vo.setTel(vo.getTel1()+"-"+vo.getTel2()+"-"+vo.getTel3());//번호 정리저장
		//admin?
		List<Integer> adminList=restDAO.getAdminID();
		int admin=0;
		UsersVO user=(UsersVO)session.getAttribute("user");
		for(int i:adminList){
			if(i==user.getId()){
				admin=i;
				break;
			}
		}
		if(admin!=0){
			String main_nuw=null;
			try {
				main_nuw = fileManager.insertFile(mainFile, "restaurant");
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vo.setId(admin);
			vo.setImg_new(main_nuw);	//파일 바꾼것
			vo.setImg_ori(mainFile.getOriginalFilename()); //파일원래이름 기억
			restDAO.restaurantInsert(vo);
		}//관리자일때만 실행
		
	}
}
