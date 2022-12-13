/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.ConnectionFactory;
import Entities.Cliente;
import Entities.Conta;
import Entities.Enum.Operacao;
import Entities.Enum.Usuario;
import Entities.Movimentacao;
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
 * @author JPGF
 */
public class DAOMovimentacao {
    
    private Connection connection = null;
    
    public DAOMovimentacao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public List<Movimentacao> buscarMovimentacoes(int idConta){
        List<Movimentacao> movimentacoes = new ArrayList<>();
        
        String sql = "select * from movimentacao where id_origem = ?"
                + " or id_destino = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1, idConta);
            stmt.setInt(2, idConta);
            ResultSet resultQuery;
            resultQuery = stmt.executeQuery();

            while (resultQuery.next()) {
                int id = resultQuery.getInt("id_movimentacao");
                BigDecimal valor = BigDecimal.valueOf(resultQuery.getDouble("valor"));
                int idOrigem = resultQuery.getInt("id_origem");
                int idDestino = resultQuery.getInt("id_destino");
                String tipoOperacao = resultQuery.getString("tipo_operacao");
                String descricao = resultQuery.getString("descricao");

                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime dataCriacao = LocalDateTime.parse(resultQuery.getTimestamp("data_criacao").toString(), formatter);
                LocalDateTime dataAlteracao = LocalDateTime.parse(resultQuery.getTimestamp("data_alteracao").toString(), formatter);

                Movimentacao movimentacaoResult = new Movimentacao();
                movimentacaoResult.setId(id);
                movimentacaoResult.setValor(valor);
                movimentacaoResult.setContaOrigem(idOrigem);
                movimentacaoResult.setContaDestino(idDestino);
                movimentacaoResult.setOperacao(Operacao.valueOf(tipoOperacao));
                movimentacaoResult.setDescricao(descricao);
                movimentacaoResult.setDataCriacao(dataCriacao);
                movimentacaoResult.setDataModificacao(dataAlteracao);
                
                movimentacoes.add(movimentacaoResult);
            }
            resultQuery.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return movimentacoes;
    }
    
    public void criarMovimentacao(BigDecimal valor, int idOrigem, int idDestino, 
            String tipoOperacao, String descricao){
        
        String sql = "insert into movimentacao "
                + "(valor, id_origem, id_destino, tipo_operacao, descricao, data_criacao, data_alteracao)" 
                + " values (?,?,?,?,?,?,?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setDouble(1, valor.doubleValue());
            stmt.setInt(2, idOrigem);
            stmt.setInt(3, idDestino);
            stmt.setString(4, tipoOperacao);
            stmt.setString(5, descricao);
            stmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            stmt.execute();

            System.out.println("Cliente inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
