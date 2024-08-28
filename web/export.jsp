<%-- 
    Document   : export
    Created on : Jan 6, 2021, 6:55:37 PM
    Author     : u274612
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@page import="myPack.Lista"%>

<%	
        
        
       
        
        Lista listaform = new Lista();
         ArrayList<ArrayList> total=listaform.exportInstalls();
        ArrayList<Integer> count=total.get(7);
        int x=count.get(0);
%>

<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Export installs</title>
   </head>
   <body>
      <table cellpadding="1"  cellspacing="1" border="1" bordercolor="gray">
         <tr>
            <td>Hostname</td>
            <td>User ID</td>
            <td>User Name</td>
            <td>Country</td>
            <td>Location</td>
            <td>Network Info</td>
            <td>Date</td>
            
         </tr>
         <%
            
                
                  if (total!= null) {
                      response.setContentType("application/vnd.ms-excel");
                      response.setHeader("Content-Disposition", "inline; filename="+ "installs report.xls");
                  }
            for(int i= 0; i<x ; i++){
            %>
         <tr>
            <td><%= total.get(0).get(i) %></td><td><%= total.get(1).get(i) %></td><td><%= total.get(2).get(i) %></td><td><%= total.get(3).get(i) %></td><td><%= total.get(4).get(i) %></td><td><%= total.get(5).get(i) %></td><td><%= total.get(6).get(i) %></td>
         </tr>
            
         <% 
            }
            %>
      </table>
   </body>
</html>
<!DOCTYPE html>

