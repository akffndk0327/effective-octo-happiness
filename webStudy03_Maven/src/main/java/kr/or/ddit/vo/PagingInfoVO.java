package kr.or.ddit.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // 기본생성자
@Getter
@Setter
public class PagingInfoVO<T> {
	private int totalRecord;
	private int totalPage;
	private int screenSize = 10; //게시글 몇개 내가 설정
	private int blockSize = 5;	//하단에 몇 페이지까지나오게 할것인가 
	private int currentPage =1; //클라이언트가 결정 함. 
	private int startRow;		 //시작 게시물번호 
	private int endRow;			 //종료 게시물번호
	private int startPage;
	private int endPage;
	
	private String pagingHTML;
	private List<T> dataList; //db에서 데이터 불러와서 리스트로 담을 변수
	
	
	
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
		endRow = currentPage*screenSize;
		startRow = endRow - (screenSize-1);
		endPage = blockSize* ((currentPage +(blockSize-1))/blockSize);
		startPage = endPage-(blockSize-1);
	}
	
	
	public String getPagingHTML() { //페이징 UI
		StringBuffer html = new StringBuffer();
		html.append("<nav aria-label='...'>");
		html.append("<ul class='pagination'>");
		String disabled = startPage <=  blockSize ? "disabled" : ""; //disabled : 비활성화되게함 .  시작페이지가 블록사이즈보다 작거나 같으면 비활성화 
		html.append("<li class='page-item " + disabled + "'>");
		html.append("<a class='page-link' data-page='" + (startPage - blockSize)
				+ "' href='#' tabindex='-1' aria-disabled='true'>Previous</a>");
		html.append("</li>");
		// 엔드페이지 값변경시키기
		endPage = endPage < totalPage ? endPage : totalPage;
		for (int page = startPage; page <= endPage; page++) {
			String active = currentPage == page ? "active" : ""; //= 보다 == 의 연산순위가 먼저임. active : 다음페이지 안나오게 
			html.append("<li class='page-item " + active + "'><a class='page-link' data-page='" + page + "' href='#'>"
					+ page);
			if (currentPage == page) {
				html.append("<span class='sr-only'>(current)</span>");
			}
			html.append("</a></li>");
		}
		disabled = endPage >= totalPage ? "disabled" : ""; // 같으면 갈피룡없어 dis~
		html.append("<li class='page-item " + disabled + "'>");
		html.append("<a class='page-link' data-page='" + (endPage + 1) + "' href='#'>Next</a>"); // data-page
		html.append("</li>");
		html.append("</ul>");
		html.append("</nav>");

		pagingHTML = html.toString();
		return pagingHTML;
	}
}
