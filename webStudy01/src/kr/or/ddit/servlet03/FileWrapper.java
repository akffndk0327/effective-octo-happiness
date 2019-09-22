package kr.or.ddit.servlet03;

import java.io.File;

import javax.servlet.ServletContext;
/**
 * Object (Adapter) :이미 존재하는 객체의 인터페이스를 변경. ( 주로 포함을 사용해서 구현한다. )
	/ Wrapper pattern
 * 한 클래스의 인터페이스를 클라이언트에서 사용하고자하는 다른 인터페이스로 변환한다.
 *어댑터를 이용하면 인터페이스 호환성 문제 때문에 같이 쓸 수 없는 클래스들을 연결해서 쓸 수 있다.
 *  클라이언트 -> request() -> 어댑터 - translatedRequest() -> 어댑티.
 * 클라이언트는 타겟 인터페이스에 맞게 구현, 어댑터는 타겟 인터페이스를 구현하며, 어댑티 인스턴스가 들어있음.
 1. 클라이언트에서 타겟 인터페이스를 사용하여 메소드를 호출함으로써 어댑터에 요청을 한다.
 2. 어댑터에서는 어댑티 인터페이스를 사용하여 그 요청을 어댑티 에 대한 하나 이상의 메소드를 호출로 변환한다.
 3. 클라이언트에서는 호출 결과를 받긴 하지만 중간에 어댑터가 껴 있는지는 전혀 알지 못한다.
 * 
 */
public class FileWrapper { //파일(File resource)을 받아서 감싸고 있는 객체
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
		file = resource.isFile();
		directory = resource.isDirectory();
	}
	
	
	private File resource;	//감싸여있는 객체 
	private ServletContext application;
	private String contextRealPath;
	private String displayName;  //li 태그의 innerText
	//서버사이트 전체 ..
	private String id ; //li태그의 id(서버사이드경로 )
	
	private boolean file;
	private boolean directory;
	
	public String getDisplayName() {
		return displayName;
	}
	public String getId() {
		return id;
	}
	public File getResource() {
		return resource;
	}

	public ServletContext getApplication() {
		return application;
	}

	public void setApplication(ServletContext application) {
		this.application = application;
	}

		
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isFile() {
		return file;
	}

	public void setFile(boolean file) {
		this.file = file;
	}

	public boolean isDirectory() {
		return directory;
	}

	public void setDirectory(boolean directory) {
		this.directory = directory;
	}
	
	
}
