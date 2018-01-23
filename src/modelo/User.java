/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author DAM
 */
public class User {
    private String username;
    private String password;
    private Integer pokeballs;
    private Integer potions;
    private Integer level;
    private String place;
    private Integer pokecoins;
    private Integer points;
    
    public User(){
        
    }

    public User(String username, String password, Integer pokeballs, Integer potions, Integer level, String place, Integer pokecoins, Integer points) {
        this.username = username;
        this.password = password;
        this.pokeballs = pokeballs;
        this.potions = potions;
        this.level = level;
        this.place = place;
        this.pokecoins = pokecoins;
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPokeballs() {
        return pokeballs;
    }

    public void setPokeballs(Integer pokeballs) {
        this.pokeballs = pokeballs;
    }

    public Integer getPotions() {
        return potions;
    }

    public void setPotions(Integer potions) {
        this.potions = potions;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getPokecoins() {
        return pokecoins;
    }

    public void setPokecoins(Integer pokecoins) {
        this.pokecoins = pokecoins;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", pokeballs=" + pokeballs + ", potions=" + potions + ", level=" + level + ", place=" + place + ", pokecoins=" + pokecoins + ", points=" + points + '}';
    }
            
            
}
