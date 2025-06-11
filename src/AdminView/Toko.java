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
        products = new HashMap<>();
        Scanner sc = new Scanner(Paths.get("./src/AdminView/dataProduk.data"));
        Scanner productScanner;
        while(sc.hasNext()){
            String nextProduct = sc.nextLine();
            boolean[] modifier = new boolean[2];
            productScanner = new Scanner(nextProduct);
            id = productScanner.nextInt();
            productName = productScanner.next();
            price = productScanner.nextInt();
            qty = productScanner.nextInt();
            while(productScanner.hasNext()){
                int modIndex = productScanner.nextInt();
                if(modIndex >= 1 && modIndex <= modifier.length){
                    modifier[modIndex-1] = true;
                }
            }
            products.put(id,(new MainProduct(id,productName,price,qty,modifier)));
            productScanner.close();
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
