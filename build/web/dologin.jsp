<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*" errorPage=""%>
<%@page import="myPack.checkAD"%>
<jsp:useBean id="uname"  scope="session" class="myPack.Bean" />

<%

String username = (request.getParameter("username") == null) ? "NA" : request.getParameter("username");
String password = (request.getParameter("password") == null) ? "NA" : request.getParameter("password");
String domain = (request.getParameter("domain") == null) ? "NA" : request.getParameter("domain");
String namechecked;
checkAD trylogin = new checkAD();
String message=trylogin.testlogin(username, password, domain);
if (message.equals("Authentication succeeded!")){
    namechecked =trylogin.checkIT(username);
    if (namechecked.equals("not allowed")){ %> <script type="text/javascript">alert("User not allowed to authenticate");window.location.href="login.jsp"</script><% }
      else{uname.setName(namechecked);uname.setUid(username);response.sendRedirect("bandwidth.jsp"); }
    }
else{%> <script type="text/javascript">alert("<%=message%>");window.location.href="login.jsp"</script><% }
%>
<script type="text/javascript">
    alert("<%=message%>");
</script> 
