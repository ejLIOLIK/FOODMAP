package PROC;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import DAO.DAOsearch;
import DB.DB;
import DTO.DTOjoin;
import DTO.DTOres;

public class boardProc { 
	
	public String keyword;
	public String keywordDcd;
	public String keywordRange;
	public String sort;
	int curPagingPage;
	int curPage;
	int totalPage;
	ArrayList<DTOres> list;
	ArrayList<DTOjoin> listJ;
	DAOsearch dao;
	
	public boardProc(String currentPage, String currentPagingPage, String sort, String keyword, String keywordRange) {
	
		this.sort = sort;
		this.keywordRange = keywordRange;
		dao = new DAOsearch();
		
		if(keyword==null || keyword.equals("null")) {
			this.keyword = keyword;
			keywordDcd="";
		}
		else {
			try {
				this.keyword = URLEncoder.encode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			try {
				keywordDcd = URLDecoder.decode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}			
		}
		
	
		totalPage = mountPage(keywordDcd, keywordRange);
		
		if(currentPage==null){ // 현재페이지
			curPage = 1; }// 넘어온 값 없으면 첫 페이지로
		else{
			curPage = Integer.parseInt(currentPage); }
		
		if(currentPagingPage==null){ //현재 페이징페이지
			curPagingPage = (curPage+DB.PAGINGBLOCK-1)/DB.PAGINGBLOCK; }// 넘어온 값 없으면 현재페이지로 계산
		else{
			curPagingPage = Integer.parseInt(currentPagingPage); }
		
		list(sort, currentPage, keywordDcd, keywordRange);
	}
	
	public void list(String sort, String currentPage, String keyword, String keywordRange){
		
		if(blSearchCheck()){ // ============================== 검색O
			if(keywordRange.equals("reply")) {
				listJ = new ArrayList<>();
				listJ = dao.listSearchReply(sort, currentPage, keyword);
			}
			else {
				list = new ArrayList<>();
				list = dao.list(sort, currentPage, keyword, keywordRange);}}
		else {	// ========================================= 검색X
			list = new ArrayList<>();
			list = dao.list(sort, currentPage);}
	}
	
	public int mountPage(String keyword, String keywordRange) {
		int mountPage;
		
		if(blSearchCheck()){ // ============================== 검색O
			mountPage = dao.countPageDB(dao.countPostDB(keyword, keywordRange));}
		else {	// ========================================= 검색X
			mountPage = dao.countPageDB(dao.countPostDB());	}
		return mountPage;
	}
	
	public boolean blSearchCheck() {
		if(keyword==null  || keyword.equals("null")) {return false;} 
		else {return true;}
	}

	public String htmlBoardList() {
		
		String html="";
		
		int countPost = 0;
		for(DTOres d:list){
			html += String.format("%s/%s/<a href='/board/read?num=%s&currentPage=%d&sort=%s&keyword=%s&keywordRange=%s'>%s</a> [%s]<br>", 
					d.num, d.point, d.num, curPage, sort, keyword, keywordRange, d.title, d.reply);
			countPost++;
		}
		if(countPost<DB.PAGINGNUM){
			for(int i=0;i<DB.PAGINGNUM-countPost;i++){
				html +="<br>";	} // 빈 공간 공백 채워줌
		}
		html+="<hr>";
		
		return html;
	}
	
	//검색범위가 리플인 경우
	public String htmlBoardListSearchReply() {

		int countPost = 0;
		String html="";
		
		for(DTOjoin d:listJ){
//			게시글번호 / 게시글 평점 / 게시글 제목[리플수]
//					ㄴ 리플평점 / 리플내용
			html += String.format("%s/%s/<a href='/board/read?num=%s&currentPage=%d&sort=%s&keyword=%s&keywordRange=%s'>%s</a> [%s]<br>"
					+ "└ %s/%s/%s <br>", 
					d.Pnum, d.point, d.Pnum, curPage, sort, keyword, keywordRange, d.title, d.reply, d.pointR, d.text, d.date);
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
			html+=String.format("<a href='/board/list?currentPage=%d&currentPagingPage=%d&sort=%s&keyword=%s&keywordRange=%s'> &lt; </a>", 
					curPage, curPagingPage-1, sort, keyword, keywordRange);
		}
		else {
			html+="&lt";
		}
		for(int i=(curPagingPage-1)*DB.PAGINGBLOCK;i<curPagingPage*DB.PAGINGBLOCK;i++){
			html+=String.format("<a href='/board/list?currentPage=%d&sort=%s&keyword=%s&keywordRange=%s'>[%d]</a>",
					i+1, sort, keyword, keywordRange, i+1);
			if(i+1==totalPage){ //최대 페이지 도달 시 브레이크
				break;
			}
		}
		if(curPagingPage<=totalPage/DB.PAGINGBLOCK){
			html+=String.format("<a href='board/list?currentPage=%d&currentPagingPage=%d&sort=%s&keyword=%s&keywordRange=%s'> &gt; </a>", 
					curPage, curPagingPage+1, sort, keyword, keywordRange);
		}
		else {
			html+="&gt";
		}
		
		return html;
	}
	
}