/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Montoo
 */
public class Persoon {
    private int pcode;
    
    public Persoon() {
        
    }
    
       public void pCode() {
        try {
            Connection connectie = DatabaseConnectie.getConnection();
            Statement statement = connectie.createStatement();
            
            String query = "select max(p_code) as maxpcode from persoon";
            
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
            pcode = result.getInt("maxpcode"); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Persoon.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("pCode Statement error!");
        }
    }
    
    
    
    public void persoonToevoegen(String voornaam, String achternaam, String adres, 
                                 String woonplaats, String postcode, int telefoonNummer, String emailAdres) {
        
        try {
            String query = "insert into persoon (p_code, voornaam, achternaam, adres, woonplaats, postcode, telefoon_nummer, email_adres) " +
                           "values(?, ?, ?, ?, ?, ?, ?, ? );";
            Connection connection = DatabaseConnectie.getConnection();
            PreparedStatement persoonStatement = connection.prepareStatement(query);
            
           pCode();
           int nieuwePcode = pcode + 1;
           
           persoonStatement.setInt(1, nieuwePcode ); 
           persoonStatement.setString(2, voornaam);
           persoonStatement.setString(3, achternaam);
           persoonStatement.setString(4, adres);
           persoonStatement.setString(5, woonplaats);
           persoonStatement.setString(6, postcode);
           persoonStatement.setInt(7, telefoonNummer);
           persoonStatement.setString(8, emailAdres);
           
           persoonStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Persoon.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("persoonToevoegen Statement fout!");
        }
    }
    

        
        

    

    
}
