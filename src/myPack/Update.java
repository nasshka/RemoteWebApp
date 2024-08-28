package myPack;

import java.sql.*;
import java.text.*;
import java.util.Date;


public class Update {
	Date date=new Date();
	String next = new String();
	String prev = new String();
	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException{
		Update test = new Update();
		test.update("admin", "test", "test2.jpg");
	}
	public void update(String powner,String palbum,String pname) throws InstantiationException, IllegalAccessException, SQLException{
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch(ClassNotFoundException e){}
		
		String url="jdbc:mysql://localhost:3306/album?user=root&password=123";

		Connection con = DriverManager.getConnection(url);
		Statement stmt = con.createStatement();

		String FindLast = "select * from photo where powner=\""+powner+"\" and palbum=\""+palbum+"\" and pnext=\"last\"";
		ResultSet rs=stmt.executeQuery(FindLast);

		if(rs.next())
		{
			prev=rs.getString("pname");
			next=pname;
		}
		else{
			prev = "head";
			next = "last";
		}
		rs.close();

		String strDate = new String();
		SimpleDateFormat format1=new SimpleDateFormat("yyyyMMddHHmmss");
		strDate=format1.format(date);
		String UpDate = "update photo set pnext=\""+next+"\" where powner=\""+powner+"\" and palbum=\""+palbum+"\" and pnext=\"last\"";
		String Insert = "insert into photo (pname,powner,palbum,ptype,pprev,pnext,pdate) values (\""+pname+"\",\""+powner+"\",\""+palbum+"\",\"1\",\""+prev+"\",\"last\",\""+strDate+"\")";
		if(!next.equals("last"))
		{
			stmt.executeUpdate(UpDate);
		}
		stmt.execute(Insert);
		
		con.close();
	}
	
	public void createalbum(String aname,String aowner,String adesc ,String atype) throws Exception{
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch(ClassNotFoundException e){}
		String url="jdbc:mysql://localhost:3306/album?user=root&password=123";
		String acover="defult.gif";
		
		Connection con = DriverManager.getConnection(url);
		Statement stmt = con.createStatement();
	
		String FindCreate = "select * from album where aname=\""+aname+"\" and aowner=\""+aowner+"\"";
		ResultSet rs=stmt.executeQuery(FindCreate);
	
		if(!rs.next())
		{		
			Date date=new Date();
			String strDate = new String();
			SimpleDateFormat format1=new SimpleDateFormat("yyyyMMddHHmmss");
			strDate=format1.format(date);
			String DoCreate="insert into album (aname,aowner,acover,ades,atype,adate) values (\""+aname+"\",\""+aowner+"\",\""+acover+"\",\""+adesc+"\",\""+atype+"\",\""+strDate+"\")";
			stmt.execute(DoCreate);
		}
		rs.close();
		stmt.close();
		con.close();
	}

	public void chgpass(String uname,String oldpass,String newpass) throws Exception{
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch(ClassNotFoundException e){}
		String url="jdbc:mysql://localhost:3306/album?user=root&password=123";
		
		Connection con = DriverManager.getConnection(url);
		Statement stmt = con.createStatement();
	
		String FindCreate = "select * from users where uname=\""+uname+"\" and upassword=\""+oldpass+"\"";
		ResultSet rs=stmt.executeQuery(FindCreate);
	
		if(!rs.next())
		{		

			String DoCreate="update users set upassword=\""+newpass+"\" where uname=\""+uname+"\"";
			stmt.executeUpdate(DoCreate);
		}
		rs.close();
		stmt.close();
		con.close();
	}
	
	public String getPrev(){
		return prev;
	}
	public String getNext(){
		return next;
	}
	public Date getTime(){
		return date;
	}
}