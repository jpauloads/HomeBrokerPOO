/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Conta;
import Entities.Movimentacao;

/**
 *
 * @author JPGF
 */
public class DAOMovimentacao {
    int maxOperacoes = 50;
    private final Movimentacao[] movimentacoes = new Movimentacao[maxOperacoes];
    
    public DAOMovimentacao(){
    }
    
    public boolean vetVazio(){
        for (Movimentacao temp : movimentacoes)
            if(temp != null){
                return false;
        }
        return true;
    }
    
    public boolean temEspaco(){
        for (Movimentacao temp : movimentacoes)
            if(temp == null){
                //Tava dando errado por causa dessa condição
                return true;
        }
        return false;
    }
    
    public boolean vetCheio(){
        for (Movimentacao temp : movimentacoes)
            if(temp == null){
                return false;
        }
        return true;
    }
    
    public int proximaPosicaoLivre(){
        for (int i = 0; i < movimentacoes.length; i++) {
            if(movimentacoes[i] == null){
                return i;
            }         
        }
        return -1;
    }   
    
    public void criar(Movimentacao movimentacaoNova){
        if(this.temEspaco()){
            int posLivre = this.proximaPosicaoLivre();
            movimentacoes[posLivre] = movimentacaoNova;
        }
    }
    
    
    /* FALTA TERMINAR */
    public StringBuilder ler(Conta conta){
        
        StringBuilder resultado = new StringBuilder("");
        if(!this.vetVazio()){
            for(Movimentacao temp : movimentacoes){
                if(temp != null){
                    if(temp.getContaOrigem() == conta || temp.getContaDestino() == conta){
                        resultado.append(temp.toString());
                    }
                }
            }
            return resultado;
        } else {
            resultado.append("\nNenhuma operacao realizada");
            return resultado;
        }
    }   
}
