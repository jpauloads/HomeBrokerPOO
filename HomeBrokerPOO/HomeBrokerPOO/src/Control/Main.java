package Control;

import javax.swing.*;
import Entities.Cliente;
import Entities.Enum.Usuario;
import View.Interfaces;

/**
 *
 * @author jp_te
 */
public class Main {
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int op;
        Cliente cliente;
        Interfaces GUI = new Interfaces();
        
        do{ 
            op = GUI.home();
            
            switch(op){
                case 1:{
                    cliente = GUI.entrar();
                    
                    if(cliente != null){
                        if(cliente.getTipoUsuario() == Usuario.ADM){
                            int opAdm;
                            do{
                                opAdm = GUI.contaLogada(cliente);
                                switch(opAdm){
                                    case 1:{
                                        GUI.criarClienteTela();
                                        break;
                                    }
                                    case 2:{
                                        GUI.alterarClienteTela();
                                        break;
                                    }
                                    case 3:{
                                        GUI.removerCliente();
                                        break;
                                    }
                                    case 4:{
                                        GUI.listaClientes();
                                        break;
                                    }
                                    case 5:{
                                        GUI.pagarDividendos(cliente);
                                        break;
                                    }
                                    case 6:{
                                        GUI.cadastrarAtivos();
                                        break;
                                    }
                                    case 7:{
                                        GUI.alterarAtivos();
                                        break;
                                    }
                                    case 8:{
                                        GUI.removerAtivos();
                                        break;
                                    }
                                    case 9:{
                                        GUI.listaTickers();
                                        break;
                                    }
                                    case 10:{
                                        GUI.removerOrdem();
                                        break;
                                    }
                                    case 11:{
                                        GUI.alterarData();
                                        break;
                                    }
                                    case 12:{
                                        break;
                                    }
                                    default: {
                                        JOptionPane.showMessageDialog (null, "Insira um valor válido");
                                        break;
                                    }
                                }
                            }while(opAdm != 12);
                            
                        }else{
                            int opComum;
                            //Cadastrar conta nova
                            if(cliente.getConta() == null){
                                do{
                                    opComum = GUI.contaLogada(cliente);
                                    switch(opComum){
                                        case 1:{
                                            
                                            GUI.cadastrarConta(cliente);
                                            opComum = 2;
                                            break;
                                        }
                                        case 2:{
                                            break;
                                        }
                                        default: {
                                            JOptionPane.showMessageDialog (null, "Insira um valor válido");
                                            break;
                                        }
                                    }
                                }while(opComum != 2);
                            }
                            
                            if(cliente.getConta() != null){
                                do{
                                    opComum = GUI.contaLogada(cliente);
                                    switch(opComum){
                                        case 1:{
                                            GUI.depositar(cliente);
                                            break;
                                        }
                                        case 2:{
                                            GUI.sacar(cliente);
                                            break;
                                        }
                                        case 3:{
                                            GUI.pagar(cliente);
                                            break;
                                        }
                                        case 4:{
                                            GUI.transferir(cliente);
                                            break;
                                        }
                                        case 5:{
                                            GUI.meusAtivos(cliente);
                                            break;
                                        }
                                        case 6:{
                                            GUI.bookOfertas(cliente);
                                            break;
                                        }
                                        case 7:{
                                            GUI.gerarExtrato(cliente);
                                            break;
                                        }
                                        case 8:{
                                            GUI.ultimaNegociacao(cliente);
                                            break;
                                        }
                                        case 9:{
                                            GUI.fecharConta(cliente);
                                            opComum = 10;
                                            break;
                                        }
                                        case 10:{
                                            break;
                                        }
                                        default: {
                                            JOptionPane.showMessageDialog (null, "Insira um valor válido");
                                            break;
                                        }
                                    }
                                }while(opComum != 10);
                            }
                        }

                    }else{
                        JOptionPane.showMessageDialog (null, "Credenciais erradas");
                    }
                    break;
                }
                case 2:{
                    JOptionPane.showMessageDialog (null, "Um home broker bem broker e ainda mais home");
                    break;
                }
                case 3:{
                    break;
                }
                default:{
                    JOptionPane.showMessageDialog (null, "Insira um valor válido");
                    break;
                }
            }
        }while(op != 3);
    }
}
