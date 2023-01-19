package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import DB.DB;
import DTO.DTOres;

public class DAOcrud extends DAO {
	
	//리스트
	public ArrayList<DTOres> listAll(String sort) {
		sort = sortSwitch(sort); // 정렬기준을 SQL 쿼리문에 맞게 변환
		ArrayList<DTOres> list = new ArrayList<DTOres>();		
		openDB();
		String query = String.format("select *from %s order by %s", DB.SERVER_BOARD, sort);
		try {
			rs = st.executeQuery(query);
			while(rs.next()) { 
				DB.dto = new DTOres(rs.getString("fm_num")
						,rs.getString("fm_title")
						,rs.getString("fm_id")
						,rs.getString("fm_time")
						,rs.getString("fm_hit")
						,rs.getString("fm_text")
						,rs.getString("fm_reply")
						,rs.getString("fm_point")
						,rs.getString("fm_adress")
						,rs.getString("fm_tel"));
				list.add(DB.dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		return list;
	}
	
	public String sortSwitch(String sort) {
		switch(sort) {
		case "최신순" : return "fm_num desc";
		default:
		}
		return "fm_num desc"; // 기본 > 최신순
	}
	
	//읽기
	public void read(String readNum) {
		openDB();
		String query = String.format("select *from %s where fm_num = %s", DB.SERVER_BOARD, readNum);
		try {
			rs = st.executeQuery(query);
			rs.next();
			DB.dto = new DTOres(rs.getString("fm_num")
					,rs.getString("fm_title")
					,rs.getString("fm_id")
					,rs.getString("fm_time")
					,rs.getString("fm_hit")
					,rs.getString("fm_text")
					,rs.getString("fm_reply")
					,rs.getString("fm_point")
					,rs.getString("fm_adress")
					,rs.getString("fm_tel"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
	}
	
	//쓰기
	public void write() {
		openDB();
		String query = String.format("insert into %s (fm_title,fm_id,fm_text,fm_point,fm_adress,fm_tel) value ('%s', '%s', '%s', '%s', '%s', '%s')",
				DB.SERVER_BOARD, DB.dto.title, DB.dto.id, DB.dto.text, DB.dto.point, DB.dto.adress, DB.dto.tel);
		try {
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
	}
	
	//수정
	public void edit(String editNum) {
		openDB();
		String query = String.format("update %s set fm_title='%s', fm_text='%s', fm_point='%s', fm_adress='%s', fm_tel='%s' where fm_num = %s",
				DB.SERVER_BOARD, DB.dto.title, DB.dto.text, DB.dto.point, DB.dto.adress, DB.dto.tel, editNum);
		try {
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
	}
	
	//삭제
	public void delete(String delNum) {
		openDB();
		String query = String.format("delete from %s where fm_num = %s", DB.SERVER_BOARD, delNum);
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeDB();
	}
	

}
