package kr.or.ddit.designpattern.builderpattern;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVO.MemberVOBuilder;
import kr.or.ddit.vo.TestVO;
import kr.or.ddit.vo.TestVO.*;
import kr.or.ddit.vo.TestVO.TestVOBuilder.*;

/**
 * 객체 생성과 관련된 패턴 
 * ex) Member 객체 : 기본형(),(아이디, 비번),(주민번호1, 주민번호2) 
 * 1. 생성자 패턴 : 점층적 생성자를 정의 하고 사용하는 구조 
 * 2. 자바빈 패턴 : 기본형 생성, setter 호출, mutable(가변) 객체에 사용 
 * 
 * 3. Builder 패턴 : immutable 객체의 정의, 다양한 사용 케이스 커버 
 *
 */
public class BuilderPatternDesc {
	public static void main(String[] args) {
		MemberVO member = new MemberVOBuilder()
							.mem_id("a001")
							.mem_pass("asdfasdf")
							.build();
		TestVO testVO = TestVO.builder()
						.mem_add1("")
						.mem_regno1("")
						.build(); //builder정체 모르게 함.
	}
}
