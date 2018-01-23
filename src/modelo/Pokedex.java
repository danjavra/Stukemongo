/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author DAM
 */
public class Pokedex {
    
    private Integer idpokedex;
    private String user;
    private String pokemon;
    private LocalDate date;
    private Integer pc;
    private Integer lifemax;
    private Integer lifecurrent;
    
    public Pokedex(){
        
    }

    public Pokedex(Integer idpokedex, String user, String pokemon, LocalDate date, Integer pc, Integer lifemax, Integer lifecurrent) {
        this.idpokedex = idpokedex;
        this.user = user;
        this.pokemon = pokemon;
        this.date = date;
        this.pc = pc;
        this.lifemax = lifemax;
        this.lifecurrent = lifecurrent;
    }

    public Integer getIdpokedex() {
        return idpokedex;
    }

    public void setIdpokedex(Integer idpokedex) {
        this.idpokedex = idpokedex;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPokemon() {
        return pokemon;
    }

    public void setPokemon(String pokemon) {
        this.pokemon = pokemon;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPc() {
        return pc;
    }

    public void setPc(Integer pc) {
        this.pc = pc;
    }

    public Integer getLifemax() {
        return lifemax;
    }

    public void setLifemax(Integer lifemax) {
        this.lifemax = lifemax;
    }

    public Integer getLifecurrent() {
        return lifecurrent;
    }

    public void setLifecurrent(Integer lifecurrent) {
        this.lifecurrent = lifecurrent;
    }

    @Override
    public String toString() {
        return "Pokedex{" + "idpokedex=" + idpokedex + ", user=" + user + ", pokemon=" + pokemon + ", date=" + date + ", pc=" + pc + ", lifemax=" + lifemax + ", lifecurrent=" + lifecurrent + '}';
    }
    
    
}
