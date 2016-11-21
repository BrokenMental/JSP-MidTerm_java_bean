package bean.test;

import java.sql.*;
import java.util.ArrayList;

public class DB {
	
	String favor = null;
	String id = null;
	String pw = null;
	int count;
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost/jspdb_j?useSSL=false&jdbcCompliantTruncation=false&characterEncoding=utf8&characterSetResults=utf8";
	private String dbid = "root";
	private String dbpw = "1234";
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public DB(){
		try{
			Class.forName(driver);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void connect(){
		try{
			con = DriverManager.getConnection(url, dbid, dbpw);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void disconnect(){
		if(pstmt != null){
			try{
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(con != null){
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public String[] getLogin(){
		connect();
		String[] array = new String[2];
		String sql = "select * from idpw";
		try{
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				array[0] = rs.getString("id");
				array[1] = rs.getString("pw");
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		return array;
	}
	
	public String[][] getFavor(){
		connect();
		String[][] array = new String[4][2];
		String sql = "select * from Favor";
		try{
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int x = 0;
			while(rs.next()){
				array[x][0] = rs.getString("fav");
				array[x][1] = rs.getString("count");
				x++;
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
		return array;
	}
	
	public void updateFav(String Favor){
		connect();
		
		String sql = "update Favor set count=count +1 where fav =?";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Favor);
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconnect();
		}
	}
}
