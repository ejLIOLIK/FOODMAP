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
	public String adress;
	public String adressDcd;
	int curPagingPage;
	int curPage;
	int totalPage;
	ArrayList<DTOres> list;
	ArrayList<DTOjoin> listJ;
	DAOsearch dao;
	
	public boardProc(String adress, String currentPage, String currentPagingPage, String sort, String keyword, String keywordRange) {
	
		this.sort = sort;
		this.keywordRange = keywordRange;
		dao = new DAOsearch();
				
		// 어드레스 디코딩
		if(adress==null || adress.equals("null")) {
			this.adress = adress;
			adressDcd=null;
		}
		else {
			try {
				this.adress = URLEncoder.encode(adress, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			try {
				adressDcd = URLDecoder.decode(adress, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}			
		}
		
		// 키워드 디코딩
		if(keyword==null || keyword.equals("null")) {
			this.keyword = keyword;
			keywordDcd=null;
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
	
		totalPage = mountPage(adressDcd, keywordDcd, keywordRange);
		
		if(currentPage==null){ // 현재페이지
			curPage = 1; }// 넘어온 값 없으면 첫 페이지로
		else{
			curPage = Integer.parseInt(currentPage); }
		
		if(currentPagingPage==null){ //현재 페이징페이지
			curPagingPage = (curPage+DB.PAGINGBLOCK-1)/DB.PAGINGBLOCK; }// 넘어온 값 없으면 현재페이지로 계산
		else{
			curPagingPage = Integer.parseInt(currentPagingPage); }
		
		list(adressDcd, sort, currentPage, keywordDcd, keywordRange);
	}
	
	public void list(String adress, String sort, String currentPage, String keyword, String keywordRange){
		
		if(blSearchCheck()){ // ============================== 검색O
			if(keywordRange.equals("reply")) {
				listJ = new ArrayList<>();
				listJ = dao.listSearchReply(adress, sort, currentPage, keyword);
			}
			else {
				list = new ArrayList<>();
				list = dao.list(adress, sort, currentPage, keyword, keywordRange);}}
		else {	// ========================================= 검색X
			list = new ArrayList<>();
			list = dao.list(adress, sort, currentPage);}
	}
	
	public int mountPage(String adress, String keyword, String keywordRange) {
		int mountPage;
		
		if(blSearchCheck()){ // ============================== 검색O
			mountPage = dao.countPageDB(dao.countPostDB(adress, keyword, keywordRange));}
		else {	// ========================================= 검색X
			mountPage = dao.countPageDB(dao.countPostDB(adress));	}
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
			
			countPost++;
			html += String.format("<a href='/board/read?adress=%s&postNum=%s&currentPage=%d&sort=%s&keyword=%s&keywordRange=%s'>%s</a> [%s] %s %s<br>", d.adress, d.num, curPage, sort, keyword, keywordRange, d.title, d.reply, d.hit, d.recmd);
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
			html += String.format("%s/%s/%s/<a href='/board/read?postNum=%s&currentPage=%d&sort=%s&keyword=%s&keywordRange=%s'>%s</a> [%s]<br>"
					+ "└ %s/%s/%s <br>", 
					d.Pnum, d.adress, d.point, d.Pnum, curPage, sort, keyword, keywordRange, d.title, d.reply, d.pointR, d.text, d.date);
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
			html+=String.format("<a href='/board/list?adress=%s&currentPage=%d&currentPagingPage=%d&sort=%s&keyword=%s&keywordRange=%s'> &lt; </a>", 
					adress, curPage, curPagingPage-1, sort, keyword, keywordRange);
		}
		else {
			html+="&lt";
		}
		for(int i=(curPagingPage-1)*DB.PAGINGBLOCK;i<curPagingPage*DB.PAGINGBLOCK;i++){
			html+=String.format("<a href='/board/list?adress=%s&currentPage=%d&sort=%s&keyword=%s&keywordRange=%s'>[%d]</a>",
					adress, i+1, sort, keyword, keywordRange, i+1);
			if(i+1==totalPage){ //최대 페이지 도달 시 브레이크
				break;
			}
		}
		if(curPagingPage<=totalPage/DB.PAGINGBLOCK){
			html+=String.format("<a href='/board/list?adress=%s&currentPage=%d&currentPagingPage=%d&sort=%s&keyword=%s&keywordRange=%s'> &gt; </a>", 
					adress, curPage, curPagingPage+1, sort, keyword, keywordRange);
		}
		else {
			html+="&gt";
		}
		
		return html;
	}
	
}
