/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

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
    
}
