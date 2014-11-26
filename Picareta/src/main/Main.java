/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import picareta.Picareta;
import picareta.PicaretaDiamante;
import picareta.PicaretaMadeira;
import picareta.Bloco;
import personagem.Principal;
import personagem.Zumbi;


/**
 *
 * @author Crispino
 */
public class Main {
    
    //função que verifica se uma string possui dígito
    public static boolean isDigito(String s){
        for (int i = 0;i < s.length();i++)
            if (Character.isDigit(s.charAt(i)))
                return true;
        
        return false;
    }
    //gera um array de blocos aleatoriamente
    public static void geraArrayBlocos(ArrayList <Bloco> array){
        Random gerador = new Random();
        int rand;
        
        //através de números aleatórios, adiciona automaticamente 15 elementos ao array 
        //de blocos
        for (int i = 0;i < 25;i++){
            rand = gerador.nextInt(6);
            
            Bloco B = null;
            switch (rand){
                case 0:
                    B = new Bloco(Bloco.Tipo.PEDRA);
                    break;
                case 1:
                    B = new Bloco(Bloco.Tipo.PEDREGULHO);
                    break;
                case 2:
                    B = new Bloco(Bloco.Tipo.TIJOLO);
                    break;
                case 3:
                    B = new Bloco(Bloco.Tipo.OURO);
                    break;
                case 4:
                    B = new Bloco(Bloco.Tipo.GELO);
                    break;
                case 5:
                    B = new Bloco(Bloco.Tipo.DIAMANTE);
                    break;
            }
            array.add(B);
        }
    }
    //varre o array de blocos, eliminando o bloco que tiver sido quebrado por uma picareta
    public static void verificaArrayBlocos(ArrayList <Bloco> array){
        for (int i = 0;i < array.size();i++){
            if (array.get(i).quebrado())
                array.remove(i);
        }
    }
    //imprime o array de blocos
    public static void imprimeArrayBlocos(ArrayList <Bloco> array){
        for (int i = 0;i < array.size();i++)
            System.out.println((i + 1) + ". " + array.get(i) + ".");
    }
    //imprime a mensagem padrão do jogo
    public static void mensagemPadrao(){
        System.out.println("Bem-vindo ao 'jogo' Minecraft -1.0 Beta!");
        System.out.println("Aperte enter para continuar: ");
        try {
            System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //primeiro menu
    public static void Menu1(){
        System.out.println("Escolha uma opção: ");
        System.out.println("1. Criar um personagem:");
        System.out.println("2. Sair do programa: ");
    }
    //menu player do jogo
    public static void MenuPrincipal(){
        System.out.println("Escolha uma opção abaixo:");
        System.out.println("1. Jogar");
        System.out.println("2. Adicionar uma picareta");
        System.out.println("3. Modificar a picareta atual");
        System.out.println("4. Sair do jogo");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner input = new Scanner(System.in);
        Random gerador = new Random();
        Principal player = new Principal();
        ArrayList <Bloco> listabloco = new ArrayList<>();
        int opcao,nbloco,rand;
        Zumbi zumbi = null;
        Picareta picaretaplayer;
        
        geraArrayBlocos(listabloco);
        
        mensagemPadrao();
        
        do{
            Menu1();
            opcao = input.nextInt();
        
            switch(opcao){
                case 1:
                    player = player.criarPersonagem();
                    break;
                case 2:
                    System.out.println("Programa encerrado!");
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    try {
                        System.in.read();
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }while(opcao != 1 && opcao != 2);
        
        if (opcao == 1){
            do{
                MenuPrincipal();
                opcao = input.nextInt();
                
                switch(opcao){
                    case 1:
                        //gera um número aleatório que vai definir se algum zumbi vai atacar o player
                        rand = gerador.nextInt(10);
                        if (rand <= 3){
                            zumbi = new Zumbi();
                            System.out.println("Um zumbi vai lhe atacar!");
                            rand = gerador.nextInt(10);
                            
                            picaretaplayer = player.buscaPicaretas();
                            
                            if (picaretaplayer == null){
                                System.out.println("Você não tem nenhuma picareta para atacar o zumbi, é melhor fugir!");
                                System.out.println("Aperte enter para fugir!");
                                try {
                                    System.in.read();
                                } catch (IOException ex) {
                                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                System.out.println("Você fugiu!");
                            }
                            else if (rand < 8){
                                System.out.println("Voce ganhou a chance de atacar!");
                                System.out.println("Aperte enter para atacar!");
                                try {
                                        System.in.read();
                                } catch (IOException ex) {
                                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                picaretaplayer.atacar(zumbi);
                            }
                            else{
                                System.out.println("O zumbi lhe atacou!");
                                zumbi.atacar(player);
                                if (player.getHP() <= 0){
                                    System.out.println("O personagem " + player + " morreu :(");
                                    try {
                                        System.in.read();
                                    } catch (IOException ex) {
                                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    System.out.println("Jogo encerrado!");
                                    return;
                                }
                            }
                        }
                        else{
                            verificaArrayBlocos(listabloco);
                            
                            if (listabloco.isEmpty()){
                                System.out.println("Não há nenhum bloco disponível!");
                                try {
                                    System.in.read();
                                } catch (IOException ex) {
                                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            else{
                                System.out.println("Escolha um bloco para quebrar: ");
                                imprimeArrayBlocos(listabloco);
                                nbloco = input.nextInt();
                                picaretaplayer = player.buscaPicaretas();
                                PicaretaMadeira PM;
                                PicaretaDiamante PD;
                                if (picaretaplayer == null)
                                    //caso o player não estiver utilizando uma picareta, ele quebra o bloco com
                                    //as próprias mãos
                                    player.quebrarBloco(listabloco.get(nbloco - 1));
                                else{
                                    //quebra o bloco escolhido utilizando a picareta atual do personagem.
                                    if (picaretaplayer instanceof PicaretaMadeira){
                                        PM = (PicaretaMadeira)picaretaplayer;
                                        PM.quebrarBloco(listabloco.get(nbloco - 1));
                                    }
                                    else{
                                        PD = (PicaretaDiamante)picaretaplayer;
                                        PD.quebrarBloco(listabloco.get(nbloco - 1));
                                    }
                                    //e o insere no array de itens do personagem
                                    player.inserirItem(listabloco.get(nbloco - 1));
                                }
                                try {
                                    System.in.read();
                                } catch (IOException ex) {
                                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        break;
                    case 2:
                        int opcaopicareta;
                        
                        System.out.println("Digite qual tipo de picareta você deseja criar: ");
                        System.out.println("1. Madeira");
                        System.out.println("2. Diamante");
                        opcaopicareta = input.nextInt();
                        
                        boolean b = player.criarPicareta(opcaopicareta);
                        
                        if(!b){
                            System.out.println("Não há blocos suficientes para a criação da picareta!");
                            try {
                                System.in.read();
                            } catch (IOException ex) {
                              Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        
                        break;
                    case 3:
                        player.modificarPicaretaAtual();
                        break;
                    case 4:
                        System.out.println("Programa encerrado!");
                        break;
                    default:
                        System.out.println("Opcao invalida!");
                        try {
                          System.in.read();
                        } catch (IOException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
            }while(opcao != 4);
        }
    }
    
}
