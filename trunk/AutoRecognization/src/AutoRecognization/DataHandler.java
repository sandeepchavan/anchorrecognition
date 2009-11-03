/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AutoRecognization;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ddda
 */
public class DataHandler {

    private static DataHandler instance = null;
    private Connection cn = null;

    public static DataHandler Instance(String url, String username, String password) {
        if (instance == null) {
            instance = new DataHandler();
        }
        try {
            if (instance.cn == null || instance.cn.isClosed()) {
                instance.connectDB(url, username, password);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return instance;
    }

    private boolean connectDB(String url, String username, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            cn = DriverManager.getConnection(url, username, password);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean updOrinstData(String path, String value) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            path = path.replace("\\", "/");
            pstmt = cn.prepareStatement("select id from gbs_douglas_card.start " +
                    "where fullfilename1 = ?");
            pstmt.setString(1, path);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                long id = rs.getLong(1);
                pstmt = cn.prepareStatement("update gbs_douglas_card.start set antragsnummer = ? where id = ?");
                pstmt.setString(1, value);
                pstmt.setLong(2, id);
                pstmt.execute();
                return true;
            }
            return false;
        } catch (Exception ex) {
            return false;
        } finally {
        }
    }

    public void closeConnection() {
        try {
            if (!cn.isClosed()) {
                cn.close();
            }
        } catch (Exception ex) {
        }
    }
}