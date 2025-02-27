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
import DAO.DAOOrdem;
import DAO.DAOOrdemExecucao;
import DAO.DataControl;
import Entities.Ativos;
import Entities.Conta;
import Entities.Enum.TipoOrdem;
import Entities.Movimentacao;
import Entities.Ordem;
import Entities.OrdemExecucao;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    private DAOOrdem daoOrdem = new DAOOrdem();
    private DAOMovimentacao daoMovimentacao = new DAOMovimentacao();
    
    public Interfaces(){
    }

    /* TELAS PADRÕES */
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
        
        Cliente cliente = daoCliente.validarLogin(loginSenha);
        
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
            builder.append("\n2- Alterar cliente");
            builder.append("\n3- Remover cliente");
            builder.append("\n4- Lista de clientes");
            builder.append("\n5- Pagar dividendos");
            builder.append("\n6- Cadastrar ticker");
            builder.append("\n7- Alterar ticker");
            builder.append("\n8- Remover ticker");
            builder.append("\n9- Lista de tickers");
            builder.append("\n10- Remover ordem");
            builder.append("\n11- Alterar data");
            builder.append("\n12- Sair");
        }else{
            if(atualizarConta(cliente).getId() == 0){
                builder.append("\n1- Cadastrar conta");
                builder.append("\n2- Sair");
            }else{
                builder.append(atualizarConta(cliente));
                builder.append("\n---------------------------");
                builder.append("\n1- Depósito");
                builder.append("\n2- Saque");
                builder.append("\n3- Pagamento");
                builder.append("\n4- Transferência");
                builder.append("\n5- Ver meus ativos");
                builder.append("\n6- Abrir book de ofertas");
                builder.append("\n7- Extrato");
                builder.append("\n8- Última negociação");
                builder.append("\n9- Fechar conta");
                builder.append("\n10- Sair");
            }
        }
        op = Integer.parseInt(JOptionPane.showInputDialog(builder));
        return op;
    }
    
    /* TELAS ADM */
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
        builder.append("\nCPF/CNPJ: ");
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
    
    public void alterarClienteTela(){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\n| Alteração de Cliente |");
        builder.append("\n---------------------------");
        builder.append("\nInsira o ID do cliente que deseja alterar");
        int id = Integer.parseInt(JOptionPane.showInputDialog(builder));
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\n| Alteração de Cliente |");
        builder.append("\n---------------------------");
        builder.append("\nAlterações do cliente de id ");
        builder.append(id);
        builder.append("\nLogin: ");
        String login = JOptionPane.showInputDialog(builder);
        builder.append(login);
        builder.append("\nSenha: ");
        String senha = JOptionPane.showInputDialog(builder);
        builder.append(senha);
        builder.append("\nNome: ");
        String nome = JOptionPane.showInputDialog(builder);
        builder.append(nome);
        builder.append("\nCPF/CNPJ: ");
        String cpf = JOptionPane.showInputDialog(builder);
        builder.append(cpf);
        builder.append("\nEndereço: ");
        String endereco = JOptionPane.showInputDialog(builder);
        builder.append(endereco);
        builder.append("\nTelefone: ");
        String telefone = JOptionPane.showInputDialog(builder);
        builder.append(telefone);
        
        daoCliente.alterarCliente(login, senha, nome, cpf, endereco, telefone, id);
    }
    
    public void removerCliente(){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\n| Exclusão de Cliente |");
        builder.append("\n---------------------------");
        builder.append("\nInsira o ID do cliente que remover");
        int id = Integer.parseInt(JOptionPane.showInputDialog(builder));
        
        daoCliente.removerCliente(id);
        
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nCliente removido com sucesso");
        JOptionPane.showMessageDialog (null, builder);
    }
    
    public void pagarDividendos(Cliente cliente){

        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\n| Pagamento de dividendos |");
        builder.append("\n---------------------------");
        builder.append("\nInsira o ticker do dividendo que deseja pagar: ");
        String ticker = JOptionPane.showInputDialog(builder);
        builder.append(ticker);
        builder.append("\nValor do dividendo: ");
        BigDecimal valorDividendo = new BigDecimal(JOptionPane.showInputDialog(builder));
        builder.append(valorDividendo);
        builder.append("\nDeseja executar agora o pagamento de dividendos?");
        int yesNo = JOptionPane.showConfirmDialog (null,"\nDeseja executar agora o pagamento de dividendos?", "HOME BROKER JJ", JOptionPane.YES_NO_OPTION);
        if(yesNo == 0){
            daoOrdem.pagarDividendos(ticker, valorDividendo);
        }
    }
    
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
        
        daoAtivos.criarAtivos(nomeEmpresa, ticker, totalAtivos, precoInicial);
    }
    
    public void alterarAtivos(){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\n| Alteração de Ativos |");
        builder.append("\n---------------------------");
        builder.append("\nInsira o ID do ativo que deseja alterar");
        int id = Integer.parseInt(JOptionPane.showInputDialog(builder));
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\n| Alteração de Ativos |");
        builder.append("\n---------------------------");
        builder.append("\nAlterações do ativo de id ");
        builder.append(id);
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
        
        daoAtivos.alterarAtivos(nomeEmpresa, ticker, totalAtivos, precoInicial, id);
    }
    
    public void removerAtivos(){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\n| Exclusão de Ativos |");
        builder.append("\n---------------------------");
        builder.append("\nInsira o ID do ativo que remover");
        int id = Integer.parseInt(JOptionPane.showInputDialog(builder));
        
        daoAtivos.removerAtivo(id);
        
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nAtivo removido com sucesso");
        JOptionPane.showMessageDialog (null, builder);
    }
    
    public void alterarData(){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\n| Alteração de data |");
        builder.append("Data atual: ");
        builder.append(DataControl.now());
        builder.append("\n---------------------------");
        builder.append("\nInsira quantos dias deseja passar: ");
        int id = Integer.parseInt(JOptionPane.showInputDialog(builder));
        DataControl.adicionaDias(id);
    }
    
    public void removerOrdem(){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\n| Remoção de ordem |");
        builder.append("\n---------------------------");
        builder.append("\nInsira o id da ordem que deseja remover: ");
        int id = Integer.parseInt(JOptionPane.showInputDialog(builder));
        daoOrdem.removerOrdem(id);
    }
    
    
    /* TELAS COMUM */
    public Conta atualizarConta(Cliente cliente){
        return daoConta.retornarConta(cliente); 
    }

    public void cadastrarConta(Cliente cliente){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nConta criada com sucesso");
        daoConta.criarConta(cliente);
        daoCliente.setContaCliente(cliente);
        
        JOptionPane.showMessageDialog (null, builder);
    }
    
    public void fecharConta(Cliente cliente){
        int yesNo = JOptionPane.showConfirmDialog (null,"\nTem certeza que deseja fechar sua conta?", "HOME BROKER JJ", JOptionPane.YES_NO_OPTION);

        if(yesNo == 0){
            daoCliente.fecharConta(cliente);
        }
    }
    
    /* TELAS REFERENTES A OPERAÇÕES DE CONTA */
    //deposito
    public void depositar(Cliente cliente){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nInsira o valor que deseja depositar: ");
        BigDecimal valor = new BigDecimal(JOptionPane.showInputDialog(builder));
        builder.append(valor);
        builder.append("\nTipo de operacao[CREDITO/DEBITO]: ");
        String tipoOperacao = JOptionPane.showInputDialog(builder);
        builder.append(tipoOperacao);
        builder.append("\nDescricao: ");
        String descricao = JOptionPane.showInputDialog(builder);
        
        daoConta.depositar(cliente.getConta().getId(), valor, tipoOperacao, descricao);
    }
    
    //saque
    public void sacar(Cliente cliente){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nInsira o valor que deseja sacar: ");
        BigDecimal valor = new BigDecimal(JOptionPane.showInputDialog(builder));
        builder.append(valor);
        String tipoOperacao = "DEBITO";
        builder.append("\nDescricao: ");
        String descricao = JOptionPane.showInputDialog(builder);

        daoConta.sacar(cliente.getConta().getId(), valor, tipoOperacao, descricao);
    }
    
    //pagamento
    public void pagar(Cliente cliente){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nInsira o valor que deseja pagar: ");
        BigDecimal valor = new BigDecimal(JOptionPane.showInputDialog(builder));
        builder.append(valor);
        builder.append("\nTipo de operacao[CREDITO/DEBITO]: ");
        String tipoOperacao = JOptionPane.showInputDialog(builder);
        builder.append(tipoOperacao);
        builder.append("\nDescricao: ");
        String descricao = JOptionPane.showInputDialog(builder);

        daoConta.pagar(cliente.getConta().getId(), valor, 1, tipoOperacao, descricao);
    }
    
    //transferencia
    public void transferir(Cliente cliente){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nInsira o valor que deseja transferir: ");
        BigDecimal valor = new BigDecimal(JOptionPane.showInputDialog(builder));
        builder.append(valor);
        builder.append("\nInsira o id da conta para qual deseja transferir");
        int idDestino = Integer.parseInt(JOptionPane.showInputDialog(builder));
        
        if(idDestino == cliente.getConta().getId()){
            JOptionPane.showMessageDialog (null, "Não é possível transferir para a própria conta");
            return;
        }
        builder.append(idDestino);
        
        builder.append("\nTipo de operacao[CREDITO/DEBITO]: ");
        String tipoOperacao = JOptionPane.showInputDialog(builder);
        builder.append(tipoOperacao);
        builder.append("\nDescricao: ");
        String descricao = JOptionPane.showInputDialog(builder);
        builder.append(descricao);
        builder.append("\nConfirmar transferência? [1- Sim, 2- Não]");
        int confirmar = Integer.parseInt(JOptionPane.showInputDialog(builder));
        
        if(confirmar == 1){
            daoConta.transferir(cliente.getConta().getId(), valor, idDestino,
                    tipoOperacao, descricao);
            JOptionPane.showMessageDialog (null, "Transferencia realizada com sucesso");
        }else{
            JOptionPane.showMessageDialog (null, "Transferencia cancelada");
        }
    }
    
    //mostrar extrato
    public void gerarExtrato(Cliente cliente){
        List<Movimentacao> movimentacoes = daoMovimentacao.buscarMovimentacoes(cliente.getConta().getId());
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\n| EXTRATO |");
        builder.append("\n---------------------------");
        for(Movimentacao movimentacao : movimentacoes){
            builder.append("\n"+ movimentacao.toString());
        }
        JOptionPane.showMessageDialog (null, builder);
    }
    
    public void ultimaNegociacao(Cliente cliente){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\n| Última negociação |");
        builder.append("\n---------------------------");
    }
    
    
    //FALTA ARRUMAR AS DATAS DE ATIVOS E DE ORDENS
    /* TELAS REFERENTES A ATIVOS */
    /* Telas de ativos */
    
    
    
    //FALTA ARRUMAR AS DATAS DE ATIVOS E DE ORDENS
    // Tela para ver os ativos comprados
    public void meusAtivos(Cliente cliente){
        List<Ordem> ordens = daoOrdem.listaOrdem();
        int op;
        Ordem ativoEscolhido = null;
        do{
            builder.delete(0, builder.length());
            builder.append("HOME BROKER JJ");
            builder.append("\n| Seus Ativos |");
            builder.append("\n---------------------------");
            for(Ordem ordem : ordens){
                if(ordem.getConta().getId() == cliente.getConta().getId()){
                    if(ordem.getTipoOrdem() == TipoOrdem.COMPRA || ordem.getTipoOrdem() == TipoOrdem.ORDEM0){
                        builder.append("\n" + ordem);
                        builder.append("\n");
                    }
                }
            }
            builder.append("\n1- Vender ativos");
            builder.append("\n2- Sair");
            op = Integer.parseInt(JOptionPane.showInputDialog(builder));
            switch(op){
                case 1:{
                    builder.append("\nInsira o ID do ativo que deseja vender: ");
                    int ativoId = Integer.parseInt(JOptionPane.showInputDialog(builder));
                    builder.append(ativoId);
                    builder.append("\nQual o valor de venda: ");
                    BigDecimal novoValor = new BigDecimal(JOptionPane.showInputDialog(builder));
                    builder.append(novoValor);
                    builder.append("\nQuantos ativos deseja vender: ");
                    int numAtivo = Integer.parseInt(JOptionPane.showInputDialog(builder));
                    builder.append(numAtivo);
                    for(Ordem ativo: ordens){
                        if(ativo.getId() == ativoId){
                            ativoEscolhido = ativo;
                            break;
                        }
                    }
                    daoOrdem.venderAtivo(cliente, novoValor, ativoEscolhido, numAtivo);
                    break;
                }
                case 2:{
                    break;
                }
                default:{
                    JOptionPane.showMessageDialog (null, "Insira um valor válido");
                }
            }
        }while(op != 2);
    }

    // Book de ofertas
    public void bookOfertas(Cliente cliente){
        List<Ordem> ativos = daoOrdem.listaOrdem();
        Ordem ativoEscolhido = null;
        int op;
        
        do{
            builder.delete(0, builder.length());
            builder.append("HOME BROKER JJ");
            builder.append("\n| BOOK DE OFERTAS |");
            builder.append("\n---------------------------");
            for(Ordem ativo: ativos){
                if(ativo.getTipoOrdem() == TipoOrdem.VENDA &&
                        ativo.getQuantidade() > 0){
                    builder.append("\n" + ativo);
                    builder.append("\n");
                }
            }
            builder.append("\n1- Comprar ativos");
            builder.append("\n2- Comprar ativos(Ordem 0)");
            builder.append("\n3- Sair");
            op = Integer.parseInt(JOptionPane.showInputDialog(builder));
            switch(op){
                case 1:{
                    builder.append("\nInsira o ID do ativo que deseja comprar: ");
                    int ativoId = Integer.parseInt(JOptionPane.showInputDialog(builder));
                    for(Ordem ativo: ativos){
                        if(ativo.getId() == ativoId){
                            ativoEscolhido = ativo;
                            break;
                        }
                    }
                    builder.append(ativoId);
                    builder.append("\nQuantos ativos deseja comprar: ");
                    int numAtivo = Integer.parseInt(JOptionPane.showInputDialog(builder));
                    builder.append(numAtivo);

                    daoOrdem.comprarAtivo(cliente, ativoEscolhido, numAtivo);
                    break;
                }
                case 2:{
                    builder.append("\nInsira o ID do ativo que deseja comprar: ");
                    int ativoId = Integer.parseInt(JOptionPane.showInputDialog(builder));
                    for(Ordem ativo: ativos){
                        if(ativo.getId() == ativoId){
                            ativoEscolhido = ativo;
                            break;
                        }
                    }
                    builder.append(ativoId);
                    builder.append("\nQuantos ativos deseja comprar (maximo 3): ");
                    int numAtivo = Integer.parseInt(JOptionPane.showInputDialog(builder));
                    builder.append(numAtivo);
                    //metodo de comprar ativo ordem 0
                    daoOrdem.comprarAtivoOrdem0(cliente, ativoEscolhido, numAtivo);
                    break;
                    
                }
                case 3:{
                    break;
                }
                default:{
                    JOptionPane.showMessageDialog (null, "Insira um valor válido");
                }
            }
        }while(op != 3);
    }
    
    public void listaClientes(){
        List<Cliente> clientes = daoCliente.getClientes();
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\n| LISTA DE CLIENTES |");
        builder.append("\n---------------------------");
        for(Cliente cliente : clientes){
            builder.append(cliente.toString());
        }
        JOptionPane.showMessageDialog (null, builder);
    }
    
    public void listaTickers(){
        List<Ativos> ativos = daoAtivos.getAtivos();
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\n| LISTA DE ATIVOS |");
        builder.append("\n---------------------------");
        for(Ativos ativo : ativos){
            builder.append("\n"+ativo.toString());
        }
        JOptionPane.showMessageDialog (null, builder);
    }
}
