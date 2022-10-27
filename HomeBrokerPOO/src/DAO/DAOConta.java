package DAO;

import Entities.Ativos;
import Entities.Cliente;

import java.math.BigDecimal;

/**
 *
 * @author jp_te
 */
public class DAOConta {
   
    public DAOConta(){
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
    
    public void comprarAtivos(Cliente cliente, Ativos ativo, String numAtivos){
        
        BigDecimal valor = ativo.getPrecoInicial().multiply(new BigDecimal(numAtivos));
        
        cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(valor));
        
        ativo.setTotalAtivos(ativo.getTotalAtivos() - Integer.parseInt(numAtivos));
    }
    
    public void venderAtivos(Cliente cliente, BigDecimal valor){
        cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(valor));
    }
    
    public void pagarDividendos(Cliente cliente){
        
    }  
    
}
