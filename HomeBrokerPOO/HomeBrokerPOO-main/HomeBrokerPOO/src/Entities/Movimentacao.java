/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Entities.Enum.Operacao;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author jJPGF
 */
public class Movimentacao {
    private static int serial;
    private final int id;
    private BigDecimal valor;
    private Conta conta;
    private Conta contaDestino;
    private Operacao tipoOperacao;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    
    public Movimentacao(){
        this.id = ++Movimentacao.serial;
    }
    
    public BigDecimal getValor(){
        return valor;
    }
    public Conta getContaOrigem(){
        return conta;
    }
    public Conta getContaDestino(){
        return contaDestino;
    }
    public Operacao getOperacao(){
        return tipoOperacao;
    }
    public String getDescricao(){
        return descricao;
    }
    public LocalDateTime getDataCriacao(){
        return dataCriacao;
    }
    public LocalDateTime getDataModificacao(){
        return dataModificacao;
    }
    

    public void setValor(BigDecimal valor){
        this.valor = valor;
    }
    public void setContaOrigem(Conta conta){
        this.conta = conta;
    }
    public void setContaDestino(Conta contaDestino){
        this.contaDestino = contaDestino;
    }
    public void setOperacao(Operacao tipoOperacao){
        this.tipoOperacao = tipoOperacao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public void setDataCriacao(LocalDateTime dataCriacao){
        this.dataCriacao = dataCriacao;
    }
    public void setDataModificacao(LocalDateTime dataModificacao){
        this.dataModificacao = dataModificacao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.valor);
        hash = 89 * hash + Objects.hashCode(this.conta);
        hash = 89 * hash + Objects.hashCode(this.tipoOperacao);
        hash = 89 * hash + Objects.hashCode(this.descricao);
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
        final Movimentacao other = (Movimentacao) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        if (!Objects.equals(this.conta, other.conta)) {
            return false;
        }
        if (this.tipoOperacao != other.tipoOperacao) {
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
        return "\nMovimentacao: \n" +"Id: " + id + 
               "\nValor: " + valor + 
               "\nConta Origem: Id = " + conta.getId() + " Nome = "+ conta.getCliente().getNome() + 
               "\nConta Destino: Id= " + contaDestino.getId()+ " nome = "+ contaDestino.getCliente().getNome() + 
               "\nOperacao: " + tipoOperacao +
               "\nDescricao: " + descricao +
               "\nData de Modificacao: " + dataModificacao;
    }
       
}
