package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	public String url = "jdbc:oracle:thin:@211:187:232:77:1521:orcl";
	public String id = "aqoong";
	public String pw = "qwer12";

	public Connection conn;
	public PreparedStatement pstmt;
	public ResultSet rs;
	
	public DBConnection() {
		// TODO Auto-generated constructor stub
		try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, id, pw );
		}catch(Exception e){e.printStackTrace();
		}finally{
			dbClose();
		}
	}
	
	public void dbClose(){
		try{
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}catch(Exception e){
			System.out.println("DB closed error : ");
		}
	}

	public int run( PreparedStatement prepare) throws SQLException{

		//run
		int cnt = prepare.executeUpdate();
		
//		if(cnt > 0){
//			System.out.println("success");
//		}else
//			System.out.println("fail");
		return cnt;
	}
	
}