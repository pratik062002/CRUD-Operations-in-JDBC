package com.registration.jdbc;

import java.sql.SQLException;

public class RegistrationMain {

    public static void main(String[] args) {
        RegistrationDAO registrationDAO = new RegistrationDAO();

        try {
           // registrationDAO.create(); 
           // registrationDAO.readRegistration(1); 
          //  registrationDAO.updateRegistration(2, "Pawan", "pawan@example.com", "1994-05-12"); 
           registrationDAO.deleteRegistration(2);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}
