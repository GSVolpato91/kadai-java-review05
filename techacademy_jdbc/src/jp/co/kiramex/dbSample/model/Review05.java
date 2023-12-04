package jp.co.kiramex.dbSample.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Review05 {

    public static void main(String[] args) {

      // 3. Variable declaration for DB connection and result retrieval
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
     // 1. Load driver class on Java
        Class.forName("com.mysql.cj.jdbc.Driver");

     // 2. Connect to DB
        con = DriverManager.getConnection(
                "jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true",
                "root",
                "Guisan123**");

     // 4. Create window to interact with DB (Prepared statement
        String sql = "SELECT * FROM kadaidb.person WHERE id = ?";
        pstmt = con.prepareStatement(sql);  //

     // 5, 6. Execute Select statement and store/assign result
        System.out.print("検索キーワードを入力してください > ");
        String input = keyIn();

        // Prepared Statement
        pstmt.setString(1, input);

        rs = pstmt.executeQuery();


     // 7. Display the results
        while (rs.next()) {

            String name = rs.getString("name");
            int age = rs.getInt("age");

            System.out.println(name);
            System.out.println(age);

        }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
     // 8. Close the connection
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
private static String keyIn() {
    String line = null;
    try {
        BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
        line = key.readLine();
    } catch (IOException e) {

    }
    return line;
}

}
