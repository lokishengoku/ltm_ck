package com.example.ltmck.model.DAO;


import com.example.ltmck.model.Bean.Account;

import java.sql.*;

public class CheckLoginDAO {
  public static Account checkAccount(String uid, String pw) {
    //contact db
    System.out.println("Check account");
    try {
      String url = "jdbc:mysql://localhost:3306/dulieu";
      String user = "root";
      String password = "";
      String sql = "SELECT * FROM `account` WHERE uid=\'"+ uid +"\' AND pw=\'"+pw+"\'";
      System.out.println(sql);

      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection = DriverManager.getConnection(url, user, password);
      System.out.println(connection.getCatalog());
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql);

      resultSet.next();
      return new Account(resultSet.getInt(1),
              resultSet.getString(2),
              resultSet.getString(3));
    } catch (SQLException | ClassNotFoundException throwables) {
      throwables.printStackTrace();
      System.out.println("Not found");
    }
    return null;
  }
}
