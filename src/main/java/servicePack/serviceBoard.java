package servicePack;

import java.util.ArrayList;

import DAO.DAOmem;
import DAO.DAOreply;
import DAO.DAOsearch;
import DTO.DTOreply;
import jakarta.servlet.http.HttpSession;


public class serviceBoard {
	DAOsearch dao;
	DAOreply daoR;
	DAOmem daoM;
	ArrayList<DTOreply> listR;
	HttpSession session;
	
	public serviceBoard() {
		dao = new DAOsearch();
		daoR = new DAOreply();
		daoM = new DAOmem();
	}
	
	public void delete(String delNum) {
		dao.delete(delNum);
	}
	
	public void write() {
		dao.write();
	}
	
	public void read(String readNum) {
		dao.read(readNum);
	}
	
	public int countPageR(String readNum) {
		return daoR.countPageDB(daoR.countReplyDB(readNum));
	}
	
	public void edit(String editNum) {
		dao.edit(editNum);
	}
	
	public void deleteReply(String delNum, String postNum) {
		daoR.delete(delNum);
		daoR.downReplyNum(postNum);
		daoR.countPointUpdate(postNum);
	}
	
	public void editReply(String editNum, String postNum) {
		daoR.edit(editNum);
		daoR.countPointUpdate(postNum);
	}
	
	public void writeReply(String postNum) {
		daoR.write();
		daoR.upReplyNum(postNum);
		daoR.countPointUpdate(postNum);
	}
	
	public int memJoin(String email, String id, String pw1, String pw2) {
		return daoM.checkMemJoin(email, id, pw1, pw2);
	}
	
	public String MemJoinMessage(int message) {
		return daoM.MemJoinMessage(message);
	}
	
	public boolean login(String id, String pw) {
		if(daoM.checkLogin(id, pw)) {
			session.setAttribute("id", id);
			session.setMaxInactiveInterval(20*60);
			return true;
		}
		else {return false;}
	}
	
	public void logout() {
		session.invalidate(); 
	}

}
