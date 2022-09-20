/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.Scanner;
import javax.swing.*;
import DAO.DAOCliente;
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
        boolean val;
        Interfaces inter = new Interfaces();
        Scanner sc = new Scanner(System.in);
        DAOCliente dCliente = new DAOCliente();
        int op;
        do{ 
            op = inter.home();
            
            switch(op){
                case 1:{
                    String[] loginSenha = new String[2];
                    loginSenha = inter.criar();
                    break;
                }
                case 2:{
                    String[] loginSenha = new String[2];
                    loginSenha = inter.entrar();
                    val = dCliente.validarLogin(loginSenha);
                    
                    if(val){
                        JOptionPane.showMessageDialog (null, "Olá " + loginSenha[0]);
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
                    JOptionPane.showMessageDialog (null, "Insira um valo válido");
                    break;
                }
            }
        }while(op != 4);
    }
    
}
