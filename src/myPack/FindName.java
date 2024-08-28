package myPack;

import java.sql.*;

public class FindName {
	String next;
	String prev;
	
	public void main(String[] args) throws Exception{

	}
	
	public void find(String powner , String palbum , String pname) throws Exception{
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch(ClassNotFoundException e){}
		
		String url="jdbc:mysql://localhost:3306/album?user=root&password=123";
		
		Connection con = DriverManager.getConnection(url);
		Statement stmt = con.createStatement();

		String Find = "select * from photo where powner=\""+powner+"\" and palbum=\""+palbum+"\" and pname=\""+pname+"\"";
		ResultSet rs=stmt.executeQuery(Find);
		
		while(rs.next()){
			next = rs.getString("pnext");
			prev = rs.getString("pprev");
		}
	}
	
	public String getPrev(){
		return prev;
	}
	
	public String getNext(){
		return next;
	}
}
