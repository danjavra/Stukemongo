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
import modelo.Pokeparada;
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
        
        User x1 = new User("Ash Ketchum", "123456", 0, 0, 0, "Aula Mac", 0, 0 );
        Pokemon j1 = new Pokemon("Bulbasur", "Planta", 100, 80, "Lavabos");
        Pokeparada m1 = new Pokeparada("Parada 1", "Sala Actos", 3, 1 );
        
        System.out.println("************************************************************");
        System.out.println("Testeando conexión con la base de datos...");
        try{
            stukemonGoDAO.conectar();
            System.out.println("Establecida la conexión.");  
            
            System.out.println("************************************************************");
            System.out.println("Testeando insert user " + x1.getUsername());
            altaUser(stukemonGoDAO, x1);
            System.out.println("************************************************************");
            System.out.println("Testeando insert user duplicado " + x1.getUsername());
            altaUser(stukemonGoDAO, x1);
            System.out.println("************************************************************");
            System.out.println("Testeando select user by username: " + x1.getUsername());
            getUserByName(stukemonGoDAO, x1);
            
            System.out.println("************************************************************");
            System.out.println("Testeando insert pokemon " + j1.getName());
            altaPokemon(stukemonGoDAO, j1);
            System.out.println("************************************************************");
            System.out.println("Testeando insert pokemon duplicado " + j1.getName());
            altaPokemon(stukemonGoDAO, j1);
            
            System.out.println("************************************************************");
            System.out.println("Testeando insert pokeparada " + m1.getName());
            altaPokeparada(stukemonGoDAO, m1);
            System.out.println("************************************************************");
            System.out.println("Testeando insert pokeparada duplicado " + m1.getName());
            altaPokeparada(stukemonGoDAO, m1);
            
        } catch (SQLException ex) {
            System.out.println("Error al conectar / desconectar: " + ex.getMessage());
        }
    
    }
    
      private static void altaUser(StukemonGoDAO stukemonGoDAO, User x1) throws SQLException {
        try {
            stukemonGoDAO.insertarUser(x1);
            System.out.println("Usuario dado de alta");
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void getUserByName(StukemonGoDAO stukemonGoDAO, User x1) throws SQLException {
        try {
            User usu = stukemonGoDAO.getUserByName(x1);
            System.out.println(usu);
            
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void altaPokemon(StukemonGoDAO stukemonGoDAO, Pokemon j1) throws SQLException {
        try {
            stukemonGoDAO.insertarPokemon(j1);
            System.out.println("Pokemon dado de alta");
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void altaPokeparada(StukemonGoDAO stukemonGoDAO, Pokeparada m1) throws SQLException {
        try {
            stukemonGoDAO.insertarPokeparada(m1);
            System.out.println("Pokeparada dado de alta");
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
    }
}