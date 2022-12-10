/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Ativos;
import Entities.Cliente;
import Entities.Conta;
import Entities.Ordem;
import Entities.Enum.Estado;
import Entities.Enum.TipoOrdem;
import Entities.OrdemExecucao;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author jp_te
 */
public class DAOOrdem {
    
    private Ordem[] vetorOrdem = new Ordem[50];
    
    public DAOOrdem(){
    }
    
    public Ordem[] getVetOrdem(){
        return vetorOrdem;
    }
    
    public int proximaPosicaoLivre(){
        for (int i = 0; i < vetorOrdem.length; i++) {
            if(vetorOrdem[i] == null){
                return i;
            }         
        }
        return -1;
    }
    
    public void adicionaVetOrdem(Ordem ordem){
        vetorOrdem[proximaPosicaoLivre()] = ordem;
    }
    
    public boolean ordem0(Cliente cliente, Ativos ativo){
        for(int i = 0; i < vetorOrdem.length; i++){
            if(vetorOrdem[i] != null){
                if(vetorOrdem[i].getConta() == cliente.getConta() && vetorOrdem[i].getTicker() == ativo.getTicker()){
                        return false;
                }
            }
        }
        return true;
    }
    
    /*public Ordem achaOrdem(){
        
    }*/

    public void comprarAtivo(Cliente cliente, Ativos ativo, int numAtivo){
        Ordem ordemCompra = new Ordem();  
        
        OrdemExecucao novaOrdem = new OrdemExecucao();
        DAOOrdemExecucao daoOrdemExecucao = new DAOOrdemExecucao();
        Date agora = new Date();
        
        novaOrdem.setContaCompra(cliente.getConta());
        novaOrdem.setDataCriacao(agora);
        //int ativos = numAtivo;
        
        if(ordem0(cliente, ativo)){
            if(numAtivo == 1){
                if(ativo.getTotalAtivos() >= numAtivo){
                    ordemCompra.setQuantidade(numAtivo);
                    ordemCompra.setTipoOrdem(TipoOrdem.ORDEM0);
                    ordemCompra.setValor(ativo.getPrecoInicial());
                    // Desconto de 10% da ordem 0 adicionado no multiply
                    ordemCompra.setValorTotal((ativo.getPrecoInicial().multiply(BigDecimal.valueOf(numAtivo))).multiply(BigDecimal.valueOf(0.1)));
                    ordemCompra.setTicker(ativo.getTicker());
                    ordemCompra.setConta(cliente.getConta());
                    
                    //novaOrdem.setContaVende();    no caso quem vende é a bolsa...
                    
                    // -1 - menor, 0 - igual, 1 - maior
                    if(cliente.getConta().getSaldo().compareTo(ordemCompra.getValorTotal()) == 1){

                        ordemCompra.setEstado(Estado.TOTAL);
                        cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(ordemCompra.getValorTotal()));
                        ativo.setTotalAtivos(ativo.getTotalAtivos() - numAtivo);      

                    }else if(cliente.getConta().getSaldo().compareTo(ordemCompra.getValorTotal()) == 0){

                        ordemCompra.setEstado(Estado.TOTAL);
                        cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(ordemCompra.getValorTotal()));
                        ativo.setTotalAtivos(ativo.getTotalAtivos() - numAtivo);

                    }else if(cliente.getConta().getSaldo().compareTo(ordemCompra.getValorTotal()) == -1){
                        ordemCompra.setEstado(Estado.NAO);
                    }
                    
                    novaOrdem.setOrdem(ordemCompra);
                    daoOrdemExecucao.criar(novaOrdem);

                }
                return;
            }else{
                numAtivo = numAtivo - 1;
            }
        }
        
        if(!ordem0(cliente, ativo)){
            if(ativo.getTotalAtivos() >= numAtivo){
                
                ordemCompra.setQuantidade(numAtivo);
                ordemCompra.setTipoOrdem(TipoOrdem.COMPRA);
                ordemCompra.setValor(ativo.getPrecoInicial());
                ordemCompra.setValorTotal(ativo.getPrecoInicial().multiply(BigDecimal.valueOf(numAtivo)));
                ordemCompra.setTicker(ativo.getTicker());
                ordemCompra.setConta(cliente.getConta());

                // -1 - menor, 0 - igual, 1 - maior
                if(cliente.getConta().getSaldo().compareTo(ordemCompra.getValorTotal()) == 1){

                    ordemCompra.setEstado(Estado.TOTAL);
                    cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(ordemCompra.getValorTotal()));
                    ativo.setTotalAtivos(ativo.getTotalAtivos() - numAtivo);

                }else if(cliente.getConta().getSaldo().compareTo(ordemCompra.getValorTotal()) == 0){

                    ordemCompra.setEstado(Estado.TOTAL);
                    cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(ordemCompra.getValorTotal()));
                    ativo.setTotalAtivos(ativo.getTotalAtivos() - numAtivo);

                }else if(cliente.getConta().getSaldo().compareTo(ordemCompra.getValorTotal()) == -1){
                    ordemCompra.setEstado(Estado.NAO);
                }
                
            } else if(ativo.getTotalAtivos() < numAtivo){
                
                ordemCompra.setEstado(Estado.PARCIAL);
                cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(ordemCompra.getValorTotal()));
                ativo.setTotalAtivos(ativo.getTotalAtivos() - numAtivo);
                int qtdeAtivosEspera = ativo.getTotalAtivos();
                ordemCompra.setQuantidade(qtdeAtivosEspera);
            }
            novaOrdem.setOrdem(ordemCompra);
            daoOrdemExecucao.criar(novaOrdem);
        }
    }
    
    public void venderAtivo(Cliente cliente, BigDecimal valorVenda, Ativos ativo, int numAtivo){
        
        OrdemExecucao novaOrdem = new OrdemExecucao();
        DAOOrdemExecucao daoOrdemExecucao = new DAOOrdemExecucao();
        Date agora = new Date();
        
        novaOrdem.setContaCompra(cliente.getConta());
        novaOrdem.setDataCriacao(agora);
        
        Ordem ordemCompra = new Ordem();
        ordemCompra.setQuantidade(numAtivo);
        ordemCompra.setTipoOrdem(TipoOrdem.VENDA);
        ordemCompra.setValor(ativo.getPrecoInicial());
        ordemCompra.setValorTotal(ativo.getPrecoInicial().multiply(BigDecimal.valueOf(numAtivo)));
        ordemCompra.setTicker(ativo.getTicker());
        ordemCompra.setConta(cliente.getConta());
        
        if(ativo.getTotalAtivos() >= numAtivo){

            // -1 - menor, 0 - igual, 1 - maior
            if(cliente.getConta().getSaldo().compareTo(ordemCompra.getValorTotal()) == 1){

                ordemCompra.setEstado(Estado.TOTAL);
                cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(ordemCompra.getValorTotal()));
                ativo.setTotalAtivos(ativo.getTotalAtivos() - numAtivo);

            }else if(cliente.getConta().getSaldo().compareTo(ordemCompra.getValorTotal()) == 0){

                ordemCompra.setEstado(Estado.TOTAL);
                cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(ordemCompra.getValorTotal()));
                ativo.setTotalAtivos(ativo.getTotalAtivos() - numAtivo);

            }else if(cliente.getConta().getSaldo().compareTo(ordemCompra.getValorTotal()) == -1){
                //adicoinar estado parcial e não
                ordemCompra.setEstado(Estado.NAO);
            }
        }else{
            //adicionar estado parcial e não
            ordemCompra.setEstado(Estado.NAO);
        }
        novaOrdem.setOrdem(ordemCompra);
        daoOrdemExecucao.criar(novaOrdem);
    }
}
