package alanprovance.Database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbOperations {

    private Logger lgr = Logger.getLogger(DbOperations.class.getName());

    public boolean saveToDatabase(boolean successDay, double dayRating, String note) {
        Date todayDate = today();
        boolean wasSuccess = true;

        if(!statsAlreadyEntered(todayDate)) {

            PreparedStatement pst = null;

            try {
                pst = DbConnector.getDbConnection().prepareStatement("INSERT INTO daily_statistics(jobs_rating, daily_rating, date_added, notes) VALUES(?, ?, ?, ?)");
                pst.setBoolean(1, successDay);
                pst.setInt(2, (int) Math.round(dayRating));
                pst.setDate(3, todayDate);
                pst.setString(4, note);

                pst.executeUpdate();

            } catch (SQLException ex) {
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
                wasSuccess = false;
            } finally {
                closePreparedStatement(pst);
            }
        } else {
            lgr.log(Level.SEVERE, "Failure to save! Previous Entry Found.");
            wasSuccess = false;
        }

        return wasSuccess;
    }

    private boolean statsAlreadyEntered(Date date) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        boolean resultsFound = false;

        try {
            pst = DbConnector.getDbConnection().prepareStatement("SELECT * FROM daily_statistics WHERE date_added = ?");
            pst.setDate(1, date);
            rs = pst.executeQuery();

            resultsFound = rs.next();

        } catch (SQLException ex) {
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            closePreparedStatement(pst);
        }

        return resultsFound;
    }

    private void closePreparedStatement(PreparedStatement pst) {
        try {
            if (pst != null) {
                pst.close();
            }

        } catch (SQLException ex) {
            lgr.log(Level.WARNING, ex.getMessage(), ex);
        }
    }

    private Date today() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        java.util.Date parsed = null;

        try {
            parsed = sdf.parse(sdf.format(new java.util.Date()));
        } catch (ParseException e) {
            lgr.log(Level.SEVERE, e.getMessage(), e);
        }

        return new Date(parsed.getTime());
    }

}
