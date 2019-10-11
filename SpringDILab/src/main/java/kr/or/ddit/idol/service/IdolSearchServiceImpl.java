package kr.or.ddit.idol.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import kr.or.ddit.idol.dao.IIdolSearchDAO;
import kr.or.ddit.idol.dao.IdolDAOFactory;
import kr.or.ddit.idol.dao.IdolSearchDAO_Mysql;
import kr.or.ddit.idol.dao.IdolSearchDAO_Oracle;

// 인터페이스로 결합력 1 낮춤 
public class IdolSearchServiceImpl implements IIdolSearchService {
//	 1. 전통적인 의존관계 형성 :  new 인스턴스직접 생성 -> 결합력 최상.
//	IIdolSerachDAO dao  = new IdolSearchDAO_Oracle(); //2
//	IIdolSerachDAO dao = new IdolSearchDAO_Mysql(); //3. 의존관계 변함 
//	2. Factory Object[Method] Pattern : factory 와  생성객체 사이의 결합력 잔존.
//	IIdolSearchDAO dao = IdolDAOFactory.getIdolDAO(); //의존성을 facotry가 가져감 ... ?????????????????
//	3. DI구조를 기반으로 한 strategy pattern  : 전략 주입자 필요(결합력 전체에 대한 책임)setter injection, Construct injection
//	전략패턴은 주입자가 필요해 !  -> 뷰에서 오류남 ->다오 만들고 서비스에 dao 넣기  
//	if> 전략주입자가 application밖에있으면,... ????
//	4. 전략 패쳔을 확장한 DI container 구조 view가 모든 객체 생성 -> 모든 결합력 발생 -> 뷰에서 뺀다면,,,?! 
//	어떻게 어떤 컨테이너 쓸거야? 정해야함 
	
	private IIdolSearchDAO dao ;  //내가 안만들고 받아올수있게 만든단면... ?  
	public IdolSearchServiceImpl(){
			super();
			System.out.printf("%s 객체생성, 기본생성자 사용 \n", getClass().getSimpleName());
		
	}
	
	public IdolSearchServiceImpl(IIdolSearchDAO dao) { //이거만 잇으닌까 공장도 없고 결합력도 안생김 ~~ ^_^ 
														//기본생성자 없으면 주입밖에 못함
		super();
		this.dao = dao;
		System.out.printf("%s 객체생성, dao 주입받아 사용 \n", getClass().getSimpleName());
	}

	public void setDao(IIdolSearchDAO dao) {
		this.dao = dao;
	} //setter만들어놓고 안쓰면 터짐... 
	
	@Override
	public String readIdol(String code) {
		String[] idol =  dao.selectIdol(code);
		String infomation = Arrays.toString(idol)+new Date(); // 배열을 문자열로 만듬.
		return infomation;
	}

	@Override
	public List<String> readIdols() {
		List<String[]> list =  dao.selectIdolList();
		List<String> infomation = new ArrayList<String>();
		for(String[] tmp :list) {
			infomation.add(Arrays.toString(tmp)); //tmp를 문자열로 만듬 
		}
		return infomation;
	}

}
