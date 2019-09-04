package kr.or.ddit.servlet01;

@FunctionalInterface
public interface Operator {
	public int operate(int left, int right);
	//functional interface 
	//인터페이스에 메소드 1개면 동일하게 생가함 .
}
