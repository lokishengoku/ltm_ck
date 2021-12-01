package com.example.ltmck.controller;


import com.example.ltmck.model.BO.CheckLoginBO;
import com.example.ltmck.model.Bean.Account;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/GotoMyHomeServlet")
public class GotoMyHomeServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public GotoMyHomeServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //2
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");

    //3, 3'
    Account a = CheckLoginBO.checkAccount(id, pw);

    //4, 4'
    if (a == null) {
      response.sendRedirect("Login.jsp");
    } else {
      request.getSession().setAttribute("account", a);
      response.sendRedirect("MyHome.jsp");
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    doGet(request, response);
  }
}
