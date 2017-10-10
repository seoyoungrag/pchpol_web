package com.dwebs.pchpol.common.vo;

import javax.servlet.http.HttpServletRequest;

import com.dwebs.pchpol.common.util.CommonUtil;

public class PagingVO {

	private int page;
	private int nApp = 20; // 기본 목록 갯수
	private int rows;
	private int startNum;
	private int lastPage;
	private int listCount;
	private int total;
	
	private String sidx;
	private String sord;
	private boolean isSearch;
	
	private String searchType;
	private String searchWord;
	
	public boolean isSearch() {
		return isSearch;
	}
	public void setSearch(boolean isSearch) {
		this.isSearch = isSearch;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getnApp() {
		return nApp;
	}
	public void setnApp(int nApp) {
		this.nApp = nApp;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	
	public String nullTrim(String str) {
    	return nullTrim(str,"");
    }
    
    public String nullTrim(String str, String def) {
    	if (str == null || str.equals("null")) {
    	    return def;
    	}
    	return str.trim();
    }
	
    public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getStartNum() {
		return startNum;
	}
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public void setStartNum(int startNum) {
		this.startNum = (this.page-1)*this.rows;	
	}
	
	public void setPaging(HttpServletRequest request){
		this.page = Integer.parseInt(nullTrim(request.getParameter("page"),"1"));
		this.rows = Integer.parseInt(nullTrim(request.getParameter("rows"),"0"));
		this.rows = this.rows == 0 ? this.nApp : this.rows;
		this.startNum = (this.page-1) == 0 ? 1 : (this.page-1) * this.rows ;	
		this.isSearch = Boolean.parseBoolean(request.getParameter("_search"));
		this.sidx = CommonUtil.null2str(request.getParameter("sidx"));
		this.sord = CommonUtil.null2str(request.getParameter("sord"));
		this.searchType = CommonUtil.null2str(request.getParameter("searchType"));
		this.searchWord = CommonUtil.null2str(request.getParameter("searchWord"));
	}
	
	public void setLastPage(){
		this.lastPage = (this.listCount % this.rows > 0)? 1:0;
		this.total = (this.listCount / this.rows) + this.lastPage;
	}
}
