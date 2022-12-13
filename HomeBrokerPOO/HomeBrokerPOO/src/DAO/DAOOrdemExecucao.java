/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.OrdemExecucao;

/**
 *
 * @author JPGF
 */
public class DAOOrdemExecucao {
    
    final int totalOrdemExec = 50;
    private final OrdemExecucao[] vetOrdemExecucao = new OrdemExecucao[totalOrdemExec];
    
    public boolean vetCheio(){
        for (OrdemExecucao temp : vetOrdemExecucao){
            if(temp == null)
                return false;
        }
        return true; 
    }
    
    public boolean temEspaco(){
        for (OrdemExecucao temp : vetOrdemExecucao)
            if(temp == null){   
                return true;
        }
        return false;
    }
    
    public boolean vetVazio(){
        for (OrdemExecucao temp : vetOrdemExecucao){
            if(temp != null)
                return false;
        }
        return true; 
    }
    
    public int proximaPosicaoLivre(){
        for(int i =0; i < vetOrdemExecucao.length; i++){
            if(vetOrdemExecucao[i] == null) 
                return i;
        }
        return -1;
    }
    
    public void criar(OrdemExecucao ordem){
        if(this.temEspaco()){
            final int pos = this.proximaPosicaoLivre();
            vetOrdemExecucao[pos] = ordem;
        }
    }
    
}
