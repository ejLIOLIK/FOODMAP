package servicePack;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAO.DAOmem;
import DAO.DAOrecmd;
import DAO.DAOreply;
import DAO.DAOsearch;
import DTO.DTOreply;


public class serviceBoard {
	DAOsearch dao;
	DAOreply daoR;
	DAOmem daoM;
	DAOrecmd daoRecmd;
	ArrayList<DTOreply> listR;
	HttpSession session;
	
	public serviceBoard() {
		dao = new DAOsearch();
		daoR = new DAOreply();
		daoM = new DAOmem();
		daoRecmd = new DAOrecmd();
	}
	
	public void delete(String delNum) {
		dao.delete(delNum);
	}
	
	public void write() {
		dao.write();
	}
	
	public void read(String readNum) {
		dao.read(readNum);
		dao.upHit(readNum);
	}
	
	public int countPageR(String readNum) {
		return daoR.countPageDB(daoR.countReplyDB(readNum));
	}
	
	public void edit(String editNum) {
		dao.edit(editNum);
		dao.downHit(editNum);
	}
	
	public void deleteReply(String delNum, String postNum) {
		daoR.delete(delNum);
		daoR.downReplyNum(postNum);
		daoR.countPointUpdate(postNum);
		daoR.downHit(postNum);
	}
	
	public void editReply(String editNum, String postNum) {
		daoR.edit(editNum);
		daoR.countPointUpdate(postNum);
		daoR.downHit(postNum);
	}
	
	public void writeReply(String postNum) {
		daoR.write();
		daoR.upReplyNum(postNum);
		daoR.countPointUpdate(postNum);
		daoR.downHit(postNum);
	}
	
	public int memJoin(String email, String id, String pw1, String pw2) {
		return daoM.checkMemJoin(email, id, pw1, pw2);
	}
	
	public String MemJoinMessage(int message) {
		return daoM.MemJoinMessage(message);
	}
	
	public boolean login(HttpServletRequest request, String id, String pw) {
		if(daoM.checkLogin(id, pw)) {
			session = request.getSession();
			session.setAttribute("id", id);
			session.setMaxInactiveInterval(5*60);
			return true;
		}
		else {return false;}
	}
	
	public void logout() {
		if(!getIdSession().equals("null")) {
			session.removeAttribute("id");
			session.invalidate();
		}
	}
	
	public boolean adminRight() {
		
		if (getIdSession().equals("admin")) {
				return true;
		}		
		return false;
	}
	
	public boolean recmdCheck(String postNum) {
		if(!getIdSession().equals("null")) {
			return daoRecmd.recmdCheck(getIdSession(), postNum);
		}
		else {
			return false;
		}
	}
	
	public void recmdUpDown(boolean blRecmd, String postNum) {
		// 여부 따라서 추천 수 + - 하고 DB에서 내역 삭제
		if(blRecmd) {
			daoRecmd.recmdDown(postNum);
			daoRecmd.recmdDelete(getIdSession(), postNum);
		}
		else {
			daoRecmd.recmdUp(postNum);
			daoRecmd.recmdInsert(getIdSession(), postNum);
		}
	}
	
	public String getIdSession() {
		if(session!=null) {
			return (String)session.getAttribute("id");
		}
		else {
			return "null";
		}
		
	}
}
