package myPack;

import java.sql.*;
import java.io.*;


public class Delete {
	String next = new String();
	String prev = new String();
	
	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException{

	}
	public void deletephoto(String powner,String palbum,String pname) throws InstantiationException, IllegalAccessException, SQLException{
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch(ClassNotFoundException e){}
		String url="jdbc:mysql://localhost:3306/album?user=root&password=123";

		Connection con = DriverManager.getConnection(url);
		Statement stmt = con.createStatement();

		String FindDrop = "select * from photo where powner=\""+powner+"\" and palbum=\""+palbum+"\" and pname=\""+pname+"\"";
		ResultSet rs=stmt.executeQuery(FindDrop);

		while(rs.next())
		{
			prev=rs.getString("pprev");
			next=rs.getString("pnext");
			String photopath="F://workspace/.metadata/.plugins/com.genuitec.eclipse.easie.tomcat.myeclipse/tomcat/webapps/Album/upload/"+rs.getString("pname");  
			String smallpath="F://workspace/.metadata/.plugins/com.genuitec.eclipse.easie.tomcat.myeclipse/tomcat/webapps/Album/upload/small/"+rs.getString("pname");  
			Runtime rt = Runtime.getRuntime();
			File photofile = new File(photopath);
			File smallfile = new File(smallpath);
			photofile.delete();
			smallfile.delete();
		}
		rs.close();
		
		String UpDate1 = "update photo set pnext=\""+next+"\" where powner=\""+powner+"\" and palbum=\""+palbum+"\" and pname=\""+prev+"\"";
		String UpDate2 = "update photo set pprev=\""+prev+"\" where powner=\""+powner+"\" and palbum=\""+palbum+"\" and pname=\""+next+"\"";
		String UpDate3 = "update album set acover=\"defult.gif\" where acover = \""+pname+"\" and aowner=\""+powner+"\" and aname=\""+palbum+"\"";
		String Delete = "delete from photo where powner=\""+powner+"\" and palbum=\""+palbum+"\" and pname=\""+pname+"\"";

		if(!prev.equals("head"))
			stmt.executeUpdate(UpDate1);
		if(!next.equals("last"))
			stmt.executeUpdate(UpDate2);
		stmt.executeUpdate(UpDate3);
		stmt.execute(Delete);
		
		stmt.close();
		con.close();
	}
	
	public void deleteAlbum(String aname,String aowner) throws Exception{
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch(ClassNotFoundException e){}
		String url="jdbc:mysql://localhost:3306/album?user=root&password=123";

		Connection con = DriverManager.getConnection(url);
		Statement stmt = con.createStatement();
		
		String FindPhoto = "select * from photo where powner=\""+aowner+"\" and palbum=\""+aname+"\"";
		ResultSet rs = stmt.executeQuery(FindPhoto);
		while(rs.next())
		{
			String photopath="F://workspace/.metadata/.plugins/com.genuitec.eclipse.easie.tomcat.myeclipse/tomcat/webapps/Album/upload/"+rs.getString("pname");  
			String smallpath="F://workspace/.metadata/.plugins/com.genuitec.eclipse.easie.tomcat.myeclipse/tomcat/webapps/Album/upload/small/"+rs.getString("pname");  
			Runtime rt = Runtime.getRuntime();
			File photofile = new File(photopath);
			File smallfile = new File(smallpath);
			photofile.delete();
			smallfile.delete();
		}
		String DeletePhoto = "delete from photo where powner=\""+aowner+"\" and palbum=\""+aname+"\"";
		stmt.execute(DeletePhoto);

		String DeleteAlbum = "delete from album where aowner=\""+aowner+"\" and aname=\""+aname+"\"";

		stmt.execute(DeleteAlbum);
		
		stmt.close();
		con.close();
	}
	
	public String getPrev(){
		return prev;
	}
	public String getNext(){
		return next;
	}
}

