/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserView;
import AdminView.Product;
import java.util.Scanner;
/**
 *
 * @author Rits08
 */
public class UserProgram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int perintah=0;
        boolean lanjut=true;
        while (lanjut){

            while(perintah==0){
                System.out.println("choose what to do\n 1.View Products\n 2.view Cart\n3.exit\n");
                try {
                    perintah = sc.nextInt();
                    if (perintah>3||perintah<1) {
                        perintah=0;
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("please choose a valid option");
                }
            }
            switch (perintah) {
                case 1: listProducts(sc);break;
                case 2: viewCart(sc);break;
                default:lanjut=false;
            }
        }
    }
    private static void listProducts(Scanner sc){
        //unimplemented
    }
    private static void viewCart(Scanner sc){
        //unimplemented
    }
    private static void addToCart(int id, int qty){
        //unimplemented
    }
    private static void checkOut(){
        //unimplemented facade?
    }
    private static int countTotalPrice(){
        return 0;       //unimplemented
    }
}
