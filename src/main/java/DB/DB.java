package DB;

import DTO.DTOres;

public class DB {

	public static String serverforName = "com.mysql.cj.jdbc.Driver";
	public static String serverConnection = "jdbc:mysql://localhost:3306/my_cat";
	public static String DB_ID = "root";
	public static String DB_PW = "root";
	
	public static String SERVER_BOARD = "BOARD_FOODMAP";
	public static int PAGINGNUM = 5;
	public static int PAGINGBLOCK = 5;
	
	public static DTOres dto;
}