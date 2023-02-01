package DAO;

import java.util.ArrayList;

import DB.DB;
import DTO.DTOjoin;
import DTO.DTOres;

public class DAOsearch extends DAOcrud {
	

	//서치 리스트
	public ArrayList<DTOres> list(String adress, String sort, String currentPage, String keyword, String keywordRange) {
		sort = sortSwitch(sort); // 정렬기준을 SQL 쿼리문에 맞게 변환
		ArrayList<DTOres> list = new ArrayList<DTOres>();
		String query="";
		
		int currentPageN;
		if(currentPage==null){ // 현재페이지
			currentPageN = 1; }// 넘어온 값 없으면 첫 페이지로
		else{
			currentPageN = Integer.parseInt(currentPage); }
		
		openDB();
		
		if(adress==null || adress.equals("null")) {
			query= String.format("select *from %s where %s order by %s limit %s, %s"
					, DB.SERVER_BOARD, switchsearchRange(keyword, keywordRange), sort,
					(currentPageN - 1) * DB.PAGINGNUM, DB.PAGINGNUM);
		}
		else {
			query= String.format("select *from %s where %s AND fm_adress='%s' order by %s limit %s, %s"
					, DB.SERVER_BOARD, switchsearchRange(keyword, keywordRange), adress, sort,
					(currentPageN - 1) * DB.PAGINGNUM, DB.PAGINGNUM);
		}
		
		try {
			rs = st.executeQuery(query);
			while (rs.next()) {
				DB.dto = new DTOres(rs.getString("fm_num"), rs.getString("fm_title"), rs.getString("fm_id"),
						rs.getString("fm_time"), rs.getString("fm_hit"), rs.getString("fm_text"),
						rs.getString("fm_reply"), rs.getString("fm_point"), rs.getString("fm_adress"),
						rs.getString("fm_tel"), rs.getString("fm_recmd"));
				list.add(DB.dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(query);
		
		closeDB();
		return list;
	}
	
	//서치 리스트 //검색 범위가 리플인 경우
	public ArrayList<DTOjoin> listSearchReply(String adress, String sort, String currentPage, String keyword) {
		
		sort = sortSwitchReply(sort); // 정렬기준을 SQL 쿼리문에 맞게 변환
		ArrayList<DTOjoin> list = new ArrayList<DTOjoin>();
		
		String query="";
		
		int currentPageN;
		if(currentPage==null){ // 현재페이지
			currentPageN = 1; }// 넘어온 값 없으면 첫 페이지로
		else{
			currentPageN = Integer.parseInt(currentPage); }
		
		openDB();
		
		if(adress==null || adress.equals("null")) {
			query= String.format("select *from %s where %s order by %s limit %s, %s"
				, DB.SERVER_JOIN, switchsearchRange(keyword, "reply"), sort,
				(currentPageN - 1) * DB.PAGINGNUM, DB.PAGINGNUM);
		}
		else {
			query= String.format("select *from %s where %s AND fm_adress='%s' order by %s limit %s, %s"
					, DB.SERVER_JOIN, switchsearchRange(keyword, "reply"), adress, sort,
					(currentPageN - 1) * DB.PAGINGNUM, DB.PAGINGNUM);
		}
		
		try {
			rs = st.executeQuery(query);
			while (rs.next()) {
				DTOjoin dtoJ = new DTOjoin(
						rs.getString(DB.SERVER_BOARD+".fm_num"), rs.getString(DB.SERVER_BOARD+".fm_title"), rs.getString(DB.SERVER_BOARD+".fm_point"), 
						rs.getString(DB.SERVER_BOARD+".fm_reply"), rs.getString(DB.SERVER_BOARD+".fm_adress"),
						rs.getString(DB.SERVER_REPLY+".fm_Rnum"), rs.getString(DB.SERVER_REPLY+".fm_id"), rs.getString(DB.SERVER_REPLY+".fm_point"), 
						rs.getString(DB.SERVER_REPLY+".fm_text"), rs.getString(DB.SERVER_REPLY+".fm_date"));
				
				list.add(dtoJ);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(query);
		
		closeDB();
		return list;
	}
	
	//전체 포스트 수
	public String countPostDB(String adress, String keyword, String keywordRange) {
		openDB();
		
		String query="";
		String mountPost = "";
		
		if(adress==null || adress.equals("null")) {
			if(keywordRange.equals("reply")) { //리플검색 결과창으로 분기
				query = String.format("select count(*) from %s where %s ", DB.SERVER_JOIN, switchsearchRange(keyword, keywordRange));	}
			else {
				query = String.format("select count(*) from %s where %s ", DB.SERVER_BOARD, switchsearchRange(keyword, keywordRange));	}
		}
		else {
			if(keywordRange.equals("reply")) { //리플검색 결과창으로 분기
				query = String.format("select count(*) from %s where %s AND fm_adress ='%s' ", 
						DB.SERVER_JOIN, switchsearchRange(keyword, keywordRange), adress);		}
			else {
				query = String.format("select count(*) from %s where %s AND fm_adress ='%s'  ", 
						DB.SERVER_BOARD, switchsearchRange(keyword, keywordRange), adress);		}
		}
		
		try {
			rs = st.executeQuery(query);
			rs.next();
			mountPost = rs.getString("count(*)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(query);
		
		closeDB();
		return mountPost;
	}
	
	public String switchsearchRange(String keyword, String keywordRange) {
		
		String tmp = "fm_title"; //default값
		
		switch(keywordRange) {
		case "title":
			tmp = String.format("fm_title like '%%%s%%' ", keyword);
			break;
		case "content" :
			tmp = String.format("fm_text like '%%%s%%' ", keyword);
			break;
		case "titlecontent" :
			tmp =  String.format("fm_title like '%%%s%%' OR fm_text like '%%%s%%' ", keyword, keyword);
			break;
		case "reply" :
			tmp =  String.format("%s.fm_text like '%%%s%%' ", DB.SERVER_REPLY, keyword);
			break;
			default:
		}
		
		return tmp;
	}
	
	// 검색범위 리플인 경우 정렬 기준 별도로 설정
	public String sortSwitchReply(String sort) {
		
		if(sort==null) {return "fm_num desc ";}
		
		switch(sort) {
		case "new" : return "fm_num desc ";
		case "old" : return "fm_num ";
		case "high" : return DB.SERVER_BOARD+".fm_point desc ";
		case "low" : return DB.SERVER_BOARD+".fm_point ";
		default:
		}
		return "fm_num desc "; // 기본 > 최신순
	}
}
