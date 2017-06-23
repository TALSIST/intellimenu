package com.sist.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class TestVO {
	private List<MultipartFile> upload;

	public List<MultipartFile> getUpload() {
		return upload;
	}

	public void setUpload(List<MultipartFile> upload) {
		this.upload = upload;
	}
}
