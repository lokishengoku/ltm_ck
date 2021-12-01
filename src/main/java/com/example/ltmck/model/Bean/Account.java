package com.example.ltmck.model.Bean;

public class Account {
  int id;
  String uid, pw;

  public Account(int id, String uid, String pw) {
    this.id = id;
    this.uid = uid;
    this.pw = pw;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getPw() {
    return pw;
  }

  public void setPw(String pw) {
    this.pw = pw;
  }
}
