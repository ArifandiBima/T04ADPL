/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminView;

import java.nio.file.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Rits08
 */
public class Toko{
   public static HashMap<Integer,MainProduct> products;
    //nanti kalo mau pake basis data diganti lagi

    public static HashMap<Integer, MainProduct> getProducts() throws Exception{
        int id;
        String productName;
        int price;
        int qty;
        boolean[] modifier;
        products = new HashMap<>();
        Scanner sc = new Scanner(Paths.get("AdminView/dataProduk.data"));

        Scanner productScanner;

        while(sc.hasNext()){
            String nextProduct = sc.nextLine();
            modifier = new boolean[2];
            // Split the line by spaces
            String[] parts = nextProduct.split(" ");
            id = Integer.parseInt(parts[0]);
            // Find price and qty positions (last two or last three if modifiers)
            int priceIndex = parts.length - 2;
            int qtyIndex = parts.length - 1;
            // Parse price and qty
            price = Integer.parseInt(parts[priceIndex]);
            qty = Integer.parseInt(parts[qtyIndex]);
            // productName is all parts between id and priceIndex
            StringBuilder nameBuilder = new StringBuilder();
            for(int i=1; i<priceIndex; i++){
                nameBuilder.append(parts[i]);
                if(i < priceIndex - 1){
                    nameBuilder.append(" ");
                }
            }
            productName = nameBuilder.toString();
            // Parse modifiers if any
            for(int i=qtyIndex+1; i<parts.length; i++){
                int modIndex = Integer.parseInt(parts[i]);
                if(modIndex >= 1 && modIndex <= modifier.length){
                    modifier[modIndex-1] = true;
                }
            }
            products.put(id,(new MainProduct(id,productName,price,qty,modifier)));
        }
        sc.close();
        
        return products;
    }
    public static void changeData()throws Exception{
        String toWrite ="";
        for (MainProduct product: products.values()){
            toWrite+= product.id+" "+product.nama+" "+product.price+" "+product.qty;
            for (int i=0;i<product.modifiable.length;i++){
                if (product.modifiable[i]){
                    toWrite+=" "+(i+1);
                }
            }
            toWrite+='\n';
        }
        Files.writeString(Paths.get("AdminView/dataProduk.data"), toWrite);
    }
    
}
