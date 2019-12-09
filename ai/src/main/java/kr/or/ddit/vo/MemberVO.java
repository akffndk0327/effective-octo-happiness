package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 허민지
 * @since 2019. 11. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자            수정내용
 * --------     	--------    ----------------------
 * 2019. 11. 5.      허민지       	최초작성
 * 2019. 11. 13.     허민지     	allList 추가
 * 2019. 11. 13.     허민지     	memAllList추가
 * 2019. 11. 17. 	  허민지		authorities 추가
 * Copyright (c) 2019 by DDIT All right reserved
 *      </pre>
 */
@Data
@ToString(of = { "memId", "memName","NoteCount" })
@EqualsAndHashCode(of= {"memId","memName","memPass"})
public class MemberVO implements UserDetails, Serializable {

	public MemberVO() {
		super();
	}

	public MemberVO(String memId) {
		super();
		this.memId = memId;
	}
	
	public MemberVO(String memId, String memPass) {
		super();
		this.memId = memId;
		this.memPass = memPass;
	}

	private String memName;
	private String memTel;
	private String memAddr1;
	private String memAddr2;
	private String memZip;
	private String memMail;
	private String memDelete;
	private String memGender;
	private String memAge;
	private String memId;
//	private List<GrantedAuthority> authorities;

	// join
	private String memPass;
	private String authorId;
	private String adminName;
	private int rnum;

	private List<MemAllergyVO> memAllList; // 회원이 갖고 있는 알러지 정보
	private List<MonthlyVO> monthlyList; // 회원이 작성한 식단공유 게시글
	private List<RecipeBoardVO> recipeBoardList; // 회원이 작성한 레시피 게시글
	private int noteCount; // 회원이 안읽은 쪽지 걋수
	private CompanyVO company;
//	private List<ProdVO> prodList;	  //상품
	private List<Order2VO> orderList; //주문내역
	
	
	
	
	
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(authorId));
	}

	@Override
	public String getPassword() {
		return memPass;
	}

	@Override
	public String getUsername() {
		return memId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !"Y".equals(memDelete);
	}

	@Override
	public boolean isAccountNonLocked() {
		return !"Y".equals(memDelete);
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !"Y".equals(memDelete);
	}

	@Override
	public boolean isEnabled() {
		return !"Y".equals(memDelete);
	}


}
