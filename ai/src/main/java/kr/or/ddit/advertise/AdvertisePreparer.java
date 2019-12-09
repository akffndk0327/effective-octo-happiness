package kr.or.ddit.advertise;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.advertise.dao.IAdvertiseDAO;
import kr.or.ddit.common.annotations.Preparer;
import kr.or.ddit.vo.AdMenuVO;
import kr.or.ddit.vo.AdvertiseVO;

@Preparer
public class AdvertisePreparer implements ViewPreparer {
	@Inject
	IAdvertiseDAO adDAO;

	@Override
	public void execute(Request req, AttributeContext ctx) {
	Map<String, Object> requestScope = 
			req.getContext(Request.REQUEST_SCOPE);
		AdMenuVO menu1 = new AdMenuVO("생활용품 목록","/dailysupply/dsList.do");
		List<AdMenuVO> menuList = Arrays.asList(menu1);
//		requestScope.put(menuList);
		requestScope.put("menu1",menu1);
		
		adDAO.selectAd(new AdvertiseVO());
	}

}
