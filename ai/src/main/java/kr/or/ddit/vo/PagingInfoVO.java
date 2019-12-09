package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 허민지
 * @since 2019. 11. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                  수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 4.      허민지       최초작성
 * 2019. 11. 6.		 최서희        pk추가
 * 2019. 11. 7.		 최서희        implements Serializable
 * 2019. 11. 15.     이진희        correctType,resId 추가 
 * 2019. 11. 16.     이진희        fcId추가
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@NoArgsConstructor
@Getter
@Setter
public class PagingInfoVO<T> implements Serializable{
	private int totalRecord;
	private int totalPage;
	private int screenSize = 10;
	private int blockSize = 5;
	private int currentPage = 1;
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	private String pagingHTML;
	private List<T> dataList;
	private T searchVO;
	private Map<String, Object> searchMap;
	private String search;
	
	private String pk; //pk
	
	private String correctType;
	private String resId;
	private String fcId;
	
	private String sendId;
	private String recieveId;
	
	public PagingInfoVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = (int)Math.ceil((totalRecord / (double)screenSize));
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		endRow = currentPage * screenSize;
		startRow = endRow - (screenSize-1);
		endPage = blockSize * (( currentPage + (blockSize-1) )/blockSize);
		startPage = endPage - (blockSize-1);
	}
	    
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		html.append("<nav aria-label='...'>");
		html.append("<ul class='pagination justify-content-center'>");
		String disabled = startPage<=blockSize?"disabled":"";
		html.append("<li class='page-item "+disabled+"'>");
	    html.append("<a class='page-link' data-page='"+(startPage - blockSize)+"' href='#' tabindex='-1' aria-disabled='true'>Previous</a>");
	    html.append("</li>");
	    endPage = endPage < totalPage ? endPage : totalPage;
	    for(int page = startPage; page <= endPage; page++) {
	    	String active = currentPage == page?"active":"";
	    	html.append("<li class='page-item "+active+"'><a class='page-link' data-page='"+page+"' href='#'>"+page);
	    	if(currentPage==page) {
	    		html.append("<span class='sr-only'>(current)</span>");
	    	}
	    	html.append("</a></li>");
	    }
	    disabled = endPage>=totalPage?"disabled":"";
	    html.append("<li class='page-item "+disabled+"'>");
	    html.append("<a class='page-link' data-page='"+(endPage+1)+"'  href='#'>Next</a>");
	    html.append("</li>");
	    html.append("</ul>");
	    html.append("</nav>");	    
		pagingHTML = html.toString();
		return pagingHTML;
	}
	
}










