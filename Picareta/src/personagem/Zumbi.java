/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package personagem;

import interfaces.Atacar;
/**
 *
 * @author Crispino
 */
public class Zumbi extends Personagem implements Atacar{

    public Zumbi(){
        super("Zumbi",10);  
    }
    
    @Override
    public void atacar(Personagem P){
        System.out.println("O zumbi acaba de atacar o personagem " + P + "!");
        System.out.println("O personagem " + P + " perdeu 5 HP!");
        P.setHP(P.getHP() - 5);
        System.out.println("HP restante do personagem " + P + " " + P.getHP());
    }
    
    @Override
    public String toString(){
        String s = "Zumbi";
        return s;
    }
    
    @Override
    public boolean equals(Object O){
        return O instanceof Zumbi;
    }
}
