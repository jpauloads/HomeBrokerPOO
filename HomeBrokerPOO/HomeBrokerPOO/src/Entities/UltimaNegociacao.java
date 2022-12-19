/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

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
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

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
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    public LocalDateTime getDataModificacao() {
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
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    public void setDataModificacao(LocalDateTime dataModificacao) {
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
        final UltimaNegociacao other = (UltimaNegociacao) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.quantidade != other.quantidade) {
            return false;
        }
        if (!Objects.equals(this.ticker, other.ticker)) {
            return false;
        }
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        if (!Objects.equals(this.valorTotal, other.valorTotal)) {
            return false;
        }
        if (!Objects.equals(this.contaCompra, other.contaCompra)) {
            return false;
        }
        if (!Objects.equals(this.contaVenda, other.contaVenda)) {
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
