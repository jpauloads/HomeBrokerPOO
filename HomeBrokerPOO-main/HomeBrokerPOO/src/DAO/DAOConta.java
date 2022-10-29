package DAO;

import Entities.Ativos;
import Entities.Cliente;
import Entities.Conta;

import java.math.BigDecimal;

/**
 *
 * @author jp_te
 */
public class DAOConta {
   
    public DAOConta(){
    }
    
    public void criarConta(Cliente cliente, Cliente bolsa){
        Conta conta = new Conta(cliente);
        cliente.setConta(conta);
        
        depositar(bolsa, BigDecimal.valueOf(500000.00));
    }
    
    public void depositar(Cliente cliente, BigDecimal valor){
        cliente.getConta().setSaldo(cliente.getConta().getSaldo().add(valor));
    }
    
    public void sacar(Cliente cliente, BigDecimal valor){
        cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(valor));
    }
    
    public void pagar(Cliente cliente, BigDecimal valor, Cliente adm){
        cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(valor));
        adm.getConta().setSaldo(adm.getConta().getSaldo().add(valor));
    }
    
    public void transferir(Cliente clienteInicial, BigDecimal valor, Cliente clienteFinal){
        clienteInicial.getConta().setSaldo(clienteInicial.getConta().getSaldo().subtract(valor));
        clienteFinal.getConta().setSaldo(clienteFinal.getConta().getSaldo().add(valor));
    }
    
    /*public void comprarAtivos(Cliente cliente, Ativos ativo, String numAtivos){
        
        BigDecimal valor = ativo.getPrecoInicial().multiply(new BigDecimal(numAtivos));
        
        
        
        cliente.getConta().setAtivos(ativo);
    }
    
    public void venderAtivos(Cliente cliente, BigDecimal novoValor, Ativos ativoEscolhido){
        for(int i = 0; i < cliente.getConta().getAtivos().length; i++){
            if(cliente.getConta().getAtivos()[i] != null){
                if(cliente.getConta().getAtivos()[i].getId() == ativoEscolhido.getId()){
                    cliente.getConta().getAtivos()[i] = null;
                    ativoEscolhido.setPrecoUltimaVenda(novoValor);
                    cliente.getConta().setSaldo(cliente.getConta().getSaldo().add(novoValor));
                }
            }
        }
    }*/
    
    public void pagarDividendos(Cliente[] cliente){
        for(Cliente temp : cliente){
            if(temp != null){
                if(temp.getConta().getAtivos() != null){
                    for(int i = 0; i < temp.getConta().getAtivos().length; i++){
                        if(temp.getConta().getAtivos()[i] != null){
                            temp.getConta().setSaldo(temp.getConta().getSaldo().add(temp.getConta().getAtivos()[i].getPrecoInicial()));
                        }
                    }
                }
            }
        }
    }  
    
}
