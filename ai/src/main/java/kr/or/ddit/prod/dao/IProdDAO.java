package kr.or.ddit.prod.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.LprodVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdAttatchVO;
import kr.or.ddit.vo.ProdRejectVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.ReviewVO;

/**
 * @author 최서희
 * @since 2019. 11. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 4.   최서희	       최초작성
 * 2019. 11. 5.	   최서희	       리뷰 추가
 * 2019. 11. 10.  최서희          대분류 중분류 셀렉트박스
 * 2019. 11. 10.  최서희          첨부파일 추가
 * 2019. 11. 14.  최서희		 제품 관리(승인/반려/판매중지/재판매) 기능
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IProdDAO {
	public int selectProdCount(PagingInfoVO<ProdVO> pagingVO);
	public List<ProdVO> selectProdList(PagingInfoVO<ProdVO> pagingVO);

	public int selectProdAdminCount(PagingInfoVO<ProdVO> pagingVO);
	public List<ProdVO> selectProdAdminList(PagingInfoVO<ProdVO> pagingVO);
	
	public ProdVO selectProd(String prodId); //상세조회하면
	
	public int insertProd(ProdVO prod); 
	public int insertAttatches(ProdVO prod); //첨부파일 인서트
	public ProdAttatchVO selectAttatch(int prodattId); //다운로드시 메타데이타 조회
	
	public int updateProdApproval(String prodId);//승인시 상태변경
	public int updateProd(ProdVO prod);//제품 상세내용 업데이트
	
	public int insertProdReject(ProdRejectVO prodRejectVO);//등록신청 반려
	public int updateProdReject(String prodId);//반려시 상태변경
	public ProdRejectVO selectProdReject(String prodId);//반려사유 조회
	
	public int updateProdStop(String prodId);//제품 판매중지
	public int updateProdStatus(String prodId);//제품 재판매
	
	public ProdAttatchVO selectAttatch(String prodId); //다운로드시 메타데이타 조회
	
	//품목명 셀렉트
	//대분류
	public List<LprodVO> selectLprodAList();
	//중분류
	public List<LprodVO> selectLprodBList(String lprodParent);

	//리뷰
	public int selectReviewCount(String prodId);
	public List<ReviewVO> selectReviewList(PagingInfoVO<ReviewVO> pagingVO);
	public int insertReview(ReviewVO review); 
	public int deleteReview(int reviewId);
}
