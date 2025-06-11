/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package AdminView;

/**
 *
 * @author Rits08
 */
public class Order {
    Product produk;
    String alamat;
    int qty;
    enum Strategy{
        pickup,
        bajaj,
        warpGate,
        
    };
    Strategy strategy;
    public Order(Product produk, String alamat, int qty){
        this.produk = produk;
        this.alamat = alamat;
        this.qty = qty;
        this.strategy = chooseStrategy(produk,alamat);
    }
    private Strategy chooseStrategy(Product produk, String alamat){
        int hitungan = produk.hashCode()*alamat.hashCode()%3;//implementasi boongan
        if (hitungan==0) return Strategy.pickup;
        if (hitungan==1) return Strategy.bajaj;
        return Strategy.warpGate;
    }
}
