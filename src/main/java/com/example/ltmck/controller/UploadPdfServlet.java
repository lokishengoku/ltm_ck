package com.example.ltmck.controller;

import com.example.ltmck.model.BO.ConvertBO;
import com.example.ltmck.model.BO.HistoryBO;
import com.example.ltmck.model.Bean.Account;
import com.example.ltmck.model.Bean.History;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

@WebServlet("/UploadPdfServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadPdfServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
//  ExecutorService executorService = Executors.newSingleThreadExecutor();
  PdfHandler pdfHandler;
  public UploadPdfServlet() {
    super();
    pdfHandler = new PdfHandler();
    new Thread(pdfHandler).start();
    System.out.println("init upload servlet");
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws IOException, jakarta.servlet.ServletException {
    Account a = (Account) request.getSession().getAttribute("account");
    if (a != null) {
    for (Part part : request.getParts()) {
        String fileName = extractFileName(part);
        fileName = new File(fileName).getName();
        fileName = System.currentTimeMillis() + "_" + fileName;
        String pdfPath = this.getFolderPdf().getAbsolutePath() + File.separator + fileName;
        String docPath = this.getFolderDoc().getAbsolutePath() + File.separator + this.pdfToDocxPath(fileName);
        part.write(pdfPath);

        // Save to database
        History newTask = HistoryBO.addNewTask(a.getUid(), pdfPath, docPath);

        // Saved
        if (newTask != null) {
          pdfHandler.addTask(newTask);
          response.sendRedirect("GotoHistoryServlet");
        }
    }
    } else {
      response.sendRedirect("Login.jsp");
    }
  }

  private String extractFileName(Part part) {
    String contentDisp = part.getHeader("content-disposition");
    String[] items = contentDisp.split(";");
    for (String s : items) {
      if (s.trim().startsWith("filename")) {
        return s.substring(s.indexOf("=") + 2, s.length() - 1);
      }
    }
    return "";
  }

  private String pdfToDocxPath(String pdfName) {
    return pdfName.replace(".pdf", ".docx");
  }

  public File getFolderPdf() {
    File folderVideo = new File("E:\\.WORK\\HK7\\LTM\\ltm_ck\\src\\main\\resources\\pdf");
    if (!folderVideo.exists()) {
      folderVideo.mkdirs();
    }
    return folderVideo;
  }

  public File getFolderDoc() {
    File folderAudio = new File("E:\\.WORK\\HK7\\LTM\\ltm_ck\\src\\main\\resources\\doc");
    if (!folderAudio.exists()) {
      folderAudio.mkdirs();
    }
    return folderAudio;
  }
}

class PdfHandler implements Runnable{
  Queue<History> historyQueue = new LinkedList<>();

  synchronized void addTask (History history) {
    historyQueue.offer(history);
  }

  public PdfHandler() {

  }

  @Override
  public void run() {
    while (true) {
      try {
        if (historyQueue.size() > 0) {
          History history = historyQueue.peek();
          historyQueue.remove();
          System.out.println("Processing history: " + history.getId());
          ConvertBO.cvPdfToDocx(new File(history.getPdf()), new File(history.getDoc()));
          HistoryBO.updateStatus(history.getId(), "Finished");
        } else {
          Thread.sleep(3000);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}