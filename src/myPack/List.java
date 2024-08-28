package myPack;

import java.sql.*;

public class List {
	String[] pname = new String[500];
	int count = 0;
	String[] aname = new String[500];
	String[] acover = new String[500];
	String[] atype = new String[500];
	String[] aowner = new String[500];
	String[] lastaname = new String[5];
	String[] lastacover = new String[5];
	String[] lastaowner = new String[5];
	String[] lastuser = new String[5];
	
	public void main(String[] args) throws Exception{
	}
	
	public void photolist(String powner, String palbum) throws Exception{
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch(ClassNotFoundException e){}
		
		String url="jdbc:mysql://localhost:3306/album?user=root&password=123";
		
		Connection con = DriverManager.getConnection(url);
		Statement stmt = con.createStatement();

		String Find = "select * from photo where powner=\""+powner+"\" and palbum=\""+palbum+"\" order by pdate";
		ResultSet rs=stmt.executeQuery(Find);
		
		while(rs.next()){
			pname[count] = rs.getString("pname");
			count++;
		}
	}
	
	public void albumlist(String owner) throws Exception{
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch(ClassNotFoundException e){}
		
		String url="jdbc:mysql://localhost:3306/album?user=root&password=123";
		
		Connection con = DriverManager.getConnection(url);
		Statement stmt = con.createStatement();

		String Find = "select * from album where aowner=\""+owner+"\" order by adate";
		ResultSet rs=stmt.executeQuery(Find);
		
		while(rs.next()){
			aname[count] = rs.getString("aname");
			acover[count] = rs.getString("acover");
			atype[count] = rs.getString("atype");
			count++;
		}
	}
	
	public void search(String Search) throws Exception{
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch(ClassNotFoundException e){}
		
		String url="jdbc:mysql://localhost:3306/album?user=root&password=123";
		
		Connection con = DriverManager.getConnection(url);
		Statement stmt = con.createStatement();

		ResultSet rs=stmt.executeQuery(Search);
		
		while(rs.next()){
			aname[count] = rs.getString("aname");
			acover[count] = rs.getString("acover");
			aowner[count] = rs.getString("aowner");
			count++;
		}
	}
	
	public void last() throws SQLException, InstantiationException, IllegalAccessException{
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch(ClassNotFoundException e){}
		
		String url="jdbc:mysql://localhost:3306/album?user=root&password=123";
		int i=0;
		int j=0;
		
		Connection con = DriverManager.getConnection(url);
		Statement stmt = con.createStatement();

		String Find = "select * from album order by adate desc";
		ResultSet rs=stmt.executeQuery(Find);
		
		while(rs.next()&&i<5){
			lastaname[i] = rs.getString("aname");
			lastacover[i] = rs.getString("acover");
			lastaowner[i] = rs.getString("aowner");
			i++;
		}
		
		Find = "select * from users order by udate desc";
		rs=stmt.executeQuery(Find);
		
		while(rs.next()&&j<5){
			lastuser[j] = rs.getString("uname");
			j++;
		}
	}
	public String[] getLastaOwner(){
		return lastaowner;
	}
	public String[] getLastUser(){
		return lastuser;
	}
	public String[] getLastaName(){
		return lastaname;
	}
	public String[] getLastaCover(){
		return lastacover;
	}
	public String[] getpName(){
		return pname;
	}
	public int getCount(){
		return count;
	}
	public String[] getaName(){
		return aname;
	}
	public String[] getaCover(){
		return acover;
	}
	public String[] getaOwner(){
		return aowner;
	}
	public int getPage(int a, int b){
		if(a%b==0) 
	  		return a/b;
	  	else
	  		return a/b+1;
	}
}
