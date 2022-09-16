/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author jp_te
 */
public class OrdemExecucao {
    private int id;
    private int quantidade;
    private Ordem ordem;
    private Conta contaCompra;
    private Conta contaVende;
    private Date dataCriacao;
    private Date dataModificacao;

    public int getId() {
        return id;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public Ordem getOrdem() {
        return ordem;
    }
    public Conta getContaCompra() {
        return contaCompra;
    }
    public Conta getContaVende() {
        return contaVende;
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
    public void setOrdem(Ordem ordem) {
        this.ordem = ordem;
    }
    public void setContaCompra(Conta contaCompra) {
        this.contaCompra = contaCompra;
    }
    public void setContaVende(Conta contaVende) {
        this.contaVende = contaVende;
    }
    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
    
    //uma ordem pode ser parcialmente executada
    
}
