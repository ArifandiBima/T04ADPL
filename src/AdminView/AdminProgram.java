/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminView;

/**
 *
 * @author Rits08
 */
import AdminView.Product;
import java.util.Scanner;

public class AdminProgram{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int perintah=0;
        boolean lanjut=true;
        while (lanjut){

            while(perintah==0){
                System.out.println("choose what to do\n 1.View Products\n 2.edit\n3.exit\n");
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
                case 1: listProducts();break;
                case 2: editProducts(sc);break;
                default:lanjut=false;
            }
        }
    }
    private static void listProducts(){
        //unimplemented
        if(Toko.products.isEmpty()){
            System.out.println("No Product Available");
        }else{
            System.out.println("Product List: ");
            for(MainProduct produk: Toko.products.values()){
                System.out.println(produk.id+ " " + produk.nama+" stock:"+produk.qty+" harga:"+produk.price);
            }
            //ADA TAMBAHAN DI SINI
        }
    }
    private static void editProducts(Scanner sc){
        //unimplemented
    }
}
