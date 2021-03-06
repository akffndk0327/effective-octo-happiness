package kr.or.ddit.advertise.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.tiles.request.collection.AddableParameterMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.advertise.dao.IAdAttatchDAO;
import kr.or.ddit.advertise.dao.IAdvertiseDAO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.vo.AdattatchVO;
import kr.or.ddit.vo.AdhitVO;
import kr.or.ddit.vo.AdvertiseVO;
import kr.or.ddit.vo.PagingInfoVO;

@Service
public class AdvertiseServiceImpl implements IAdvertiseService {
	@Inject
	IAdvertiseDAO adDAO;

	@Inject
	IAdAttatchDAO adattDAO;

	@Inject
	WebApplicationContext container;
	ServletContext application;

	@Value("#{appInfo.imgFolderRealPath}")
	String saveFolderURL;
	File saveFolder;

	@PostConstruct
	public void init() {
		application = container.getServletContext();
		saveFolder = new File(saveFolderURL);
		if (!saveFolder.exists())
			saveFolder.mkdirs();
	}

	private int deleteAttatch(AdvertiseVO advertise) {
		String delAtts = advertise.getDelAdAttaches();
		int cnt = 0;
		if (delAtts != null) {
			AdattatchVO attatch = adattDAO.selectAttatch(delAtts);
			FileUtils.deleteQuietly(new File(saveFolder, attatch.getAdattSavename()));
			cnt += adattDAO.deleteAttatches(advertise); // attatchDAO 메서드
		}
		return cnt;
	}

	@Override
	public int retrieveAdCount(PagingInfoVO<AdvertiseVO> pagingVO) {
		return adDAO.selectAdCount(pagingVO);
	}

	@Override
	public List<AdvertiseVO> retrieveAdList(PagingInfoVO<AdvertiseVO> pagingVO) {
		return adDAO.selectadAdList(pagingVO);
	}

	@Override
	public AdvertiseVO retrieveAd(AdvertiseVO advertise) {
		AdvertiseVO saveAd = adDAO.selectAd(advertise);
		if (saveAd == null)
			throw new CommonException(saveAd.getAdId() + ", 해당 글이 없음.");
		return saveAd;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public ServiceResult insertAd(AdvertiseVO advertise) {
		int cnt = adDAO.insertAd(advertise);
		AdhitVO adhit = new AdhitVO();
		adhit.setAdId(advertise.getAdId());
		adhit.setAdpoId(advertise.getAdposition().getAdpoId());
		adhit.setResId(advertise.getResource2().getResId());
		cnt += adDAO.insertposition(adhit);
		ServiceResult result = null;
		if (cnt > 0)
			result = ServiceResult.OK;
		else
			result = ServiceResult.FAILED;
		return result;
	}

	// 회원이 광고 수정
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public ServiceResult modifyAd(AdvertiseVO advertise) {
		ServiceResult result = null;
		int cnt = adDAO.updateAd(advertise);
		cnt += deleteAttatch(advertise); // 수정할때 첨부파일 삭제하기
		// cnt += adDAO.updateposition(advertise); //위치 수정할 때
		if (cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public ServiceResult removeAd(AdvertiseVO advertise) {
		AdvertiseVO saveAd = retrieveAd(advertise);
		ServiceResult result = null;
		if (saveAd.getMemId().equals(advertise.getMemId())) {
			List<AdattatchVO> attatchList = saveAd.getAdattatchList();
			int cnt = 0;
			if (attatchList != null && attatchList.size() > 0) {
				// meta delete
				cnt += adattDAO.deleteAttatches(advertise);
			}
			// board delete
		}
		return result;
	}

	// adhit count +
	@Override
	public ServiceResult updateAdHit(int adId) {
		int cnt = adDAO.updateAdHit(adId);
		ServiceResult result = null;
		if (cnt > 0)
			result = ServiceResult.OK;
		else
			result = ServiceResult.FAILED;
		return result;
	}

	@Override
	public AdattatchVO downloadAttatch(String what) {
		AdattatchVO attatch = adattDAO.selectAttatch(what);
		if (attatch == null)
			throw new CommonException(what + "파일없음");
		return attatch;
	}

	// ==============관리자 ====================

	@Override
	public int retrieveAdminCount(PagingInfoVO<AdvertiseVO> pagingVO) {
		return adDAO.selectAdminCount(pagingVO);
	}

	@Override
	public List<AdvertiseVO> retrieveadAdminList(PagingInfoVO<AdvertiseVO> pagingVO) {
		return adDAO.selectadAdminList(pagingVO);
	}

	@Override
	public ServiceResult updateApprove(AdvertiseVO advertise) {
		int cnt = adDAO.updateApprove(advertise);
		ServiceResult result = null;
		if (cnt > 0)
			result = ServiceResult.OK;
		else
			result = ServiceResult.FAILED;
		return result;
	}

	@Override
	public ServiceResult updateposition(AdvertiseVO adhit) {
		int cnt = adDAO.updateposition(adhit);
		ServiceResult result = null;
		if (cnt > 0)
			result = ServiceResult.OK;
		else
			result = ServiceResult.FAILED;
		return result;
	}

	// 광고 이미지 append
	@Override
	public ServiceResult adimgAppend(AdvertiseVO advertise) {

		return null;
	}

	@Override
	public String processAttatch(AdvertiseVO advertise) {
		List<AdattatchVO> adattatchList = advertise.getAdattatchList();
		int cnt = 0;
		if (adattatchList != null && adattatchList.size() > 0) {
			cnt = adattDAO.insertAttatches(advertise);
			if (cnt > 0) {
				try {
					AdattatchVO adattatch = adattatchList.get(0);
					adattatch.saveFile(saveFolder);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return adattatchList.get(0).getAdattSavename();
	}

	//광고이미지승인후 띄우기
	@Override
	public AdvertiseVO retrieveAdImage() {
		return adDAO.selectAdImage();
	}


}
