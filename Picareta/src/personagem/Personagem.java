/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package personagem;

import main.Main;

/**
 *
 * @author Crispino
 */
public abstract class Personagem {
    protected String nome; //nome do personagem
    protected int hp; //hp(vida) do personagem
    
     public Personagem(String nome,int hp){
        if (Main.isDigito(nome))
            this.nome = "Padrao";
        else
            this.nome = nome;
        
        //validação do atributo "hp" não é necessária porque o construtor sempre receberá os mesmos valores...
        //...de acordo com a subclasse que o chama no momento.
        this.hp = hp;
        //this.picaretas = new ArrayList<>();
        //picaretaatual = -1;
    }
    //construtor vazio
    public Personagem(){
        this.nome = "Padrao";
        this.hp = 20;
        //this.picaretas = new ArrayList<>();
        //picaretaatual = -1;
    }
    
    public int getHP(){
        return this.hp;
    }
    
    public void setHP(int hp){
        this.hp = hp;
    }
    
    @Override
    public abstract String toString();
    
    @Override
    public boolean equals(Object O){
        if (O instanceof Personagem){
            Personagem P = (Personagem)O;
            return this.nome.equals(P.nome);
        }
        else
            return false;
    }
}
