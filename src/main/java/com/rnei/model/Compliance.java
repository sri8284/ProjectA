package com.rnei.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Compliance {
	private List<MultipartFile> uploadFiles;
	 
    public List<MultipartFile> getFiles() {
        return uploadFiles;
    }
 
    public void setFiles(List<MultipartFile> files) {
        this.uploadFiles = files;
    }
}