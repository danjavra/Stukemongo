/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import excepciones.MiExcepcion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Pokemon;
import modelo.Pokeparada;
import modelo.User;

/**
 *
 * @author DAM
 */
public class StukemonGoDAO {
    private Connection conexion;
    

      public void insertarUser ( User a) throws SQLException, MiExcepcion {
      if (existeUser(a)){
          throw new MiExcepcion("Ya existe un usuario con ese nombre");
      } else { 
        // Definimos la consulta
        String insert = "insert into user values (?, ?, ?, ?, ?, ?, ?, ?)";
        // Necesitamos preparar la consulta parametrizada
        PreparedStatement ps = conexion.prepareStatement(insert); 
        // Le damos valor a los interrogantes
        ps.setString(1, a.getUsername());
        ps.setString(2, a.getPassword());
        ps.setInt(3, a.getPokeballs());
        ps.setInt(4, a.getPotions());
        ps.setInt(5, a.getLevel());
        ps.setString(6, a.getPlace());
        ps.setInt(7, a.getPokecoins());
        ps.setInt(8, a.getPoints());
        // Ejecutamos la consulta
        ps.executeUpdate();
        // cerramos recursos
        ps.close();
      }
    }
    
        private boolean existeUser(User a) throws SQLException {
        String select = "select * from user where username='" + a.getUsername() + "'";
        Statement st = conexion.createStatement();
        boolean existe = false;
        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            existe = true;
        }
        rs.close();
        st.close();
        return existe;
    }
   
      public void insertarPokemon (Pokemon j) throws SQLException, MiExcepcion {
      if (existePokemon(j)){
          throw new MiExcepcion("Ya existe un pokemon con ese nombre");
      } else { 
        // Definimos la consulta
        String insert = "insert into pokemon values (?, ?, ?, ?, ?)";
        // Necesitamos preparar la consulta parametrizada
        PreparedStatement ps = conexion.prepareStatement(insert); 
        // Le damos valor a los interrogantes
        ps.setString(1, j.getName());
        ps.setString(2, j.getType());
        ps.setInt(3, j.getPc());
        ps.setInt(4, j.getLife());
        ps.setString(5, j.getPlace());
        
        ps.executeUpdate();
        ps.close();
      }
    }
      
    private boolean existePokemon(Pokemon a) throws SQLException {
        String select = "select * from pokemon where name='" + a.getName() + "'";
        Statement st = conexion.createStatement();
        boolean existe = false;
        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            existe = true;
        }
        rs.close();
        st.close();
        return existe;
    }  
    
      public void insertarPokeparada (Pokeparada n) throws SQLException, MiExcepcion {
      if (existePokeparada(n)){
          throw new MiExcepcion("Ya existe una pokeparada con ese nombre");
      } else { 
        // Definimos la consulta
        String insert = "insert into pokeparada values (?, ?, ?, ?)";
        // Necesitamos preparar la consulta parametrizada
        PreparedStatement ps = conexion.prepareStatement(insert); 
        // Le damos valor a los interrogantes
        ps.setString(1, n.getName());
        ps.setString(2, n.getPlace());
        ps.setInt(3, n.getPokeballs());
        ps.setInt(4, n.getPotions());
        
        ps.executeUpdate();
        ps.close();
      }
    }    

    private boolean existePokeparada(Pokeparada b) throws SQLException {
        String select = "select * from pokeparada where name='" + b.getName() + "'";
        Statement st = conexion.createStatement();
        boolean existe = false;
        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            existe = true;
        }
        rs.close();
        st.close();
        return existe;
    }    
     
public User getUserByName(User a)throws SQLException, MiExcepcion {
      User aux = new User(a.getUsername());
      if (!existeUser(aux)){
          throw new MiExcepcion("No existe un usuario con ese nombre");
      } else { 
          String select = "select * from user where username='" + a.getUsername() + "'";
          Statement st = conexion.createStatement();
          ResultSet rs = st.executeQuery(select);
          if(rs.next()){
            a.setUsername(a.getUsername());
            a.setPokeballs(rs.getInt("pokeballs"));
            a.setPotions(rs.getInt("potions"));
            a.setLevel(rs.getInt("level"));
            a.setPlace(rs.getString("place"));
            a.setPokecoins(rs.getInt("pokecoins"));
            a.setPoints(rs.getInt("points"));
          }
          rs.close();
          st.close();
          return a;
    }
      }
    
    public void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/stukemongo";
        String user = "root";
        String pass = "";
        conexion = DriverManager.getConnection(url, user, pass);
    }

    public void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
    
}
