package com.sid.onlonebanking.service;

public interface S3Service {
	public void downloadFile(String keyName, String downloadFilePath);
	public void uploadFile(String keyName, String uploadFilePath,String username);

}
