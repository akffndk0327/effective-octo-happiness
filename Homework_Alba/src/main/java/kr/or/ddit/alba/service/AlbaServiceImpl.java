package kr.or.ddit.alba.service;

import java.util.List;

import kr.or.ddit.alba.dao.AlbaDaoImpl;
import kr.or.ddit.alba.dao.IAlbaDao;
import kr.or.ddit.alba.vo.AlbaVO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.CommonException;

public class AlbaServiceImpl implements IAlbaService {
	public IAlbaDao dao = new AlbaDaoImpl();
	private static AlbaServiceImpl service;
	public static AlbaServiceImpl getInstance() {
		if(service ==null) {
			service =new AlbaServiceImpl();
		}
		return service;
	}
	
	@Override
	public List<AlbaVO> selectAlbaList() {
		List<AlbaVO> list = null;
		list = dao.selectAlbaList();
		return list;
	}

	@Override
	public AlbaVO selctAlba(String al_id) {
		AlbaVO alba = dao.selctAlba(al_id);
		if(alba ==null)
			throw new CommonException(al_id+"알바 없음 ");
		return alba;
	}

	@Override
	public ServiceResult updateAlba(AlbaVO alba) {
		selctAlba(alba.getAL_ID());
		ServiceResult result = null;
		int cnt = dao.updateAlba(alba);
		if(cnt>0) result = ServiceResult.OK;
		else result = ServiceResult.FAILED;
		return result;
	}

	@Override
	public ServiceResult insertAlba(AlbaVO alba) {
		ServiceResult result = null;
		int cnt = dao.intsertAlba(alba);
		if(cnt>0)result = ServiceResult.OK;
		else result = ServiceResult.FAILED;
		return result;
	}

	@Override
	public ServiceResult deleteAlba(AlbaVO alba) {
		ServiceResult result = null;
		int cnt = dao.deleteAlba(alba);
		if(cnt>0)result = ServiceResult.OK;
		else result = ServiceResult.FAILED;
		return result;
	}

}
