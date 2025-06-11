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
    int idProduk;
    String alamat;
    int qty;
    enum Strategy{
        pickup,
        bajaj,
        warpGate,
        
    };
    Strategy strategy;
    public Order(int idProduk, String alamat, int qty){
        this.idProduk = idProduk;
        this.alamat = alamat;
        this.qty = qty;
        this.strategy = chooseStrategy(idProduk,alamat);
    }
    private Strategy chooseStrategy(int idProduk, String alamat){
        int hitungan = idProduk*alamat.hashCode()%3;//implementasi boongan
        if (hitungan==0) return Strategy.pickup;
        if (hitungan==1) return Strategy.bajaj;
        return Strategy.warpGate;
    }
}
