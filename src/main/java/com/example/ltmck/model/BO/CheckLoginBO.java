package com.example.ltmck.model.BO;


import com.example.ltmck.model.Bean.Account;
import com.example.ltmck.model.DAO.CheckLoginDAO;

public class CheckLoginBO {
  public static Account checkAccount(String id, String pw) {
    return CheckLoginDAO.checkAccount(id, pw);
  }
}
