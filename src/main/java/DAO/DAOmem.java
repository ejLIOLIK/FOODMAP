package DAO;

import java.sql.SQLException;

import DB.DB;

public class DAOmem extends DAO {
	//회원가입
	
	//회원가입 이메일/아이디/비밀번호 체크
	public int checkMemJoin(String email, String id, String pw1, String pw2) {
		if(!checkMemJoinMail(email)) {return 1;}
		else if(!checkMemJoinID(id)) {return 2;}
		else if(!checkMemJoinPW(pw1, pw2)) {return 3;}
		else {
			openDB();
			String query = String.format("insert into %s (email, id, pw) value ('%s','%s','%s') ", DB.SERVER_MEMBER, email, id, pw1);
			System.out.println(query);
			try {
				st.executeUpdate(query);				
			} catch (Exception e) {
				e.printStackTrace();
			}
			closeDB();
			return 4;}
	}
	
	public boolean checkMemJoinMail(String email) {
		openDB();
		String check="";
		String query = String.format("select count(*) from %s where email = '%s'", DB.SERVER_MEMBER, email);
		System.out.println(query);
		try {
			rs = st.executeQuery(query);
			rs.next();
			check = rs.getString("count(*)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		
		if(check.equals("0")) {return true;}
		else {return false;}
	}
	
	public boolean checkMemJoinID(String id) {
		openDB();
		String check="";
		String query = String.format("select count(*) from %s where id = '%s'", DB.SERVER_MEMBER, id);
		System.out.println(query);
		try {
			rs = st.executeQuery(query);
			rs.next();
			check = rs.getString("count(*)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		
		if(check.equals("0")) {return true;}
		else {return false;}
	}
	
	public boolean checkMemJoinPW(String pw1, String pw2) {
		if(pw1.equals(pw2)) {
			return true;}
		else {
			return false;}
	}
	
	public String MemJoinMessage(int state) {
		
		String message = "";
		switch(state) {
		case 1: message = "이미 가입한 email입니다.";
			break;
		case 2: message = "중복된 id입니다.";
			break;
		case 3: message = "비밀번호가 상이합니다.";
			break;
		case 4: message = "가입되었습니다.";
			break;
			default:
		}
		
		return message;
	}

	//로그인 아이디/비밀번호 체크
	public boolean checkLogin(String id, String pw) {
		if(checkLoginID(id) && checkLoginPW(id, pw)) {
			return true; }
		else {return false;	}
	}
	
	public boolean checkLoginID(String id) {
		openDB();
		String Tmp="";
		String query = String.format("select count(*) from %s where id = '%s'", DB.SERVER_MEMBER, id);
		System.out.println(query);
		try {
			rs = st.executeQuery(query);
			rs.next();
			Tmp = rs.getString("count(*)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		
		if(Tmp.equals("1")) {
			return true;}
		else {
			return false;}
	}
	
	public boolean checkLoginPW(String id, String pw) {
		openDB();
		String pwTmp="";
		String query = String.format("select * from %s where id = '%s'", DB.SERVER_MEMBER, id);
		System.out.println(query);
		try {
			rs = st.executeQuery(query);
			rs.next();
			pwTmp = rs.getString("pw");
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		
		if(pwTmp.equals(pw)){
			return true;}
		else {
			return false;}
	}
}
