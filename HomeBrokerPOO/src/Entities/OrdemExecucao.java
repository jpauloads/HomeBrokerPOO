/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 5;
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
        final OrdemExecucao other = (OrdemExecucao) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.quantidade != other.quantidade) {
            return false;
        }
        if (!Objects.equals(this.ordem, other.ordem)) {
            return false;
        }
        if (!Objects.equals(this.contaCompra, other.contaCompra)) {
            return false;
        }
        if (!Objects.equals(this.contaVende, other.contaVende)) {
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
