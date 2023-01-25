package DTO;

//식당 정보
public class DTOreply {
	public String Pnum;		//고유번호 //AUTO_INCREMENT
	public String Rnum;
	public String id;
	public String point;
	public String text;
	public String date;
	
	public DTOreply(String Pnum, String Rnum, String id, String point, String text, String date) {
		this.Pnum	=Pnum	;
		this.Rnum	=Rnum	;
		this.id		=id		;
		this.point	=point	;
		this.text	=text	;
		this.date	=date	;
	}

	public DTOreply(String Pnum, String id, String point, String text) {
		this.Pnum	=Pnum	;
		this.id		=id		;
		this.point	=point	;
		this.text	=text	;
	}
}

