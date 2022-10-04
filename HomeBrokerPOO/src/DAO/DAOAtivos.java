/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Ativos;
import java.math.BigDecimal;

/**
 *
 * @author jp_te
 */
public class DAOAtivos {
    
    private Ativos[] ativos = new Ativos[5];
    
    public DAOAtivos(){
        Ativos ativo1 = new Ativos();
        ativo1.setNomeEmpresa("João Pedro & Paulo");
        ativo1.setTicker("JPP");
        ativo1.setTotalAtivos(5);
        ativo1.setPrecoInicial(new BigDecimal("50"));
        
        Ativos ativo2 = new Ativos();
        ativo2.setNomeEmpresa("Magalu");
        ativo2.setTicker("MAG");
        ativo2.setTotalAtivos(5);
        ativo2.setPrecoInicial(new BigDecimal("50"));
        
        Ativos ativo3 = new Ativos();
        ativo3.setNomeEmpresa("Only fans");
        ativo3.setTicker("ONF");
        ativo3.setTotalAtivos(5);
        ativo3.setPrecoInicial(new BigDecimal("50"));
        
        ativos[0] = ativo1;
        ativos[1] = ativo2;
        ativos[2] = ativo3;
    }

    public Ativos[] getAtivos() {
        return ativos;
    }
    
    public void criarAtivos(String nomeEmpresa, String ticker, BigDecimal precoInicial, int totalAtivos){
        Ativos ativo = new Ativos();
        
        ativo.setNomeEmpresa(nomeEmpresa);
        ativo.setTicker(ticker);
        ativo.setTotalAtivos(totalAtivos);
        ativo.setPrecoInicial(precoInicial);
        
        for(int i = 0; i < ativos.length; i++){
            if(ativos[i] == null){
                ativos[i] = ativo;
                break;
            }
        }
    }
    
}
