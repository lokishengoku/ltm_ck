<%@ page import="com.example.ltmck.model.Bean.Account" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
    </head>
    <body>
        <%
            Account a = (Account) request.getSession().getAttribute("account");
            if (a == null) {
        %>
        <div style="margin: 100px auto;text-align: center; width: 100%;">
            <div style="border: 1px solid chocolate; display: inline-block; padding: 20px 50px;">
                <h3>LOGIN</h3>
                <form action="GotoMyHomeServlet" method="post">
                    <table style="border-collapse: collapse;margin: 0 auto;">
                        <tr style="margin-top: 10px;">
                            <td style="margin-top: 10px;">Username</td>
                            <td style="margin-top: 10px;">
                                <label>
                                    <input type="text" name="id">
                                </label>
                            </td>
                        </tr>
                        <tr style="margin-top: 10px;">
                            <td style="margin-top: 10px;">Password</td>
                            <td style="margin-top: 10px;">
                                <label>
                                    <input type="password" name="pw">
                                </label>
                            </td>
                        </tr>
                        <tr style="margin-top: 10px;">
                            <td colspan="2" style="text-align: center; vertical-align: center;">
                                <input style="margin-top: 20px;" type="submit" value="Đăng nhập">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <%
            } else {
                response.sendRedirect("MyHome.jsp");
            }
        %>
    </body>
</html>
