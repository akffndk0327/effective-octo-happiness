package kr.or.ddit.enums;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public enum CommandType {
	COPY((src, targetFolder)->{
		File target = new File(targetFolder,src.getName());
		FileUtils.copyFile(src, target);
	}), 
	
	MOVE((src, targetFolder)->{
		File target = new File(targetFolder,src.getName());
		FileUtils.moveFile(src,target);
	}), 
	DELETE((src, targetFolder)->{
		FileUtils.forceDelete(src);
	});
	
	public static interface CommandProcessor{
		public void process(File src, File targetFolder ) throws IOException;//함수적 인터페이스. 람다식사용가능 
		
	}
	private CommandProcessor processor;
	private CommandType(CommandProcessor processor) { //이건 숨겨. 왜 굳이 만든...?
		this.processor = processor;
	}
	public void commandProcess(File src, File targetFolder) throws IOException{ //사용자가 쓰는ㄱ ㅓtemplate메서드...
		processor.process(src, targetFolder);
	}
	
}
