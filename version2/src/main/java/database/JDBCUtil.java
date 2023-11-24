package database;

import model.Word;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class JDBCUtil {
    public static Connection getConnection() {
        Connection c = null;
        String url = "jdbc:mySQL://localhost:3306/test";
        String username = "root";
        String password = "";
        try {
            c = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return c;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Connection connection = JDBCUtil.getConnection();
        ArrayList<Word> res = WordDAO.getInstance().selectAll();
        System.out.println(res);
    }
}
