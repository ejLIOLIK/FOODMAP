package servicePack;

import java.util.ArrayList;

import DAO.DAOreply;
import DAO.DAOsearch;
import DTO.DTOreply;
import DTO.DTOres;

public class serviceBoard {
	DAOsearch dao;
	DAOreply daoR;
	ArrayList<DTOreply> listR;
	
	public serviceBoard() {
		dao = new DAOsearch();
		daoR = new DAOreply();
	}
	
	public void delete(String delNum) {
		dao.delete(delNum);
	}
	
	public void write() {
		dao.write();
	}
	
	public ArrayList<DTOreply> read(String readNum) {
		dao.read(readNum);
		listR = new ArrayList<>();
		listR = daoR.list(readNum);
		
		return listR;
	}
	
	public void edit(String editNum) {
		dao.edit(editNum);
	}
	
//	public ArrayList<DTOres> list(String keyword, String sort, String currentPage){
//		list = new ArrayList<>();
//		if(keyword==null){ // ============================== 검색X
//			list = dao.list(sort, currentPage);}
//		else {	// ========================================= 검색O
//			list = dao.list(sort, currentPage, keyword);}
//		
//		return list;
//	}
//	
//	public int mountPage(String keyword) {
//		int mountPage;
//		if(keyword==null){ // ============================== 검색X
//			mountPage = dao.countPageDB(dao.countPostDB());	}
//		else {	// ========================================= 검색O
//			mountPage = dao.countPageDB(dao.countPostDB(keyword));}
//		return mountPage;
//	}
	
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

}
