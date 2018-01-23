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
public class Pokemon {
    private String name;
    private String type;
    private Integer pc;
    private Integer life;
    private String place;
    
    public Pokemon(){
        
    }

    public Pokemon(String name, String type, Integer pc, Integer life, String place) {
        this.name = name;
        this.type = type;
        this.pc = pc;
        this.life = life;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPc() {
        return pc;
    }

    public void setPc(Integer pc) {
        this.pc = pc;
    }

    public Integer getLife() {
        return life;
    }

    public void setLife(Integer life) {
        this.life = life;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Pokemon{" + "name=" + name + ", type=" + type + ", pc=" + pc + ", life=" + life + ", place=" + place + '}';
    }
    
    
}
