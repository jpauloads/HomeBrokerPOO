/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JOptionPane;

/**
 *
 * @author jp_te
 */
public class Interfaces {
    
    StringBuilder builder = new StringBuilder();
    
    public int home(){
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\n1 - Criar conta");
        builder.append("\n2 - Entrar");
        builder.append("\n3 - Sobre");
        builder.append("\n4 - Sair");
        int op = Integer.parseInt(JOptionPane.showInputDialog(builder));
        return op;
    }
    
    public String[] criar(){
        String[] loginSenha = new String[2];
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nCriar login: ");
        loginSenha[0] = JOptionPane.showInputDialog(builder);
        builder.append(loginSenha[0]);
        builder.append("\nCriar senha: ");
        loginSenha[1] = JOptionPane.showInputDialog(builder);
        
        return loginSenha;
    }
    
    public String[] entrar(){
        String[] loginSenha = new String[2];
        builder.delete(0, builder.length());
        builder.append("HOME BROKER JJ");
        builder.append("\nLogin: ");
        loginSenha[0] = JOptionPane.showInputDialog(builder);
        builder.append(loginSenha[0]);
        builder.append("\nSenha: ");
        loginSenha[1] = JOptionPane.showInputDialog(builder);
        
        return loginSenha;
    }
    
}
