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
    
        public List<Ordem> listaOrdem(Cliente cliente){
        List<Ordem> ordens = new ArrayList<>();
        
        String sql = "select * from ordem";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet resultQuery = stmt.executeQuery();){           

            while (resultQuery.next()) {
                int id = resultQuery.getInt("id");
                int idConta = resultQuery.getInt("id_conta");
                String tipoOrdem = resultQuery.getString("tipo_ordem");
                String ticker = resultQuery.getString("id_ticker");
                int quantidade = resultQuery.getInt("quantidade");
                String estado = resultQuery.getString("estado");
                BigDecimal valor = BigDecimal.valueOf(resultQuery.getDouble("valor"));
                BigDecimal valorTotal = BigDecimal.valueOf(resultQuery.getDouble("valor_total"));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime dataCriacao = LocalDateTime.parse(resultQuery.getTimestamp("data_criacao").toString(), formatter);
                LocalDateTime dataAlteracao = LocalDateTime.parse(resultQuery.getTimestamp("data_alteracao").toString(), formatter);

                Ordem ordemResult = new Ordem();

                //acho q vai dar certo
                //Agora ta indo a conta pra arraylist tbm, fiz um método no daoconta que retorna a conta passando o id
                ordemResult.setId(id);
                ordemResult.setConta(daoConta.buscarContaPorId(idConta));
                ordemResult.setTipoOrdem(TipoOrdem.valueOf(tipoOrdem));
                ordemResult.setTicker(ticker);
                ordemResult.setQuantidade(quantidade);
                ordemResult.setEstado(Estado.valueOf(estado));
                ordemResult.setValor(valor);
                ordemResult.setValor(valorTotal);
                ordemResult.setDataCriacao(dataCriacao);
                ordemResult.setDataModificacao(dataAlteracao);
                
                ordens.add(ordemResult);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordens;
    }
        
     public void removerConta(int id) {
        String sql = "delete from ordem where id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, id);
            stmt.execute();
            //System.out.println("Ordem removida com sucesso.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
/*
    public boolean ordem0(Cliente cliente, Ativos ativo){
        for(int i = 0; i < vetorOrdem.length; i++){
            if(vetorOrdem[i] != null){
                if(vetorOrdem[i].getConta() == cliente.getConta() && vetorOrdem[i].getTicker() == ativo.getTicker()){
                        return false;
                }
            }
        }
        return true;
    }
    */
    /*public Ordem achaOrdem(){
        
    }*/
/*
    public void comprarAtivo(Cliente cliente, Ativos ativo, int numAtivo){
        Ordem ordemCompra = new Ordem();  
        
        OrdemExecucao novaOrdem = new OrdemExecucao();
        DAOOrdemExecucao daoOrdemExecucao = new DAOOrdemExecucao();
        Date agora = new Date();
        
        novaOrdem.setContaCompra(cliente.getConta());
        novaOrdem.setDataCriacao(agora);
        //int ativos = numAtivo;
        
        if(ordem0(cliente, ativo)){
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
        }
    }
    */
    public void venderAtivo(Cliente cliente, BigDecimal valorVenda, Ativos ativo, int numAtivo){
        
        OrdemExecucao novaOrdem = new OrdemExecucao();
        DAOOrdemExecucao daoOrdemExecucao = new DAOOrdemExecucao();
        Date agora = new Date();
        
        novaOrdem.setContaCompra(cliente.getConta());
        novaOrdem.setDataCriacao(agora);
        
        Ordem ordemCompra = new Ordem();
        ordemCompra.setQuantidade(numAtivo);
        ordemCompra.setTipoOrdem(TipoOrdem.VENDA);
        ordemCompra.setValor(ativo.getPrecoInicial());
        ordemCompra.setValorTotal(ativo.getPrecoInicial().multiply(BigDecimal.valueOf(numAtivo)));
        ordemCompra.setTicker(ativo.getTicker());
        ordemCompra.setConta(cliente.getConta());
        
        if(ativo.getTotalAtivos() >= numAtivo){

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
                //adicoinar estado parcial e não
                ordemCompra.setEstado(Estado.NAO);
            }
        }else{
            //adicionar estado parcial e não
            ordemCompra.setEstado(Estado.NAO);
        }
        novaOrdem.setOrdem(ordemCompra);
        daoOrdemExecucao.criar(novaOrdem);
    }
}
