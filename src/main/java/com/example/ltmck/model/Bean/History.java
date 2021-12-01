package com.example.ltmck.model.Bean;

public class History {
  private int id;
  private String uid;
  private String pdf;
  private String doc;
  private String status;

  public History(int id, String uid, String pdf, String doc, String status) {
    this.id = id;
    this.uid = uid;
    this.pdf = pdf;
    this.doc = doc;
    this.status = status;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getPdf() {
    return pdf;
  }

  public void setPdf(String pdf) {
    this.pdf = pdf;
  }

  public String getDoc() {
    return doc;
  }

  public void setDoc(String doc) {
    this.doc = doc;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
