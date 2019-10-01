package kr.or.ddit.buyer.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PagingInfoVO<T> {
	private int totalRecord;
	private int totalPage;
	private int screenSize=10;
	private int blockSize=5;
	private int currentPage=1;
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	
	private String pagingHTML;
	private List<T> dataList;
	
	public PagingInfoVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	} 
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage=(int)Math.ceil((totalRecord/(double)screenSize));
	}

	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		endRow = currentPage*screenSize;
		startRow = endRow - (screenSize-1);
		endPage = blockSize* ((currentPage +(blockSize-1))/blockSize);
		startPage = endPage-(blockSize-1);
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
