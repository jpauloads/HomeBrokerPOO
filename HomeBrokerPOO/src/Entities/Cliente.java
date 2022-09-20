/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Entities.Enum.Usuario;
import java.util.Date;
import java.util.Objects;

/**
 *Cada cliente possui 1 conta
 * @author jp_te
 */
public class Cliente {
    private int id;
    private String cpf;
    private String endereco;
    private String login;
    private String nome;
    private String telefone;
    private String senha;
    private Usuario tipoUsuario;
    private Date dataCriacao;
    private Date dataModificacao;

    public int getId(){
        return id;
    }
    public String getCpf() {
        return cpf;
    }
    public String getEndereco(){
        return endereco;
    }
    public String getLogin(){
        return login;
    }
    public String getNome(){
        return nome;
    }
    public String getTelefone(){
        return telefone;
    }
    public String getSenha(){
        return senha;
    }
    public Usuario getTipoUsuario(){
        return tipoUsuario;
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
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    public void setLogin(String login){
        this.login = login;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }
    public void setTipoUsuario(Usuario tipoUsuario){
        this.tipoUsuario = tipoUsuario;
    }
    public void setDataCriacao(Date dataCriacao){
        this.dataCriacao = dataCriacao;
    }
    public void setDataModificacao(Date dataModificacao){
        this.dataModificacao = dataModificacao;
    }
    
    public Cliente(){
        
    }

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
        final Cliente other = (Cliente) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (this.tipoUsuario != other.tipoUsuario) {
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
