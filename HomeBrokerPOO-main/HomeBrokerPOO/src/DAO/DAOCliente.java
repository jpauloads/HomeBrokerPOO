package DAO;
import Entities.Cliente;
import Entities.Conta;
import Entities.Enum.Usuario;
import java.math.BigDecimal;

/**
 *
 * @author jp_te
 */
public class DAOCliente {
    
    private DAOConta daoConta = new DAOConta();
    private Cliente[] vetorAdm = new Cliente[5];
    private Cliente[] vetorComum = new Cliente[5];
    private Cliente[] vetorBolsa = new Cliente[5];
    public Cliente bolsa;
    
    public DAOCliente(){
        // Bolsa
        bolsa = new Cliente();
        bolsa.setCpf("312312000134");
        bolsa.setEndereco("Home broker J5J");
        bolsa.setLogin("bolsa");
        bolsa.setNome("IBOVESPA");
        bolsa.setTelefone("31231312");
        bolsa.setSenha("135");
        bolsa.setTipoUsuario(Usuario.BOLSA);
        Conta contaBolsa = new Conta(bolsa);
        bolsa.setConta(contaBolsa);
        
        //Criação do cliente adm
        Cliente adm1 = new Cliente();
        adm1.setCpf("95550664003");
        adm1.setEndereco("Rua dos Bobos, 0");
        adm1.setLogin("adm1");
        adm1.setNome("João Paulo");
        adm1.setTelefone("34987641029");
        adm1.setSenha("123");
        adm1.setTipoUsuario(Usuario.ADM);
        daoConta.criarConta(adm1, bolsa);
        
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
        
        //Criação do cliente comum
        Cliente comum1 = new Cliente();
        comum1.setCpf("24520690005");
        comum1.setEndereco("Se essa rua fosse minha, 450");
        comum1.setLogin("comum1");
        comum1.setNome("Jojo Todyson");
        comum1.setTelefone("84933821382");
        comum1.setSenha("321");
        comum1.setTipoUsuario(Usuario.COMUM);
        daoConta.criarConta(comum1, bolsa);

        Cliente comum2 = new Cliente();
        comum2.setCpf("24520690005");
        comum2.setEndereco("Se essa rua fosse minha, 450");
        comum2.setLogin("comum2");
        comum2.setNome("MC Carol");
        comum2.setTelefone("84933821382");
        comum2.setSenha("321");
        comum2.setTipoUsuario(Usuario.COMUM);
        daoConta.criarConta(comum2, bolsa);

        Cliente comum3 = new Cliente();
        comum3.setCpf("4123");
        comum3.setEndereco("Se essa rua fsosse minha, 450");
        comum3.setLogin("comum3");
        comum3.setNome("Roberto Carlos");
        comum3.setTelefone("84933821382");
        comum3.setSenha("321");
        comum3.setTipoUsuario(Usuario.COMUM);
        daoConta.criarConta(comum3, bolsa);
        
        vetorComum[0] = comum1;
        vetorComum[1] = comum2;
        vetorComum[2] = comum3;
  
    }
    
    public Cliente getBolsa(){
        return bolsa;
    }

    public Cliente[] getVetorAdm() {
        return vetorAdm;
    }

    public Cliente[] getVetorComum() {
        return vetorComum;
    }
    
    public Cliente validarLogin(String[] loginSenha){
        System.out.println(bolsa);
        for(int i = 0; i < vetorAdm.length; i++){
            if(vetorAdm[i] != null){
                System.out.println(vetorAdm[i]);
            }
        }
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
        
        if(loginSenha[0].equals(bolsa.getLogin())){
            if(loginSenha[1].equals(bolsa.getSenha())){
                return bolsa;
            }
        }

        return null;
    }
    
    public int novaPosicao(Cliente[] vetor){
        for(int i = 0; i < vetorComum.length; i++){
            if(vetorComum[i] == null){
                return i;
            }
        }
        return -1;
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
        
        if(tipoUsuario.equals("COMUM")){
            vetorComum[novaPosicao(vetorComum)] = cliente;
        }else if (tipoUsuario.equals("ADM")){
            vetorAdm[novaPosicao(vetorAdm)] = cliente;
        }else{
            vetorBolsa[novaPosicao(vetorBolsa)] = cliente;
        }
    }
}
