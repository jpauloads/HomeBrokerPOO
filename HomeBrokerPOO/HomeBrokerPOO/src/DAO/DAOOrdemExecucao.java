/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.ConnectionFactory;
import Entities.Cliente;
import Entities.Enum.Estado;
import Entities.Enum.TipoOrdem;
import Entities.Ordem;
import Entities.OrdemExecucao;
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
public class DAOOrdemExecucao {

    private Connection connection = null;
    private DAOConta daoConta = new DAOConta();
    private DAOOrdem daoOrdem = new DAOOrdem();

    public DAOOrdemExecucao() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void criarOrdemExecucao(int quantidade, int idContaCompra, int idContaVende, int idOrdem) {
        String sql = "insert into ordem_execucao"
                + "(quantidade, id_conta_compra, id_compra_vende, id_ordem, data_criacao, data_alteracao)"
                + " values (?,?,?,?,?,?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, quantidade);
            stmt.setInt(2, idContaCompra);
            stmt.setInt(3, idContaVende);
            stmt.setInt(4, idOrdem);
            stmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            stmt.execute();

            System.out.println("Ordem_execucao inserida com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OrdemExecucao> listaOrdemExecucao() {
        List<OrdemExecucao> ordens = new ArrayList<>();

        String sql = "select * from ordem_execucao";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet resultQuery = stmt.executeQuery();) {

            while (resultQuery.next()) {
                int id = resultQuery.getInt("id");
                int quantidade = resultQuery.getInt("quantidade");
                int idContaCompra = resultQuery.getInt("id_conta_compra");
                int idContaVende = resultQuery.getInt("id_conta_vende");
                int idOrdem = resultQuery.getInt("id_ordem");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime dataCriacao = LocalDateTime.parse(resultQuery.getTimestamp("data_criacao").toString(), formatter);
                LocalDateTime dataAlteracao = LocalDateTime.parse(resultQuery.getTimestamp("data_alteracao").toString(), formatter);

                OrdemExecucao ordemResult = new OrdemExecucao();

                ordemResult.setId(id);
                ordemResult.setQuantidade(quantidade);
                ordemResult.setContaCompra(daoConta.buscarContaPorId(idContaCompra));
                ordemResult.setContaVende(daoConta.buscarContaPorId(idContaVende));
                ordemResult.setOrdem(daoOrdem.buscaOrdemPorId(idOrdem));
                ordemResult.setDataCriacao(dataCriacao);
                ordemResult.setDataModificacao(dataAlteracao);

                ordens.add(ordemResult);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordens;
    }

    public void removerOrdem(int id) {
        String sql = "delete from ordem_execucao where id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, id);
            stmt.execute();

            System.out.println("Ordem de execucao removida com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
