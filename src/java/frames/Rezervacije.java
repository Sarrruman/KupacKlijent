package frames;

import beans.Rezervacija;
import beans.Soba;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.text.DateFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.UtilDateModel;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;
import messages.ListaRezervacija;
import messages.Login;
import messages.RezervacijaMessage;
import messages.SobeMessage;
import utils.Helpers;
import utils.TipZahteva;

public class Rezervacije extends javax.swing.JFrame {

    public void refresh() {
        new Rezervacije(this.soba, this.status.getText()).setVisible(true);
        this.dispose();
    }

    private Soba soba;
    public JLabel status;

    public Rezervacije(Soba s, String statusString) {
        this.soba = s;
        initComponents();
        dohvatiRezervacije();

        this.setLayout(new GridLayout(soba.getRezervacije().size() + 2, 3));

        // dodavanje statusnog polja
        status = new JLabel(statusString);
        this.add(status);
        this.add(new JLabel());
        this.add(new JLabel());

        List<Rezervacija> rezervacije = soba.getRezervacije();
        for (int i = 0; i < rezervacije.size(); i++) {
            JPanel p1 = new JPanel();
            JPanel p2 = new JPanel();
            JPanel p3 = new JPanel();

            JLabel l1 = new JLabel(rezervacije.get(i).getId() + "");
            JLabel l2 = new JLabel(rezervacije.get(i).getDatumPrijave().toString());
            JLabel l3 = new JLabel(rezervacije.get(i).getDatumOdjave().toString());

            l1.setPreferredSize(new Dimension(200, 20));
            l1.setBorder(new BasicBorders.FieldBorder(Color.yellow, Color.darkGray, Color.lightGray, Color.lightGray));
            l2.setPreferredSize(new Dimension(200, 20));
            l2.setBorder(new BasicBorders.FieldBorder(Color.yellow, Color.darkGray, Color.lightGray, Color.lightGray));
            l3.setPreferredSize(new Dimension(200, 20));
            l3.setBorder(new BasicBorders.FieldBorder(Color.yellow, Color.darkGray, Color.lightGray, Color.lightGray));

            p1.add(l1);
            p2.add(l2);
            p3.add(l3);

            this.add(p1);
            this.add(p2);
            this.add(p3);
        }

        SqlDateModel model = new SqlDateModel();
        java.util.Properties p = new java.util.Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datumOd = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        this.add(datumOd);

        SqlDateModel model1 = new SqlDateModel();
        java.util.Properties p1 = new java.util.Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p1);
        JDatePickerImpl datumDo = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
        this.add(datumDo);

        JButton unos = new JButton("Unesi");
        JPanel jpanelunos = new JPanel();
        jpanelunos.add(unos);
        this.add(jpanelunos);

        unos.addActionListener(new ActionListener() {
            private Soba s;
            private Rezervacije panel;
            private JDatePickerImpl datum1, datum2;

            public void actionPerformed(ActionEvent e) {
                JMSContext context = kupac.Kupac.connectionFactory.createContext();

                Destination destination = kupac.Kupac.zahtevi;
                String username = kupac.Kupac.kupac.getUsername();
                String password = kupac.Kupac.kupac.getPassword();

                JMSConsumer consumer = context.createConsumer(kupac.Kupac.odgovori, Helpers.getId(username, password));
                JMSProducer producer = context.createProducer();

                ObjectMessage zahtev = context.createObjectMessage();
                try {
                    zahtev.setStringProperty("id", username + password);

                    RezervacijaMessage rez = new RezervacijaMessage();
                    rez.setSoba(s);
                    rez.setDatumOd((java.sql.Date) datum1.getModel().getValue());
                    rez.setDatumDo((java.sql.Date) datum2.getModel().getValue());
                    rez.setKupac(kupac.Kupac.kupac);

                    zahtev.setObject(rez);
                    zahtev.setIntProperty("tip", TipZahteva.NOVA_REZERVACIJA.ordinal());

                    producer.send(destination, zahtev);
                } catch (JMSException ex) {
                    Logger.getLogger(Rezervacije.class.getName()).log(Level.SEVERE, null, ex);
                }

                Message odgovor = consumer.receive();
                if (odgovor instanceof ObjectMessage) {
                    try {
                        Object objekat = ((ObjectMessage) odgovor).getObject();
                        if (objekat != null) {
                            status.setText("Uspesno");
                            panel.refresh();
                        } else {
                            status.setText("Neuspesno (Vec rezervisano)");
                        }
                    } catch (JMSException ex) {
                        Logger.getLogger(Rezervacije.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Logger.getLogger(Rezervacije.class.getName()).log(Level.SEVERE, null, "Primljen objekat koji nije tipa object");
                }
            }

            private ActionListener init(Rezervacije r, Soba s, JDatePickerImpl datum1, JDatePickerImpl datum2) {
                this.s = s;
                this.panel = r;
                this.datum1 = datum1;
                this.datum2 = datum2;
                return this;
            }
        }.init(this, soba, datumOd, datumDo));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closingHandler(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 773, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 657, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closingHandler(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closingHandler
        kupac.Kupac.homePanel.refresh();
        this.dispose();
    }//GEN-LAST:event_closingHandler

    private void dohvatiRezervacije() {
        JMSContext context = kupac.Kupac.connectionFactory.createContext();

        Destination destination = kupac.Kupac.zahtevi;
        String username = kupac.Kupac.kupac.getUsername();
        String password = kupac.Kupac.kupac.getPassword();

        JMSConsumer consumer = context.createConsumer(kupac.Kupac.odgovori, Helpers.getId(username, password));
        JMSProducer producer = context.createProducer();

        ObjectMessage zahtev = context.createObjectMessage();
        try {
            zahtev.setStringProperty("id", username + password);

            zahtev.setObject(soba);
            zahtev.setIntProperty("tip", TipZahteva.DOHVATANJE_SVIH_REZERVACIJA.ordinal());

            producer.send(destination, zahtev);
        } catch (JMSException ex) {
            Logger.getLogger(Rezervacije.class.getName()).log(Level.SEVERE, null, ex);
        }

        Message odgovor = consumer.receive();
        if (odgovor instanceof ObjectMessage) {
            try {
                Object objekat = ((ObjectMessage) odgovor).getObject();
                if (objekat != null) {
                    soba.setRezervacije(((ListaRezervacija) objekat).getRezervacije());
                } else {
                }
            } catch (JMSException ex) {
                Logger.getLogger(Rezervacije.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Logger.getLogger(Rezervacije.class.getName()).log(Level.SEVERE, null, "Primljen objekat koji nije tipa object");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
