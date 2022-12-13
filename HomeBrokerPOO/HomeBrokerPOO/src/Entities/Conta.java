/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Entities.Enum.Usuario;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *Cada conta esta ligada a 1 cliente apenas
 * @author jp_te
 */
public class Conta {
    private int id;
    private BigDecimal saldo = BigDecimal.valueOf(0.00);
    private Cliente cliente;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    
    public Conta(Cliente cliente){
        this.setCliente(cliente);
    }

    public Conta() {
    }
    
    public int getId(){
        return id;
    }
    public BigDecimal getSaldo(){
        return saldo;
    }
    public Cliente getCliente(){
        return cliente;
    }
    public LocalDateTime getDataCriacao(){
        return dataCriacao;
    }
    public LocalDateTime getDataModificacao(){
        return dataModificacao;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setSaldo(BigDecimal saldo){
        this.saldo = saldo;
    }
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    public void setDataCriacao(LocalDateTime dataCriacao){
        this.dataCriacao = dataCriacao;
    }
    public void setDataModificacao(LocalDateTime dataModificacao){
        this.dataModificacao = dataModificacao;
    }
        
    //O custo de manutenção será de R$20,00 e será debitado todo dia 15.

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Conta other = (Conta) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.saldo, other.saldo)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.dataCriacao, other.dataCriacao)) {
            return false;
        }
        if (!Objects.equals(this.dataModificacao, other.dataModificacao)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {

        return "\nId da conta = " + id + 
                "\nNome do proprietário = " + cliente.getNome() +
                "\nSaldo = " + saldo + 
                "\nConta aberta em = " + dataCriacao + 
                "\nUltima modificação = " + dataModificacao;
    }
    
    
}
