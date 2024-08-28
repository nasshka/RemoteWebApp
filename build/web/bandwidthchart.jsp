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
        String startdate=request.getParameter("startdate");if (startdate==null){startdate="";}
        String enddate=request.getParameter("enddate");if (enddate==null){enddate="";}
        
        
        Lista newLista=new Lista();
        
        if (newLista.validateString(host)==false || newLista.validateString(userID)==false){%><script>alert("Some characters are invalid or search exceeded maximum allowed characters" ); window.location = 'bandwidthchart.jsp';</script> <%}
        List<List> bandwidth=newLista.listBandwidthChart(host,userID,startdate,enddate);
        List<Double> ChinaBD=bandwidth.get(0);
        List<Double> IndiaBD=bandwidth.get(1);
        List<Double> PhilippinesBD=bandwidth.get(2);
        List<Double> polandBD=bandwidth.get(3);
        List<Double> RomaniaBD=bandwidth.get(4);
        List<Double> SouthABD=bandwidth.get(5);
        List<Double> SriLankaBD=bandwidth.get(6);
        List<Double> TurkeyBD=bandwidth.get(7);
        List<Double> UKBD=bandwidth.get(8);
        List<Double> USBD=bandwidth.get(9);
        List<Double> CostaRicaBD=bandwidth.get(10);
        List<Double> SpainBD=bandwidth.get(11);
        
%>
	


<!DOCTYPE html>
<head> 
<meta http-equiv="Cache-control" content='no-cache, no-store'>
<meta http-equiv="Pragma" content="no-cache">
<title> Charts </title> 
<link rel='stylesheet' type='text/css' href='css/index.css' />
<script type="text/javascript" src="css/jquery.min.js"></script>
        <script type="text/javascript" src="css/moment.min.js"></script>
        <script type="text/javascript" src="css/daterangepicker.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/daterangepicker.css" />
  <script src="css/loader.js"></script>
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
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawVisualization);

      function drawVisualization() {
        // Some raw data (not necessarily accurate)
        var data = google.visualization.arrayToDataTable([
          ['Country', 'Download', 'Upload'],
          ['China',<%=ChinaBD.get(0)%>,<%=ChinaBD.get(1)%>],
          ['India',<%=IndiaBD.get(0)%>,<%=IndiaBD.get(1)%>],
          ['Philippines',<%=PhilippinesBD.get(0)%>,<%=PhilippinesBD.get(1)%>],
          ['Poland',<%=polandBD.get(0)%>,<%=polandBD.get(1)%>],
          ['Romania',<%=RomaniaBD.get(0)%>,<%=RomaniaBD.get(1)%>],
          ['South Africa',<%=SouthABD.get(0)%>,<%=SouthABD.get(1)%>],
          ['Sri Lanka',<%=SriLankaBD.get(0)%>,<%=SriLankaBD.get(1)%>],
          ['Spain', <%=SpainBD.get(0)%>,<%=SpainBD.get(1)%>],
          ['UK',<%=UKBD.get(0)%>,<%=UKBD.get(1)%>],
          ['US',<%=USBD.get(0)%>,<%=USBD.get(1)%>],
          ['Costa Rica',<%=CostaRicaBD.get(0)%>,<%=CostaRicaBD.get(1)%>],
          ['Turkey',<%=TurkeyBD.get(0)%>,<%=TurkeyBD.get(1)%>]
        ]);

        var options = {
          title : 'Graph view for Average Bandwidth',
          vAxis: {title: ''},
          hAxis: {title: 'Country'},
          seriesType: 'bars',
          series: {5: {type: 'line'}}
        };

        var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
    
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawVisualization);

      function drawVisualization() {
        // Some raw data (not necessarily accurate)
        var data = google.visualization.arrayToDataTable([
          ['Country', 'Ping', 'Jitter'],
          ['China',<%=ChinaBD.get(2)%>,<%=ChinaBD.get(3)%>],
          ['India',<%=IndiaBD.get(2)%>,<%=IndiaBD.get(3)%>],
          ['Philippines',<%=PhilippinesBD.get(2)%>,<%=PhilippinesBD.get(3)%>],
          ['Poland',<%=polandBD.get(2)%>,<%=polandBD.get(3)%>],
          ['Romania',<%=RomaniaBD.get(2)%>,<%=RomaniaBD.get(3)%>],
          ['South Africa',<%=SouthABD.get(2)%>,<%=SouthABD.get(3)%>],
          ['Sri Lanka',<%=SriLankaBD.get(2)%>,<%=SriLankaBD.get(3)%>],
          ['Spain',<%=SpainBD.get(2)%>,<%=SpainBD.get(3)%>],
          ['UK',<%=UKBD.get(2)%>,<%=UKBD.get(3)%>],
          ['US',<%=USBD.get(2)%>,<%=USBD.get(3)%>],
          ['Costa Rica',<%=CostaRicaBD.get(2)%>,<%=CostaRicaBD.get(3)%>],
          ['Turkey',<%=TurkeyBD.get(2)%>,<%=TurkeyBD.get(3)%>]
        ]);

        var options = {
          title : 'Graph view for Ping and Jitter',
          vAxis: {title: ''},
          hAxis: {title: 'Country'},
          seriesType: 'bars',
          series: {5: {type: 'line'}}
        };

        var chart = new google.visualization.ComboChart(document.getElementById('jitter_div'));
        chart.draw(data, options);
      }
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
      <form method='post' autocomplete="off" action="bandwidthchart.jsp"><input type='hidden' />  
      <TABLE class="filtertable" cellpadding="0"  border="0" >
          <TR>
             <TD width=100></TD>
                <TD width=100>Hostname
                </TD>
                <TD width=100>User ID
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
  
  <div id="chart_div" style="width: 1200px; height: 500px;"></div>
  <div id="jitter_div" style="width: 1200px; height: 500px;"></div>
  
</div> 
</div>
 

    
    

    <b><center> <a>All rights reserved to WNS Global Services</a></center></b> 
</body> 
</html>

