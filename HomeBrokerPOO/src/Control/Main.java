/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
                    String[] loginSenha = new String[2];
                    loginSenha = GUI.criar();
                    break;
                }
                case 2:{
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
                                        //pagarDividendos
                                        break;
                                    }
                                    case 3:{
                                        GUI.cadastrarAtivos();
                                        break;
                                    }
                                    case 4:{
                                        break;
                                    }
                                    default: {
                                        JOptionPane.showMessageDialog (null, "Insira um valor válido");
                                        break;
                                    }
                                }
                            }while(opAdm != 4);
                            
                        }else{
                            int opComum;
                            if(cliente.getConta() == null){
                                do{
                                    opComum = GUI.contaLogada(cliente);
                                    switch(opComum){
                                        case 1:{
                                            GUI.cadastrarConta(cliente);
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
                                }while(opComum != 2 || cliente.getConta() != null);
                            }else{
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
                                            //Pagamento
                                            break;
                                        }
                                        case 4:{
                                            GUI.transferir(cliente);
                                            break;
                                        }
                                        case 5:{
                                            GUI.comprarAtivos(cliente);
                                            break;
                                        }
                                        case 6:{
                                            //Vender ativos
                                            break;
                                        }
                                        case 7:{
                                            //Extrato
                                            break;
                                        }
                                        case 8:{
                                            break;
                                        }
                                        default: {
                                            JOptionPane.showMessageDialog (null, "Insira um valor válido");
                                            break;
                                        }
                                    }
                                }while(opComum != 8);
                            }
                            
                            
                        }
                    }else{
                        JOptionPane.showMessageDialog (null, "Credenciais erradas");
                    }
                    break;
                }
                case 3:{
                    JOptionPane.showMessageDialog (null, "Um home broker bem broker e ainda mais home");
                    break;
                }
                case 4:{
                    break;
                }
                default:{
                    JOptionPane.showMessageDialog (null, "Insira um valor válido");
                    break;
                }
            }
        }while(op != 4);
    }
    
}
