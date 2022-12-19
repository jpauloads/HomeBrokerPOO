/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.ConnectionFactory;
import Entities.Enum.Estado;
import Entities.Enum.TipoOrdem;
import Entities.Ordem;
import Entities.UltimaNegociacao;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jp_te
 */
public class DAOUltimaNegociacao {
    
    private Connection connection = null;
    private DAOConta daoConta = new DAOConta();
    
    public DAOUltimaNegociacao(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void criarUltimaNegociacao(int quantidade, BigDecimal valor, BigDecimal valorTotal,
            String ticker, int idContaCompra, int idContaVenda){
        String sql = "insert into ultima_negociacao"
                + "(quantidade, valor, valor_total, ticker, id_conta_compra, id_conta_venda, data_criacao, data_alteracao)"
                + "values(?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1, quantidade);
            stmt.setDouble(2, valor.doubleValue());
            stmt.setDouble(3, valorTotal.doubleValue());
            stmt.setString(4, ticker);
            stmt.setInt(5, idContaCompra);
            stmt.setInt(6, idContaVenda);
            stmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
            stmt.execute();

            System.out.println("Última ordem inserida com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public UltimaNegociacao buscaUltimaNegociacao(int idConta){
        String sql = "select * from ultima_negociacao where id_conta_compra = ?"
                + "or id_conta_venda = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet resultQuery = stmt.executeQuery();){           

            while (resultQuery.next()) {
                int id = resultQuery.getInt("id");
                int quantidade = resultQuery.getInt("quantidade");
                BigDecimal valor = BigDecimal.valueOf(resultQuery.getDouble("valor"));
                BigDecimal valorTotal = BigDecimal.valueOf(resultQuery.getDouble("valor_total"));
                String ticker = resultQuery.getString("ticker");
                int idContaCompra = resultQuery.getInt("id_conta_compra");
                int idContaVenda = resultQuery.getInt("id_conta_venda");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime dataCriacao = LocalDateTime.parse(resultQuery.getTimestamp("data_criacao").toString(), formatter);
                LocalDateTime dataAlteracao = LocalDateTime.parse(resultQuery.getTimestamp("data_alteracao").toString(), formatter);

                UltimaNegociacao ultNegociacao = new UltimaNegociacao();

                ultNegociacao.setId(id);
                ultNegociacao.setQuantidade(quantidade);
                ultNegociacao.setValor(valor);
                ultNegociacao.setValorTotal(valorTotal);
                ultNegociacao.setTicker(ticker);
                ultNegociacao.setContaCompra(daoConta.buscarContaPorId(idContaCompra));
                ultNegociacao.setContaVenda(daoConta.buscarContaPorId(idContaVenda));
                ultNegociacao.setDataCriacao(dataCriacao);
                ultNegociacao.setDataModificacao(dataAlteracao);
                
                return ultNegociacao;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return null;
    }
    
    public void alterarUltimaOrdem(int idOrdem, BigDecimal valor){
        String sql = "update ultima_negociacao set valor = ? where id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setDouble(1, valor.doubleValue());
            stmt.setInt(2, idOrdem);
            stmt.execute();

            System.out.println("Ultima ordem atualizada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void excluiUltimaOrdem(int idOrdem){
        String sql = "delete from ultima_negociacao where id= ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1, idOrdem);
            stmt.execute();

            System.out.println("Ultima ordem excluída com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
