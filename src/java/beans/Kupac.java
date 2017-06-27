package beans;

import java.io.Serializable;

public class Kupac extends Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;
    private String brKreditneKartice;

    public String getBrKreditneKartice() {
        return brKreditneKartice;
    }

    public void setBrKreditneKartice(String brKreditneKartice) {
        this.brKreditneKartice = brKreditneKartice;
    }

}
