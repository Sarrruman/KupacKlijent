package beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

public class Prodavac extends Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;

    private long POSBr;
    private List<Apartman> apartmani;

    public long getPOSBr() {
        return POSBr;
    }

    public void setPOSBr(long POSBr) {
        this.POSBr = POSBr;
    }

    public List<Apartman> getApartmani() {
        return apartmani;
    }

    public void setApartmani(List<Apartman> apartmani) {
        this.apartmani = apartmani;
    }

}
