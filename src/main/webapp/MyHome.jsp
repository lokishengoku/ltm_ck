<%@ page import="com.example.ltmck.model.Bean.Account" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>My Home</title>
    </head>
    <body>
        <%
            Account a = (Account) request.getSession().getAttribute("account");
            if (a != null) {
        %>
        <div style=" width: 260px; margin: 220px auto;text-align: center;">
            <form method="post" action="UploadPdfServlet"
                  enctype="multipart/form-data"
                  style="border: 1px solid gray;border-radius: 8px; display: block;text-align: center;padding: 12px;">
                <label for="file_input">
                    <img width="120px"
                         src="https://icons-for-free.com/iconfiles/png/512/box+document+outline+share+top+upload+icon-1320195323221671611.png"
                         alt="upload_image" srcset="">
                </label>
                <input type="file" accept="application/pdf" name="pdf_file" id="file_input"/>
                <p>Upload your PDF file</p>
                <button>Submit</button>
            </form>
            <form action="GotoHistoryServlet">
                <button style="margin-top: 8px;">Open History</button>
            </form>
        </div>
        <%
            } else {
                response.sendRedirect("Login.jsp");
            }
        %>
    </body>
</html>
