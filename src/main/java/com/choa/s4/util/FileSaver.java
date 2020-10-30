package com.choa.s4.util;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	
	private MultipartFile photo;
	private File file;
	private String name;
	private Calendar ca;
	private long time;
	
	//MultipartFile transferTo
	public String saveTransfer(File dest, MultipartFile multipartFile) throws Exception{
		if(!dest.exists()) {
			dest.mkdirs();
		}
		//이름
		//2. Unique 한 이름 생성하는 객체
		String fileName = UUID.randomUUID().toString();
		fileName = fileName+"_"+ multipartFile.getOriginalFilename();
		dest = new File(dest, fileName);
		multipartFile.transferTo(dest);
		

		return fileName;
	}
	
	
	//FilecopyUtil.copy
	public String saveCopy(File dest, MultipartFile multipartFile) throws Exception{
		if(!dest.exists()) {
			dest.mkdirs();
		}
		//이름
		//2. Unique 한 이름 생성하는 객체
		String fileName = UUID.randomUUID().toString();
		fileName = fileName+"_"+ multipartFile.getOriginalFilename();
		dest = new File(dest, fileName);
		FileCopyUtils.copy(multipartFile.getBytes(), dest);
		
		return fileName;
		
	}
	
	public void save2(String path) throws Exception{
		FileSaver fileSaver = new FileSaver();
		File file2 = new File(path);
		if(!file2.exists()) {
			file2.mkdirs();
		}
		File file = new File(path);
		System.out.println(file.exists());
		//이름
		//1 . 시간
		ca = Calendar.getInstance();
		time = ca.getTimeInMillis();
		name= photo.getOriginalFilename();
		name = time + "_" + name;
		System.out.println(name);
	}
	

	
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Calendar getCa() {
		return ca;
	}
	public void setCa(Calendar ca) {
		this.ca = ca;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	

	
	
}
