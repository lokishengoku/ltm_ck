package com.example.ltmck.model.BO;

import com.example.ltmck.model.Bean.History;
import com.example.ltmck.model.DAO.HistoryDAO;

import java.util.Vector;

public class HistoryBO {
  public static Vector<History> getHistory(String uid) {
    return HistoryDAO.getHistory(uid);
  }
  public static History addNewTask(String uid, String pdfPath, String docPath) { return HistoryDAO.addNewTask(uid, pdfPath, docPath); }
  public static void updateStatus(int historyId, String newStatus) {
    HistoryDAO.updateStatus(historyId, newStatus);
  }
  public static void updateDocPath(int historyId, String docPath) {
    HistoryDAO.updateDocPath(historyId, docPath);
  }
}
