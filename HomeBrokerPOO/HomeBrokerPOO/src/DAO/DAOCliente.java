package DAO;
import Connection.ConnectionFactory;
import Entities.Cliente;
import Entities.Conta;
import Entities.Enum.Usuario;
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
public class DAOCliente {
    
    private DAOConta daoConta = new DAOConta();
    private Connection connection = null;
    
    
    public DAOCliente(){
        this.connection = new ConnectionFactory().getConnection();  
    }

    
    public Cliente validarLogin(String[] loginSenha){
        List<Cliente> clientes = getClientes();
        
        for(Cliente cliente : clientes){
            if(cliente.getLogin().equals(loginSenha[0])){
                if(cliente.getSenha().equals(loginSenha[1])){
                    return cliente;
                }
            }
        }
        return null;
    }

    public List<Cliente> getClientes(){
        List<Cliente> clientes = new ArrayList<>();
        String sql = "select * from cliente";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){

            ResultSet resultQuery;
            resultQuery = stmt.executeQuery();

            while (resultQuery.next()) {
                int id = resultQuery.getInt("id_cliente");
                String login = resultQuery.getString("login");
                String senha = resultQuery.getString("senha");
                String nome = resultQuery.getString("nome");
                String cpf= resultQuery.getString("cpf");
                String endereco = resultQuery.getString("endereco");
                String telefone = resultQuery.getString("telefone");
                int conta = resultQuery.getInt("id_conta");
                String tipoUsuario = resultQuery.getString("tipo");
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime dataCriacao = LocalDateTime.parse(resultQuery.getTimestamp("data_criacao").toString(), formatter);
                LocalDateTime dataAlteracao = LocalDateTime.parse(resultQuery.getTimestamp("data_alteracao").toString(), formatter);

                Cliente clienteResult = new Cliente();
                clienteResult.setId(id);
                clienteResult.setLogin(login);
                clienteResult.setSenha(senha);
                clienteResult.setNome(nome);
                clienteResult.setCpf(cpf);
                clienteResult.setEndereco(endereco);
                clienteResult.setTelefone(telefone);
                clienteResult.setTipoUsuario(Usuario.valueOf(tipoUsuario));
                clienteResult.setDataCriacao(dataCriacao);
                clienteResult.setDataModificacao(dataAlteracao);
                if(conta != 0){
                    clienteResult.setConta(daoConta.retornarConta(clienteResult));
                }
                clientes.add(clienteResult);
            }
            resultQuery.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }
        
    
    public void criarCliente(String login, String senha, String nome, String cpf, 
            String endereco, String telefone, String tipoUsuario){
        String sql = "insert into cliente "
                + "(login, senha, nome, cpf, endereco, telefone, tipo, data_criacao, data_alteracao)" 
                + " values (?,?,?,?,?,?,?,?,?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setString(1, login);
            stmt.setString(2, senha);
            stmt.setString(3, nome);
            stmt.setString(4, cpf);
            stmt.setString(5, endereco);
            stmt.setString(6, telefone);
            stmt.setString(7, tipoUsuario);
            stmt.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            stmt.execute();

            System.out.println("Cliente inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void alterarCliente(String login, String senha, String nome, String cpf, 
            String endereco, String telefone, int id){
        String sql = "update cliente set "
                + "login = ?, senha = ?, nome = ?, cpf = ?, endereco = ?, telefone = ?" 
                + "where id_cliente = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            
            stmt.setString(1, login);
            stmt.setString(2, senha);
            stmt.setString(3, nome);
            stmt.setString(4, cpf);
            stmt.setString(5, endereco);
            stmt.setString(6, telefone);
            stmt.setInt(7, id);
            stmt.execute();

            System.out.println("Cliente alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        dataAlteracao(id);
    }
    
    public void setContaCliente(Cliente cliente){

        Conta conta = daoConta.retornarConta(cliente);

        String sql = "update cliente set id_conta = ? where id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1, conta.getId());
            stmt.setInt(2, cliente.getId());
            stmt.execute();
            System.out.println("Conta atrelada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        dataAlteracao(cliente.getId());
    }
    
    public void dataAlteracao(int idCliente){
        String sql = "update cliente set "
                + "data_alteracao = ?" 
                + "where id_cliente = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(2, idCliente);
            stmt.execute();

            System.out.println("Data atualizada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void removerCliente(int id){
        
        daoConta.removerConta(id);
        
        String sql = "delete from cliente where id_cliente = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1, id);
            stmt.execute();

            System.out.println("Cliente excluído com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void fecharConta(Cliente cliente){
        daoConta.removerConta(cliente.getId());
        
        String sql = "update cliente set "
                + "id_conta = null " 
                
                + "where id_cliente = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setInt(1, cliente.getId());
            stmt.execute();

            System.out.println("Conta fechada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
