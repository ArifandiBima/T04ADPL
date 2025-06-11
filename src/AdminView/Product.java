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
    @Override
    public int countPrice(){
        return this.price;
    }
    @Override
    public String toString(){
        return this.nama;
    }
    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }
    @Override
    public boolean equals(Object other){
        if (!(other instanceof MainProduct)) return false;
        return this.id==((MainProduct) other).id;
    }
}

class BungkusKado extends Product{
    Product produk;    
    public BungkusKado(Product isi){
        this.produk = isi;
    }
    @Override
    public int countPrice(){
        return this.produk.countPrice()+3000;                   //unimplemented
    }
    @Override
    public String toString(){
        return this.produk.toString()+" dibungkus kado";
    }
    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }
    @Override
    public boolean equals(Object other){
        if (!(other instanceof BungkusKado)) return false;
        return this.produk.equals(((BungkusKado)other).produk);
    }
}

class BubbleWrap extends Product{
    Product produk;    
    public BubbleWrap(Product isi){
        this.produk = isi;
    }
    @Override
    public int countPrice(){
        return this.produk.countPrice()+3000;                   //unimplemented
    }
    @Override
    public String toString(){
        return this.produk.toString()+" dibungkus wrap";
    }
    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }
    @Override
    public boolean equals(Object other){
        if (!(other instanceof BubbleWrap)) return false;
        return this.produk.equals(((BubbleWrap)other).produk);
    }
}


//bisa nambahin hal lain yang bisa dikasih extra

