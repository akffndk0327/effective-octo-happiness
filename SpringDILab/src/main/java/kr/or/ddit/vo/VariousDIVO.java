package kr.or.ddit.vo;

import java.io.File;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//1014 주입하려면 getter, 생성자 만들어야해
@AllArgsConstructor //생성자 주입 사용가능 constructor injection
@NoArgsConstructor 
@Data				//setter injection
public class VariousDIVO {
	//주입하려면 setter
	private int num;
	private char ch;
	private boolean bool;
	private String str;
	
	private File file;
	
}
