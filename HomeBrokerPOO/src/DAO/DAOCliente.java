/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Entities.Cliente;

/**
 *
 * @author jp_te
 */
public class DAOCliente {
    
    
    public boolean validarLogin(String[] loginSenha){
        
        Cliente[] contas = new Cliente[5];
        Cliente c1 = new Cliente();
        c1.setLogin("teste");
        c1.setSenha("123");
        contas[0] = c1;
        
        return loginSenha[0].equals(contas[0].getLogin()) && loginSenha[1].equals(contas[0].getSenha());
    }
}
