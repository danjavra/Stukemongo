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
public class Pokeparada {
    
    private String name;
    private String place;
    private Integer pokeballs;
    private Integer potions;
    
   public Pokeparada(){
       
   } 

    public Pokeparada(String name, String place, Integer pokeballs, Integer potions) {
        this.name = name;
        this.place = place;
        this.pokeballs = pokeballs;
        this.potions = potions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    @Override
    public String toString() {
        return "Pokeparada{" + "name=" + name + ", place=" + place + ", pokeballs=" + pokeballs + ", potions=" + potions + '}';
    }
   
   
}
