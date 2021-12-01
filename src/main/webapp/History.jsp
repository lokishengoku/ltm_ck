<%@ page import="java.util.Vector" %>
<%@ page import="com.example.ltmck.model.Bean.History" %>
<%@ page import="com.example.ltmck.model.Bean.Account" %>
<%@ page import="java.io.File" %><%--
  Created by IntelliJ IDEA.
  User: dragc
  Date: 11/29/2021
  Time: 3:41 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>History</title>
    </head>
    <body>
        <%
            Account a = (Account) request.getSession().getAttribute("account");
            if (a != null) {
                Vector<History> histories = (Vector<History>) request.getSession().getAttribute("histories");
        %>
        <table style="border: 1px solid chocolate; border-collapse: collapse; width: 60%; margin: 100px auto;text-align: center;">
            <tr style="margin-top: 10px;">
                <th style="margin-top: 10px; border: 1px solid chocolate;">STT</th>
                <th style="margin-top: 10px; border: 1px solid chocolate;">Status</th>
                <th style="margin-top: 10px; border: 1px solid chocolate;">Result</th>
            </tr>
            <%
                for (int i = 0; i < histories.size(); i++) {
            %>
                <tr valign="middle">
                    <td style="margin-top: 10px; border: 1px solid chocolate;"><%= i + 1 %></td>
                    <td style="margin-top: 10px; border: 1px solid chocolate;"><%= histories.get(i).getStatus() %></td>
                    <td style="margin-top: 10px; border: 1px solid chocolate;">
                        <%
                            if (histories.get(i).getDoc().isEmpty()) {
                        %>
                        "Not available"
                        <%
                            } else {
                        %>
                        <form action="DownloadDocServlet" method="post" >
                            <input type="hidden" name="path" value="<%= histories.get(i).getDoc()%>">
                            <input type="submit" value="Download" style="background-color: coral; border: 1px solid mediumaquamarine; margin-top: 10px">
                        </form>
                        <%
                            }
                        %>
                    </td>
                </tr>
            <%
                }
            %>
        </table>
        <%
            } else {
                response.sendRedirect("Login.jsp");
            }
        %>
    </body>
</html>
