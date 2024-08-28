<%-- 
    Document   : login
    Created on : Aug 6, 2020, 5:05:18 PM
    Author     : u274612
--%>


<%@page import="java.sql.Date "%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@page import="myPack.Lista"%>
<jsp:useBean id="uname" scope="session" class="myPack.Bean" />
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
 
<%String username = uname.getName();

if(username.equals(""))
 		response.sendRedirect("login.jsp");
%> 
<%	
        
	String host = request.getParameter("hostname");if (host==null){host="";}
	String userID=request.getParameter("uid");if (userID==null){userID="";}
        String event=request.getParameter("eventtype");if (event==null){event="";}
        String startdate=request.getParameter("startdate");if (startdate==null){startdate="";}
        String enddate=request.getParameter("enddate");if (enddate==null){enddate="";}
        String getcountry=request.getParameter("country");if (getcountry==null){getcountry="";}
        
        Lista newcountlist=new Lista();
        
        if (newcountlist.validateString(host)==false || newcountlist.validateString(userID)==false || newcountlist.validateString(getcountry)==false){%><script>alert("Some characters are invalid or search exceeded maximum allowed characters" ); window.location = 'eventschart.jsp';</script> <%}
        List<Integer> getcounts=newcountlist.listChart(host,userID,event,getcountry,startdate,enddate);
        
%>
	


<!DOCTYPE html>
<head> 
<meta http-equiv="Cache-control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Expires" content="0">
<title> Charts </title> 
<link rel='stylesheet' type='text/css' href='css/index.css' />
<script type="text/javascript" src="css/jquery.min.js"></script>
        <script type="text/javascript" src="css/moment.min.js"></script>
        <script type="text/javascript" src="css/daterangepicker.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/daterangepicker.css" />
  <script type="text/javascript" src="css/loader.js"></script>
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

<script type="text/javascript">
    google.charts.load("current", {packages:['corechart','controls']});
    google.charts.setOnLoadCallback(drawChart);
    var chart;
    var data;
    var view;
    function drawChart() {
        
       var data = google.visualization.arrayToDataTable([
        ["Element", "Count", { role: "style" } ],
        ["Network Reset", <%=getcounts.get(1)%>, "#E32636"],
        ["Proxy Settings", <%=getcounts.get(2)%>, "silver"],
        ["Cache Clear", <%=getcounts.get(3)%>, "gold"],
        ["GPU Update", <%=getcounts.get(4)%>, "color: #b87333"],
        ["Network logs", <%=getcounts.get(5)%>, "color: #8DB600"],
        ["Clear Network Cache", <%=getcounts.get(6)%>, "color: #3B444B"],
        ["McAfee Update", <%=getcounts.get(7)%>, "color: #FF9966"],
        ["Install-VPN", <%=getcounts.get(8)%>, "color: #007FFF"],
        ["Update SCCM", <%=getcounts.get(9)%>, "color: #BF94E4"],
        ["Audio Enable", <%=getcounts.get(10)%>, "color: #004225"],
        ["Diagnostic check", <%=getcounts.get(11)%>, "color: #ACE1AF"],
        ["Re-build Profile", <%=getcounts.get(12)%>, "color: #ACE1AF"]  
      ]);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
         
          title: "Graph view for event types",
        width: 1200,
        height: 500,
        bar: {groupWidth: "95%"},
        legend: { position: "none" },
        backgroundColor:"transparent",
        is3D:true
      };
      chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
      chart.draw(view, options);
       
  button1 = document.getElementById("switchChart");
  button1.addEventListener("click", switchChart, false);
  button = document.getElementById("switchColumn");
  button.addEventListener("click", switchColumn, false);
  function switchChart() {
   
    chart = new google.visualization.PieChart(document.getElementById("columnchart_values"));
    chart.draw(view,options);}
 function switchColumn() {
    
    chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
    chart.draw(view,options);}
      };

  
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
    
    

<div class='wrapper'> 
<div class='main'> 
<div class="maintop">
      <form method='post' autocomplete="off" action="eventschart.jsp"><input type='hidden' />  
      <TABLE class="filtertable" cellpadding="0"  border="0" >
          <TR>
             <TD width=100></TD>
                <TD width=100>Hostname
                </TD>
                <TD width=100>User ID
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
    <input type="text" id="country" name="country" value="<%=getcountry%>"/>
</TD>
<TD width=100>
    <input type="text" id="startdate" name="startdate" value="<%=startdate%>"/>
</TD>
<TD width=100>
  <input type="text" id="enddate" name="enddate" value="<%=enddate%>"/>
    
</TD>
<TD width=100>
    <input type='submit' value='Apply Filter' class='btn' />
</TD>
</TR>
</TABLE> 
  </form>  
</div>
  
  <div class="chartdiv">
      <table>
          <tr>
              <td> <div id="columnchart_values" class="centertable" ></div>
              </td>
               <td><tr><input type="button" id="switchChart" value="Pie Chart"  /></tr>
                
                <tr><input type="button" id="switchColumn" value="Column Chart"  /></tr>
              </td>
          </tr>
      </table>    
   </div>
  
  
</div> 
</div>
 

    
    

    <b><center> <a>All rights reserved to WNS Global Services</a></center></b> 
</body> 
</html>

