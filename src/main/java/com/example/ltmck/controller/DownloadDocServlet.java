package com.example.ltmck.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.zip.GZIPOutputStream;

@WebServlet("/DownloadDocServlet")
public class DownloadDocServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  static byte[] data = null;

  public DownloadDocServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/octet");
    response.setHeader("Content-Disposition", "inline; filename=\"ltm.docx\"");
    response.setHeader("Content-Encoding", "gzip");

    String file_path = request.getParameter("path");
    System.out.println(file_path);

    FileInputStream fileInputStream = new FileInputStream(file_path);
    BufferedInputStream bis = new BufferedInputStream(fileInputStream);
    BufferedOutputStream os = new BufferedOutputStream(new GZIPOutputStream(response.getOutputStream()));
    if(data==null){
      data = bis.readAllBytes();
    }
    os.write(data);
    os.close();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    doGet(request, response);
  }
}
