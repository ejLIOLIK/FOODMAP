package BOARD;

import java.util.ArrayList;

import DAO.DAOsearch;
import DB.DB;
import DTO.DTOres;

public class boardProc { 
	
	public String keyword;
	public String sort;
	int curPagingPage;
	int curPage;
	int totalPage;
	ArrayList<DTOres> list;
	DAOsearch dao;
	
	public boardProc(String currentPage, String currentPagingPage, String sort, String keyword) {
	
		this.keyword = keyword;
		this.sort = sort;
		dao = new DAOsearch();
		
		totalPage = mountPage(keyword);
		
		if(currentPage==null){ // 현재페이지
			curPage = 1; }// 넘어온 값 없으면 첫 페이지로
		else{
			curPage = Integer.parseInt(currentPage); }
		
		if(currentPagingPage==null){ //현재 페이징페이지
			curPagingPage = (curPage+DB.PAGINGBLOCK-1)/DB.PAGINGBLOCK; }// 넘어온 값 없으면 현재페이지로 계산
		else{
			curPagingPage = Integer.parseInt(currentPagingPage); }
		
		list = new ArrayList<>();
		list = list(keyword, sort, currentPage);
	}
	
	public ArrayList<DTOres> list(String keyword, String sort, String currentPage){
		list = new ArrayList<>();
		if(keyword==null){ // ============================== 검색X
			list = dao.list(sort, currentPage);}
		else {	// ========================================= 검색O
			list = dao.list(sort, currentPage, keyword);}
		
		return list;
	}
	
	public int mountPage(String keyword) {
		int mountPage;
		if(keyword==null){ // ============================== 검색X
			mountPage = dao.countPageDB(dao.countPostDB());	}
		else {	// ========================================= 검색O
			mountPage = dao.countPageDB(dao.countPostDB(keyword));}
		return mountPage;
	}
	
	public boolean blSearchCheck() {
		if(keyword==null) {return false;}
		else {return true;}
	}
	
	public String htmlBoardList() {

		int countPost = 0;
		String html="";
		
		for(DTOres d:list){
			html += String.format("%s/%s/<a href='/board/read?num=%s&currentPage=%d&sort=%s&keyword=%s'>%s</a> [%s]<br>", 
					d.num, d.point, d.num, curPage, sort, keyword, d.title, d.reply);
			countPost++;
		}
		if(countPost<DB.PAGINGNUM){
			for(int i=0;i<DB.PAGINGNUM-countPost;i++){
				html +="<br>";	} // 빈 공간 공백 채워줌
		}
		html+="<hr>";
		
		return html;
	}
	
	public String htmlBoardPage() {
		String html = "";
		
		if(curPagingPage>1){
			html+=String.format("<a href='/board/list?currentPage=%d&currentPagingPage=%d&sort=%s&keyword=%s'> &lt; </a>", 
					curPage, curPagingPage-1, sort, keyword);
		}
		else {
			html+="&lt";
		}
		for(int i=(curPagingPage-1)*DB.PAGINGBLOCK;i<curPagingPage*DB.PAGINGBLOCK;i++){
			html+=String.format("<a href='/board/list?currentPage=%d&sort=%s&blp.keyword=%s'>[%d]</a>",
					i+1, sort, keyword, i+1);
			if(i+1==totalPage){ //최대 페이지 도달 시 브레이크
				break;
			}
		}
		if(curPagingPage<=totalPage/DB.PAGINGBLOCK){
			html+=String.format("<a href='board/list?currentPage=%d&currentPagingPage=%d&sort=%s&keyword=%s'> &gt; </a>", 
					curPage, curPagingPage+1, sort, keyword);
		}
		else {
			html+="&gt";
		}
		
		return html;
	}
	
}
