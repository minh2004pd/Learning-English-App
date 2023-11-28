package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.PreparableStatement;
import model.Word;

public class WordDAO implements DAOInterface<Word> {
    public static WordDAO getInstance() {
        return new WordDAO();
    }

    @Override
    public int insert(Word w) {
        int res = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "INSERT INTO tbl_edict (idx, word, detail)"
                    + " VALUES(?,?,?)";
            PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1, w.getId());
            st.setString(2, w.getWord_target());
            st.setString(3, w.getWord_explain());

            res = st.executeUpdate();
            System.out.println(sql);

            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int update(Word w) {
        int res = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "UPDATE tbl_edict "
                    + " SET "
                    + " word = ?, "
                    + " detail = ? "
                    + " WHERE idx = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, w.getWord_target());
            pst.setString(2, w.getWord_explain());
            pst.setInt(3, w.getId());

            res = pst.executeUpdate();

            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public int delete(Word w) {
        int res = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "DELETE FROM tbl_edict WHERE idx = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, w.getId());

            res = pst.executeUpdate();

            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public ArrayList<Word> selectAll() throws Exception {
        ArrayList<Word> res = new ArrayList<Word>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM tbl_edict ";
            PreparedStatement pst = c.prepareStatement(sql);

            ResultSet resultSet = pst.executeQuery();

            while(resultSet.next()) {
                int wordID = resultSet.getInt("idx");
                String word = resultSet.getString("word");
                String detail = resultSet.getString("detail");
                Word newWords = new Word(wordID, word, detail);
                res.add(newWords);
            }

            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    @Override
    public Word selectById(Word w) {
        Word res = null;
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM tbl_edict"
                    + " WHERE idx = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, w.getId());

            ResultSet resultSet = pst.executeQuery();

            while(resultSet.next()) {
                int wordID = resultSet.getInt("idx");
                String word = resultSet.getString("word");
                String detail = resultSet.getString("detail");
                Word newWords = new Word(wordID, word, detail);
                res = newWords;
            }

            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public ArrayList<Word> selectByConditions(String condition) {
        ArrayList<Word> res = new ArrayList<Word>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM tbl_edict"
                    + " WHERE " + condition;
            PreparedStatement pst = c.prepareStatement(sql);

            ResultSet resultSet = pst.executeQuery();

            while(resultSet.next()) {
                int wordID = resultSet.getInt("idx");
                String word = resultSet.getString("word");
                String detail = resultSet.getString("detail");
                Word newWords = new Word(wordID, word, detail);
                res.add(newWords);
            }

            JDBCUtil.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
