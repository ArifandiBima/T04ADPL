/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminView;

/**
 *
 * @author Rits08
 */
import java.util.Scanner;

// test
public class AdminProgram {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int perintah = 0;
        boolean lanjut = true;
        while (lanjut) {

            while (perintah == 0) {
                // rapihin menu
                System.out.println(
                        " ==== ADMIN MENU ==== \n choose what to do: \n 1.View Products\n 2.Add Product \n 3.Edit Product \n 4.Delete Product \n 5.Exit\n");
                try {
                    perintah = sc.nextInt();
                    if (perintah > 5 || perintah < 1) {
                        perintah = 0;
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("please choose a valid option");
                }
            }
            switch (perintah) {
                case 1:
                    listProducts();
                    break;
                case 2:
                    try {
                        addNewProduct(sc);
                    } catch (Exception e) {
                        System.out.println("Terjadi kesalahan saat tambah produk: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        editProducts(sc);
                    } catch (Exception e) {
                        System.out.println("Terjadi kesalahan saat edit produk: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        deleteProduct(sc);
                    } catch (Exception e) {
                        System.out.println("Terjadi kesalahan saat hapus produk: " + e.getMessage());
                    }
                    break;
                case 5:
                    lanjut = false;
                    break;
                default:
                    lanjut = false;
            }
            perintah = 0;
        }
    }

    public static void addNewProduct(Scanner sc) {
        try {
            // Load current products
            Toko.products = Toko.getProducts();

            System.out.println("Enter new product ID:");
            int id = sc.nextInt();
            if (Toko.products.containsKey(id)) {
                System.out.println("Product ID already exists. Cannot add product.");
                return;
            }

            sc.nextLine(); // consume leftover newline

            System.out.println("Enter product name:");
            String name = sc.next();

            System.out.println("Enter product price:");
            int price = sc.nextInt();

            System.out.println("Enter product quantity:");
            int qty = sc.nextInt();

            boolean[] modifiable = new boolean[2];

            MainProduct newProduct = new MainProduct(id, name, price, qty, modifiable);
            Toko.products.put(id, newProduct);

            // Save changes to dataProduk.data
            Toko.changeData();

            System.out.println("Product added successfully.");

        } catch (Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    private static void listProducts() {
        try {
            Toko.products = Toko.getProducts();
        } catch (Exception e) {
        }
        if (Toko.products.isEmpty()) {
            System.out.println("No Product Available");
        } else {
            System.out.println("Product List: ");
            for (MainProduct produk : Toko.products.values()) {
                System.out.println(produk.id + " " + produk.nama + " stock:" + produk.qty + " harga:" + produk.price);
            }
            // ADA TAMBAHAN DI SINI
        }
    }

    private static void editProducts(Scanner sc) throws Exception {
        if (Toko.getProducts().isEmpty()) {
            System.out.println("Tidak ada produk untuk diedit.");
            return;
        }

        System.out.println("Masukkan ID produk yang ingin diedit:");
        int id = sc.nextInt();

        MainProduct produk = Toko.products.get(id);
        if (produk == null) {
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
            switch (pilihan) {
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

    public static void deleteProduct(Scanner sc) throws Exception {
        Toko.products = Toko.getProducts();
        if (Toko.products.isEmpty()) {
            System.out.println("Tidak ada produk untuk dihapus.");
            return;
        }
        System.out.println("Masukkan ID produk yang ingin dihapus:");
        int id = sc.nextInt();
        if (!Toko.products.containsKey(id)) {
            System.out.println("Produk dengan ID tersebut tidak ditemukan.");
            return;
        }
        Toko.products.remove(id);
        Toko.changeData();
        System.out.println("Produk berhasil dihapus.");
    }
}
