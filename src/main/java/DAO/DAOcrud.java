package DAO;

import java.sql.SQLException;
import java.text.Format;
import java.util.ArrayList;

import DB.DB;
import DTO.DTOres;

public class DAOcrud extends DAO {
	
	//리스트
	public ArrayList<DTOres> list(String sort, int currentPage) {
		sort = sortSwitch(sort); // 정렬기준을 SQL 쿼리문에 맞게 변환
		ArrayList<DTOres> list = new ArrayList<DTOres>();		
		openDB();
		String query = String.format("select *from %s order by %s limit %s, %s", DB.SERVER_BOARD, sort, (currentPage-1)*DB.PAGINGNUM, DB.PAGINGNUM);
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
		case "오래된순" : return "fm_num ";
		case "평점높은순" : return "fm_point desc";
		case "평점낮은순" : return "fm_point ";
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
		String query = String.format("insert into %s (fm_title,fm_id,fm_text,fm_adress,fm_tel) value ('%s', '%s', '%s', '%s', '%s')",
				DB.SERVER_BOARD, DB.dto.title, DB.dto.id, DB.dto.text, DB.dto.adress, DB.dto.tel);
		try {
			System.out.println(query);
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
	}
	
	//수정
	public void edit(String editNum) {
		openDB();
		String query = String.format("update %s set fm_title='%s', fm_text='%s', fm_adress='%s', fm_tel='%s' where fm_num = %s",
				DB.SERVER_BOARD, DB.dto.title, DB.dto.text, DB.dto.adress, DB.dto.tel, editNum);
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
	
	//전체 포스트 수
	public String countPostDB() {
		openDB();
		String query = String.format("select count(*) from %s", DB.SERVER_BOARD);
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
	
	//전체 페이지 수
	public int countPageDB(String strTemp) {

		int mountPost = Integer.parseInt(strTemp);
		int mountPage;
		
		if(mountPost==0) { return 1; }
		else if(mountPost%DB.PAGINGNUM==0) {
			mountPage = mountPost/DB.PAGINGNUM;
		}
		else {
			mountPage = mountPost/DB.PAGINGNUM + 1;
		}
		
		return mountPage;
	}
	
	//별점 업데이트
	public void countPointUpdate(String postNum, double point) {
		openDB();
		String query = String.format("update %s set fm_point='%f' where fm_num = %s",
				DB.SERVER_BOARD, point, postNum);
		try {
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
	}
	
	//리플 수 +1
	public void upReplyNum(String postNum) {
		openDB();
		String query = String.format("update %s set fm_reply = fm_reply+1 where fm_num = %s", DB.SERVER_BOARD, postNum);
		try {
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
	}
}
