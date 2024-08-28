<%-- 
    Document   : login
    Created on : Sep 1, 2020, 10:05:18 AM
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
        
        
        
	String userID=request.getParameter("uid");if (userID==null){userID="";}
        String getcountry=request.getParameter("country");if (getcountry==null){getcountry="";}
        String host=request.getParameter("hostname");if (host==null){host="";}
        String getLocation=request.getParameter("location");if (getLocation==null){getLocation="";}
        String getDepartament=request.getParameter("departament");if (getDepartament==null){getDepartament="";}
        String startdate=request.getParameter("startdate");if (startdate==null){startdate="";}
        String enddate=request.getParameter("enddate");if (enddate==null){enddate="";}
        Lista listaform = new Lista();
        
        
        if (listaform.validateString(getDepartament)==false || listaform.validateString(getLocation)==false || listaform.validateString(host)==false || listaform.validateString(userID)==false || listaform.validateString(getcountry)==false){%><script>alert("Some characters are invalid or search exceeded maximum allowed characters" ); window.location = 'users.jsp';</script> <%}
        
        listaform.listUsers(host,userID,getcountry,getLocation,getDepartament,offset);
        
        String[] uid = listaform.getUid();
        String[] name = listaform.getName();
        String[] country = listaform.getCountry();
        String[] location=listaform.getLocation();
	String[] departament = listaform.getDepartament();
	String[] departamentID = listaform.getDepID();
        String[] clientip = listaform.getIp();
	String[] hostname = listaform.getHostname();
        String[] date=listaform.getDate();
        int count = listaform.getCount();
        int i;
        
%>
	


<!DOCTYPE html>
<head> 
<meta http-equiv="Cache-control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Expires" content="0">
<title> App Runs </title> 
<link rel='stylesheet' type='text/css' href='css/index.css' />

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
 <table class="buttonsbarcentered" cellpadding="0" border="0">
       <tr >
        <td class="tdlinks" ><a href="users.jsp">View Installations</td>
        <td class="tdlinks"><a href="filtered.jsp">View Events</td>
        <td class="tdlinks" cellspacing="0"><div class="dropdown"> Charts   
                             <div class="dropdown-content">
                                        <a href="eventschart.jsp">Events</a>
                                        <a href="bandwidthchart.jsp">Bandwidth</a>
                             </div></div>
        <td class="tdlinks"><a href="appruns.jsp">Application Runs</td>
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
                <TD width=100>
                </TD>
                <TD width=100>User ID
                </TD>
                <TD width=100>Dept Name
                </TD>
                <TD width=100>Hostname
                </TD>
                <TD width=100>Country
                </TD>
                <TD width=100>Location
                </TD>
                
          </TR>
<TR>
<TD width=100>Filter by : </TD>

<TD width=100>
    <input type="text" id="uid" name="uid" value="<%=userID%>">
</TD>
<TD width=100>
    <input type="text" id="departament" name="departament" value="<%=getDepartament%>">
    
</TD>
<TD width=100>
    <input type="text" id="hostname" name="hostname" value="<%=host%>">
    
</TD>
<TD width=100>
  <input type="text" id="country" name="country" value="<%=getcountry%>">
</TD>
<TD width=100>
    <input type="text"   name="location" value="<%=getLocation%>"/>
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
             
                <TH >User ID
                </TH>
                <TH >User Name
                </TH>
                <TH >Dept ID
                </TH>
                <TH >Dept Name
                </TH>
                <TH >Country
                </TH>
                <TH >Location
                 </TH>
                <TH >Hostname
                </TH>
                <TH > Date
                 </TH>
                <TH >Client Ip
                </TH>
                
          </TR>
          
      
         <%for(i=0;i<count;i++){ %>
        
          <TR style="background-color: #ccffff;color:blue;">
              <TD >
                 <%=uid[i]%> 
              </TD>
                <TD >
                  <%=name[i]%>
                </TD>
                <TD ><%=departamentID[i]%>
                </TD>
                <TD ><%=departament[i]%>
                </TD>
                <TD ><%=country[i]%>
                </TD>
                <TD ><%=location[i]%>
                </TD>
                <TD ><%=hostname[i]%>
                </TD>
                <TD ><%=date[i]%></TD>
                <TD > <%=clientip[i]%>
                </TD>
                
          </TR>
          
          <%} %>
          
     </table>
     
 </div>   
    
        <% 
            if(offset==0&i<30){
        %>   
            
            
            
        <%
            }else if(offset>0&i<30){
        %>
          
          
          <button type="submit" name="pageoffset" value="<%=offset-30%>" class="btn-link">Previous Page</button>
          
          
        <% 
          }else if(offset==0&i==30) { 
        %>
          <%offset=i+offset;%>
          
           
           <button type="submit" name="pageoffset" value="<%=offset%>" class="btn-link">Next Page</button>
        <%
            }else {
        %>   
         
          <%offset=i+offset;%>
       
        <button type="submit" name="pageoffset" value="<%=offset-(2*i)%>" class="btn-link">Previous Page</button>
        <button type="submit" name="pageoffset" value="<%=offset%>" class="btn-link">Next Page</button>
       <%}%> 
</div> 
</div></form>


    
    

    <b><center> <a>All rights reserved to WNS Global Services</a></center></b> 
</body> 
</html>

