package kr.or.ddit.servlet03;

import java.io.File;

import javax.servlet.ServletContext;
/**
 * Object (Adapter) / Wrapper pattern
 * 
 */
public class FileWrapper { //감싸고 있는 객체
	public FileWrapper(File resource , ServletContext application) {
		this(resource, application, false);
	}
	
	public FileWrapper(File resource , ServletContext application, boolean wildcard) {
		super();
		this.resource = resource;
		this.application = application; //왜?
		contextRealPath = application.getRealPath("");
		displayName = resource.getName();
		if(wildcard) displayName="..";
		String absolutePath = resource.getAbsolutePath();
		id = absolutePath.substring(contextRealPath.length()-1);
		id= id.replace(File.separatorChar, '/');
	}
	
	
	private File resource;	//감싸여있는 객체 
	private ServletContext application;
	private String contextRealPath;
	private String displayName;  //li 태그의 innerText
	//서버사이트 전체 ..
	private String id ; //li태그의 id(서버사이드경로 )
	
	public String getDisplayName() {
		return displayName;
	}
	public String getId() {
		return id;
	}
	public File getResource() {
		return resource;
	}
	public boolean isDirectory() {
		return resource.isDirectory();
	}
	public boolean isFile() {
		return resource.isFile();
	}
	
}
