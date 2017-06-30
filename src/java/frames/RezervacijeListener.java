/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import javax.jms.Message;
import javax.jms.MessageListener;

public class RezervacijeListener implements MessageListener {

    private Rezervacije panel;

    public RezervacijeListener(Rezervacije panel) {
        this.panel = panel;
    }

    @Override
    public void onMessage(Message message) {
        if (panel.isVisible()) {
            panel.refresh();
        }
    }

}
