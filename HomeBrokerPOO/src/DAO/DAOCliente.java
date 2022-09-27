package DAO;
import Entities.Cliente;
import Entities.Conta;
import Entities.Enum.Usuario;

/**
 *
 * @author jp_te
 */
public class DAOCliente {
    
    private Cliente[] vetorAdm = new Cliente[5];
    private Cliente[] vetorComum = new Cliente[5];
    
    public DAOCliente(){
        //Criação do cliente adm padrão
        Cliente adm1 = new Cliente();
        adm1.setCpf("95550664003");
        adm1.setEndereco("Rua dos Bobos, 0");
        adm1.setLogin("adm1");
        adm1.setNome("João Paulo");
        adm1.setTelefone("34987641029");
        adm1.setSenha("123");
        adm1.setTipoUsuario(Usuario.ADM);
        
        Cliente adm2 = new Cliente();
        adm2.setCpf("72510999001");
        adm2.setEndereco("Rua dos Bobos, 10");
        adm2.setLogin("adm2");
        adm2.setNome("João Pedro");
        adm2.setTelefone("3488755301");
        adm2.setSenha("123");
        adm2.setTipoUsuario(Usuario.ADM);
        
        Cliente adm3 = new Cliente();
        adm3.setCpf("77328134054");
        adm3.setEndereco("Rua dos Bobos, 340");
        adm3.setLogin("adm3");
        adm3.setNome("Danete Danone");
        adm3.setTelefone("34976378219");
        adm3.setSenha("123");
        adm3.setTipoUsuario(Usuario.ADM);
        
        vetorAdm[0] = adm1;
        vetorAdm[1] = adm2;
        vetorAdm[2] = adm3;
        Conta conta = new Conta();
        Cliente comum1 = new Cliente();
        comum1.setCpf("24520690005");
        comum1.setEndereco("Se essa rua fosse minha, 450");
        comum1.setLogin("comum1");
        comum1.setNome("Jojo Todyson");
        comum1.setTelefone("84933821382");
        comum1.setSenha("321");
        comum1.setTipoUsuario(Usuario.COMUM);
        comum1.setConta(conta);
        conta.setCliente(comum1);
        
        vetorComum[0] = comum1;
    }
    
    public Cliente validarLogin(String[] loginSenha){
        
        for(int i = 0; i < vetorComum.length; i++){
            if(vetorComum[i] != null){
                System.out.println(vetorComum[i]);
            }
        }
        
        for(int i = 0; i < vetorAdm.length; i++){
            if(vetorAdm[i] != null && (loginSenha[0].equals(vetorAdm[i].getLogin()))){
                if(loginSenha[1].equals(vetorAdm[i].getSenha())){
                    return vetorAdm[i];
                }
            }
        }
        
        for(int i = 0; i < vetorComum.length; i++){
            if(vetorComum[i] != null && (loginSenha[0].equals(vetorComum[i].getLogin()))){
                if(loginSenha[1].equals(vetorComum[i].getSenha())){
                    return vetorComum[i];
                }
            }
        }
        
        return null;
    }
    
    public void criarCliente(String login, String senha, String nome, String cpf, String endereco, String telefone, String tipoUsuario){
        Cliente cliente = new Cliente();
        
        cliente.setCpf(cpf);
        cliente.setEndereco(endereco);
        cliente.setLogin(login);
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setSenha(senha);
        cliente.setTipoUsuario(Usuario.valueOf(tipoUsuario));
        
        for(int i = 0; i < vetorComum.length; i++){
            if(vetorComum[i] == null){
                vetorComum[i] = cliente;
                break;
            }
        }
    }
}
