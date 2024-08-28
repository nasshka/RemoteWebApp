<%-- 
    Document   : login
    Created on : Aug 6, 2020, 5:05:18 PM
    Author     : u274612
--%>


<%@page import="java.sql.Date "%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@page import="myPack.Lista"%>
<jsp:useBean id="uname" scope="session" class="myPack.Bean" />
 
<%String username = uname.getName();

if(username.equals(""))
 		response.sendRedirect("login.jsp");
%>   
<%	
        
        String offsett=request.getParameter("pageoffset");
        if(offsett == null) offsett="0";
        int offset=Integer.parseInt(offsett); 
        String host = request.getParameter("hostname");if (host==null){host="";}
	String userID=request.getParameter("uid");if (userID==null){userID="";}
        String getcountry=request.getParameter("country");if (getcountry==null){getcountry="";}
        String getlocation=request.getParameter("location");if (getlocation==null){getlocation="";}
        String startdate=request.getParameter("startdate");if (startdate==null){startdate="";}
        String enddate=request.getParameter("enddate");if (enddate==null){enddate="";}
       
        
        Lista listaform = new Lista();
       
        if (listaform.validateString(getlocation)==false || listaform.validateString(host)==false || listaform.validateString(userID)==false || listaform.validateString(getcountry)==false){%><script>alert("Some characters are invalid or search exceeded maximum allowed characters" ); window.location = 'users.jsp';</script> <%}
        //listaform.populate_ivectortemp();
        
        listaform.listInstalls(host,userID,getcountry,getlocation,offset,startdate,enddate);
        String[] hostname = listaform.getHostname();
        String[] uid = listaform.getUid();
        String[] country = listaform.getCountry();
        String[] location = listaform.getLocation();
        String[] employee = listaform.getEmployee();
        String[] NetworkInfo = listaform.getNetworkInfo();
        String[] isp = listaform.getIsp();
        String[] date=listaform.getDate();
        //String[] itIsRunned=listaform.itIsRunned(); 
        int count = listaform.getCount();
        int i;
        
%>
	


<!DOCTYPE html>
<head> 
<meta http-equiv="Cache-control" content='no-cache, no-store'>
<meta http-equiv="Pragma" content="no-cache">
<title> List Installs </title> 
<link rel='stylesheet' type='text/css' href='css/index.css' />
        <script type="text/javascript" src="css/jquery.min.js"></script>
        <script type="text/javascript" src="css/moment.min.js"></script>
        <script type="text/javascript" src="css/daterangepicker.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/daterangepicker.css" />
  <script>
  $(function() {
  $('input[name="startdate"]').daterangepicker({
    singleDatePicker: true,
    showDropdowns: true,
    changeMonth: true,
    minYear: 2010,
    maxYear: 2030
  });
});
$(function() {
  $('input[name="enddate"]').daterangepicker({
    singleDatePicker: true,
    showDropdowns: true,
    changeMonth: true,
    minYear: 2010,
    maxYear: 2030
  });
});
  </script> 
</head> 
<link rel="shortcut icon" href="/images/wnslogo.png">
<body>
 
<div class="header">
  <div class="logo"></div>
  <div class="prestatus"> 
      <div class="statusbox">
          <table>
          <tr>
              <td>Logged in as: <strong> <%=username %></strong> </td>
          </tr>
          <tr><td ><a href="logout.jsp">LOGOUT</td></tr>
          </table>
      </div>
  </div>
</div>    

<div class="buttonsbar"> 
 <table class="buttonsbarcentered" cellpadding="0"  border="0">
      <tr >
        <td class="tdlinks" ><a href="users.jsp">View Installations</td>
        <td class="tdlinks"><a href="filtered.jsp">View Events</td>
        <td class="tdlinks"><div class="dropdown"> Charts   
                             <div class="dropdown-content">
                                        <a href="eventschart.jsp">Events</a>
                                        <a href="bandwidthchart.jsp">Bandwidth</a>
                             </div></div>
        <td class="tdlinks">Inventory</td>
        <td class="tdlinks"><a href="bandwidth.jsp">Bandwidth logs</td>
      </tr>
 </table>
