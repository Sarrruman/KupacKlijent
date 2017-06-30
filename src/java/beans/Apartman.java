package beans;

import java.io.Serializable;
import java.util.List;

public class Apartman implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    private String ime;

    private Adresa adresa;

    private Prodavac prodavac;

    private List<Soba> sobe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
        adresa.setApartman(this);
    }

    public List<Soba> getSobe() {
        return sobe;
    }

    public void setSobe(List<Soba> sobe) {
        this.sobe = sobe;
    }

}
