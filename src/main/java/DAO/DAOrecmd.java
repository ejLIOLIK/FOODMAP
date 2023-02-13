package DAO;

import DB.DB;

public class DAOrecmd extends DAO {
	
	//DB.SERVER_RECMD
	
	//세션이랑 비교해서 기존 추천 여부 리턴
	public boolean recmdCheck(String id, String postNum) {

		openDB();
		String query = String.format("select count(*) from %s where id='%s' AND postNum=%s", DB.SERVER_RECMD, id, postNum);
		String count = "";
	
		try {
			rs = st.executeQuery(query);
			rs.next();
			count = rs.getString("count(*)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		
		if(count.equals("1")) {return true;} // 추천 O
		else {return false;} // 추천 X
	}

	//추천 수 +
	public void recmdUp(String postNum) {
		openDB();
		String query = String.format("update %s set fm_recmd = fm_recmd+1 where fm_num = %s", DB.SERVER_BOARD, postNum);
		try {
			System.out.println(query);
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
	}
	
	//추천 수 - 
	public void recmdDown(String postNum) {
		openDB();
		String query = String.format("update %s set fm_recmd = fm_recmd-1 where fm_num = %s", DB.SERVER_BOARD, postNum);
		try {
			System.out.println(query);
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
	}
	
	//DB에서 내역 추가
	public void recmdInsert(String id, String postNum) {
		openDB();
		String query = String.format("insert into %s (id, postNum) value ('%s','%s')", DB.SERVER_RECMD, id, postNum);
		try {
			System.out.println(query);
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
	}
	
	//DB에서 내역 삭제
	public void recmdDelete(String id, String postNum) {
		openDB();
		String query = String.format("delete from %s where id = '%s' AND postNum = %s", DB.SERVER_RECMD, id, postNum);
		try {
			System.out.println(query);
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
	}

}

