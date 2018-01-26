/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stukemongo;

import stukemongo.StukemonGo;
import dao.StukemonGoDAO;
import excepciones.MiExcepcion;
import java.sql.SQLException;
import modelo.User;
import modelo.Pokemon;
/**
 *
 * @author DAM
 */
public class StukemonGo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        StukemonGoDAO stukemonGoDAO = new StukemonGoDAO();
        
        User x1 = new User("Ash Ketchum", "123456", 20, 0, 0, "Aula Mac", 0, 0 );
        
        System.out.println("************************************************************");
        System.out.println("Testeando conexión con la base de datos...");
        try{
            stukemonGoDAO.conectar();
            System.out.println("Establecida la conexión.");  
            
            System.out.println("************************************************************");
            System.out.println("Testeando insert user " + x1.getUsername());
            altaUser(stukemonGoDAO, x1);
        } catch (SQLException ex) {
            System.out.println("Error al conectar / desconectar: " + ex.getMessage());
        }
    
    }
    
      private static void altaUser(StukemonGoDAO stukemonGoDAO, User x1) throws SQLException {
        try {
            stukemonGoDAO.insertarUser(x1);
            System.out.println("Cocinero dado de alta");
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
    }
}
