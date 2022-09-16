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
public class Ativos {
    private int id;
    private int totalAtivos;
    private BigDecimal precoInicial;
    private String nomeEmpresa;
    private String ticker;
    private Date dataCriacao;
    private Date dataModificacao;
    
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
    
    public void setId(int id){
        this.id = id;
    }
    public void setTotalAtivos(int totalAtivos){
        this.totalAtivos = totalAtivos;
    }
    public void setPrecoInicial(BigDecimal precoInicial){
        this.precoInicial = precoInicial;
    }
    public void setNomeEmpresa(String nomeEmpresa){
        this.nomeEmpresa = nomeEmpresa;
    }
    public void setTicker(String ticker){
        this.ticker = ticker;
    }
    public void setDataCriacao(Date dataCriacao){
        this.dataCriacao = dataCriacao;
    }
    public void setDataModificacao(Date dataModificacao){
        this.dataModificacao = dataModificacao;
    }
}
