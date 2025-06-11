package UserView;


import AdminView.Product;
import AdminView.Toko;
import java.util.Map;
import java.util.Scanner;

public class UserProgram {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int perintah = 0;
        boolean lanjut = true;

        while (lanjut) {
            while (perintah == 0) {
                System.out.println("Choose what to do:\n 1. View Products\n 2. View Cart\n 3. Exit");
                try {
                    perintah = sc.nextInt();
                    if (perintah < 1 || perintah > 3) {
                        perintah = 0;
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Please choose a valid option");
                    sc.nextLine(); // penting agar scanner tidak macet
                }
            }

            
            switch (perintah) {
                case 1:
                    listProducts(sc);
                    break;
                case 2:
                    viewCart(sc);
                    break;
                default:
                    lanjut = false;
            }
            perintah = 0;
        }

        sc.close();
    }
    private static void listProducts(Scanner sc) throws Exception{
        if (Toko.getProducts().isEmpty()) {
            System.out.println("Tidak ada produk tersedia.");
            return;
        }

        System.out.println("Daftar Produk:");
        for (Product entry : Toko.products.values()) {
            System.out.println(entry.toString());
        }

        System.out.print("Masukkan ID produk untuk ditambahkan ke keranjang (atau ketik '0' untuk kembali): ");
        int id = sc.nextInt();
        if (id==0) return;

        Product produk = Toko.products.get(id);
        if (produk == null) {
            System.out.println("Produk tidak ditemukan.");
            return;
        }

        System.out.print("Masukkan jumlah: ");
        int qty = sc.nextInt();

        addToCart(produk, qty);
        System.out.println("Produk ditambahkan ke keranjang.");
    }

    private static void viewCart(Scanner sc) {
        Cart myCart = Cart.getCart();
        if (myCart.contents.isEmpty()) {
            System.out.println("Keranjang kamu kosong.");
            return;
        }

        System.out.println("Isi Keranjang:");
        for (Map.Entry<Product,Integer> entry: myCart.contents.entrySet()) {
            System.out.println(entry.getKey().toString() + " | Qty: " + entry.getValue());
        }

        System.out.println("Total: " + countTotalPrice());
        System.out.print("Ketik 1 untuk checkout atau 0 untuk kembali: ");
        int opsi = sc.nextInt();
        if (opsi == 1) checkOut(sc);
    }

    private static void addToCart(Product produk, int qty){
        Cart myCart = Cart.getCart();
        myCart.addToCart(produk, qty);
    }
    
    private static void checkOut(Scanner sc){
        System.out.println("Melakukan checkout...");
        Cart myCart = Cart.getCart();
        System.out.println("Tolong pilih metode pembayaran");
        System.out.println("Masukkan alamat tujuan\n");
        String alamat = sc.nextLine();
        System.out.println("1.Qris\n2.Transfer Bank\n");
        int metode = sc.nextInt();
        if(myCart.checkOut(metode, alamat))
            System.out.println("Checkout berhasil. Terima kasih sudah belanja!");
    }

    private static int countTotalPrice(){
        Cart myCart = Cart.getCart();
        return myCart.countTotal();
        } 
}
