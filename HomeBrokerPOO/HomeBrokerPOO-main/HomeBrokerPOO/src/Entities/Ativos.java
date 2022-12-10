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
 *
 * @author jp_te
 */
public class Ativos {
    private static int nextId;
    private int id;
    private int totalAtivos;
    private BigDecimal precoInicial;
    private String nomeEmpresa;
    private String ticker;
    private Date dataCriacao;
    private Date dataModificacao;
    
    public Ativos(){
        this.id = nextId++;
        this.dataCriacao = new Date();
    }
    
    public int getId(){
        return id;
    }
    public int getTotalAtivos(){
        return totalAtivos;
    }
    public BigDecimal getPrecoInicial(){
        return precoInicial;
    }
    public String getNomeEmpresa(){
        return nomeEmpresa;
    }
    public String getTicker(){
        return ticker;
    }
    public Date getDataCriacao(){
        return dataCriacao;
    }
    public Date getDataModificacao(){
        return dataModificacao;
    }
    
    
    public void setTotalAtivos(int totalAtivos){
        this.totalAtivos = totalAtivos;
        setDataModificacao(new Date());
    }
    public void setPrecoInicial(BigDecimal precoInicial){
        this.precoInicial = precoInicial;
        setDataModificacao(new Date());
    }
    public void setNomeEmpresa(String nomeEmpresa){
        this.nomeEmpresa = nomeEmpresa;
        setDataModificacao(new Date());
    }
    public void setTicker(String ticker){
        this.ticker = ticker;
        setDataModificacao(new Date());
    }
    public void setDataCriacao(Date dataCriacao){
        this.dataCriacao = dataCriacao;
        setDataModificacao(new Date());
    }
    public void setDataModificacao(Date dataModificacao){
        this.dataModificacao = dataModificacao;
    }
    

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
        final Ativos other = (Ativos) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.totalAtivos != other.totalAtivos) {
            return false;
        }
        if (!Objects.equals(this.nomeEmpresa, other.nomeEmpresa)) {
            return false;
        }
        if (!Objects.equals(this.ticker, other.ticker)) {
            return false;
        }
        if (!Objects.equals(this.precoInicial, other.precoInicial)) {
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
        return "Id do ativo = " + id + 
                "\nEmpresa = " + nomeEmpresa + 
                "\nTicker = " + ticker + 
                "\nPreço = " + precoInicial + 
                "\nTotal de ativos disponíveis = " + totalAtivos;
    }
}
