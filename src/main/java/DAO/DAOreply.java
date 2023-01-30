package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import DB.DB;
import DTO.DTOreply;

public class DAOreply extends DAOcrud {

	//리스트
	public ArrayList<DTOreply> listR(String postNum, String currentPageR) {
		
		int currentPageRN;
		if(currentPageR==null || currentPageR.equals("null")) {
			currentPageRN = 1; }
		else {
			currentPageRN = Integer.parseInt(currentPageR);	}

		ArrayList<DTOreply> list = new ArrayList<DTOreply>();
		openDB();
		String query = String.format("select * from %s where fm_Pnum = %s order by fm_Rnum desc limit %s, %s", DB.SERVER_REPLY, postNum, (currentPageRN-1)*DB.PAGINGREPLY ,DB.PAGINGREPLY);
		try {
			rs = st.executeQuery(query);
			while(rs.next()) {
				DB.dtoR = new DTOreply(
					rs.getString("fm_Pnum"), rs.getString("fm_Rnum"), rs.getString("fm_id"),
					rs.getString("fm_point"), rs.getString("fm_text"), rs.getString("fm_date"));
				
				list.add(DB.dtoR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(query);
		closeDB();
		
		return list;
	}
	
	//쓰기
	@Override
	public void write() {
		openDB();
		String query = String.format("insert into %s (fm_Pnum,fm_id,fm_point,fm_text) value ('%s', '%s', '%s', '%s')",
				DB.SERVER_REPLY, DB.dtoR.Pnum, DB.dtoR.id, DB.dtoR.point, DB.dtoR.text);
		try {
			//System.out.println(query);
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
	}
	
	//삭제
	@Override
	public void delete(String delNum) {
		openDB();
		String query = String.format("delete from %s where fm_Rnum = %s", DB.SERVER_REPLY, delNum);
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeDB();
	}
	
	//수정
	@Override
	public void edit(String editNum) {
		openDB();
		String query = String.format("update %s set fm_point='%s', fm_text='%s' where fm_Rnum = %s",
				DB.SERVER_REPLY, DB.dtoR.point, DB.dtoR.text, editNum);
		try {
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
	}
	
	//특정 포스트 리플 수
	public double countReplyDB(String postNum) {
		openDB();
		String query = String.format("select count(*) from %s where fm_Pnum = %s", DB.SERVER_REPLY, postNum);
		String mountReply = "";
		try {
			rs = st.executeQuery(query);
			rs.next();
			mountReply = rs.getString("count(*)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		if(mountReply.equals("0")) {
			return -1;
		}
		else {
			return Float.valueOf(mountReply);
		}
	}
	
	//리플 페이지 수
	public int countPageDB(double countReply) {

		int mountPost = (int)countReply;
		int mountPage;
		
		if(mountPost==0) { return 1; }
		else if(mountPost%DB.PAGINGREPLY==0) {
			mountPage = mountPost/DB.PAGINGREPLY;
		}
		else {
			mountPage = mountPost/DB.PAGINGREPLY + 1;
		}
		
		return mountPage;
	}
	
	//특정 포스트 총 평점
	public double countPointDB(String postNum) {
		double mountPoint=0;
		openDB();
		String query = String.format("select * from %s where fm_Pnum = %s", DB.SERVER_REPLY, postNum);
		try {
			rs = st.executeQuery(query);
			while(rs.next()) {				
				mountPoint+=Float.valueOf(rs.getString("fm_point"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(query);
		closeDB();
		return mountPoint;
	}
	
	//별점 업데이트
	public void countPointUpdate(String postNum) {
		
		double point;
		if(countReplyDB(postNum)==-1) {
			point = -1; // 미평가
		}
		else if(countPointDB(postNum)==0) {point = 0;}
		else {
			point = countPointDB(postNum)/countReplyDB(postNum);
		}
		
		openDB();
		String query = String.format("update %s set fm_point=%f where fm_num = %s",
				DB.SERVER_BOARD, point, postNum);
		try {
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
	}
	
}
