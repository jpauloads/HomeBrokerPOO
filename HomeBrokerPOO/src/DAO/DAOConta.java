package DAO;

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
    
    public void pagar(Cliente cliente, BigDecimal valor){
        cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(valor));
    }
    
    public void transferir(Cliente cliente, BigDecimal valor, Cliente clienteFinal){
        System.out.println("transfer: " + clienteFinal);
        cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(valor));
        clienteFinal.getConta().setSaldo(clienteFinal.getConta().getSaldo().add(valor));
    }
    
    public void comprarAtivos(Cliente cliente, BigDecimal valor){
        cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(valor));
    }
    
    public void venderAtivos(Cliente cliente, BigDecimal valor){
        cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(valor));
    }
    
    public void extrato(){
        
    }
    
}
