/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminView;

import UserView.Cart;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;
import java.time.LocalDate;
/**
 *
 * @author Rits08
 */

//maybe ini observer juga tapi kayak listener doang gitu
public class Logger{
    public static void logPesanan(Cart cart, String alamat) throws Exception{
        ArrayList<Order> orders = new ArrayList<>();
        //log tiap kali ada order per item kita tentuin ngirimnya pake apa dan dilog
        for (Map.Entry<Product, Integer> entry:cart.contents.entrySet()){
            Order pesanan = new Order(entry.getKey(), alamat,entry.getValue());
            orders.add(pesanan);
        }
        String pesananBaru = "";
        for (Order order:orders){
            pesananBaru+=LocalDate.now()+" "+order.qty+" "+order.produk.toString()+" to be delivered by "+order.strategy+" to "+alamat+"\n";
        }
        try{
            FileWriter fw = new FileWriter("./src/AdminView/logs.data", true);
            fw.write(pesananBaru);
            fw.close();
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
    //method static tiap kali ada update di sini dilog, cuma write ke log doang
}
