package kupac;

import frames.HomePanel;
import frames.LoginPanel;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

public class Kupac {

    public static HomePanel homePanel;

    @Resource(lookup = "Zahtevi")
    public static Queue zahtevi;
    @Resource(lookup = "Odgovori")
    public static Topic odgovori;
    @Resource(lookup = "jms/__defaultConnectionFactory")
    public static ConnectionFactory connectionFactory;
    @Resource(lookup = "Obavestenja")
    public static Topic obavestenja;
    @Resource(lookup = "ObavestenjaSobe")
    public static Topic obavestenjaSobe;

    public static beans.Kupac kupac;

    public static void main(String[] args) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPanel().setVisible(true);
            }
        });

        System.out.println("Kupac pokrenut");
    }

}
