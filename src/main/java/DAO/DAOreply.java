package DAO;

import java.util.ArrayList;

import DB.DB;
import DTO.DTOreply;
import DTO.DTOres;

public class DAOreply extends DAOcrud {

	//리스트
	public ArrayList<DTOreply> list(String postNum) {

		ArrayList<DTOreply> list = new ArrayList<DTOreply>();
		openDB();
		String query = String.format("select * from %s where fm_Pnum = %s order by fm_Rnum desc", DB.SERVER_REPLY, postNum);
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
			System.out.println(query);
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
	}
	
	//삭제
	
	
	//수정
	
	
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
		return Float.valueOf(mountReply);
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
		System.out.println(query);
		closeDB();
		return mountPoint;
	}
	
}
