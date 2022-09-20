/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 *Cada conta esta ligada a 1 cliente apenas
 * @author jp_te
 */
public class Conta {
    private int id;
    private BigDecimal saldo;
    private Cliente cliente;
    private Date dataCriacao;
    private Date dataModificacao;
    
    public int getId(){
        return id;
    }
    public BigDecimal getSaldo(){
        return saldo;
    }
    public Cliente getCliente(){
        return cliente;
    }
    public Date getDataCriacao(){
        return dataCriacao;
    }
    public Date getDataModificacao(){
        return dataModificacao;
    }
    
    public void setId(int id){
        this.id = id;
    }
    public void setSaldo(BigDecimal saldo){
        this.saldo = saldo;
    }
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    public void setDataCriacao(Date dataCriacao){
        this.dataCriacao = dataCriacao;
    }
    public void setDataModificacao(Date dataModificacao){
        this.dataModificacao = dataModificacao;
    }
    
    public void deposito(BigDecimal valor){
        this.saldo.add(valor);
    }
    public void saque(BigDecimal valor){
        this.saldo.subtract(valor);
    }
    public void pagamento(BigDecimal valor){
        this.saldo.subtract(valor);
        //pra onde vai?
    }
    public void transferencia(BigDecimal valor){
        this.saldo.subtract(valor);
        //pra onde vai?
    }
    public void extrato(){
        //O extrato não mostra os valores em ativos alocados
        //qualé que é?
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
    
    
}
