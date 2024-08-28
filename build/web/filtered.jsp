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
        String event=request.getParameter("eventtype");if (event==null){event="";}
        String getcountry=request.getParameter("country");if (getcountry==null){getcountry="";}
        String getlocation=request.getParameter("location");if (getlocation==null){getlocation="";}
        String startdate=request.getParameter("startdate");if (startdate==null){startdate="";}
        String enddate=request.getParameter("enddate");if (enddate==null){enddate="";}
        Lista listaform = new Lista();
        
        if (listaform.validateString(getlocation)==false || listaform.validateString(host)==false || listaform.validateString(userID)==false || listaform.validateString(getcountry)==false){%><script>alert("Some characters are invalid or search exceeded maximum allowed characters" ); window.location = 'filtered.jsp';</script> <%}
        
        listaform.listFiltered(host,userID,getcountry,event,offset,startdate,enddate);
        String[] hostname = listaform.getHostname();
        String[] uid = listaform.getUid();
        String[] eventname = listaform.getEventName();
	String[] date=listaform.getDate();
	String[] clientip = listaform.getIp();
	String[] serialnumber = listaform.getSerialNumber();
        String[] country = listaform.getCountry();
        String[] location = listaform.getLocation();
        String[] employee = listaform.getEmployee();
        int count = listaform.getCount();
        int i;
        
%>
	


<!DOCTYPE html>
<head> 

<title> Filtered Events </title> 
<meta http-equiv="Cache-control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Expires" content="0">
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
    
    
<form method='post' autocomplete="off" action="filtered.jsp"><input type='hidden' /> 
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
                <TD width=100>Event Type
                </TD>
                <TD width=100>Country
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
    <input type="text" id="hostname" name="hostname" value="<%=host%>">
</TD>
<TD width=100>
    <input type="text" id="uid" name="uid" value="<%=userID%>">
</TD>
<TD width=100>
  <select name="eventtype" id="eventtype">
  <option value="<%=event%>"><%=event%></option>
  <option value="">All</option>
  <option value="Restart Network">Restart Network</option>
  <option value="Check Proxy Settings">Check Proxy Settings</option>
  <option value="Clear Internet Cache">Clear internet cache</option>
  <option value="GP-Update">GP-Update</option>
  <option value="Collect Network Logs">Collect Network Logs</option>
  <option value="Clear Network Cache">Clear Network Cache</option>
  <option value="Force McAfee Update">Force McAfee Update</option>
  <option value="Install-VPN">Install-VPN</option>
  <option value="Update SCCM">Update SCCM</option>
  <option value="Enable Audio Services">Enable Audio Services</option>
  <option value="Diagnostic check">Diagnostic check</option>
  <option value="Re-build Profile">Re-build Profile</option>
</select>
</TD>
<TD width=100>
    <input type="text"   name="country" value="<%=getcountry%>"/>
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
</TR>
</TABLE>  
</div>
 <div class='maincenter'>    
     
     <TABLE class="centertable" cellpadding="2"  border="1" >
         <TR style="background-color:#2c3e50">
             
                <TH >Hostname
                </TH>
                <TH >Serial Number
                </TH>
                <TH >User ID
                </TH>
                <TH >User Name
                </TH>
                <TH >Country
                </TH>
                <TH >Location
                </TH>
                <TH >Action Performed
                </TH>
                <TH > Date
                 </TH>
                <TH >Client Ip
                </TH>
                
          </TR>
          
      
         <%for(i=0;i<count;i++){ %>
        
          <TR style="background-color: #ccffff;color:blue;">
              <TD >
                 <%=hostname[i]%> 
              </TD>
                <TD ><%=serialnumber[i]%>
                </TD>
                <TD ><%=uid[i]%>
                </TD>
                <TD ><%=employee[i]%>
                </TD>
                <TD ><%=country[i]%>
                </TD>
                <TD ><%=location[i]%>
                </TD>
                <TD ><%=eventname[i]%>
                </TD>
                <TD ><%=date[i]%>
                </TD>
                <TD><%=clientip[i]%>
                </TD>
              <%--  <TD width=100><a target="popup" onclick="window.open('../logs.jsp','name','width=600,height=400')"><div style="color:blue" type="link"><b style="cursor: pointer"> </b> </div> </a>
                </TD> --%>
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
</div> 
</div></form>


    
    

    <b><center> <a>All rights reserved to WNS Global Services</a></center></b> 
</body> 
</html>

