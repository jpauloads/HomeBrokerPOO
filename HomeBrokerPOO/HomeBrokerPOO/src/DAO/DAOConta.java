package DAO;

import Connection.ConnectionFactory;
import Entities.Cliente;
import Entities.Conta;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jp_te
 */
public class DAOConta {
   
    private Connection connection = null;
    
    public DAOConta(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    //Q q faz? Pq esse codigo não retorna uma lista?
    public Conta retornarConta(Cliente cliente){
        Conta conta = new Conta(cliente);
        String sql = "select * from conta where id_cliente = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1, cliente.getId());
            ResultSet resultQuery;
            resultQuery = stmt.executeQuery();
            while (resultQuery.next()) {
                int id = resultQuery.getInt("id_conta");
                BigDecimal saldo = BigDecimal.valueOf(resultQuery.getDouble("saldo"));
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime dataCriacao = LocalDateTime.parse(resultQuery.getTimestamp("data_criacao").toString(), formatter);
                LocalDateTime dataAlteracao = LocalDateTime.parse(resultQuery.getTimestamp("data_alteracao").toString(), formatter);
                
                conta.setId(id);
                conta.setSaldo(saldo);
                conta.setDataCriacao(dataCriacao);
                conta.setDataModificacao(dataAlteracao);
            }
            resultQuery.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conta;
        
    }
    
    public void criarConta(Cliente cliente){

        String sql = "insert into conta "
                + "(id_cliente, saldo, data_criacao, data_alteracao)" 
                + " values (?,?,?,?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1, cliente.getId());
            stmt.setDouble(2, 20000.00);
            stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stmt.execute();
            System.out.println("Conta criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        adicionarBolsa();
    }
    
    public void removerConta(int id){
        String sql = "delete from conta where id_cliente = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1, id);
            stmt.execute();

            System.out.println("Conta excluída com sucesso.");
            
            removerValor(1, BigDecimal.valueOf(500000.00));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /* OPERAÇÕES DE CONTA */
    public void depositar(int idConta, BigDecimal valor){
        adicionarValor(idConta, valor);
    }
    
    public void sacar(int idConta, BigDecimal valor){
        removerValor(idConta, valor);
    }
    
    public void pagar(int idContaOrigem, BigDecimal valor, int idContaDestino){
        removerValor(idContaOrigem, valor);
        adicionarValor(idContaDestino, valor);
    }
    
    public void transferir(int idContaOrigem, BigDecimal valor, int idContaDestino){
        removerValor(idContaOrigem, valor);
        adicionarValor(idContaDestino, valor);
    }

    /*public void comprarAtivos(Cliente cliente, Ativos ativo, String numAtivos){
        
        BigDecimal valor = ativo.getPrecoInicial().multiply(new BigDecimal(numAtivos));
        
        
        
        cliente.getConta().setAtivos(ativo);
    }
    
    public void venderAtivos(Cliente cliente, BigDecimal novoValor, Ativos ativoEscolhido){
        for(int i = 0; i < cliente.getConta().getAtivos().length; i++){
            if(cliente.getConta().getAtivos()[i] != null){
                if(cliente.getConta().getAtivos()[i].getId() == ativoEscolhido.getId()){
                    cliente.getConta().getAtivos()[i] = null;
                    ativoEscolhido.setPrecoUltimaVenda(novoValor);
                    cliente.getConta().setSaldo(cliente.getConta().getSaldo().add(novoValor));
                }
            }
        }
    }
    
    public void pagarDividendos(Cliente[] cliente){
        for(Cliente temp : cliente){
            if(temp != null){
                if(temp.getConta().getAtivos() != null){
                    for(int i = 0; i < temp.getConta().getAtivos().length; i++){
                        if(temp.getConta().getAtivos()[i] != null){
                            temp.getConta().setSaldo(temp.getConta().getSaldo().add(temp.getConta().getAtivos()[i].getPrecoInicial()));
                        }
                    }
                }
            }
        }
    }  */
    
    public void adicionarValor(int idConta, BigDecimal valor){
        BigDecimal saldo = BigDecimal.valueOf(0.00);
        
        String sql = "select saldo from conta where id_conta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1, idConta);
            ResultSet resultQuery;
            resultQuery = stmt.executeQuery();
            while (resultQuery.next()) {
                saldo = BigDecimal.valueOf(resultQuery.getDouble("saldo"));
            }
            resultQuery.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        saldo = saldo.add(valor);
        
        String sqlAdicionarValor = "update conta set "
                + "saldo = ?" 
                + "where id_conta = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sqlAdicionarValor);){
            stmt.setDouble(1, saldo.doubleValue());
            stmt.setInt(2, idConta);
            stmt.execute();

            System.out.println("Valor adicionado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        dataAlteracao(idConta);
    }

    public void removerValor(int idConta, BigDecimal valor){
        BigDecimal saldo = BigDecimal.valueOf(0.00);
        
        String sql = "select saldo from conta where id_conta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1, idConta);
            ResultSet resultQuery;
            resultQuery = stmt.executeQuery();
            while (resultQuery.next()) {
                saldo = BigDecimal.valueOf(resultQuery.getDouble("saldo"));
            }
            resultQuery.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        saldo = saldo.subtract(valor);
        
        String sqlRmoverValor = "update conta set "
                + "saldo = ?" 
                + "where id_conta = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sqlRmoverValor);){
            stmt.setDouble(1, saldo.doubleValue());
            stmt.setInt(2, idConta);
            stmt.execute();

            System.out.println("Valor removido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        dataAlteracao(idConta);
    }
    
    public void adicionarBolsa(){
        adicionarValor(1, BigDecimal.valueOf(500000.00));
        dataAlteracao(1);
    }
    
    public void dataAlteracao(int idConta){
        String sql = "update conta set "
                + "data_alteracao = ?" 
                + "where id_conta = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(2, idConta);
            stmt.execute();

            System.out.println("Data atualizada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
