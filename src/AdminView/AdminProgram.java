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

// test
public class AdminProgram{
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int perintah=0;
        boolean lanjut=true;
        while (lanjut){

            while(perintah==0){
                // rapihin menu
                System.out.println(" ==== ADMIN MENU ==== \n choose what to do: \n 1.View Products\n 2.Edit Product\n 3.Exit\n"); 
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
                case 2: try {
                    editProducts(sc);
                        } catch (Exception e) {
                        System.out.println("Terjadi kesalahan saat edit produk: " + e.getMessage());
                    }
                    break;
                default:lanjut=false;
            }
            perintah = 0;
        }
    }
    private static void listProducts(){
        try{
            Toko.products = Toko.getProducts();
        }
        catch(Exception e){}
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
    private static void editProducts(Scanner sc)throws Exception{
    if(Toko.getProducts().isEmpty()){
        System.out.println("Tidak ada produk untuk diedit.");
        return;
    }

    System.out.println("Masukkan ID produk yang ingin diedit:");
    int id = sc.nextInt();
    
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
        switch(pilihan){
            case 1:
                System.out.print("Masukkan nama baru: ");
                produk.nama = sc.next();
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
    Toko.changeData();
}
}
