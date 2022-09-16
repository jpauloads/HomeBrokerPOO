/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homebrokerpoo;

import java.util.Scanner;

/**
 *
 * @author jp_te
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op;
        do{
            System.out.println("HOME BROKER JJ");
            System.out.println("1 - Criar conta");
            System.out.println("2 - Entrar");
            System.out.println("3 - Sobre");
            System.out.println("4 - Sair");
            op = Integer.parseInt(sc.nextLine());
        }while(op != 4);
    }
    
}
