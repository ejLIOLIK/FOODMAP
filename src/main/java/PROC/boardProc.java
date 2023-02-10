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
	public String keywordEcd;
	public String keywordRange;
	public String sort;
	public String category;
	public String categoryEcd;
	int curPagingPage;
	int curPage;
	int totalPage;
	ArrayList<DTOres> list;
	ArrayList<DTOjoin> listJ;
	DAOsearch dao;
	
	public boardProc(String category, String currentPage, String currentPagingPage, String sort, String keyword, String keywordRange) {
	
		this.sort = sort;
		this.keywordRange = keywordRange;
		dao = new DAOsearch();
				
		// 어드레스 디코딩
		if(category==null || category.equals("null")) {
			this.category = null;
			categoryEcd=null;
		}
		else {
			try {
				this.category = category;
				categoryEcd = URLEncoder.encode(category, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
//			try {
//				this.category = URLDecoder.decode(category, "UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}			
		}
		
		// 키워드 디코딩
		if(keyword==null || keyword.equals("null")) {
			this.keyword = null;
			keywordEcd=null;
		}
		else {
			try {
				this.keyword = keyword;
				keywordEcd = URLEncoder.encode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
//			try {
//				keyword = URLDecoder.decode(keyword, "UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}			
		}
	
		totalPage = mountPage(category, keyword, keywordRange);
		
		if(currentPage==null){ // 현재페이지
			curPage = 1; }// 넘어온 값 없으면 첫 페이지로
		else{
			curPage = Integer.parseInt(currentPage); }
		
		if(currentPagingPage==null){ //현재 페이징페이지
			curPagingPage = (curPage+DB.PAGINGBLOCK-1)/DB.PAGINGBLOCK; }// 넘어온 값 없으면 현재페이지로 계산
		else{
			curPagingPage = Integer.parseInt(currentPagingPage); }
		
		list(category, sort, currentPage, keyword, keywordRange);
	}
	
	public void list(String category, String sort, String currentPage, String keyword, String keywordRange){
		
		if(blSearchCheck()){ // ============================== 검색O
			if(keywordRange.equals("reply")) {
				listJ = new ArrayList<>();
				listJ = dao.listSearchReply(category, sort, currentPage, keyword);
			}
			else {
				list = new ArrayList<>();
				list = dao.list(category, sort, currentPage, keyword, keywordRange);}}
		else {	// ========================================= 검색X
			list = new ArrayList<>();
			list = dao.list(category, sort, currentPage);}
	}
	
	public int mountPage(String category, String keyword, String keywordRange) {
		int mountPage;
		
		if(blSearchCheck()){ // ============================== 검색O
			mountPage = dao.countPageDB(dao.countPostDB(category, keyword, keywordRange));}
		else {	// ========================================= 검색X
			mountPage = dao.countPageDB(dao.countPostDB(category));	}
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
//			html += String.format("%s/%s/%s/<a href='/board/read?adress=%s&postNum=%s&currentPage=%d&sort=%s&keyword=%s&keywordRange=%s'> %s</a> [%s] %s %s<br>", 
//					d.num, d.adress, d.point, d.adress, d.num, curPage, sort, keyword, keywordRange, d.title, d.reply, d.hit, d.recmd);
			html += String.format("%s/%s/%s/", d.num, d.adress, d.point);

			if(d.img==null || d.img.equals("null")){ // 썸네일
				html += " <img src='\\upload\\no.jpg' alt='img' width='30'> ";
			}
			else{
				html += " <img src='\\upload\\" + d.img + "' alt='img' width='30'> ";
			}
			
			html += String.format("<a href='/board/read?category=%s&postNum=%s&currentPage=%d&sort=%s&keyword=%s&keywordRange=%s'>%s</a> [%s] %s %s<br>", 
					categoryEcd, d.num, curPage, sort, keywordEcd, keywordRange, d.title, d.reply, d.hit, d.recmd);
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
//			html += String.format("%s/%s/%s/<a href='/board/read?postNum=%s&currentPage=%d&sort=%s&keyword=%s&keywordRange=%s'>%s</a> [%s]<br>"
//					+ "└ %s/%s/%s <br>", 
//					d.Pnum, d.adress, d.point, d.Pnum, curPage, sort, keyword, keywordRange, d.title, d.reply, d.pointR, d.text, d.date);
			html += String.format("%s/%s/%s/", 
					d.Pnum, d.adress, d.point);
					
			if(d.img==null || d.img.equals("null")){ // 썸네일
				html += " <img src='\\upload\\no.jpg' alt='img' width='30'> ";
			}
			else{
				html += " <img src='\\upload\\" + d.img + "' alt='img' width='30'> ";
			}
					
			html += String.format("<a href='/board/read?postNum=%s&currentPage=%d&sort=%s&keyword=%s&keywordRange=%s'>%s</a> [%s]<br>"
					+ "└ %s/%s/%s <br>", 
					d.Pnum, curPage, sort, keywordEcd, keywordRange, d.title, d.reply, d.pointR, d.text, d.date);
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
			html+=String.format("<a href='/board/list?category=%s&currentPage=%d&currentPagingPage=%d&sort=%s&keyword=%s&keywordRange=%s'> &lt; </a>", 
					categoryEcd, curPage, curPagingPage-1, sort, keywordEcd, keywordRange);
		}
		else {
			html+="&lt";
		}
		for(int i=(curPagingPage-1)*DB.PAGINGBLOCK;i<curPagingPage*DB.PAGINGBLOCK;i++){
			html+=String.format("<a href='/board/list?category=%s&currentPage=%d&sort=%s&keyword=%s&keywordRange=%s'>[%d]</a>",
					categoryEcd, i+1, sort, keywordEcd, keywordRange, i+1);
			if(i+1==totalPage){ //최대 페이지 도달 시 브레이크
				break;
			}
		}
		if(curPagingPage<=totalPage/DB.PAGINGBLOCK){
			html+=String.format("<a href='/board/list?category=%s&currentPage=%d&currentPagingPage=%d&sort=%s&keyword=%s&keywordRange=%s'> &gt; </a>", 
					categoryEcd, curPage, curPagingPage+1, sort, keywordEcd, keywordRange);
		}
		else {
			html+="&gt";
		}
		
		return html;
	}
	
}
