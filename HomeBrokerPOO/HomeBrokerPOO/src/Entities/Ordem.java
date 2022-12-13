/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Entities.Enum.TipoOrdem;
import Entities.Enum.Estado;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author jp_te
 */
public class Ordem {
    private static int nextId;
    private int id;
    private int quantidade;
    private BigDecimal valor;
    private BigDecimal valorTotal;
    private Conta conta;
    private String ticker;
    private TipoOrdem tipoOrdem;
    private Estado estado;
    private Date dataCriacao;
    private Date dataModificacao;
    
    public Ordem(){
        this.id = nextId++;
        this.dataCriacao = new Date();
    }

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
    public Conta getConta() {
        return conta;
    }
    public String getTicker() {
        return ticker;
    }
    public TipoOrdem getTipoOrdem() {
        return tipoOrdem;
    }
    public Estado getEstado() {
        return estado;
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
    public void setConta(Conta conta) {
        this.conta = conta;
    }
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
    public void setTipoOrdem(TipoOrdem tipoOrdem) {
        this.tipoOrdem = tipoOrdem;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
    
    //você escolhe a quantidade que deseja (comprar/vender), valor que deseja 
    //(comprar/vender) e isso gera um valor total da ordem.
    //Só é possível efetuar uma operação de compra se você tem saldo disponível 
    //para isso. 
    //Uma ordem tem um valor fixo 10 reais para o comprador e para o vendedor. 
    //O valor é transferido para a bolsa.

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
        final Ordem other = (Ordem) obj;
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
        if (!Objects.equals(this.conta, other.conta)) {
            return false;
        }
        if (this.tipoOrdem != other.tipoOrdem) {
            return false;
        }
        if (this.estado != other.estado) {
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
