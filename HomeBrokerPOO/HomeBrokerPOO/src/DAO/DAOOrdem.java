/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.ConnectionFactory;
import Entities.Ativos;
import Entities.Cliente;
import Entities.Conta;
import Entities.Ordem;
import Entities.Enum.Estado;
import Entities.Enum.Operacao;
import Entities.Enum.TipoOrdem;
import Entities.Movimentacao;
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
import java.util.Date;
import java.util.List;

/**
 *
 * @author jp_te
 */
public class DAOOrdem {
    
    private Connection connection = null;
    private DAOConta daoConta = new DAOConta();
    
    public DAOOrdem(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void criarOrdem(int idConta, String tipoOrdem, String ticker, int quantidade, String estado, 
            BigDecimal valor, BigDecimal valorTotal){
        String sql = "insert into ordem "
                + "(id_conta, tipo_ordem, ticker, quantidade, estado, valor, valor_total, data_criacao, data_alteracao)" 
                + " values (?,?,?,?,?,?,?,?,?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1, idConta);
            stmt.setString(2, tipoOrdem);
            stmt.setString(3, ticker);
            stmt.setInt(4, quantidade);
            stmt.setString(5, estado);
            stmt.setDouble(6, valor.doubleValue());
            stmt.setDouble(7, valorTotal.doubleValue());
            stmt.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            stmt.execute();

            System.out.println("Ordem inserida com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Ordem buscaOrdemPorId(int id) {
        String sql = "select * from ordem where id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int idOrdem = rs.getInt("id");
                int idConta = rs.getInt("id_conta");
                String tipoOrdem = rs.getString("tipo_ordem");
                String ticker = rs.getString("ticker");
                int quantidade = rs.getInt("quantidade");
                String estado = rs.getString("estado");
                BigDecimal valor = BigDecimal.valueOf(rs.getDouble("valor"));
                BigDecimal valorTotal = BigDecimal.valueOf(rs.getDouble("valor_total"));
                
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime dataCriacao = LocalDateTime.parse(rs.getTimestamp("data_criacao").toString(), formatter);
                LocalDateTime dataAlteracao = LocalDateTime.parse(rs.getTimestamp("data_alteracao").toString(), formatter);

                Ordem ordem = new Ordem();
                ordem.setId(idOrdem);
                ordem.setConta(daoConta.buscarContaPorId(idConta));
                ordem.setTipoOrdem(TipoOrdem.valueOf(tipoOrdem));
                ordem.setTicker(ticker);
                ordem.setQuantidade(quantidade);
                ordem.setEstado(Estado.valueOf(estado));
                ordem.setValor(valor);
                ordem.setValorTotal(valorTotal);
                ordem.setDataCriacao(dataCriacao);
                ordem.setDataModificacao(dataAlteracao);

                return ordem;
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    
    public List<Ordem> listaOrdem(){
        List<Ordem> ordens = new ArrayList<>();
        
        String sql = "select * from ordem";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet resultQuery = stmt.executeQuery();){           

            while (resultQuery.next()) {
                int id = resultQuery.getInt("id");
                int idConta = resultQuery.getInt("id_conta");
                String tipoOrdem = resultQuery.getString("tipo_ordem");
                String ticker = resultQuery.getString("ticker");
                int quantidade = resultQuery.getInt("quantidade");
                String estado = resultQuery.getString("estado");
                BigDecimal valor = BigDecimal.valueOf(resultQuery.getDouble("valor"));
                BigDecimal valorTotal = BigDecimal.valueOf(resultQuery.getDouble("valor_total"));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime dataCriacao = LocalDateTime.parse(resultQuery.getTimestamp("data_criacao").toString(), formatter);
                LocalDateTime dataAlteracao = LocalDateTime.parse(resultQuery.getTimestamp("data_alteracao").toString(), formatter);

                Ordem ordemResult = new Ordem();

                ordemResult.setId(id);
                ordemResult.setConta(daoConta.buscarContaPorId(idConta));
                ordemResult.setTipoOrdem(TipoOrdem.valueOf(tipoOrdem));
                ordemResult.setTicker(ticker);
                ordemResult.setQuantidade(quantidade);
                ordemResult.setEstado(Estado.valueOf(estado));
                ordemResult.setValor(valor);
                ordemResult.setValorTotal(valorTotal);
                ordemResult.setDataCriacao(dataCriacao);
                ordemResult.setDataModificacao(dataAlteracao);
                
                ordens.add(ordemResult);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordens;
    }
        
    public void removerOrdem(int id){
        String sql = "delete from ordem where id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, id);
            stmt.execute();
            
            System.out.println("Ordem removida com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }    
    
    public void comprarAtivoOrdem0(Cliente cliente, Ordem ativoEscolhido, int numAtivo){
        boolean operacao = daoConta.comprarAtivosOrdem0(cliente, ativoEscolhido, numAtivo);
        if(operacao){
            criarOrdem(cliente.getConta().getId(), "ORDEM0", ativoEscolhido.getTicker(),
                    numAtivo, "TOTAL", ativoEscolhido.getValor(), ativoEscolhido.getValor().multiply(BigDecimal.valueOf(numAtivo).multiply(BigDecimal.valueOf(0.9))));
                alteraOrdem(ativoEscolhido, ativoEscolhido.getQuantidade() - numAtivo);
        }else{
            criarOrdem(cliente.getConta().getId(), "ORDEM0", ativoEscolhido.getTicker(),
                numAtivo, "NAO", ativoEscolhido.getValor(), ativoEscolhido.getValor().multiply(BigDecimal.valueOf(numAtivo).multiply(BigDecimal.valueOf(0.9))));
            alteraOrdem(ativoEscolhido, ativoEscolhido.getQuantidade() - numAtivo);
        }
    }
    
    public void comprarAtivo(Cliente cliente, Ordem ativoEscolhido, int numAtivo){
        boolean operacao = daoConta.comprarAtivos(cliente, ativoEscolhido, numAtivo);
        if(operacao){
            //ja cria a ordem e ja compra o ativo
            if(numAtivo > ativoEscolhido.getQuantidade()){
                criarOrdem(cliente.getConta().getId(), "COMPRA", ativoEscolhido.getTicker(),
                    numAtivo, "PARCIAL", ativoEscolhido.getValor(), ativoEscolhido.getValorTotal());
                alteraOrdem(ativoEscolhido, ativoEscolhido.getQuantidade() - numAtivo);
            }else{
                criarOrdem(cliente.getConta().getId(), "COMPRA", ativoEscolhido.getTicker(),
                    numAtivo, "TOTAL", ativoEscolhido.getValor(), ativoEscolhido.getValor().multiply(BigDecimal.valueOf(numAtivo)));
                alteraOrdem(ativoEscolhido, ativoEscolhido.getQuantidade() - numAtivo);
            }
        }else{
            criarOrdem(cliente.getConta().getId(), "COMPRA", ativoEscolhido.getTicker(),
                numAtivo, "NAO", ativoEscolhido.getValor(), ativoEscolhido.getValor().multiply(BigDecimal.valueOf(numAtivo)));
            alteraOrdem(ativoEscolhido, ativoEscolhido.getQuantidade() - numAtivo);
        }
        
        
        
        /*if(ordem0(cliente, ativo)){
            if(numAtivo == 1){
                if(ativo.getTotalAtivos() >= numAtivo){
                    ordemCompra.setQuantidade(numAtivo);
                    ordemCompra.setTipoOrdem(TipoOrdem.ORDEM0);
                    ordemCompra.setValor(ativo.getPrecoInicial());
                    // Desconto de 10% da ordem 0 adicionado no multiply
                    ordemCompra.setValorTotal((ativo.getPrecoInicial().multiply(BigDecimal.valueOf(numAtivo))).multiply(BigDecimal.valueOf(0.1)));
                    ordemCompra.setTicker(ativo.getTicker());
                    ordemCompra.setConta(cliente.getConta());
                    
                    //novaOrdem.setContaVende();    no caso quem vende é a bolsa...
                    
                    // -1 - menor, 0 - igual, 1 - maior
                    if(cliente.getConta().getSaldo().compareTo(ordemCompra.getValorTotal()) == 1){

                        ordemCompra.setEstado(Estado.TOTAL);
                        cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(ordemCompra.getValorTotal()));
                        ativo.setTotalAtivos(ativo.getTotalAtivos() - numAtivo);      

                    }else if(cliente.getConta().getSaldo().compareTo(ordemCompra.getValorTotal()) == 0){

                        ordemCompra.setEstado(Estado.TOTAL);
                        cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(ordemCompra.getValorTotal()));
                        ativo.setTotalAtivos(ativo.getTotalAtivos() - numAtivo);

                    }else if(cliente.getConta().getSaldo().compareTo(ordemCompra.getValorTotal()) == -1){
                        ordemCompra.setEstado(Estado.NAO);
                    }
                    
                    novaOrdem.setOrdem(ordemCompra);
                    daoOrdemExecucao.criar(novaOrdem);

                }
                return;
            }else{
                numAtivo = numAtivo - 1;
            }
        }
        
        if(!ordem0(cliente, ativo)){
            if(ativo.getTotalAtivos() >= numAtivo){
                
                ordemCompra.setQuantidade(numAtivo);
                ordemCompra.setTipoOrdem(TipoOrdem.COMPRA);
                ordemCompra.setValor(ativo.getPrecoInicial());
                ordemCompra.setValorTotal(ativo.getPrecoInicial().multiply(BigDecimal.valueOf(numAtivo)));
                ordemCompra.setTicker(ativo.getTicker());
                ordemCompra.setConta(cliente.getConta());

                // -1 - menor, 0 - igual, 1 - maior
                if(cliente.getConta().getSaldo().compareTo(ordemCompra.getValorTotal()) == 1){

                    ordemCompra.setEstado(Estado.TOTAL);
                    cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(ordemCompra.getValorTotal()));
                    ativo.setTotalAtivos(ativo.getTotalAtivos() - numAtivo);

                }else if(cliente.getConta().getSaldo().compareTo(ordemCompra.getValorTotal()) == 0){

                    ordemCompra.setEstado(Estado.TOTAL);
                    cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(ordemCompra.getValorTotal()));
                    ativo.setTotalAtivos(ativo.getTotalAtivos() - numAtivo);

                }else if(cliente.getConta().getSaldo().compareTo(ordemCompra.getValorTotal()) == -1){
                    ordemCompra.setEstado(Estado.NAO);
                }
                
            } else if(ativo.getTotalAtivos() < numAtivo){
                
                ordemCompra.setEstado(Estado.PARCIAL);
                cliente.getConta().setSaldo(cliente.getConta().getSaldo().subtract(ordemCompra.getValorTotal()));
                ativo.setTotalAtivos(ativo.getTotalAtivos() - numAtivo);
                int qtdeAtivosEspera = ativo.getTotalAtivos();
                ordemCompra.setQuantidade(qtdeAtivosEspera);
            }
            novaOrdem.setOrdem(ordemCompra);
            daoOrdemExecucao.criar(novaOrdem);
        }*/
    }

    public void venderAtivo(Cliente cliente, BigDecimal valorVenda, Ordem ativo, int numAtivo){
        criarOrdem(cliente.getConta().getId(), "VENDA", ativo.getTicker(), numAtivo, 
                "TOTAL", valorVenda, valorVenda.multiply(BigDecimal.valueOf(numAtivo)));
        alteraOrdem(ativo, ativo.getQuantidade() - numAtivo);
    }
    
    public void pagarDividendos(String ticker, BigDecimal valorDividendo){
        List<Ordem> ordens = listaOrdem();
        
        for(Ordem ordem : ordens){
            if(ordem.getTicker().equals(ticker)){
                int quant = ordem.getQuantidade();
                Conta conta = ordem.getConta();
                if(conta.getId() != 1){
                    boolean op = daoConta.removerValor(1, valorDividendo.multiply(BigDecimal.valueOf(quant)));
                    if(op)
                        daoConta.adicionarValor(conta.getId(), valorDividendo.multiply(BigDecimal.valueOf(quant)));
                }
            }
        }
    }
    
    public void alteraOrdem(Ordem ordem, int restante){
        String sql = "update ordem set quantidade = ?, data_alteracao = ? where id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1, restante);
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(3, ordem.getId());
            stmt.execute();

            System.out.println("Ordem alterada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
