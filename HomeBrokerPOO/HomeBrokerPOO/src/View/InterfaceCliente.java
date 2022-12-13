/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.DAOAtivos;
import DAO.DAOCliente;
import DAO.DAOConta;
import DAO.DAOMovimentacao;
import DAO.DAOOrdem;
import Entities.Cliente;
import javax.swing.JOptionPane;

/**
 *
 * @author jp_te
 */
public class InterfaceCliente {
    
    private int op;
    private StringBuilder builder = new StringBuilder();
    private DAOCliente daoCliente = new DAOCliente();
    private DAOConta daoConta = new DAOConta();
    private DAOAtivos daoAtivos = new DAOAtivos();
    private Cliente cliente = new Cliente();
    private DAOOrdem daoOrdem = new DAOOrdem();
    private DAOMovimentacao daoMovimentacao = new DAOMovimentacao();
    
    // ADM
    
}
