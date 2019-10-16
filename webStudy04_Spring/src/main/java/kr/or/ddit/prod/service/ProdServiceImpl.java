package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

@Service("prodService")
public class ProdServiceImpl implements IProdService {
	@Inject
	IProdDAO dao;
	@Inject //자기꺼 주이 ㅂ가능함
	WebApplicationContext container;
	ServletContext application;
	String saveFolderURL = "/prodImages"; // 여기에 이미지 하나 저장됨 .!
	File saveFolder;
	
	@PostConstruct
	public void init() { //injection 되고 마지막에 실행됨
		application= container.getServletContext();
		String saveFolderPath = application.getRealPath(saveFolderURL);
		saveFolder = new File(saveFolderPath);
		if (!saveFolder.exists()) saveFolder.mkdirs();
	}

	
	@Override
   public ServiceResult createProd(ProdVO prod) {
      ServiceResult result = null;
      
      int cnt = dao.insertProd(prod);
      //1016
      if(cnt > 0) { 
    	  try {
    	  prod.transfer(saveFolder);
    	  }catch (IllegalStateException|IOException e) {
    		  throw new RuntimeException(e);
    	  }
    	  result = ServiceResult.OK;
//		  if(partWrapper != null) {
			// 1. 저장위치
		
			// 2.저장명 원본파일명 쓰지마0
//			String savename = UUID.randomUUID().toString(); 이미 컨트롤러에 슴 
//			try (InputStream is = partWrapper.getInputStream();) {
//				FileUtils.copyInputStreamToFile(is, new File(saveFolder, savename)); // 여기서 복사 떠짐
//			} // 저장명이라는 메타데이터 생김
//			prod.setProd_img(savename); // 입력받지 않은 이미지 경로가 생김 그ㅓ나 이미지는 웹서버상에 images에 저장됨.
//  			} //if 1 end 
  			
//  		}//if 2 end 	  
      }
	else result=ServiceResult.FAILED;

	return result;

	}

	@Override
   public int retreieveProdCount(PagingInfoVO pagingVO) {
      return dao.selectProdCount(pagingVO);
   }

	@Override
   public List<ProdVO> retrieveProdList(PagingInfoVO pagingVO) {
      return dao.selectProdList(pagingVO);
   }

	@Override
   public ProdVO retrieveProd(String prod_id) {
      ProdVO prod = dao.selectProd(prod_id);
      if(prod==null) 
         throw new CommonException(prod_id+" 상품이 없음.");
      return prod;
   }

	@Override
   public ServiceResult modifyProd(ProdVO prod) {
      retrieveProd(prod.getProd_id());
      ServiceResult result = null;
      int cnt = dao.updateProd(prod);
      if(cnt > 0) {  try {
    	  prod.transfer(saveFolder);
    	  }catch (IllegalStateException|IOException e) {
    		  throw new RuntimeException(e);
    	  }
    	  result = ServiceResult.OK;
      }
      else result = ServiceResult.FAILED;
      return result;
      
   }

}