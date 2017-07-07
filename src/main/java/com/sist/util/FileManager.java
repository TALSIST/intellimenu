package com.sist.util;

import java.io.File;
import java.io.FileOutputStream;
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
	private String finalPath;
	@Autowired // 사진을 저장 할 경로를 얻는다
	public FileManager(ServletContext ctx) {
		path = ctx.getRealPath("/resources/");	
	}
	
	//upload 컨트롤러에서 사용
	public String getFinalPath() {
		return finalPath;
	}
	
	public void setFinalPath(String finalPath) {
		this.finalPath = finalPath;
	}
	
	//파일 확장자 반환
	public String getExtension(String fileName){
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}
	//파일 확장자 반환 오버로드
	public String getExtension(MultipartFile file){
		String oriName=file.getOriginalFilename();
		return oriName.substring(oriName.lastIndexOf(".")+1);
	}
	
	/**
	 * 저장 위치 : /resources/{tableName}/{연도}/변환된파일명
	 * 이름 변환 규칙 :
	 * System.currentTimeMillis()로 얻은 현재시각을 변환 + MD5로 변환한 hashcode + 확장자 없음
	 */
	private String reName(MultipartFile file) {
   		String newName = null;
		try {
			// 현재 날짜 시각
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			String currTime = df.format(System.currentTimeMillis());
			// MD5 hash
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(file.getBytes());
			StringBuffer sb = new StringBuffer(); 
			for(byte b : md.digest()) {
				// 2 vs 102.substring(1) -> 02
				sb.append(Integer.toHexString((b&0xff) + 0x100).substring(1));
			}
			newName = currTime +"."+sb.toString()+"."+getExtension(file);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newName;
	}
	
	/**
	 * 파일 저장 method overloading
	 */
	public String insertFile(MultipartFile file, String tableName)
			throws IllegalStateException, IOException {
		String fileName = "";
		System.out.println(finalPath);
		if(!file.isEmpty()) {
			fileName=save(file, tableName);
			//fileName = reName(file);
		} else {
			
			return fileName;
		}
		
		return fileName;
	}

	// 파일의 순번을 유지해야 한다 ( 1~5 번 중 2, 3번 항목은 파일이 없을 수 도 있음)
	public List<String> insertFile(List<MultipartFile> fileList, String tableName)
			throws IllegalStateException, IOException {
		List<String> fileNames = new ArrayList();
		if (!fileList.isEmpty()) {
			for (MultipartFile mf : fileList) {
				if (!mf.isEmpty()) {
					save(mf, tableName);
					fileNames.add(reName(mf));
				} else {
					fileNames.add("");
				}
			}
		}
		return fileNames;
	}
	
	private String save(MultipartFile file, String tableName) throws IllegalStateException, IOException {
		String oldName = file.getName();
		String newName = reName(file);
		// 디렉토리 생성 : /resources/{table_name}/{year}/
		String dirPath = path+tableName+File.separator+newName.substring(0,4);
		System.out.println(dirPath);
		File dirChk = new File(dirPath);
		if (!dirChk.exists()) {
			dirChk.mkdirs();
		}
		// Ajax를 통해 보여줄 InputSteam을 만들기 위해 파일 최종경로를 getter/setter로 공유함
		String finalPath=dirPath+File.separator;
		setFinalPath(finalPath);

		
		
		// 파일 저장 (tomcat Server)
		//file.transferTo(new File(finalPath+newName));
		FileOutputStream fos=null;
		  try {
		         //buffered이미지로 바꿔야함
		         byte fileData[]=file.getBytes();
		         fos=new FileOutputStream(finalPath+newName);
		         fos.write(fileData);
		      } catch (Exception e) {
		         e.getStackTrace();
		      }
		      // 파일 저장 (tomcat Server)
		      //file.transferTo(new File(finalPath+newName));
		//System.out.println(finalPath+newName);
		return newName;
	}

	/**
	 * 파일 삭제 method overloading
	 */
	public void deleteFile(String newFileName, String tableName) {
		remove(newFileName, tableName);
	}

	public void deleteFile(List<String> newFileNames, String tableName) {
		if (newFileNames.size() > 0) {
			for (String fn : newFileNames) {
				remove(fn, tableName);
			}
		}
	}
	
	private void remove(String newFileName, String tableName) {
		// 파일이름, table명으로 경로를 찾아 삭제한다
		//System.out.println("삭제할파일:"+path+tableName+File.separator+newFileName.substring(0,4) + File.separator + newFileName);
		new File(path+tableName+File.separator+newFileName.substring(0,4) + File.separator + newFileName).delete();
	}
	
}
