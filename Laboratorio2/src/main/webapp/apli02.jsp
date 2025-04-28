<%--
  Created by IntelliJ IDEA.
  User: ItzDaniel
  Date: 26/03/2025
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    double n1 = Double.parseDouble(request.getParameter("txtn1"));
    double n2 = Double.parseDouble(request.getParameter("txtn2"));
    double s,r,m,d;
    s = n1+n2;
    r = n1-n2;
    m = n1*n2;
    d = n1/n2;
    out.println("La suma es: "+s);
    out.println("La resta es: "+r);
    out.println("La multiplicacion es: "+m);
    out.println("La division es: "+d);


%>
</body>
</html>
