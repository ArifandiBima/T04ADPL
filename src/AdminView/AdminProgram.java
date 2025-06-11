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
    if(Toko.products.isEmpty()){
        System.out.println("Tidak ada produk untuk diedit.");
        return;
    }

    System.out.println("Masukkan ID produk yang ingin diedit:");
    String id = sc.next();
    
    MainProduct produk = Toko.products.get(id);
    if(produk == null){
        System.out.println("Produk dengan ID tersebut tidak ditemukan.");
        return;
    }

    boolean selesai = false;
    while (!selesai) {
        System.out.println("\nProduk ditemukan: " + produk.nama);
        System.out.println("Pilih yang ingin diedit:");
        System.out.println("1. Nama");
        System.out.println("2. Stok");
        System.out.println("3. Harga");
        System.out.println("4. Kembali");

        int pilihan = sc.nextInt();
        sc.nextLine(); // bersihkan newline
        switch(pilihan){
            case 1:
                System.out.print("Masukkan nama baru: ");
                produk.nama = sc.nextLine();
                System.out.println("Nama diperbarui.");
                break;
            case 2:
                System.out.print("Masukkan jumlah stok baru: ");
                produk.qty = sc.nextInt();
                System.out.println("Stok diperbarui.");
                break;
            case 3:
                System.out.print("Masukkan harga baru: ");
                produk.price = sc.nextInt();
                System.out.println("Harga diperbarui.");
                break;
            case 4:
                selesai = true;
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }
}
}