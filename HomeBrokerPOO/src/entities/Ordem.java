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
public class Ordem {
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
}
