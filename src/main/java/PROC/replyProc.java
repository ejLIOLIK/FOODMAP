package PROC;

import java.util.ArrayList;

import DAO.DAOreply;
import DTO.DTOreply;
import DB.DB;

public class replyProc { // 리플 관련 이동예정
	
	public ArrayList<DTOreply> listR;
	DAOreply daoR = new DAOreply();
	public int currentPageRN, currentPageBRN;
	public int totalPageR;
	
	public int getCurrentPageRN() {
		return currentPageRN;
	}

	public int getCurrentPageBRN() {
		return currentPageBRN;
	}

	public int getTotalPageR() {
		return totalPageR;
	}

	String postNum;

	public replyProc(String postNum, String currentPageR, String currentPageBR, int totalPageR) {
		
		listR = new ArrayList<>();
		listR = daoR.listR(postNum, currentPageR);
		this.totalPageR = totalPageR;
		
		this.listR = listR;
		this.postNum = postNum;
		
		// 리플 페이지
		if(currentPageR==null || currentPageR.equals("null")){
			currentPageRN = 1;	
		}
		else{
			currentPageRN = Integer.parseInt(currentPageR);
		}
		// 리플 페이지 블락 
		if(currentPageBR==null || currentPageBR.equals("null")){
			currentPageBRN = 1;
		}
		else{
			currentPageBRN = Integer.parseInt(currentPageBR);
		}
	}
	
	public String htmlReplyPage() {
		
		String html = "";
		
		// 페이지블록
		if(currentPageBRN>1){
			html += String.format("<a href='/board/read?postNum=%s&currentPageR=%d&currentPageBR=%d'>&lt</a>",
					postNum, currentPageRN, currentPageBRN-1);
		}
		else{
			html += "&lt";
		}
		for(int i=0;i<totalPageR && i<DB.PAGINGREPLYBLOCK;i++){
			int pageBN = (currentPageBRN-1)*DB.PAGINGREPLYBLOCK+i+1;
			if(pageBN>totalPageR){break;}
			html += String.format("<a href='/board/read?postNum=%s&currentPageR=%d&currentPageBR=%d'>[%d]</a>", 
					postNum, pageBN, currentPageBRN, pageBN);
		}
		if(currentPageBRN>=(totalPageR+DB.PAGINGREPLYBLOCK-1)/DB.PAGINGREPLYBLOCK){
			html += "&gt";
		}
		else{
			html += String.format("<a href='/board/read?postNum=%s&currentPageR=%d&currentPageBR=%d'>&gt</a>", 
					postNum, currentPageRN,currentPageBRN+1);
		}
		html += "<hr>";
		
		return html;
	}
}
