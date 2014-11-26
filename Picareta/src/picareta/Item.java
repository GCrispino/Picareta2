/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package picareta;

import interfaces.Utilizavel;
import main.Main;

/**
 *
 * @author Crispino
 */
public abstract class Item implements Utilizavel{
    protected String nome;
    protected boolean utilizado; //bool que indica se o item está sendo utilizado no momento.
    
    //construtor vazio
    public Item(){
        this.nome = "Item";
        this.utilizado = false;
    }
    
    public Item(String nome){
        if (Main.isDigito(nome))
            this.nome = "Item";
        else
            this.nome = nome;
        
        this.utilizado = false;
    }
    
    //construtor de cópia
    public Item(Item I){
        this.nome = I.nome;
        this.utilizado = I.utilizado;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        if (Main.isDigito(nome))
            this.nome = "Item";
        else
            this.nome = nome;
    }
    
    public boolean isUtilizado(){
        return this.utilizado;
    }
    
    @Override
    public abstract void utilizar();
    
    @Override
    public abstract String toString();
    
    @Override
    public abstract boolean equals(Object O);
}
