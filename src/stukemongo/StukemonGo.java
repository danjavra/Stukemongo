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
import java.util.List;
import modelo.Pokedex;
import modelo.User;
import modelo.Pokemon;
import modelo.Pokeparada;
import java.sql.Date;
import java.time.LocalDate;
import modelo.RankingUsuario;

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
        User x2 = new User("Gary Oak", "123456", 20, 0, 0, "Aula 32", 0, 0 );
        User x3 = new User("Entrenador 1", "123456", 0, 0, 0, "Cafeteria", 0, 0 );
        Pokemon j1 = new Pokemon("Bulbasur", "Planta", 100, 80, "Lavabos");
        Pokemon j2 = new Pokemon("Charmander", "fuego", 90, 100, "Cafeteria");
        Pokeparada m1 = new Pokeparada("Parada 1", "Sala Actos", 3, 1 );
        Pokeparada m2 = new Pokeparada("Pokeparada 3", "Cafeteria", 10, 5 );
        Pokedex p1 = new Pokedex(1, "Ash Ketchum", "Charmander", LocalDate.of(2018, 2, 2) ,90,100,100);
        Pokedex p2 = new Pokedex(2, "Ash Ketchum", "Charmander", LocalDate.of(2018, 2, 2) ,90,100,100);
        
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
            
            System.out.println("************************************************************");
            System.out.println("Testeando validar user por nombre y contraseña, nombre: 'Ash Ketchum' y password: '123456' ");
            if(stukemonGoDAO.validateUserByPassword("Ash Ketchum", "123456")){
                System.out.println("Usuario Validado");
            } else {
                System.out.println("Usuario no válido");
            }
            System.out.println("************************************************************");
            System.out.println("Testeando validar user erróneo por nombre y contraseña, nombre: ewque y password: 65423 ");
            if(stukemonGoDAO.validateUserByPassword("ewque", "65423")){
                System.out.println("Usuario Validado");
            } else {
                System.out.println("Usuario no válido");
            }
            
            System.out.println("************************************************************");
            System.out.println("Testeando modificar lugar del usuario " + x1.getUsername());
            System.out.println("Datos actuales");
            System.out.println(x1);
            System.out.println("Estableciendo nuevo lugar en: 'Cafeteria'");
            x1.setPlace("Cafeteria");
            updateUserPlace(stukemonGoDAO, x1);
            
            System.out.println("************************************************************");
            System.out.println("Testeando modificar lugar del pokemon " + j1.getName());
            System.out.println("Datos actuales");
            System.out.println(j1);
            System.out.println("Estableciendo nuevo lugar en: 'Aula 32'");
            j1.setPlace("Aula 32");
            updatePokemonPlace(stukemonGoDAO, j1);
            
            System.out.println("************************************************************");
            System.out.println("Testeando mostrar lista de pokemons que esten en el mismo lugar que el usuario " + x1.getUsername());
            System.out.println("Listado de todos los pokemons que estan en el mismo lugar que el User");
            printarListaPokemonByUserPlace(stukemonGoDAO, x1);
            
            System.out.println("************************************************************");
            System.out.println("Testeando mostrar lista de usuarios que esten en el mismo lugar que el usuario " + x1.getUsername());
            System.out.println("Listado de todos los usuarios que estan en el mismo lugar que el User");
            printarListaUsersByUserPlace(stukemonGoDAO, x1);
            
            System.out.println("************************************************************");
            System.out.println("Testeando mostrar lista de pokeparadas que esten en el mismo lugar que el usuario " + x1.getUsername());
            System.out.println("Listado de todos los pokeparadas que estan en el mismo lugar que el User");
            printarListaPokeparadasByUserPlace(stukemonGoDAO, x1);
            
            System.out.println("************************************************************");
            System.out.println("Testeando modificar potions y pokeballs del usuario " + x1.getUsername()+" por recoger regalos en la Pokeparada");
            System.out.println("Datos actuales");
            System.out.println(x1);
            System.out.println("Recogiendo regalos en: "+m2.getName()+"");
            System.out.println(m2);
            cogerRegalos(stukemonGoDAO, x1,m2);
            System.out.println("Datos actualizados");
            getUserByName(stukemonGoDAO, x1);
            
            System.out.println("************************************************************");
            System.out.println("Testeando capturar pokemon: "+j2.getName()+" por el usuario: "+x1.getUsername());
            atrapaPokemon(stukemonGoDAO, x1,j2);
            
            System.out.println("************************************************************");
            System.out.println("Testeando liberar pokemon: "+p1.getIdpokedex()+" del usuario: "+x1.getUsername());
            liberaPokemon(stukemonGoDAO, x1,p1);
            System.out.println(x1.getUsername()+" recibe 25 stucoins por liberar un pokemon");
            
            /*System.out.println("************************************************************");
            System.out.println("Testeando combate pokemon");
            System.out.println(x1.getUsername()+" con su pokemon "+ x1.getPokedex().get(2)+" vs. "+x3.getUsername()+" con su pokemon "+ x3.getPokedex().get(2));
            //No consigo convertir el arrayList en List
            stukemonGoDAO.combatePokemon(x1, x1.getPokedexUsuario().get(0),x3,x3.getPokedexUsuario().get(0)); */
            
            System.out.println("************************************************************");
            System.out.println("Testeando PC y vida del pokemon de un usuario determinado");
            System.out.println("Mejorar pokemon "+ p2.getPokemon() +" del usuario "+x1.getUsername());
            updatePcVidaPokemon(stukemonGoDAO, x1,p2);
            
            System.out.println("************************************************************");
            System.out.println("Testeando obtener el ranking de los usuarios que tengan más pokemons");
            for(RankingUsuario rankingusuario : stukemonGoDAO.obtenerRankingUsuarios()){
                    System.out.println(rankingusuario);
                }
           
        } catch (SQLException | MiExcepcion ex) {
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

    private static void updateUserPlace(StukemonGoDAO stukemonGoDAO, User x1) throws SQLException{
       try {
            stukemonGoDAO.updateUserPlace(x1);
            System.out.println("Modificado lugar del usuario");
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
    }
    
       private static void updatePokemonPlace(StukemonGoDAO stukemonGoDAO, Pokemon j1) throws SQLException{
       try {
            stukemonGoDAO.updatePokemonPlace(j1);
            System.out.println("Modificado lugar del pokemon");
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
    }
    
       public static void printarListaPokemonByUserPlace (StukemonGoDAO stukemonGoDAO, User user) throws SQLException{
           try{
               List<Pokemon> poke = stukemonGoDAO.getPokemonByUserPlace(user);
               System.out.println("Pokemons de la zona");
               for(Pokemon pokemPlace : poke){
                   System.out.println(pokemPlace);
               }
           } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
       }
       
          public static void printarListaUsersByUserPlace (StukemonGoDAO stukemonGoDAO, User user) throws SQLException{
           try{
               List<User> usu = stukemonGoDAO.getUsersByUserPlace(user);
               System.out.println("Usuarios de la zona");
               for(User userPlace : usu){
                   System.out.println(userPlace);
               }
           } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
       }
          
           public static void printarListaPokeparadasByUserPlace (StukemonGoDAO stukemonGoDAO, User user) throws SQLException{
           try{
               List<Pokeparada> parada = stukemonGoDAO.getPokeparadaByUserPlace(user);
               System.out.println("Pokeparadas de la zona");
               for(Pokeparada pokeparadaPlace : parada){
                   System.out.println(pokeparadaPlace);
               }
           } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
       }
           
              private static void cogerRegalos(StukemonGoDAO stukemonGoDAO, User x1, Pokeparada m2) throws SQLException{
       try {
            stukemonGoDAO.recogerRegalosPokeparada(x1,m2);
            System.out.println("Modificado pociones y pokeballs del usuario");
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
    }
           
              private static void atrapaPokemon(StukemonGoDAO stukemonGoDAO, User x1, Pokemon j2) throws SQLException{
                  try{
                      stukemonGoDAO.capturarPokemon(x1, j2);
                      System.out.println("Se ha capturado correctamente el pokemon " + j2.getName() + " para el usuario " + x1.getUsername());
                  } catch (MiExcepcion ex) {
                        System.out.println(ex.getMessage());
                  }
              }
              
               private static void liberaPokemon(StukemonGoDAO stukemonGoDAO, User x1, Pokedex p1) throws SQLException{
                  try{
                      stukemonGoDAO.liberarPokemon(x1, p1);
                      System.out.println("Pokemon " + p1.getPokemon() + " liberado correctamente por el usuario " + x1.getUsername());
                  } catch (MiExcepcion ex) {
                        System.out.println(ex.getMessage());
                  }
              }
               
              private static void updatePcVidaPokemon(StukemonGoDAO stukemonGoDAO, User x1, Pokedex p2) throws SQLException{
                  try{
                      stukemonGoDAO.mejorarPcVidaPokemon(x1, p2);
                      System.out.println("Pokemon " + p2.getPokemon() + " mejorado pc y vida correctamente");
                  } catch (MiExcepcion ex) {
                        System.out.println(ex.getMessage());
                  }
              } 
              
}