/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminView;

/**
 *
 * @author Rits08
 */
//menggunakan design pattern decorator
public abstract class Product {
    Product produk;    
    public abstract int countPrice();
}

class MainProduct extends Product{
    int id;
    String nama;
    int price;
    int qty;
    boolean[] modifiable;     //buat ngedata dia bisa dikasih apa aja gue
    public MainProduct(int id, String nama, int price, int qty, boolean[] modifiable){
        this.id=id;
        this.nama=nama;
        this.price=price;
        this.qty=qty;
        this.modifiable = modifiable;
    }
    public MainProduct(int id, String nama, int price){
        this(id,nama, price,0,new boolean[1]);
    }
    public MainProduct(int id, String nama, int price, boolean[] modifiable){
        this(id,nama, price, 0, modifiable);
    }
    public int countPrice(){
        return this.price;
    }
    @Override
    public String toString(){
        return this.nama;
    }
}

class BungkusKado extends Product{
    public BungkusKado(Product isi){
        this.produk = isi;
    }
    public int countPrice(){
        return this.produk.countPrice()+3000;                   //unimplemented
    }
    public String toString(){
        return this.produk.toString()+" dibungkus kado";
    }
}

//bisa nambahin hal lain yang bisa dikasih extra

