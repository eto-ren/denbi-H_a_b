package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class loginDAO {
	  // データベース接続に使用する情報
	  private final String JDBC_URL = "jdbc:postgresql://localhost:5432/denbi H_a_b";
	  private final String DB_USER = "postgres";
	  private final String DB_PASS = "postgres";
	  public List<User> findAll() {
		    List<User> userList = new ArrayList<User>();
		    try {
		        Class.forName("org.postgresql.Driver");
		    } catch (ClassNotFoundException e) {
		        throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		    }
		   
		    // データベース接続
		    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
	
		      // SELECT文の準備
		      String sql = "SELECT name,pass FROM User ORDER BY id DESC";
		      PreparedStatement pStmt = conn.prepareStatement(sql);
	
		      // SELECT文を実行
		      ResultSet rs = pStmt.executeQuery();
	
		      // SELECT文の結果をArrayListに格納
		      while (rs.next()) {
		        String userName = rs.getString("name")
		        String pass = rs.getString("pass");
		        User user = new User(name, pass);
		        userList.add(user);
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		      return null;
		    }
		    return userList;
		  }
		  public boolean create(User user) {
			  try {
			        Class.forName("org.postgresql.Driver");
			    } catch (ClassNotFoundException e) {
			        throw new IllegalStateException("JDBCドライバを読み込めませんでした");
			    }
		    // データベース接続
		    try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
	
		      // INSERT文の準備(idは自動連番なので指定しなくてよい）
		      String sql = "INSERT INTO mutter(userName, pass) VALUES(?, ?)";
		      PreparedStatement pStmt = conn.prepareStatement(sql);
		      
		      // INSERT文中の「?」に使用する値を設定しSQLを完成
		      pStmt.setString(1, user.getName());
		      pStmt.setString(2, pass.getPass());
	
		      // INSERT文を実行（resultには追加された行数が代入される）
		      int result = pStmt.executeUpdate();
		      if (result != 1) {
		        return false;
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		      return false;
		    }
	    return true;
	}
}