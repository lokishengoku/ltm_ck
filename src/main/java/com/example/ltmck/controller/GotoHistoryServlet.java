package com.example.ltmck.controller;

import com.example.ltmck.model.BO.HistoryBO;
import com.example.ltmck.model.Bean.Account;
import com.example.ltmck.model.Bean.History;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Vector;

@WebServlet("/GotoHistoryServlet")
public class GotoHistoryServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public GotoHistoryServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Account a = (Account) request.getSession().getAttribute("account");
    if (a != null) {
      Vector<History> histories = HistoryBO.getHistory(a.getUid());
      request.getSession().setAttribute("histories", histories);
      response.sendRedirect("History.jsp");
    } else {
      response.sendRedirect("Login.jsp");
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    doGet(request, response);
  }
}
