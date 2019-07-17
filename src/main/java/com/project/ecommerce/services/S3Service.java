package com.project.ecommerce.services;

import java.io.InputStream;
import java.net.URI;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface S3Service {
	
	URI uploadFile(MultipartFile multipartFile);
	
	URI uploadFile(InputStream is, String fileName, String contentType);
	
}
