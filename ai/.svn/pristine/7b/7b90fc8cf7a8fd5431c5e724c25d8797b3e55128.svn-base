package kr.or.ddit.recipeboard.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.Like2VO;
import kr.or.ddit.vo.PagingInfoVO;
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
@Repository
public interface IRecipeBoardDAO {
	public List<RecipeBoardVO> selectRecipeList(PagingInfoVO<RecipeBoardVO> pagingVO);
	public RecipeBoardVO selectRecipe(RecipeBoardVO recipe);
	public int selectRecipeCount(PagingInfoVO<RecipeBoardVO> pagingVO);
	public int insertRecipe(RecipeBoardVO recipe);
	public int UpdateRecipe(RecipeBoardVO recipe);
	public int deleteRecipe(RecipeBoardVO recipe);
	public List<RecipeTypeVO> selectAllRecipeType();
	public int selectRecentRecipe();
	public List<Like2VO> selectLike(String id);
	public int insertLike(Like2VO like);
	public int deleteLike(Like2VO like);
	public List<RecipeBoardVO> recipeMypage(String id);
	public List<RecipeBoardVO> selectLikeMypage(String id);
	
}
