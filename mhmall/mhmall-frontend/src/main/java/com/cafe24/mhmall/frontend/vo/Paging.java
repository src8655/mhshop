package com.cafe24.mhmall.frontend.vo;

public class Paging {
	private Integer count;
	private Integer lastPage;
	private Integer startNum;
	private Integer rangeStart;
	private Integer boardCnt;
	private Integer pageCnt;

	private Long categoryNo;
	private Integer pages;
	private String kwd;
	

	public Paging() {}
	public Paging(Integer count, Integer lastPage, Integer startNum, Integer rangeStart, Integer boardCnt,
			Integer pageCnt, Long categoryNo, Integer pages, String kwd) {
		this.count = count;
		this.lastPage = lastPage;
		this.startNum = startNum;
		this.rangeStart = rangeStart;
		this.boardCnt = boardCnt;
		this.pageCnt = pageCnt;
		this.categoryNo = categoryNo;
		this.pages = pages;
		this.kwd = kwd;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getLastPage() {
		return lastPage;
	}
	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}
	public Integer getStartNum() {
		return startNum;
	}
	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}
	public Integer getRangeStart() {
		return rangeStart;
	}
	public void setRangeStart(Integer rangeStart) {
		this.rangeStart = rangeStart;
	}
	public Integer getBoardCnt() {
		return boardCnt;
	}
	public void setBoardCnt(Integer boardCnt) {
		this.boardCnt = boardCnt;
	}
	public Integer getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(Integer pageCnt) {
		this.pageCnt = pageCnt;
	}
	public Long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public String getKwd() {
		return kwd;
	}
	public void setKwd(String kwd) {
		this.kwd = kwd;
	}
	@Override
	public String toString() {
		return "Paging [count=" + count + ", lastPage=" + lastPage + ", startNum=" + startNum + ", rangeStart="
				+ rangeStart + ", boardCnt=" + boardCnt + ", pageCnt=" + pageCnt + ", categoryNo=" + categoryNo
				+ ", pages=" + pages + ", kwd=" + kwd + "]";
	}
	
}
