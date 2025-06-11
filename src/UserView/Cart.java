/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserView;

import java.util.HashMap;

import AdminView.Logger;
import AdminView.Product;
/**
 *
 * @author Rits08
 */

//singleton cart
public class Cart {
    private static Cart implemented;
    private HashMap<Product,int> contents;
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
    public static boolean Checkout(int metode,int id, String embel_embel){
        if(!PaymentMethod.verify(id, embel_embel)) return false;
        Logger.log(implemented);
        implemented =null;
        return true;
    }
}
