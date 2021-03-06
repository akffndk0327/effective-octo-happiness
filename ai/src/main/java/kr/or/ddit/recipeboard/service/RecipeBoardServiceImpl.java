package kr.or.ddit.recipeboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.recipeboard.dao.IRecipeBoardDAO;
import kr.or.ddit.vo.Like2VO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.RecipeAttatchVO;
import kr.or.ddit.vo.RecipeBoardVO;
import kr.or.ddit.vo.RecipeTypeVO;
import kr.or.ddit.vo.ReplyVO;

/**
 * @author 이유진
 * @since 2019. 11. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 4.      이유진       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class RecipeBoardServiceImpl implements IRecipeBoardService {

	@Inject
	IRecipeBoardDAO dao;

	@Override
	public RecipeBoardVO retrieveBoard(RecipeBoardVO recipe) {
		RecipeBoardVO vo = dao.selectRecipe(recipe);
		if (vo == null)
			throw new CommonException(recipe.getRecipeTitle() + " 해당 글이 존재하지 않습니다");
		return vo;
	}

	@Override
	public int retrieveBoardCount(PagingInfoVO<RecipeBoardVO> pagingVO) {
		return dao.selectRecipeCount(pagingVO);
	}

	@Override
	public List<RecipeBoardVO> retrieveBoardList(PagingInfoVO<RecipeBoardVO> pagingVO) {
		
		return dao.selectRecipeList(pagingVO);
	}

	@Override
	public ServiceResult createRecipe(RecipeBoardVO recipe) {
		int cnt =dao.insertRecipe(recipe);
		if(cnt>=1) {
			return ServiceResult.OK;
		}else{
			return ServiceResult.FAILED;
		}
	}

	@Override
	public List<RecipeTypeVO> selectAllRecipeType() {
		return dao.selectAllRecipeType();
	}

	@Override
	public ServiceResult modifyRecipe(RecipeBoardVO recipe) {
		int cnt =dao.UpdateRecipe(recipe);
		if(cnt>=1) {
			return ServiceResult.OK;
		}else{
			return ServiceResult.FAILED;
		}
	}

	@Override
	public int selectRecentRecipe() {
		return dao.selectRecentRecipe();
	}

	@Override
	public ServiceResult deleteRecipe(RecipeBoardVO recipe) {
		int cnt =dao.deleteRecipe(recipe);
		if(cnt>=1) {
			return ServiceResult.OK;
		}else{
			return ServiceResult.FAILED;
		}
	}


	@Override
	public List<Like2VO> selectLike(String id) {
		// TODO Auto-generated method stub
		return dao.selectLike(id);
	}

	@Override
	public ServiceResult insertLike(Like2VO like) {
		int cnt =dao.insertLike(like);
		if(cnt>=1) {
			return ServiceResult.OK;
		}else{
			return ServiceResult.FAILED;
		}
	}

	@Override
	public ServiceResult deleteLike(Like2VO like) {
		int cnt =dao.deleteLike(like);
		if(cnt>=1) {
			return ServiceResult.OK;
		}else{
			return ServiceResult.FAILED;
		}
	}

	@Override
	public List<RecipeBoardVO> recipeMypage(String id) {
		// TODO Auto-generated method stub
		return dao.recipeMypage(id);
	}

	@Override
	public List<RecipeBoardVO> selectLikeMypage(String id) {
		// TODO Auto-generated method stub
		return dao.selectLikeMypage(id);
	}



	

	

}
