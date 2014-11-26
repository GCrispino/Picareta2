/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package picareta;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Crispino
 */
public class PicaretaMadeira extends Picareta{
    
    public PicaretaMadeira(){
        //através do construtor da superclasse define a durabilidade da picareta de madeira
        super(60,"Picareta de Madeira");
    }
    //construtor de cópia
    public PicaretaMadeira(PicaretaMadeira Pm){
        super(Pm);
    }
    
    //sobrescrita do metodo "quebrarBloco"
    @Override
    public void quebrarBloco(Bloco B){
        if (this.quebrado()){
            System.out.println("A picareta de madeira esta quebrada!");
            return ;
        }
        
        System.out.println("O " + B + " esta sendo quebrado: ");
        try {
            for (int i = 0;i < 5;i++){
                System.out.println("...");
                //pausa o programa pelo tempo que a picareta leva para quebrar o bloco dado.
                Thread.sleep((long) ((B.getTempo1() * 1000)/5));
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(PicaretaMadeira.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        B.setQuebrado(true);
        System.out.println("Bloco quebrado!");
        
        this.durabilidade--;
        
        if (this.durabilidade == 0)
            this.quebrarPicareta();
    }

    @Override
    public String toString(){
        String s = "Picareta de madeira";
        
        return s;
    }
    
    @Override
    public boolean equals(Object O){
        return O instanceof PicaretaMadeira;
    }
    
    @Override
    public void utilizar(){
        this.utilizado = true;
    }
    
    @Override
     public void inutilizar(){
         this.utilizado = false;
    }

    @Override
    public int compareTo(Picareta P) {
        if (P instanceof PicaretaMadeira)
            return 1;
        else
            return 0;
    }

}
