/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 *Cada conta esta ligada a 1 cliente apenas
 * @author jp_te
 */
public class Conta {
    private static int nextId;
    private int id;
    private BigDecimal saldo = BigDecimal.valueOf(0.00);
    private Cliente cliente;
    private Date dataCriacao;
    private Date dataModificacao;
    
    public Conta(){
        BigDecimal depositoGratis = new BigDecimal("20000");
        this.id = nextId++;
        this.dataCriacao = new Date();
        this.dataModificacao = new Date();
        this.saldo = depositoGratis;
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
    public Date getDataCriacao(){
        return dataCriacao;
    }
    public Date getDataModificacao(){
        return dataModificacao;
    }
    
    public void setSaldo(BigDecimal saldo){
        this.saldo = saldo;
        setDataModificacao(new Date());
    }
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
        setDataModificacao(new Date());
    }
    public void setDataCriacao(Date dataCriacao){
        this.dataCriacao = dataCriacao;
        setDataModificacao(new Date());
    }
    private void setDataModificacao(Date dataModificacao){
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

        return "\nId da conta = " + id + "\nSaldo = " + saldo + "\nConta aberta em = " + dataCriacao + "\nUltima modificação = " + dataModificacao;
    }
    
    
}
