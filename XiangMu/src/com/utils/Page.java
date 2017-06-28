package com.utils;

public class Page {
	private int pageCount =5;
	private int currPage=1;
	private int totalPage;
	private int start;//开始取数据的位置
	private int end;//取数据结束的位置
	
	
	public void toPage(int currPage){
		if(currPage < 1){//超出首页，都看为首页
			currPage = 1;
		}
		if(currPage > totalPage){
			System.out.println("aaa+"+totalPage);
			currPage = totalPage;
			System.out.println("ccc");
		}
		this.currPage = currPage;
		start = (currPage-1)*pageCount;
		end = currPage*pageCount;
	}
	
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	/**
	 * 计算总页数
	 * 
	 */
	public void setTotalPage(int size) {
		totalPage = (size+pageCount-1)/pageCount;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
}
