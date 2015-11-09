package sample.Database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnector {

    private static DbConnector dbConn = new DbConnector();
    private static Connection con = null;

    private static String url = "jdbc:postgresql://localhost/derekprovance";
    private static String user = "test";

    //TODO - look into proper storage of database passwords with client side applications
    private static String password = "<enter password>";

    private static Logger lgr = Logger.getLogger(DbConnector.class.getName());

    private DbConnector() { }

    public static DbConnector getInstance() {
        return dbConn;
    }

    public static Connection getDbConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(url, user, password);
            } catch (SQLException ex) {
                lgr.log(Level.SEVERE, ex.getMessage(), ex);

            }
        }
        return con;
    }

    public static void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}