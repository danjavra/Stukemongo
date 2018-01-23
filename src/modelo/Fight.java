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
public class Fight {
    
    private Integer idfight;
    private Integer pokemon1;
    private Integer pokemon2;
    private Integer winner;
    
    public Fight(){
        
    }

    public Fight(Integer idfight, Integer pokemon1, Integer pokemon2, Integer winner) {
        this.idfight = idfight;
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.winner = winner;
    }

    public Integer getIdfight() {
        return idfight;
    }

    public void setIdfight(Integer idfight) {
        this.idfight = idfight;
    }

    public Integer getPokemon1() {
        return pokemon1;
    }

    public void setPokemon1(Integer pokemon1) {
        this.pokemon1 = pokemon1;
    }

    public Integer getPokemon2() {
        return pokemon2;
    }

    public void setPokemon2(Integer pokemon2) {
        this.pokemon2 = pokemon2;
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "Fight{" + "idfight=" + idfight + ", pokemon1=" + pokemon1 + ", pokemon2=" + pokemon2 + ", winner=" + winner + '}';
    }
    
    
}
