/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package personagem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import picareta.Bloco;
import picareta.Picareta;
import picareta.PicaretaMadeira;
import picareta.PicaretaDiamante;
import picareta.Item;
import main.Main;

/**
 *
 * @author Crispino
 */
public class Principal extends Personagem{
    private ArrayList <Item> itens; //array de itens que o personagem guarda;
    private static final int QTDMAXITENS = 20; //quantidade máxima de itens que o personagem pode guardar
    
    
    public Principal(String nome){
        super(nome,20);
        
        this.itens = new ArrayList<>();
    }
    
    //construtor vazio
    public Principal(){
        super("Padrao",20);
        this.itens = new ArrayList<>();
    }
    
    public String getNome(){
        return this.nome;
    }
    
    //insere um item no array de itens
    public void inserirItem(Item I){
        this.itens.add(I);
    }
    
    public Principal criarPersonagem(){
        String nome;
        Scanner input = new Scanner(System.in);
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Digite o nome do personagem: ");
        nome = input.nextLine();
        
        Principal P = new Principal(nome);
        
        System.out.println("Personagem "+ P.nome + " criado com sucesso!");
        try {
            System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(Personagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return P;
    }
    
    public void verificaPicaretas(){
        boolean removeu = false;
        
        for (int i = 0;i < this.itens.size();i++){
            if (this.itens.get(i) instanceof Picareta){
                Picareta P = (Picareta)this.itens.get(i);
                if (P.quebrado()){
                    this.itens.remove(i);
                    removeu = true;
                }
            }
        }
    }
    
    //função que verifica se o personagem principal está usando alguma picareta.
    public Picareta buscaPicaretas(){
        
        for (Item item: this.itens){
            if (item instanceof Picareta)
                if (item.isUtilizado())
                    return (Picareta)item;
            else
                if (item.isUtilizado())
                    return null;
        }
        
        return null;
    }
    
    //função que realiza a operação de criar uma picareta nova usando os blocos disponíveis
    public boolean criarPicareta(int tipo){
        int contm = 0,contd = 0;
        if (tipo == 1){ //se igual a 0, tenta criar uma picareta de madeira
            for (Item item : this.itens){
                if (item instanceof Bloco){
                    Bloco B = (Bloco)item;
                    if (B.getNome() == "Pedra")
                        contm++;
                }
            }
            if (contm >= 3){
            //se o personagem possui 3 blocos de pedra ou mais, ele pode construir uma picareta de madeira
                for (int i = 0;i < this.itens.size();i++/*Item item : this.itens*/){
                    //varre o array de itens para eliminar os blocos usados na construção da picareta
                    if (this.itens.get(i) instanceof Bloco && this.itens.get(i).getNome() == "Pedra")
                        this.itens.remove(i);
                }
                
                PicaretaMadeira PM = new PicaretaMadeira();
                this.itens.add(PM);
                System.out.println(PM + " criada e adicionada ao inventário!");
                try {
                    System.in.read();
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                return true;
            }
            else 
                return false;
        }
        else if (tipo == 2){ //senão, tenta criar uma de diamante
            for (Item item : this.itens){
                if (item instanceof Bloco){
                    Bloco B = (Bloco)item;
                    if (B.getNome() == "Diamante")
                        contd++;
                }
            }
            if (contd >= 3){
            //se o personagem possui 3 blocos de pedra ou mais, ele pode construir uma picareta de madeira
                for (int i = 0;i < this.itens.size();i++){
                    //varre o array de itens para eliminar os blocos usados na construção da picareta
                    if (this.itens.get(i) instanceof Bloco && this.itens.get(i).getNome() == "Diamante"){
                        this.itens.remove(i);
                    }
                }
                
                PicaretaDiamante PD = new PicaretaDiamante();
                this.itens.add(PD);
                
                System.out.println(PD + " criada e adicionada ao inventário!");
                try {
                    System.in.read();
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return true;
            }
            else
                return false;
            
        }
        else
            return false;
    }
    
    public void modificarPicaretaAtual(){
        Scanner input = new Scanner(System.in);
        char r;
        int i = 0,j = i + 1,opcao,contp = 0,posatual = -1;
        
        this.verificaPicaretas();

            if (this.itemAtual() == null)
                System.out.println("O personagem não está utilizando nenhum item!");
            else{
                System.out.println("Picareta atual: ");
                System.out.println(this.itemAtual());
            }
            try {
                    System.in.read();
                } catch (IOException ex) {
                  Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            for (Item item: this.itens){
                if (item instanceof Picareta && !item.isUtilizado())
                    contp++;
            }
            
            if (contp == 0){
                System.out.println("O personagem não possui nenhuma picareta disponível!");
                try {
                    System.in.read();
                } catch (IOException ex) {
                    Logger.getLogger(Personagem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                System.out.println("Lista de picaretas:");
                while (i < this.itens.size()){
                    if (this.itens.get(i) instanceof Picareta){
                        if (this.itens.get(i).isUtilizado() == false){
                            System.out.println(j + ". " + this.itens.get(i));
                            j++;
                        }
                        else posatual = i;
                    }
                    i++;
                }
                System.out.println("Digite o número correspondente à picareta que você deseja utilizar:");
                opcao = input.nextInt();
                
                if (opcao <= posatual){
                    if (posatual != -1) //se a posição do item atual for diferente de -1, significa que 
                                        //algum item está sendo utilizado
                        this.itens.get(posatual).inutilizar();
                    
                    for (Item item: this.itens){
                        //laço que busca pela picareta selecionada
                        if (item instanceof Picareta &&opcao == 0){
                            System.out.println(item);
                            item.utilizar();
                            break;
                        }
                        if (item instanceof Picareta && item.isUtilizado() == false)
                            opcao--;
                    }
                    
//                    this.itens.get(opcao - 1).utilizar();
//                    System.out.println(this.itens.get(opcao));
                    
                }
                else{
                    opcao--;
                    if (posatual != -1)
                        this.itens.get(posatual).inutilizar();
                    
                    for (Item item: this.itens){
                        if (item instanceof Picareta && opcao == 0){
                            System.out.println(item);
                            item.utilizar();
                            break;
                        }
                        //laço que busca pela picareta selecionada
                        if (item instanceof Picareta && item.isUtilizado() == false)
                            opcao--;
                    }
                    
//                    this.itens.get(opcao).utilizar();
//                    System.out.println(this.itens.get(opcao));
                    try {
                        System.in.read();
                    } catch (IOException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            
                System.out.println(this.itemAtual() + " selecionada!");
                try {
                    System.in.read();
                } catch (IOException ex) {
                    Logger.getLogger(Personagem.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    
    //função que retorna o item que o personagem está utilizando no momento.
    public Item itemAtual(){
        for (Item item: this.itens){
            if (item.isUtilizado())
                return item;
        }
        
        return null; //se nenhum item estiver sendo utiliado, retorna null.
    }
    
    //Personagem principal pode quebrar blocos com as suas próprias mãos
    public void quebrarBloco(Bloco B){
        double tempo = 0;
        
        System.out.println("O " + B + " esta sendo quebrado: ");
            for (int i = 0;i < 5;i++){
                System.out.println("...");
                switch(B.getNome()){
                    case "Pedra":
                        tempo = 7.5;
                        break;
                    case "Pedregulho":
                        tempo = 10;
                        break;
                    case "Tijolo":
                        tempo = 10;
                        break;
                    case "Ouro":
                        tempo = 15;
                        break;
                    case "Gelo":
                        tempo = 1;
                        break;
                    case "Diamante":
                        tempo = 25;
                        break;
                }
                
            try {
                //pausa o programa pelo tempo que a picareta leva para quebrar o bloco dado.
                Thread.sleep((long) ((tempo * 1000)/5));
            } catch (InterruptedException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
        B.setQuebrado(true);
        this.inserirItem(B);
        System.out.println("Bloco quebrado!");
    }
    
    @Override
    public String toString(){
        String s = this.nome;
        return s;
    }
    
    @Override
    public boolean equals(Object O){
        if (O instanceof Principal){
            Principal P = (Principal)O;
            return this.nome.equals(P.nome);
        }
        else
            return false;
    }
    
}
