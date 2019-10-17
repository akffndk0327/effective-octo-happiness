package kr.or.ddit.member;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.common.annotations.Preparer;
import kr.or.ddit.vo.MenuVO;

@Preparer
public class MemberViewPreparer implements ViewPreparer {
	//injection 하려면 빈 등록해야해 
	//웹 종속 때문에 하위에 등록해야애 근데 component는 상위에잇는ㄷ...? => 그럼 우리만ㅇ ㅢ 어노테이션을 만들어보자 !!
	@Override
	public void execute(Request req, AttributeContext ctx) {
		MenuVO menu1 = new MenuVO("회원 목록","/member/memberList.do");
		MenuVO menu2 = new MenuVO("신규가입","/member/memberInsert.do");
		List<MenuVO> menuList =  Arrays.asList(menu1,menu2);
		Map<String, Object> requestScope = req.getContext(Request.REQUEST_SCOPE);
		requestScope.put("menuList", menuList);
	} //뷰보다 앞서서 준비한다 

}
