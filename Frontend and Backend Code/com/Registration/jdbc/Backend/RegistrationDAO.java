package com.registration.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationDAO {

    public void create() throws SQLException {
        createRegistration("Mohan", "Mohan@example.com", "1998-01-01");
        createRegistration("Suraj", "suraj@example.com", "1995-05-05");
    }

    private void createRegistration(String name, String email, String dob) throws SQLException {
        // Check for empty fields
        if (name == null || name.isEmpty() || email == null || email.isEmpty() || dob == null || dob.isEmpty()) {
            System.out.println("All fields are required.");
            return;
        }

        // SQL query to insert 
        String query = "INSERT INTO Registration (Name, Email, DateOfBirth) VALUES (?, ?, ?)";
        
        // Create database connection and prepare statement
        Connection conn = Databaseconnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        
        // Set the values for the query
        stmt.setString(1, name);
        stmt.setString(2, email);
        stmt.setString(3, dob);
        
        // Execute the insert
        stmt.executeUpdate();
        System.out.println("Registration created successfully for " + name + ".");
        
        // Close the resources
        stmt.close();
        conn.close();
    }

    public void readRegistration(int id) throws SQLException {
        String query = "SELECT * FROM Registration WHERE ID = ?";
        
        Connection conn = Databaseconnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        
        stmt.setInt(1, id); 
        
        ResultSet rs = stmt.executeQuery(); 
        
        if (rs.next()) {
            System.out.println("ID: " + rs.getInt("ID"));
            System.out.println("Name: " + rs.getString("Name"));
            System.out.println("Email: " + rs.getString("Email"));
            System.out.println("Date of Birth: " + rs.getDate("DateOfBirth"));
        } else {
            System.out.println("Registration not found.");
        }
        
        rs.close();
        stmt.close();
        conn.close();
    }

    public void updateRegistration(int id, String name, String email, String dob) throws SQLException {
        String query = "UPDATE Registration SET Name = ?, Email = ?, DateOfBirth = ? WHERE ID = ?";
        
        Connection conn = Databaseconnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        
        stmt.setString(1, name);
        stmt.setString(2, email);
        stmt.setString(3, dob);
        stmt.setInt(4, id);
        
        int rowsUpdated = stmt.executeUpdate(); 
        
        if (rowsUpdated > 0) {
            System.out.println("Registration updated successfully for " + name + ".");
        } else {
            System.out.println("Registration not found.");
        }
        
        stmt.close();
        conn.close();
    }

    public void deleteRegistration(int id) throws SQLException {
        String query = "DELETE FROM Registration WHERE ID = ?";
        
        Connection conn = Databaseconnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        
        stmt.setInt(1, id); 
        
        int rowsDeleted = stmt.executeUpdate(); 
        
        if (rowsDeleted > 0) {
            System.out.println("Registration deleted successfully.");
        } else {
            System.out.println("Registration not found.");
        }
        
        stmt.close();
        conn.close();
    }
}
