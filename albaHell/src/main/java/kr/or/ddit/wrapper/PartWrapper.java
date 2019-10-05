package kr.or.ddit.wrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

public class PartWrapper {
	private Part origin;

	public PartWrapper(Part origin) {
		super();
		this.origin = origin;
	}
	
	public String getHeader(String name) {
		return origin.getHeader(name);
	}
	
	public String getContentType() {
		return origin.getContentType();
	}
	public InputStream getInputStream() throws IOException {
		return origin.getInputStream();
	}
	public long getSize() {
		return origin.getSize();
	}
	
	public byte[] getBytes() throws IOException{ //input들고와서 output으로 리턴하기 
	try( //memberinsert에서 가져옴 
		InputStream is =getInputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		){
		 IOUtils.copy(is,bos);
		 return bos.toByteArray();
		}
	}
	
	public String getFileName(){ //원본파일명 wrapper로 빼준거 
		String header = origin.getHeader("Content-Disposition");
		int firstIdx = header.indexOf("filename");
		int secondIdx = header.indexOf("=", firstIdx);
		String originalFilename = header.substring(secondIdx+1)
										.replace('"', ' ').trim(); //""사이의 파일명 들어옴
		return originalFilename;
	}
	
	public void delete(){
		
	}
}
