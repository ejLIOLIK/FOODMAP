package DTO;

public class DTOjoin {
	
	//게시글
	public String Pnum; // 고유번호 //AUTO_INCREMENT
	public String title; //글제목
	public String point; //총평점
	public String reply;
	//리플
	public String Rnum;
	public String id;
	public String pointR; //리플평점
	public String text;
	public String date;

	public DTOjoin(String Pnum, String title, String point, String reply, String Rnum, String id, String pointR, String text, String date) {
		this.Pnum = Pnum;
		this.title = title;
		this.point = point;
		this.reply = reply;
		this.Rnum = Rnum;
		this.id = id;
		this.pointR = pointR;
		this.text = text;
		this.date = date;
	}
}
