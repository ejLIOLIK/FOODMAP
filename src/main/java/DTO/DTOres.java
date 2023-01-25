package DTO;

//식당 정보
public class DTOres {
	public String num;		//고유번호 //AUTO_INCREMENT
	public String title;
	public String id;
	public String time;	//auto
	public String hit;  	//조회 수 //DEFAULT 0
	public String text;
	public String reply;	//리플 수 //DEFAULT 0
	public String point;
	public String adress;
	public String tel;
	
	public DTOres(String num, String title, String id, String time, String hit, String text, String reply, String point, String adress, String tel) {
		this.num	=num	;
		this.title	=title	;
		this.id		=id		;
		this.time	=time	;
		this.hit	=hit	;
		this.text	=text	;
		this.reply	=reply	;
		this.point	=point	;
		this.adress	=adress	;
		this.tel	=tel	;
		
		if(this.point.equals("-1.0")) {
			this.point = "미평가";
		}
		else {
			this.point=this.point.substring(0, 3);
		}
	}
	
	public DTOres(String title, String id, String text, String adress, String tel) {
		this.title	=title	;
		this.id		=id		;
		this.text	=text	;
		this.adress	=adress	;
		this.tel	=tel	;
	}
	
	public DTOres(String title, String text, String adress, String tel) { // EDIT
		this.title	=title	;
		this.text	=text	;
		this.adress	=adress	;
		this.tel	=tel	;

	}
}

