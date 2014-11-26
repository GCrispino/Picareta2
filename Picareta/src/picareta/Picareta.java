/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package picareta;

import interfaces.Atacar;
import personagem.Personagem;
import personagem.Zumbi;

/**
 *
 * @author Crispino
 */
public abstract class Picareta extends Item implements Atacar, Comparable<Picareta>{

    /**
     * @param args the command line arguments
     */
   
    //durabilidade da picareta(quantos usos ela leva para quebrar).
    protected int durabilidade;
    //variável booleana que indica se a picareta está quebrada.
    protected boolean quebrado;
    
    //construtor
    protected Picareta(int durabilidade,String nome){
        super(nome);
        this.durabilidade = durabilidade;
        this.quebrado = false;
    }
    //construtor de cópia
    protected Picareta(Picareta p){
        super(p);
        this.durabilidade = p.durabilidade;
        this.quebrado = p.quebrado;
    }
    
    //quebra a picareta modificando a variável 'quebrado'
    public void quebrarPicareta(){
        this.quebrado = true;
    }
    
    public boolean quebrado(){
        return this.quebrado;
    }
    
    public void setQuebrado(boolean quebrado){
        this.quebrado = quebrado;
    }
    
    public abstract void quebrarBloco(Bloco B);
    
    @Override
    public abstract String toString();
    
    @Override 
    public abstract boolean equals(Object O);
    
    @Override
    public abstract void utilizar();
    
    @Override
    public abstract void inutilizar();
    
    @Override
    public void atacar(Personagem P) {
        if (!(P instanceof Zumbi)) {
            //se o personagem passado não for zumbi, a função não faz nada
        }
        else{
            System.out.println("O zumbi foi derrotado!");
            P.setHP(0);
            this.durabilidade--;
        }
    }
    
    @Override
    public abstract int compareTo(Picareta P);
}
