/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.math.BigDecimal;
import java.util.Date;

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
    
    
}
