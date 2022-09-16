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
public class UltimaNegociacao {
    private int id;
    private int quantidade;
    private BigDecimal valor;
    private BigDecimal valorTotal;
    private String ticker;
    private Conta contaCompra;
    private Conta contaVenda;
    private Date dataCriacao;
    private Date dataModificacao;

    public int getId() {
        return id;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public BigDecimal getValorTotal() {
        return valorTotal;
    }
    public String getTicker() {
        return ticker;
    }
    public Conta getContaCompra() {
        return contaCompra;
    }
    public Conta getContaVenda() {
        return contaVenda;
    }
    public Date getDataCriacao() {
        return dataCriacao;
    }
    public Date getDataModificacao() {
        return dataModificacao;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
    public void setContaCompra(Conta contaCompra) {
        this.contaCompra = contaCompra;
    }
    public void setContaVenda(Conta contaVenda) {
        this.contaVenda = contaVenda;
    }
    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
    
    
}
