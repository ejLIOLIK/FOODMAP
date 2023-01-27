package DAO;

import java.util.ArrayList;

import DB.DB;
import DTO.DTOres;

public class DAOsearch extends DAOcrud {
	
	//서치 리스트
	public ArrayList<DTOres> list(String sort, String currentPage, String keyword) {
		sort = sortSwitch(sort); // 정렬기준을 SQL 쿼리문에 맞게 변환
		ArrayList<DTOres> list = new ArrayList<DTOres>();
		
		int currentPageN;
		if(currentPage==null){ // 현재페이지
			currentPageN = 1; }// 넘어온 값 없으면 첫 페이지로
		else{
			currentPageN = Integer.parseInt(currentPage); }
		
		openDB();
		String query = String.format("select *from %s where fm_title like '%%%s%%' order by %s limit %s, %s"
				, DB.SERVER_BOARD, keyword, sort,
				(currentPageN - 1) * DB.PAGINGNUM, DB.PAGINGNUM);
		try {
			rs = st.executeQuery(query);
			while (rs.next()) {
				DB.dto = new DTOres(rs.getString("fm_num"), rs.getString("fm_title"), rs.getString("fm_id"),
						rs.getString("fm_time"), rs.getString("fm_hit"), rs.getString("fm_text"),
						rs.getString("fm_reply"), rs.getString("fm_point"), rs.getString("fm_adress"),
						rs.getString("fm_tel"));
				list.add(DB.dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		return list;
	}
	
	//전체 포스트 수
	public String countPostDB(String keyword) {
		openDB();
		String query = String.format("select count(*) from %s where fm_title like '%%%s%%' ", DB.SERVER_BOARD, keyword);
		String mountPost = "";
		try {
			rs = st.executeQuery(query);
			rs.next();
			mountPost = rs.getString("count(*)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		return mountPost;
	}
	
	public String switchsearchRange(String searchRange) {
		
		String tmp = "fm_title"; //default값
		
		switch(searchRange) {
		case "제목":
			tmp = "fm_title";
		case "내용" :
			tmp = "fm_text";
		//case "리플" :
			default:
		}
		
		return tmp;
	}
	
}
