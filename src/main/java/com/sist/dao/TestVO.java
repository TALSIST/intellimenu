package com.sist.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class TestVO {
	private List<MultipartFile> stepsFile;

	public List<MultipartFile> getStepsFile() {
		return stepsFile;
	}

	public void setStepsFile(List<MultipartFile> stepsFile) {
		this.stepsFile = stepsFile;
	}


}
