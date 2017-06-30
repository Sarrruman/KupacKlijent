package beans;

import java.io.Serializable;

public class Adresa implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    private Apartman apartman;

    private String drzava;
    private String grad;
    private String ulica;
    private long broj;
    private String opis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Apartman getApartman() {
        return apartman;
    }

    public void setApartman(Apartman apartman) {
        this.apartman = apartman;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public long getBroj() {
        return broj;
    }

    public void setBroj(long broj) {
        this.broj = broj;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

}
