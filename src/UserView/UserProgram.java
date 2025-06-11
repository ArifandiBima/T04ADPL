package UserView;


import AdminView.Product;
import AdminView.MainProduct;
import AdminView.Toko;
import java.util.Map;
import java.util.Scanner;

public class UserProgram {
    public static void main(String[] args) {
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

    private static void listProducts(Scanner sc) {
        if (Toko.products.isEmpty()) {
            System.out.println("Tidak ada produk tersedia.");
            return;
        }

        System.out.println("Daftar Produk:");
        for (MainProduct p : Toko.products.values()) {
            System.out.println("ID: " + p.id + " | " + p.nama + " | Harga: " + p.price + " | Stok: " + p.qty);
        }

        System.out.print("Masukkan ID produk untuk ditambahkan ke keranjang (atau ketik '0' untuk kembali): ");
        String idStr = sc.next();
        if (idStr.equals("0")) return;

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("ID tidak valid.");
            return;
        }

        MainProduct selected = Toko.products.get(idStr);
        if (selected == null) {
            System.out.println("Produk tidak ditemukan.");
            return;
        }

        System.out.print("Masukkan jumlah: ");
        int qty = sc.nextInt();
        if (qty <= 0 || qty > selected.qty) {
            System.out.println("Jumlah tidak valid atau stok tidak mencukupi.");
            return;
        }

        Cart myCart = Cart.getCart();
        myCart.addToCart(selected, qty);
        System.out.println("Produk ditambahkan ke keranjang.");
    }

    private static void viewCart(Scanner sc) {
        Cart myCart = Cart.getCart();

        if (myCart.contents.isEmpty()) {
            System.out.println("Keranjang kamu kosong.");
            return;
        }

        System.out.println("Isi Keranjang:");
        for (Map.Entry<Product, Integer> entry : myCart.contents.entrySet()) {
            System.out.println(entry.getKey().toString() + " | Qty: " + entry.getValue());
        }

        System.out.println("Total: " + countTotalPrice(myCart));
        System.out.print("Ketik 1 untuk checkout atau 0 untuk kembali: ");
        int opsi = sc.nextInt();
        if (opsi == 1) {
            checkOut(myCart);
        }
    }

    private static void checkOut(Cart myCart) {
        System.out.println("Melakukan checkout...");

        for (Map.Entry<Product, Integer> entry : myCart.contents.entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();

            if (p instanceof MainProduct) {
                MainProduct mp = (MainProduct) p;
                mp.qty -= qty;
            }
        }

        System.out.println("Checkout berhasil. Terima kasih sudah belanja!");
        myCart.contents.clear();
    }

    private static int countTotalPrice(Cart myCart) {
        int total = 0;
        for (Map.Entry<Product, Integer> entry : myCart.contents.entrySet()) {
            total += entry.getKey().countPrice() * entry.getValue();
        }
        return total;
    }
}
