/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.ConnectionFactory;
import Entities.Ativos;
import Entities.Cliente;
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
public class DAOAtivos {
    
    private DAOConta daoConta = new DAOConta();
    private DAOOrdem daoOrdem = new DAOOrdem();
    private Connection connection = null;
    
    public DAOAtivos(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public List<Ativos> getAtivos() {
        List<Ativos> ativos = new ArrayList<>();
        String sql = "select * from tickers";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet resultQuery = stmt.executeQuery()){

            while (resultQuery.next()) {
                int id = resultQuery.getInt("id_ticker");
                String nomeEmpresa = resultQuery.getString("nome_empresa");
                String ticker = resultQuery.getString("ticker");
                int totalAtivos = resultQuery.getInt("total_ativos");
                BigDecimal preco = BigDecimal.valueOf(resultQuery.getDouble("preco_inicial"));
  
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime dataCriacao = LocalDateTime.parse(resultQuery.getTimestamp("data_criacao").toString(), formatter);
                LocalDateTime dataAlteracao = LocalDateTime.parse(resultQuery.getTimestamp("data_alteracao").toString(), formatter);

                Ativos ativoResult = new Ativos();
                ativoResult.setId(id);
                ativoResult.setNomeEmpresa(nomeEmpresa);
                ativoResult.setTicker(ticker);
                ativoResult.setTotalAtivos(totalAtivos);
                ativoResult.setPrecoInicial(preco);
                ativoResult.setDataCriacao(dataCriacao);
                ativoResult.setDataModificacao(dataAlteracao);
                
                ativos.add(ativoResult);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ativos;
    }
    
    public void criarAtivos(String nomeEmpresa, String ticker, int totalAtivos, BigDecimal precoInicial){
        
        String sql = "insert into tickers "
                + "(nome_empresa, ticker, total_ativos, preco_inicial, data_criacao, data_alteracao)" 
                + " values (?,?,?,?,?,?)";

        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setString(1, nomeEmpresa);
            stmt.setString(2, ticker);
            stmt.setInt(3, totalAtivos);
            stmt.setDouble(4, precoInicial.doubleValue());
            stmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            stmt.execute();

            System.out.println("Ativo inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        daoOrdem.criarOrdem(1, "VENDA", ticker, totalAtivos, "TOTAL", 
                precoInicial, precoInicial.multiply(BigDecimal.valueOf(totalAtivos)));
    }
    
    public void alterarAtivos(String nomeEmpresa, String ticker, int totalAtivos, BigDecimal precoInicial, int id){
        String sql = "update tickers set "
                + "nome_empresa = ?, ticker = ?, total_ativos = ?, preco_inicial = ?, data_alteracao = ?"
                + "where id_ticker = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            
            stmt.setString(1, nomeEmpresa);
            stmt.setString(2, ticker);
            stmt.setInt(3, totalAtivos);
            stmt.setDouble(4, precoInicial.doubleValue());
            stmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(6, id);
            stmt.execute();

            System.out.println("Ativo alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerAtivo(int id){
        String sql = "delete from tickers where id_ticker = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1, id);
            stmt.execute();

            System.out.println("Ativo excluído com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