</div>        
    
    
<form method='post' autocomplete="off" action="users.jsp"><input type='hidden' /> 
<div class='wrapper'> 
<div class='main'> 
    <div class="maintop">
        
      <TABLE class="filtertable" cellpadding="0"  border="0" >
          <TR>
             <TD width=100></TD>
                <TD width=100>Hostname
                </TD>
                <TD width=100>User ID
                </TD>
                <TD width=100>Country
                </TD>
                <TD width=100>Location
                </TD>
                <TD width=100>Start Date
                </TD>
                <TD width=100>End Date
                </TD>
                <TD width=100>
                </TD>
          </TR>
<TR>
<TD width=100>Filter by : </TD>

<TD width=100>
    <input type="text" autocomplete="off" id="hostname" name="hostname" value="<%=host%>">
</TD>
<TD width=100>
    <input type="text" id="uid" name="uid" value="<%=userID%>">
</TD>
<TD width=100>
    <input type="text" id="country" name="country" value="<%=getcountry%>">
</TD>
<TD width=100>
    <input type="text" id="location" name="location" value="<%=getlocation%>">
</TD>

<TD width=100>
    <input type="text" id="startdate" class="inputtext" name="startdate" value="<%=startdate%>"/>
</TD>
<TD width=100>
  <input type="text" id="enddate" name="enddate" class="inputtext" value="<%=enddate%>"/>
    
</TD>
<TD width=100>
    <input type='submit' value='Apply Filter' class='btn' />
</TD>
<TD width=100>
    <a href="export.jsp"><input type="button" value="Export" class="btn"/></a>
  </TD>
</TR>

</TABLE> 
  
</div>
 <div class='maincenter'>    
     
     <TABLE class="centertable" cellpadding="2"  border="1" >
         <TR style="background-color:#2c3e50">
             
                <TH >Hostname
                </TH>
                <TH >User ID
                </TH>
                <TH >User Name
                </TH>
                <TH >Country
                </TH>
                <TH >Location
                </TH>
                <TH  width=100>Network Info
                </TH>
                <TH >ISP Name
                </TH>
                <TH >Date
                </TH>
                
                
          </TR>
          
      
         <%for(i=0;i<count;i++){ %>
        
          <TR style="background-color: #ccffff;color:blue;font-weight:lighter;">
              <TH >
                 <%=hostname[i]%> 
              </TD>
                <TH >
                  <%=uid[i]%>
                </TD>
                <TH ><%=employee[i]%>
                </TD>
                <TH ><%=country[i]%>
                </TD>
               <TH ><%=location[i]%>
                </TD>
                <TH  width=100><%=NetworkInfo[i]%>
                </TD>
                <TH ><%=isp[i]%>
                </TD>
                <TH ><%=date[i]%>
                </TD>
                
                
          </TR>
          
          <%} %>
          
     </table>
     
 </div>   
    <% if(offset==0&i<30){%> <%}else if(offset>0&i<30){ %>
         <button type="submit" name="pageoffset" value="<%=offset-30%>" class="btn-link">Previous Page</button>
        <% }else if(offset==0&i==30) { %> <%offset=i+offset;%>
         <button type="submit" name="pageoffset" value="<%=offset%>" class="btn-link">Next Page</button>
        <% }else {%><%offset=i+offset;%>
        <button type="submit" name="pageoffset" value="<%=offset-(2*i)%>" class="btn-link">Previous Page</button>
        <button type="submit" name="pageoffset" value="<%=offset%>" class="btn-link">Next Page</button>
       <%}%> 
       <%System.out.println(offset);%>
</div> 
</div></form>


    
    

    <b><center> <a>All rights reserved to WNS Global Services</a></center></b> 
</body> 
</html>

