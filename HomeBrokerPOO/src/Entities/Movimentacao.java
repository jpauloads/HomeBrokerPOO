/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Entities.Enum.Operacao;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author jp_te
 */
public class Movimentacao {
    private int id;
    private BigDecimal valor;
    private Conta conta;
    private Operacao operacao;
    private String descricao;
    private Date dataCriacao;
    private Date dataModificacao;
    
    public int getId(){
        return id;
    }
    public BigDecimal getValor(){
        return valor;
    }
    public Conta getConta(){
        return conta;
    }
    public Operacao getOperacao(){
        return operacao;
    }
    public String getDescricao(){
        return descricao;
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
    public void setValor(BigDecimal valor){
        this.valor = valor;
    }
    public void setConta(Conta conta){
        this.conta = conta;
    }
    public void setOperacao(Operacao operacao){
        this.operacao = operacao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public void setDataCriacao(Date dataCriacao){
        this.dataCriacao = dataCriacao;
    }
    public void setDataModificacao(Date dataModificacao){
        this.dataModificacao = dataModificacao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Movimentacao other = (Movimentacao) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        if (!Objects.equals(this.conta, other.conta)) {
            return false;
        }
        if (this.operacao != other.operacao) {
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
