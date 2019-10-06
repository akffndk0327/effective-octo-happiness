package kr.or.ddit.alba.service;

import java.util.List;

import kr.or.ddit.alba.dao.AlbaDaoImpl;
import kr.or.ddit.alba.dao.IAlbaDao;
import kr.or.ddit.alba.vo.AlbaVO;
import kr.or.ddit.alba.vo.Lic_albaVO;
import kr.or.ddit.alba.vo.LicenseVO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.exception.UserNotFoundException;

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
	public AlbaVO selctAlba(AlbaVO alba) {
		AlbaVO al_id = dao.selctAlba(alba);
		if(al_id ==null)
			throw new UserNotFoundException(al_id+"알바 없음 ");
		return al_id;
	}

	@Override
	public ServiceResult updateAlba(AlbaVO alba) {
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
	public String deleteAlba(String alba) {
		int cnt = dao.deleteAlba(alba);

		if(cnt>0) 
			return "OK";
		else
			return "FAILED";
	}

	@Override
	public Lic_albaVO selectLicAlba(Lic_albaVO licAlba) {
		return dao.selectLicAlba(licAlba);
	}

	@Override
	public String updateLicAlba(Lic_albaVO licAlba) {
		int cnt = dao.updateLicAlba(licAlba);

		if(cnt>0) 
			return "OK";
		else
			return "FAILED";
	}

	
	@Override
	public void deleteLicAlba(String al_id) {
		dao.deleteLicAlba(al_id);

	}

	@Override
	public List<LicenseVO> selectLic() {
		return dao.selectLic();
	}

	@Override
	public String insertLicAlba(Lic_albaVO licAlba) {
		int cnt = dao.insertLicAlba(licAlba);

		if(cnt>0) 
			return "OK";
		else
			return "FAILED";
	}

	@Override
	public String deleteLic(Lic_albaVO licAlba) {
		int cnt = dao.deleteLic(licAlba);

		if(cnt>0) 
			return "OK";
		else
			return "FAILED";
	}

}
