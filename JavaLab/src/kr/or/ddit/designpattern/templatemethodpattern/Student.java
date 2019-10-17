package kr.or.ddit.designpattern.templatemethodpattern;

public abstract class Student { //추상메서드가 하나있으닌까 클래스도 추상클래스로 ! 단독으로 객체 생성 x 하고싶으면 상속받을클래스가 잇어야함 
	//재정의 못하게 ㅁ해야해 =>final
	//template method = 서블릿 service
	public final void lifecycle() {//작업의 순서 정의함. 서블릿으로 말하면 service임 =서블릿도 서비스가 제일 먼저만나닌까
		inchecking();
		work();
		outchecking();
	} 
	
	private void inchecking() {
		System.out.println("지문 인체크");
	}
	
	//hook method = 서블릿 doXXX
	protected abstract void work();  //자식이 상속할때까지 구현x =>protected 로 변환  
	
	private void outchecking() {
		System.out.println("지문 아웃체크");
	}
}
