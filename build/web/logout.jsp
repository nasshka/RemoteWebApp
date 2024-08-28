<jsp:useBean id="uname" scope="session" class="myPack.Bean" />
<%
uname.removeName();
session  = request.getSession();
if(session !=null)
try
{      
    session.removeAttribute("logonSessData");
    session.invalidate();                               
    //String pageToForward = request.getContextPath();
    response.sendRedirect("login.jsp");           }
catch (Exception sqle)
{
    System.out.println("error UserValidateServlet message : " + sqle.getMessage());
    System.out.println("error UserValidateServlet exception : " + sqle);
}
else
{
  //session already null/ expired
}

%>
