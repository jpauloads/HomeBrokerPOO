/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Entities.Cliente;
import Entities.Enum.Usuario;
import javax.swing.JOptionPane;
import DAO.DAOCliente;
import DAO.DAOConta;
import Entities.Conta;
import java.math.BigDecimal;

/**
 *
 * @author jp_te
 */
public class Interfaces {
    
    private int op;
    private StringBuilder builder = new StringBuilder();
    private DAOCliente daoCliente = new DAOCliente();
    private DAOConta daoConta = new DAOConta();
    private Cliente cliente = new Cliente();
    
    public Interfaces(){
    }
    
    /* TELAS REFERENTES AO CLIENTE */
    public int home(){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\n1 - Criar conta");
        builder.append("\n2 - Entrar");
        builder.append("\n3 - Sobre");
        builder.append("\n4 - Sair");
        op = Integer.parseInt(JOptionPane.showInputDialog(builder));
        return op;
    }
    
    public String[] criar(){
        String[] loginSenha = new String[2];
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nCriar login: ");
        loginSenha[0] = JOptionPane.showInputDialog(builder);
        builder.append(loginSenha[0]);
        builder.append("\nCriar senha: ");
        loginSenha[1] = JOptionPane.showInputDialog(builder);
        
        return loginSenha;
    }
    
    public Cliente entrar(){
        String[] loginSenha = new String[2];
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nLogin: ");
        loginSenha[0] = JOptionPane.showInputDialog(builder);
        builder.append(loginSenha[0]);
        builder.append("\nSenha: ");
        loginSenha[1] = JOptionPane.showInputDialog(builder);
        
        cliente = daoCliente.validarLogin(loginSenha);
        
        return cliente;
    }
    
    public int contaLogada(Cliente cliente){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nOlá " + cliente.getNome());
        builder.append("\nTipo de cliente: " + cliente.getTipoUsuario());
        builder.append("\nOpções");
        builder.append("\n---------------------------");
        if(cliente.getTipoUsuario() == Usuario.ADM){
            builder.append("\n1- Criar novo cliente");
            builder.append("\n2- Pagar dividendos");
            builder.append("\n3- Cadastrar tickets");
            builder.append("\n4- Sair");
        }else{
            if(cliente.getConta() == null){
                builder.append("\n1- Cadastrar conta");
                builder.append("\n2- Sair");
            }else{
                builder.append(cliente.getConta());
                builder.append("\n---------------------------");
                builder.append("\n1- Depósito");
                builder.append("\n2- Saque");
                builder.append("\n3- Pagamento");
                builder.append("\n4- Transferência");
                builder.append("\n5- Comprar ativos");
                builder.append("\n6- Vender ativos");
                builder.append("\n7- Extrato");
                builder.append("\n8- Sair");

            }   
        }
        op = Integer.parseInt(JOptionPane.showInputDialog(builder));
        return op;
    }
    
    public void criarClienteTela(){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nCriar Cliente");
        builder.append("\n---------------------------");
        builder.append("\nLogin: ");
        String login = JOptionPane.showInputDialog(builder);
        builder.append(login);
        builder.append("\nSenha: ");
        String senha = JOptionPane.showInputDialog(builder);
        builder.append(senha);
        builder.append("\nNome: ");
        String nome = JOptionPane.showInputDialog(builder);
        builder.append(nome);
        builder.append("\nCPF: ");
        String cpf = JOptionPane.showInputDialog(builder);
        builder.append(cpf);
        builder.append("\nEndereço: ");
        String endereco = JOptionPane.showInputDialog(builder);
        builder.append(endereco);
        builder.append("\nTelefone: ");
        String telefone = JOptionPane.showInputDialog(builder);
        builder.append(telefone);
        builder.append("\nTipo de usuário: ");
        String tipoUsuario = JOptionPane.showInputDialog(builder);
        builder.append(tipoUsuario);
        
        daoCliente.criarCliente(login, senha, nome, cpf, endereco, telefone, tipoUsuario);
    }
    
    public void cadastrarConta(Cliente cliente){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nConta criada com sucesso");
        
        Conta conta = new Conta();
        conta.setCliente(cliente);
        cliente.setConta(conta);
        
        JOptionPane.showMessageDialog (null, builder);
    }
    
    
    /* TELAS REFERENTES A OPERAÇÕES DE CONTA */
    public void depositar(Cliente cliente){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nInsira o valor que deseja depositar");
        BigDecimal valor = new BigDecimal(JOptionPane.showInputDialog(builder));
        
        daoConta.depositar(cliente, valor);
    }
    
    public void sacar(Cliente cliente){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nInsira o valor que sacar depositar");
        BigDecimal valor = new BigDecimal(JOptionPane.showInputDialog(builder));
        
        daoConta.sacar(cliente, valor);
    }
    
    public void pagar(Cliente cliente){
        
    }
    
    public void transferir(Cliente cliente){
        Cliente[] vetorComum = daoCliente.getVetorComum();
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nQuanto deseja transferir");
        BigDecimal valor = new BigDecimal(JOptionPane.showInputDialog(builder));
        builder.append("\nInsira o id da conta para qual deseja transferir");
        int idConta = Integer.parseInt(JOptionPane.showInputDialog(builder));
        builder.append(idConta);
        
        for(int i = 0; i < vetorComum.length; i++){
            if((vetorComum[i] != null) && (idConta == vetorComum[i].getConta().getId())){
                builder.append("\nOs dados conferem? [1- Sim, 2- Não]");
                builder.append(vetorComum[i].getConta());
                int confirmar = Integer.parseInt(JOptionPane.showInputDialog(builder));
                if(confirmar == 1){
                    daoConta.transferir(cliente, valor, vetorComum[i]);
                    JOptionPane.showMessageDialog (null, "Transferencia realizada com sucesso");
                }
            }
        }
        
    }
    
}
