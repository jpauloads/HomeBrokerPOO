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
import DAO.DAOAtivos;
import DAO.DAOMovimentacao;
import Entities.Ativos;
import Entities.Conta;
import Entities.Enum.Operacao;
import Entities.Movimentacao;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author jp_te
 */
public class Interfaces {
    
    private int op;
    private StringBuilder builder = new StringBuilder();
    private DAOCliente daoCliente = new DAOCliente();
    private DAOConta daoConta = new DAOConta();
    private DAOAtivos daoAtivos = new DAOAtivos();
    private Cliente cliente = new Cliente();
    
    private DAOMovimentacao daoMovimentacao = new DAOMovimentacao();
    
    public Interfaces(){
    }
    //JOptionPane.yes_no_option
    /* TELAS REFERENTES AO CLIENTE */
    public int home(){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\n1 - Entrar");
        builder.append("\n2 - Sobre");
        builder.append("\n3 - Sair");
        op = Integer.parseInt(JOptionPane.showInputDialog(builder));
        return op;
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
            if(cliente.getTipoUsuario() == Usuario.ADM && cliente.getId() == 0){
                builder.append("\nSaldo da bolsa = " + cliente.getConta().getSaldo());
            }
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
                builder.append("\n5- Abrir book de ofertas");
                builder.append("\n6- Extrato");
                builder.append("\n7- Sair");

            }   
        }
        op = Integer.parseInt(JOptionPane.showInputDialog(builder));
        return op;
    }
    
    public void criarClienteTela(){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\n| Cadastro de Cliente |");
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
    //deposito
    public void depositar(Cliente cliente){
        Movimentacao nova = new Movimentacao();
        Date agora = new Date();
                
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nInsira o valor que deseja depositar");
        BigDecimal valor = new BigDecimal(JOptionPane.showInputDialog(builder));
        builder.append("\nDescricao: ");
        String descricao = JOptionPane.showInputDialog(builder);
        
        nova.setOperacao(Operacao.DEBITO);
        nova.setValor(valor);
        nova.setDataModificacao(agora);
        nova.setDescricao(descricao);
        
        daoConta.depositar(cliente, valor);
        daoMovimentacao.criar(nova);
    }
    
    //saque
    public void sacar(Cliente cliente){
        Movimentacao nova = new Movimentacao();
        Date agora = new Date();
        
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nInsira o valor que sacar depositar");
        BigDecimal valor = new BigDecimal(JOptionPane.showInputDialog(builder));
        builder.append("\nDescricao: ");
        String descricao = JOptionPane.showInputDialog(builder);
        
        nova.setOperacao(Operacao.DEBITO);
        nova.setValor(valor);
        nova.setDataModificacao(agora);
        nova.setDescricao(descricao);
  
        daoConta.sacar(cliente, valor);
        daoMovimentacao.criar(nova);
    }
    
    //pagamento
    public void pagar(Cliente cliente){
        Cliente[] adm = daoCliente.getVetorAdm();
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nQuanto é o valor do pagamento");
        BigDecimal valor = new BigDecimal(JOptionPane.showInputDialog(builder));
        
        //colocar tbm o daoMovimentacao
        daoConta.pagar(cliente, valor, adm[0]);
    }
    
    //transferencia
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
                if(idConta == cliente.getConta().getId()){
                    JOptionPane.showMessageDialog (null, "Não é possível transferir para a própria conta");
                }else{
                    builder.append("\nOs dados conferem? [1- Sim, 2- Não]");
                    builder.append(vetorComum[i].getConta());
                    int confirmar = Integer.parseInt(JOptionPane.showInputDialog(builder));
                    
                    if(confirmar == 1){
                        
                        Movimentacao nova = new Movimentacao();
                        Date agora = new Date();
                        builder.append("\nDescricao: ");
                        String descricao = JOptionPane.showInputDialog(builder);
                        
                        daoConta.transferir(cliente, valor, vetorComum[i]);
                        
                        nova.setOperacao(Operacao.DEBITO);
                        nova.setContaOrigem(cliente.getConta());
                        nova.setContaDestino(vetorComum[i].getConta());
                        nova.setDataModificacao(agora);
                        nova.setDescricao(descricao);
                        nova.setValor(valor);
                        
                        daoMovimentacao.criar(nova);
                        JOptionPane.showMessageDialog (null, "Transferencia realizada com sucesso");
                    }
                }
            }
        }
    }
    
    public void pagarDividendos(Cliente cliente){
        Cliente[] vetorComum = daoCliente.getVetorComum();
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nDeseja executar agora o pagamento de dividendos?");
        int yesNo = JOptionPane.showConfirmDialog (null,"\nDeseja executar agora o pagamento de dividendos?", "HOME BROKER JJ", JOptionPane.YES_NO_OPTION);
        
        if(yesNo == 0){
            daoConta.pagarDividendos(cliente);
        }
        
    }
    
    //FALTA TERMINAR
    public void gerarExtrato(Cliente cliente){
        //para gerar o extrato precisa-se estar logado na conta cliente
                
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\n| Extrato |");
        builder.append("\n---------------------------");
        //extrato da conta que está logada
        builder.append(daoMovimentacao.ler(cliente.getConta()));
        
        
        
    }
    
    /* Telas de ativos */
    public void cadastrarAtivos(){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\n| Cadastro de Ativos |");
        builder.append("\n---------------------------");
        builder.append("\nNome da empresa: ");
        String nomeEmpresa = JOptionPane.showInputDialog(builder);
        builder.append(nomeEmpresa);
        builder.append("\nTicker: ");
        String ticker = JOptionPane.showInputDialog(builder);
        builder.append(ticker);
        builder.append("\nTotal de ativos: ");
        int totalAtivos = Integer.parseInt(JOptionPane.showInputDialog(builder));
        builder.append(totalAtivos);
        builder.append("\nPreço inicial: ");
        BigDecimal precoInicial = new BigDecimal(JOptionPane.showInputDialog(builder));
        builder.append(precoInicial);
        
        daoAtivos.criarAtivos(nomeEmpresa, ticker, precoInicial, totalAtivos);
    }
    
    
    
    /*STANDBY*/
    public void comprarAtivos(Cliente cliente){
        Ativos[] vetorAtivos = daoAtivos.getAtivos();
        Ativos ativoEscolhido = null;
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\n| Compra de Ativos |");
        builder.append("\n---------------------------");
        for(int i = 0; i < vetorAtivos.length; i++){
            if(vetorAtivos[i] != null){
                builder.append("\n" + vetorAtivos[i]);
                builder.append("\n");
            }
        }
        builder.append("\nInsira o ID do ativo que deseja comprar: ");
        int ativoId = Integer.parseInt(JOptionPane.showInputDialog(builder));
        for(int i = 0; i < vetorAtivos.length; i++){
            if(vetorAtivos[i] != null && vetorAtivos[i].getId() == ativoId){
                ativoEscolhido = vetorAtivos[i];
                break;
            }
        }
        builder.append(ativoId);
        builder.append("\nQuantos ativos deseja comprar: ");
        String numAtivo = JOptionPane.showInputDialog(builder);
        builder.append(numAtivo);
        
        daoConta.comprarAtivos(cliente, ativoEscolhido, numAtivo);
    }
}
