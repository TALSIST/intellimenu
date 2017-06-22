package com.sist.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	private String path;
	
	@Autowired
	public FileManager(ServletContext ctx) {
		path = ctx.getRealPath("/img/");
	}
	
	private String reName(String oriName) {
		
		return "";
	}
	
	public String insertFile(MultipartFile file, String folder)
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

}
