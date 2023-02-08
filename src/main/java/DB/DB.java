package DB;

import DTO.DTOreply;
import DTO.DTOres;

public class DB {

	public static String serverforName = "com.mysql.cj.jdbc.Driver";
	public static String serverConnection = "jdbc:mysql://localhost:3306/my_cat";
	public static String DB_ID = "root";
	public static String DB_PW = "root";
	
	public static String SERVER_BOARD = "BOARD_FOODMAP";
	public static int PAGINGNUM = 10;
	public static int PAGINGBLOCK = 10;

	public static String SERVER_REPLY = "REPLY_FOODMAP";
	public static int PAGINGREPLY = 5;		//테스트용 임시값
	public static int PAGINGREPLYBLOCK = 3; //테스트용 임시값
	
	public static String SERVER_JOIN = "BOARD_FOODMAP inner join REPLY_FOODMAP on BOARD_FOODMAP.fm_num = REPLY_FOODMAP.fm_Pnum";
	
	public static String SERVER_MEMBER = "MEM_FOODMAP"; //회원
	public static String SERVER_RECMD = "RECMD_FOODMAP"; //추천
	
	public static DTOres dto;
	public static DTOreply dtoR;	
}
