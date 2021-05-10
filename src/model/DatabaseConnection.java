package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {

    private Connection conn;

    public boolean connect() {
        try {
            Class.forName(Constants.DRIVER);
            conn = DriverManager.getConnection(Constants.URL, Constants.USER_NAME, Constants.PASSWORD);
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public String insert(String nom, Date date_creation, boolean cnam, float prix_consultation) {
        try {
            String query = "insert into cliniques (nom, date_creation, cnam, prix_consultation)"
                    + " values (?, ?, ?, ?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, nom);
            preparedStmt.setDate(2, date_creation);
            preparedStmt.setBoolean(3, cnam);
            preparedStmt.setFloat(4, prix_consultation);
            preparedStmt.execute();

            conn.close();
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
