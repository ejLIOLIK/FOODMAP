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
	
	public ArrayList<DTOreply> read(String readNum, String currentPageR) {
		dao.read(readNum);
		listR = new ArrayList<>();
		listR = daoR.listR(readNum, currentPageR); //테스트용 임시값
		
		return listR;
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

}
