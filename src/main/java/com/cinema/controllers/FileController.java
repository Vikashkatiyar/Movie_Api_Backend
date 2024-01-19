package com.cinema.controllers;


import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cinema.service.FileService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/file")
public class FileController {

	private final FileService fileService;

	public FileController(FileService fileService) {
		this.fileService = fileService;
	}

	@Value("${project.poster}")
	private String path;

	@PostMapping
	public ResponseEntity<String> uploadFileHandler(@RequestPart MultipartFile file) throws IOException {
		String uploadedFileName = fileService.uploadFile(path, file);
		return ResponseEntity.ok("File uploaded successfully : " + uploadedFileName);
	}
	
	@GetMapping(value = "/{fileName}")
	public void serveFileHandler(@PathVariable String fileName, HttpServletResponse response) throws IOException {
		InputStream resourceFile = fileService.getResourceFile(path, fileName);
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		StreamUtils.copy(resourceFile, response.getOutputStream());
	}
	

}
