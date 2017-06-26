package com.sist.util;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	private String path;
	
//	@Autowired // 사진을 저장 할 경로를 얻는다
//	public FileManager(ServletContext ctx) {
//		path = ctx.getRealPath("/resources/");
//	}
	
	/**
	 * 저장 위치 : /resources/{tableName}/{연도}/변환된파일명
	 * 이름 변환 규칙 :
	 * System.currentTimeMillis()로 얻은 현재시각을 변환 + MD5로 변환한 hashcode + 확장자 없음
	 */
	private String reName(String file) {
		String newName = null;
		try {
			// 현재 날짜 시각
			DateFormat df = new SimpleDateFormat("yyyyMMddkkmmssSS");
			String currTime = df.format(System.currentTimeMillis());
			// MD5 hash
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(file.getBytes());
			StringBuffer sb = new StringBuffer(); 
			for(byte b : md.digest()) {
				// 2 vs 102.substring(1) -> 02
				sb.append(Integer.toHexString((b&0xff) + 0x100).substring(1));
			}
			newName = currTime +" "+ sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
		}
		
		return newName;
	}
	
	public String insertFile(MultipartFile file, String tableName)
			throws IllegalStateException, IOException {
		String fileName = null;
		if(!file.isEmpty()) {
			
		}
		return fileName;
	}

	public List<String> insertFile(List<MultipartFile> fileList, String folder)
			throws IllegalStateException, IOException {
		List<String> fileNames = new ArrayList();
		if (!fileList.isEmpty()) {
			for (MultipartFile mf : fileList) {
				String fileName = mf.getOriginalFilename();
				Long fileSize = mf.getSize();
				mf.transferTo(new File("c:\\upload\\" + fileName));
			}
		}
		return fileNames;
	}

	public int deleteFile(String fileName, String folder) {

		return 0;
	}

	public int deleteFile(List<String> fileNames, String folder) {
		int result = 0;
		if (fileNames.size() > 0) {
			for (String fn : fileNames) {
				new File("c:\\upload\\" + fn).delete();

			}
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println(new FileManager().reName("goodboycomm.jpg"));
	}

}
