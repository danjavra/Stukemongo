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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Pokedex;
import modelo.Pokemon;
import modelo.Pokeparada;
import modelo.RankingUsuario;
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

    public boolean existePokeparada(Pokeparada b) throws SQLException {
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
    
    public boolean validateUserByPassword(String name, String pass)throws SQLException, MiExcepcion {
     String select = "select * from user where username = '"+name+"' and password = '"+pass+"' ";
     Statement st = conexion.createStatement();
     boolean validate = false;
        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            validate = true;
        }
        rs.close();
        st.close();
        return validate;
    }    
     
    public void updateUserPlace(User c) throws SQLException, MiExcepcion {
          if (!existeUser(c)) {
            throw new MiExcepcion("ERROR: No existe un usuario con ese nombre");
        }
        String update = "update user set place=? where username=?";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.setString(1, c.getPlace());
        ps.setString(2, c.getUsername());
        ps.executeUpdate();
        ps.close();
    }
    
    public void updatePokemonPlace(Pokemon s) throws SQLException, MiExcepcion {
          if (!existePokemon(s)) {
            throw new MiExcepcion("ERROR: No existe un pokemon con ese nombre");
        }
        String update = "update pokemon set place=? where name=?";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.setString(1, s.getPlace());
        ps.setString(2, s.getName());
        ps.executeUpdate();
        ps.close();
    }
    
 
    public List<Pokemon> getPokemonByUserPlace (User a) throws SQLException, MiExcepcion {
        List<Pokemon> pokemonPlace = new ArrayList<>();  
        if (!existeUser(a)) {
            throw new MiExcepcion("ERROR: No existe un usuario con ese nombre");
        } 
        String select = "select * from pokemon where place ='"+a.getPlace()+"'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        while (rs.next()){
            Pokemon listPokemon = new Pokemon();
            listPokemon.setName(rs.getString("name"));
            listPokemon.setType(rs.getString("type"));
            listPokemon.setPc(rs.getInt("pc"));
            listPokemon.setLife(rs.getInt("life"));
            listPokemon.setPlace(rs.getString("place"));
            pokemonPlace.add(listPokemon);
          }
          rs.close();
          st.close();
          return pokemonPlace;
     
    }    
    
    public List<User> getUsersByUserPlace (User a) throws SQLException, MiExcepcion {
        List<User> userPlace = new ArrayList<>();  
        if (!existeUser(a)) {
            throw new MiExcepcion("ERROR: No existe un usuario con ese nombre");
        } 
        String select = "select * from user where place ='"+a.getPlace()+"'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        while (rs.next()){
            User listUsers = new User();
            listUsers.setUsername(rs.getString("username"));
            listUsers.setPassword(rs.getString("password"));
            listUsers.setPokeballs(rs.getInt("pokeballs"));
            listUsers.setPotions(rs.getInt("potions"));
            listUsers.setLevel(rs.getInt("level"));
            listUsers.setPlace(rs.getString("place"));
            listUsers.setPokecoins(rs.getInt("pokecoins"));
            listUsers.setPoints(rs.getInt("points"));
            userPlace.add(listUsers);
          }
          rs.close();
          st.close();
          return userPlace;
    }   
    
    public List<Pokeparada> getPokeparadaByUserPlace (User a) throws SQLException, MiExcepcion {
        List<Pokeparada> pokeparadaPlace = new ArrayList<>();  
        if (!existeUser(a)) {
            throw new MiExcepcion("ERROR: No existe un usuario con ese nombre");
        } 
        String select = "select * from pokeparada where place ='"+a.getPlace()+"'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        while (rs.next()){
            Pokeparada listParadas = new Pokeparada();
            listParadas.setName(rs.getString("name"));
            listParadas.setPlace(rs.getString("place"));
            listParadas.setPokeballs(rs.getInt("pokeballs"));
            listParadas.setPotions(rs.getInt("potions"));
            pokeparadaPlace.add(listParadas);
          }
          rs.close();
          st.close();
          return pokeparadaPlace;
    }   
    
    public void recogerRegalosPokeparada (User user , Pokeparada pokeparada) throws SQLException, MiExcepcion {
        if(!user.getPlace().equalsIgnoreCase(pokeparada.getPlace())){
            throw new MiExcepcion("No están en el mismo lugar");
        }
        String recogerRegalos = "update user set pokeballs = (pokeballs+" + pokeparada.getPokeballs() + "), potions = (potions+" + pokeparada.getPotions() + ") where username = '" + user.getUsername() + "'" ;
        Statement st = conexion.createStatement();
        System.out.println(recogerRegalos);
        st.executeUpdate(recogerRegalos);
        st.close();
    }
    
    public void restaPokeballs (User user) throws SQLException {
        String update = "update user set pokeballs = (pokeballs-1) where username = ?";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.setString(1,user.getUsername());
        ps.executeUpdate();
        ps.close();
    }
    
    public void pokedexInsertPokemonCapturado(User user, Pokemon pokemon) throws SQLException , MiExcepcion {
        String insert = "insert into pokedex (user,pokemon,date,pc,lifemax,lifecurrent) values(?,?,?,?,?,?)";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1,user.getUsername());
        ps.setString(2,pokemon.getName());
        ps.setDate(3,Date.valueOf(LocalDate.now()));
        ps.setInt(4,pokemon.getPc());
        ps.setInt(5,pokemon.getLife());
        ps.setInt(6,pokemon.getLife());

        ps.executeUpdate();
        ps.close();
    }
    
        public void capturarPokemon(User user, Pokemon pokemon) throws SQLException, MiExcepcion {
        if(user.getPokeballs()==0){
            throw new MiExcepcion("El usuario no tiene pokeballs disponibles");
        }else if(!existePokemon(pokemon)){
            throw new MiExcepcion("Pokemon no encontrado");
        }else if(!existeUser(user)){
            throw new MiExcepcion("Usuario no registrado");
        }else if(!user.getPlace().equals(pokemon.getPlace())){
            throw new MiExcepcion("Usuario y pokemon no se hallan en el mismo lugar");
        }
            restaPokeballs(user);
            int numPokemon = ((int) Math.floor(Math.random() * (50 - 20) + 20));
            int numEntrenador = ((int) Math.floor(Math.random() * (30 - 10) + 10)) + user.getLevel();
            if(numEntrenador > numPokemon){
                pokedexInsertPokemonCapturado(user,pokemon);
                LocalDate date = LocalDate.now();
             }else{
                 throw new MiExcepcion("Pokemon no capturado");
            }
    }
        
        private void conseguir25Pokecoins (User user) throws SQLException {
        String conseguir25Pokecoins = "update user set pokecoins = (pokecoins + 25) where username = ?";
        PreparedStatement ps = conexion.prepareStatement(conseguir25Pokecoins);
        ps.setString(1,user.getUsername());
        ps.executeUpdate();
        ps.close();
    }
        
        private boolean existePokemonPokedexUsuario(User user, Pokedex p) throws SQLException {
        boolean existe = false;
        String select = "select * from pokedex where idpokedex = " + p.getIdpokedex();
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        if(rs.next()){
            existe = true;
        }
        st.close();
        rs.close();
        return existe;
    }
        
        public void liberarPokemon(User user,Pokedex pokedex) throws SQLException, MiExcepcion {
        if(!existeUser(user)){
            throw new MiExcepcion("No existe el usuario");
        }
        if(!existePokemonPokedexUsuario(user,pokedex)){
            throw new MiExcepcion("Pokemon no registrado en la pokedex del usuario");
        }
        String delete = "delete from pokedex where idpokedex = " + pokedex.getIdpokedex();
        PreparedStatement ps = conexion.prepareStatement(delete);
        conseguir25Pokecoins(user);
        ps.executeUpdate(delete);
        ps.close();
    }
     
        private boolean tieneLife (Pokedex pokedex1,Pokedex pokedex2){
        if(pokedex1.getLifecurrent()>0 && pokedex2.getLifecurrent()>0){
            return true;
        }else{
            return false;
        }
    }
        
        private void winnerPoints(String user, int points) throws SQLException {
        String update = "update user set points = (points+" + points + ") where username = ?";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.setString(1,user);
        System.out.println( user +" recibe " + points + " points por la victoria " );
        ps.executeUpdate();
        ps.close();
    }

        private void winnerPokecoins(String user, int pokecoins) throws SQLException {
        String update = "update user set pokecoins = (pokecoins +" + pokecoins + ") where username = ?";
        PreparedStatement ps = conexion.prepareStatement(update);
        System.out.println(user +" recibe " + pokecoins + " pokecoins por la victoria ");
        ps.setString(1,user);
        ps.executeUpdate();
        ps.close();
    }
        
        private void insertFight(Pokedex pokedex1, Pokedex pokedex2, Pokedex ganador) throws SQLException {
        String insert = "insert into fight (pokemon1,pokemon2,winner) values (?,?,?)";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setInt(1,pokedex1.getIdpokedex());
        ps.setInt(2,pokedex2.getIdpokedex());
        ps.setInt(3,ganador.getIdpokedex());
        ps.executeUpdate();
        ps.close();
    }
    
        private void levelUp(User user) throws SQLException {
        String subirNivel = "update user set level = (level+1) where username = ?";
        String reiniciaPuntos = "update user set points = 0 where username = ?";
        PreparedStatement ps1 = conexion.prepareStatement(subirNivel);
        PreparedStatement ps2 = conexion.prepareStatement(reiniciaPuntos);
        ps1.setString(1,user.getUsername());
        ps2.setString(1,user.getUsername());
        ps1.executeUpdate();
        ps2.executeUpdate();
        ps1.close();
        ps2.close();
    }

        private void updateLifecurrent(Pokedex pokedex1, int vidaActual1,Pokedex pokedex2, int vidaActual2) throws SQLException, MiExcepcion {
        String actualizaVidaPokemon1 = "update pokedex set lifecurrent = '" + vidaActual1 + "'where idpokedex = " + pokedex1.getIdpokedex();
        String actualizaVidaPokemon2 = "update pokedex set lifecurrent = '" + vidaActual2 + "'where idpokedex = " + pokedex2.getIdpokedex();
        PreparedStatement ps1= conexion.prepareStatement(actualizaVidaPokemon1);
        PreparedStatement ps2 = conexion.prepareStatement(actualizaVidaPokemon2);
        ps1.executeUpdate();
        ps2.executeUpdate();
        ps1.close();
        ps2.close();
    }
        
        public void combatePokemon(User user1, Pokedex pokedex1, User user2, Pokedex pokedex2) throws SQLException, MiExcepcion {
        User aux1 = new User(user1.getUsername());
        User aux2 = new User(user2.getUsername());
        if (!existeUser(aux1) || !existeUser(aux2)) {
            throw new MiExcepcion("No existe ese usuario");
        }
        else if (!existePokemonPokedexUsuario(user1, pokedex1) || !existePokemonPokedexUsuario(user2, pokedex2)) {
            throw new MiExcepcion("No existe ese pokemon en la pokedex del usuario");
        }
        else if(!tieneLife(pokedex1, pokedex2)){
            throw new MiExcepcion("Combate anulado porque el pokemon seleccionado no tiene vida");
        }
        int pokemon1Life = pokedex1.getLifecurrent(); 
        int pokemon2Life = pokedex2.getLifecurrent();
        int pokemon1Ataque = pokedex1.getPc();
        int pokemon2Ataque = pokedex2.getPc();

        int pokecoins = (int) Math.floor(Math.random() * (50 - 20) + 20);
        int points = (int) Math.floor(Math.random() * (8 - 4) + 4);
        while(pokemon1Life > 0 && pokemon2Life > 0){
            int rand = (int) Math.floor(Math.random() * (4 - 1) + 1);
            if(pokemon1Life < pokemon2Life){
                pokemon2Life = pokemon2Life - (pokemon1Ataque/2+rand);
                if(pokemon2Life <= 0){
                    pokemon2Life = 0;
                }else{
                    pokemon1Life = pokemon1Ataque/2+rand;
                }
            }else{
                pokemon1Life = pokemon1Life - (pokemon2Ataque/2+rand);
                if(pokemon1Life <= 0){
                    pokemon1Life = 0;
                }else{
                    pokemon2Life = pokemon2Ataque/2+rand;
                }
            }
        }
        if(pokemon1Life>0){
            winnerPokecoins(user1.getUsername(),pokecoins);
            winnerPoints(user1.getUsername(),points);
            insertFight(pokedex1,pokedex2,pokedex1);
        }else if(pokemon2Life>0){
            winnerPokecoins(user2.getUsername(),pokecoins);
            winnerPoints(user2.getUsername(),points);
            insertFight(pokedex1,pokedex2,pokedex2);
        }
            updateLifecurrent(pokedex1,pokemon1Life,pokedex2,pokemon2Life);
            if(user1.getPoints()>15){
                levelUp(user1);
            }else if(user2.getPoints()>15){
                levelUp(user2);
            }
    }

        private Pokemon getPokemonByNamePokedex(String nombre) throws SQLException {
        String select = "select * from pokemon where name = '" + nombre + "'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        Pokemon pokemon = new Pokemon();
        if(rs.next()){
            pokemon.setName(rs.getString("name"));
            pokemon.setType(rs.getString("type"));
            pokemon.setPc(rs.getInt("pc"));
            pokemon.setLife(rs.getInt("life"));
            pokemon.setPlace(rs.getString("place"));
        }
        rs.close();
        st.close();
        return pokemon;
    }
        
        public List<Pokedex> getPokedexUsuario(String user) throws SQLException {
        String select = "select * from pokedex where user = '" + user + "'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        List<Pokedex> listpokedex = new ArrayList<>();

        while(rs.next()){
            String name = getPokemonByNamePokedex(rs.getString("pokemon")).getName();
            String type = getPokemonByNamePokedex(rs.getString("pokemon")).getType();
            int pc = getPokemonByNamePokedex(rs.getString("pokemon")).getPc();
            int life = getPokemonByNamePokedex(rs.getString("pokemon")).getLife();
            String place = getPokemonByNamePokedex(rs.getString("pokemon")).getPlace();
            Pokedex pokedex = new Pokedex();
            pokedex.setIdpokedex(rs.getInt("idpokedex"));
            Pokemon aux = new Pokemon(name,type,pc,life,place);
            pokedex.setPokemon(aux.getName());
            pokedex.setDate(rs.getDate("date").toLocalDate());
            pokedex.setPc(rs.getInt("pc"));
            pokedex.setLifemax(rs.getInt("lifemax"));
            pokedex.setLifecurrent(rs.getInt("lifecurrent"));
            listpokedex.add(pokedex);
        }
        rs.close();
        st.close();
        return listpokedex;
    }

        private void restar100Pokecoins (User user) throws SQLException {
        String restarPokecoins = "update user set pokecoins = (pokecoins-100) where username = ?";
        PreparedStatement ps = conexion.prepareStatement(restarPokecoins);
        ps.setString(1,user.getUsername());
        ps.executeUpdate();
        ps.close();
    }
     
        private User getUserDatos(User a) throws SQLException {
        String select = "select * from user where username = '" + a.getUsername() + "'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        User user1 = new User();
        if(rs.next()){
            user1.setUsername(rs.getString("username"));
            user1.setPassword(rs.getString("password"));
            user1.setPokeballs(rs.getInt("pokeballs"));
            user1.setPotions(rs.getInt("potions"));
            user1.setLevel(rs.getInt("level"));
            user1.setPlace(rs.getString("place"));
            user1.setPokecoins(rs.getInt("pokecoins"));
            user1.setPoints(rs.getInt("points"));
        }
        rs.close();
        st.close();
        return user1;
    }
        
        private boolean tiene100Pokecoins (User a) throws SQLException, MiExcepcion{
        if(getUserDatos(a).getPokecoins()>=100){
            return true;
        }else{
            return false;
        }
    }
        
        public void mejorarPcVidaPokemon(User user,Pokedex pokedex) throws SQLException,MiExcepcion {
        if(!tiene100Pokecoins(user)){
            throw new MiExcepcion("No tienes suficiente pokecoins");
        }
        else if(!existeUser(user)){
            throw new MiExcepcion("No existe el usuario");
        }
        else if(!existePokemonPokedexUsuario(user,pokedex)){
            throw new MiExcepcion("No existe el pokemon en la pokedex del usuario");
        }
        String subirPcVida = "update pokedex set pc = (pc+5) , lifecurrent = (lifecurrent+2) , lifemax = (lifemax+2) where idpokedex = " + pokedex.getIdpokedex();
        PreparedStatement ps = conexion.prepareStatement(subirPcVida);
        ps.executeUpdate();
        restar100Pokecoins(user);
        System.out.println("Se ha mejorado correctamente el pokemon " + pokedex.getPokemon());
        ps.close();
    }

        public List<RankingUsuario> obtenerRankingUsuarios() throws SQLException,MiExcepcion {
        String select = "select user, COUNT(user) as numtotalpokemon from pokedex group by user order by numtotalpokemon desc";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        List<RankingUsuario> rankingList = new ArrayList<>();
        int rank = 1;
        while(rs.next()){
            RankingUsuario ranking = new RankingUsuario();
            ranking.setRanking(rank);
            ranking.setUser(rs.getString("user"));
            ranking.setNumtotalpokemon(rs.getInt("numtotalpokemon"));
            rankingList.add(ranking);
            rank++;
        }
        rs.close();
        st.close();
        return rankingList;
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
