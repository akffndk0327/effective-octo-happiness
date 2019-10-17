package kr.or.ddit.board;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.common.annotations.Preparer;
import kr.or.ddit.vo.MenuVO;
@Preparer
public class BoardViewPreparer implements ViewPreparer {

	@Override
	public void execute(Request req, AttributeContext ctx) {
		MenuVO menu1 = new MenuVO("게시판 목록","/board/boardList.do");
		MenuVO menu2 = new MenuVO("글작성","/board/boardInsert.do");
		List<MenuVO> menuList =  Arrays.asList(menu1,menu2);
		Map<String, Object> requestScope = req.getContext(Request.REQUEST_SCOPE);
		requestScope.put("menuList", menuList);
		
	}

}
