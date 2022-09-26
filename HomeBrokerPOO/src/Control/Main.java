/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.Scanner;
import javax.swing.*;
import DAO.DAOCliente;
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
        DAOCliente dCliente;
        
        do{ 
            op = GUI.home();
            
            switch(op){
                case 1:{
                    String[] loginSenha = new String[2];
                    loginSenha = GUI.criar();
                    break;
                }
                case 2:{
                    String[] loginSenha = new String[2];
                    cliente = GUI.entrar();
                    
                    if(cliente != null){
                        if(cliente.getTipoUsuario() == Usuario.ADM){
                            int opAdm = GUI.contaLogada(cliente);
                            
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
                                    //cadastrarTickets;
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
                        }else{
                            int opComum = GUI.contaLogada(cliente);
                        }
                        //posso deixar já pre definido as opções
                        // a parte de intarface é só pra ter noção
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
