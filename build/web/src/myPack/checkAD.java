/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myPack;

/**
 *
 * @author u274612 
 */
import com.sun.jndi.ldap.LdapCtxFactory;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.naming.Context;
import javax.naming.AuthenticationException;

import javax.naming.NamingException;

import javax.naming.directory.DirContext;

public class checkAD {
String message="Something went wrong";
//String url="jdbc:sqlserver://10.33.200.43:51433;database=RemoteApp;user=iVector;password=!Vector$2020;";
String url="jdbc:sqlserver://10.33.193.93:51433;database=RemoteApp_Phase2;user=RemoteApp;password=wns@1234;";    
    
      
      public String testlogin (String name,String password,String domainName){
       String principalName = ""+name+"@"+domainName;
       
        Hashtable props = new Hashtable();
        props.put("com.sun.jndi.ldap.connect.pool", "false");
        props.put(Context.SECURITY_PRINCIPAL, principalName);
        props.put(Context.SECURITY_CREDENTIALS, password);
        DirContext context;
        String server;
        if (domainName.equals("sharedservices.wind.wnsgroup.net")){server="wimssdc01.";}else {server="wimdc3.";}
        
        try {
            context = LdapCtxFactory.getLdapCtxInstance("ldap://"+server+""+domainName + '/', props);
            //context = LdapCtxFactory.getLdapCtxInstance("ldap://"+domainName + '/', props);
            
            message="Authentication succeeded!";
            
            context.close();
        } catch (AuthenticationException a) {
            System.out.println("Authentication failed: " + a);
            message="Authentication failed";
            
        } catch (NamingException e) {
            message="Failed to contact server";
            
            
        }return message;
    }
      
      public String checkIT(String nameTocheck) throws Exception{
		String id;String name;String surname;
                String nametoreturn;
           CallableStatement callableStatement = null;	
            try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		}catch(ClassNotFoundException e){}
                
                
                Connection con = DriverManager.getConnection(url);
		String getID = "{call Check_IT_Emp(?)}";
                callableStatement = con.prepareCall(getID);
                callableStatement.setString(1, nameTocheck.substring(1));
                ResultSet rs= callableStatement.executeQuery();
                if(rs.next()){
		id=rs.getString(2);
                name=rs.getString(3);
                surname=rs.getString(4);
                nametoreturn=name+" "+surname;
                
		}else{nametoreturn="not allowed";}
               
                return nametoreturn;
	}
      private static String toDC(String domainName) {
        StringBuilder buf = new StringBuilder();
        for (String token : domainName.split("\\.")) {
            if (token.length() == 0)
                continue; // defensive check
            if (buf.length() > 0)
                buf.append(",");
            buf.append("DC=").append(token);
        }
        //System.out.println(buf.toString());
        return buf.toString();
    }
    
	
}   
        
    

