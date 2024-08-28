<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*" errorPage=""%>
<%@page import="myPack.Lista"%>
<jsp:useBean id="uname" scope="session" class="myPack.Bean" />
<%

String username = (request.getParameter("username") == null) ? "NA" : request.getParameter("username");
String password = (request.getParameter("cookieID") == null) ? "NA" : request.getParameter("cookieID");
if (username.equals("")) {%> <script type="text/javascript">alert("Username or password empty field");window.location.href="login.jsp"</script><%}
//check string
Lista checkLogin = new Lista();


String[] isValid =checkLogin.tryLogin(username, password);
if (isValid[0].equals("true")  ) {

uname.setName(username);
response.sendRedirect("users.jsp");
} else {%> <script>alert("<%=isValid[1]%>");window.location.href="login.jsp"</script><%}
%>