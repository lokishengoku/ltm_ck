package com.example.ltmck.model.DAO;


import com.example.ltmck.model.Bean.History;

import java.sql.*;
import java.util.Vector;

public class HistoryDAO {
  public static Vector<History> getHistory(String uid) {
    //contact db
    System.out.println("get history");
    Vector<History> histories = new Vector<>();
    try {
      Connection connection = getConnection();

      String sql = "SELECT * FROM `history` WHERE uid=\'"+ uid + "\'";
      System.out.println(sql);

      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql);

      while (resultSet.next()) {
        histories.add(new History(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5)
        ));
      }
      connection.close();
    } catch (SQLException | ClassNotFoundException throwables) {
      throwables.printStackTrace();
      System.out.println("Not found");
    }
    return histories;
  }

  public static History addNewTask(String uid, String pdfPath, String docPath) {
    //contact db
    System.out.println("add new task");
    try {
      Connection connection = getConnection();
      String sql = "INSERT INTO `history` (uid, pdf, doc, status) VALUES (?, ?, ?, ?)";
      System.out.println(sql);

      PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, uid);
      preparedStatement.setString(2, pdfPath);
      preparedStatement.setString(3, docPath);
      preparedStatement.setString(4, "Processing");
      int rowAffected = preparedStatement.executeUpdate();

      System.out.println(String.format("Row affected %d", rowAffected));
      if (rowAffected == 1) {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
          if (generatedKeys.next()) {
            return new History(
              generatedKeys.getInt(1),
              uid,
              pdfPath,
              docPath,
              "Processing"
            );
          } else {
            throw new SQLException("Add new task failed");
          }
        }
      }
      connection.close();
    } catch (SQLException | ClassNotFoundException throwables) {
      throwables.printStackTrace();
      System.out.println("Add new task failed");
    }
    return null;
  }

  public static void updateStatus(int historyId, String newStatus) {
    //contact db
    System.out.println("update status");
    try {
      Connection connection = getConnection();
      String sql = "UPDATE `history` SET status=? WHERE id=?";
      System.out.println(sql);

      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, newStatus);
      preparedStatement.setInt(2, historyId);
      int rowAffected = preparedStatement.executeUpdate();

      System.out.println(String.format("Row affected %d", rowAffected));
      connection.close();
    } catch (SQLException | ClassNotFoundException throwables) {
      throwables.printStackTrace();
      System.out.println("Not found");
    }
  }

  public static void updateDocPath(int historyId, String docPath) {
    //contact db
    System.out.println("update status");
    try {
      Connection connection = getConnection();
      String sql = "UPDATE `history` SET doc=? WHERE id=?";
      System.out.println(sql);

      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, docPath);
      preparedStatement.setInt(2, historyId);
      int rowAffected = preparedStatement.executeUpdate();

      System.out.println(String.format("Row affected %d", rowAffected));
      connection.close();
    } catch (SQLException | ClassNotFoundException throwables) {
      throwables.printStackTrace();
      System.out.println("Not found");
    }
  }

  private static Connection getConnection() throws ClassNotFoundException, SQLException {
    String url = "jdbc:mysql://localhost:3306/dulieu";
    String user = "root";
    String password = "";

    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection(url, user, password);
    System.out.println(connection.getCatalog());

    return connection;
  }
}
