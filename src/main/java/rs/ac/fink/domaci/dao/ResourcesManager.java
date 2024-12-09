package rs.ac.fink.domaci.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import rs.ac.fink.domaci.exception.RacunarskaOpremaException;

public class ResourcesManager {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/online_shop?user=root&password=");
        return con;
    }

    public static void closeResources(ResultSet resultSet, PreparedStatement preparedStatement) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }

    public static void closeConnection(Connection con) throws RacunarskaOpremaException {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                throw new RacunarskaOpremaException("Failed to close database connection.", ex);
            }
        }
    }

    public static void rollbackTransactions(Connection con) throws RacunarskaOpremaException {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new RacunarskaOpremaException("Failed to rollback database transactions.", ex);
            }
        }
    }
}
