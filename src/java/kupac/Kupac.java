/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kupac;

import frames.AdminPanel;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 *
 * @author hp
 */
public class Kupac {

     @Resource(lookup = "Zahtevi")
     public static Queue zahtevi;
     @Resource(lookup = "Odgovori")
     public static Topic odgovori;
     @Resource(lookup = "jms/__defaultConnectionFactory")
     public static ConnectionFactory connectionFactory;

     public static void main(String[] args) {
          /* Create and display the form */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    new AdminPanel().setVisible(true);
               }
          });
          
          System.out.println("Kupac pokrenut");
     }

}
