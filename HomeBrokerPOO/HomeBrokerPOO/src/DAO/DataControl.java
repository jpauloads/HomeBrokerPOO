/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.time.LocalDateTime;

/**
 *
 * @author jp_te
 */
public class DataControl {
    
    private static LocalDateTime data = LocalDateTime.now();
    private static DAOConta daoConta = new DAOConta();

    public static LocalDateTime now() {
        return data;
    }

    public static void adicionaDias(int prox) {

        int dia = 0;

        while (dia < prox) {
            if (data.getDayOfMonth() == 15) {
                daoConta.custoManutencao();
            }
            data = data.plusDays(1);
            dia++;
        }

    }
    
}
