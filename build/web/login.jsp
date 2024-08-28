<%-- 
    Document   : login
    Created on : Aug 6, 2020, 5:05:18 PM
    Author     : u274612
--%>
<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*" errorPage=""%>
<%@page import="myPack.Lista"%>

<!DOCTYPE html>
<head> 
<meta http-equiv="Cache-control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Expires" content="0">
<title> Login </title> 
<script src="css/doc.js"></script>
<link rel='stylesheet' type='text/css' href='css/login.css' /> 
</head> 
<link rel="shortcut icon" href="/images/wnslogo.png">
<body>
    <img src="images/ivectorBack.jpg" class="bg">
<div class='wrapper'> 
<div class='main'> 

<div style='padding-top: 5px;float:right;max-width:320px;'> 
<div style='float:left;padding: 4px 10px 4px 0px;font-weight:bold;font-size:11px;'>Login</div> 
<div class='clearfix'></div> 

<form id='form1' method='post' autocomplete='off' action="dologin.jsp">

<div style='float:right;padding: 4px 10px 4px 0px;'><input type='text' size='40' name='username' id='username' onfocus="if (this.value == 'username') this.value='';" value='username' /></div> 
<div class='clearfix'></div> 

<div style='float:right;padding: 4px 10px 4px 0px;'><input type='password' size='40' name='password' autocomplete='off' id='password' onfocus="if (this.value == 'password') this.value='';" value='password'/></div> 
<div class='clearfix'></div> 
<div style='float:left;padding: 4px 10px 4px 0px;'><select name="domain" id="domain">
  <option value="sharedservices.wind.wnsgroup.net">SharedServices</option>
  <option value="wind.wnsgroup.net">WIND</option></div> </select>
<div class='clearfix'></div> 
<div style='float:left;position:relative;top:8px;'> 
<div style='float:left;margin-right:5px;'></div> 
<div style='float:left;'></div> 
<div style='float:left;margin-right:5px;margin-left:5px;'></a></div> 
<div style='float:left;'></div> 
<div style='float:left;margin-left:5px;'></div> 
<div class='clearfix'></div> 
</div> 
<div style='padding-top:2px;'><input type='submit' id='cryptstr' value='Login' class='btn' /></div> 
<div class='clearfix'></div>
</form> 
</div> 
<div class='clearfix'></div>


</div> 
</div>
   
</body> 
</html>

