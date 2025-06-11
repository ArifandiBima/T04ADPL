/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserView;

import java.util.HashMap;

import AdminView.Logger;
import AdminView.Product;
import java.util.Map;
/**
 *
 * @author Rits08
 */

//singleton cart
public class Cart {
    private static Cart implemented;
    public HashMap<Product,Integer> contents;
    private Cart(){
        this.contents = new HashMap<>();
    }
    public void addToCart(Product product, int qty){
        qty += this.contents.getOrDefault(product, 0);
        this.contents.put(product, qty);
    }
    public static Cart getCart(){
        if (implemented==null){
            implemented = new Cart();
        }
        return implemented;
    }
    public int countTotal(){
        int hasil=0;
        for (Map.Entry<Product,Integer> entry: contents.entrySet()){
            hasil+= entry.getKey().countPrice()*entry.getValue();
        }
        return hasil;
    }
    public boolean checkOut(int metode, String alamat){
        if(!PaymentMethod.verify(metode, "embel_embel")) return false;
        try{
            Logger.logPesanan(implemented, alamat);
        }
        catch(Exception e){
            System.out.print("gak kelog");
        }
        implemented =null;
        return true;
    }
}
