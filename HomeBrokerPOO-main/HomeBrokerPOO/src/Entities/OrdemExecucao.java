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
    private final int id;
    private static int serial;
    
    private int quantidade;
    private Ordem ordem;
    private Conta contaCompra;
    private Conta contaVende;
    private Date dataCriacao;
    private Date dataModificacao;
    
    public OrdemExecucao() {
        this.id = ++OrdemExecucao.serial;
        this.quantidade = 1;
    }

    public static int getSerial() {
        return serial;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Ordem getOrdem() {
        return ordem;
    }

    public void setOrdem(Ordem ordem) {
        this.ordem = ordem;
    }

    public Conta getContaCompra() {
        return contaCompra;
    }

    public void setContaCompra(Conta contaCompra) {
        this.contaCompra = contaCompra;
    }

    public Conta getContaVende() {
        return contaVende;
    }

    public void setContaVende(Conta contaVende) {
        this.contaVende = contaVende;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + this.quantidade;
        hash = 89 * hash + Objects.hashCode(this.ordem);
        hash = 89 * hash + Objects.hashCode(this.contaCompra);
        hash = 89 * hash + Objects.hashCode(this.contaVende);
        hash = 89 * hash + Objects.hashCode(this.dataCriacao);
        hash = 89 * hash + Objects.hashCode(this.dataModificacao);
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

    @Override
    public String toString() {
        return "\nOrdemExecucao" + " id = " + id + 
                "\nQuantidade = " + quantidade + 
                "\nOrdem = " + ordem + 
                "\nContaCompra = " + contaCompra + 
                "\nContaVende = " + contaVende + 
                "\nDataCriacao = " + dataCriacao + 
                "\nDataModificacao = " + dataModificacao;
    }
    
}
