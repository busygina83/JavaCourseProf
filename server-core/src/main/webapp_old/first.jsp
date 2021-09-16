<html>
<head><title>First JSP</title></head>
<body>
<%@ page contentType="text/html; charset=UTF-8" %>
  <%
    double num = Math.random();
    if (num > 0.95) {
  %>
      <h2>Удачный день для Вас!</h2><p>(<%= num %>)</p>
  <%
    } else {
  %>
      <h2>Что ж, жизнь все равно продолжается ... </h2><p>(<%= num %>)</p>
  <%
    }
  %>
  <a href="<%= request.getRequestURI() %>"><h3>Еще шанс!</h3></a>
</body>
</html>