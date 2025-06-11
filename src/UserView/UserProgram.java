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
    if (Toko.products.isEmpty()) {
        System.out.println("Tidak ada produk tersedia.");
        return;
    }

    System.out.println("Daftar Produk:");
    for (MainProduct p : Toko.products.values()) {
        System.out.println(p.id + " | " + p.nama + " | Stok: " + p.qty + " | Harga: " + p.price);
    }

    System.out.print("Masukkan ID produk untuk ditambahkan ke keranjang (atau ketik '0' untuk kembali): ");
    String id = sc.next();
    if (id.equals("0")) return;

    MainProduct produk = Toko.products.get(id);
    if (produk == null) {
        System.out.println("Produk tidak ditemukan.");
        return;
    }

    System.out.print("Masukkan jumlah: ");
    int qty = sc.nextInt();
    if (qty <= 0 || qty > produk.qty) {
        System.out.println("Jumlah tidak valid atau stok tidak mencukupi.");
        return;
    }

    addToCart(produk.id, qty);
    System.out.println("Produk ditambahkan ke keranjang.");
}

    private static void viewCart(Scanner sc){
    if (Cart.isEmpty()) {
        System.out.println("Keranjang kamu kosong.");
        return;
    }

    System.out.println("Isi Keranjang:");
    for (String id : cart.keySet()) {
        MainProduct p = Toko.products.get(id);
        System.out.println(p.nama + " | Qty: " + Cart.getCart(id) + " | Subtotal: " + (p.price * Cart.getCart(id)));
    }

    System.out.println("Total: " + countTotalPrice());
    System.out.print("Ketik 1 untuk checkout atau 0 untuk kembali: ");
    int opsi = sc.nextInt();
    if (opsi == 1) checkOut();
}

    private static void addToCart(int id, int qty){
        Cart.put(id, Cart.getOrDefault(id, 0) + qty);
    }
    
    private static void checkOut(){
    System.out.println("Melakukan checkout...");
    for (String id : Cart.keySet()) {
        int qty = Cart.get(id);
        MainProduct p = Toko.products.get(id);
        p.qty -= qty; // kurangi stok
    }
    System.out.println("Checkout berhasil. Terima kasih sudah belanja!");
    Cart.clear(); // kosongkan keranjang
}

    private static int countTotalPrice(){
    int total = 0;
    for (String id : Cart.keySet()) {
        MainProduct p = Toko.products.get(id);
        total += p.price * cart.get(id);
    }
    return total;
}
        //return 0;       //unimplemented
    
}
