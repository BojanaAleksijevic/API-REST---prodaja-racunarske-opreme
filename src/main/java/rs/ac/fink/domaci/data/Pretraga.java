package rs.ac.fink.domaci.data;

import java.io.Serializable;

public class Pretraga {
    private int pretraga_id=-1;
    private Korisnik korisnik_id;
    private PodesavanjePretrage podesavanje_pretrage_id;
    
    Pretraga() {  
    }

    public Pretraga(int pretraga_id, Korisnik korisnik_id, PodesavanjePretrage podesavanje_pretrage_id) {
        this.pretraga_id = pretraga_id;
        this.korisnik_id = korisnik_id;
        this.podesavanje_pretrage_id = podesavanje_pretrage_id;
    }

    public int getPretraga_id() {
        return pretraga_id;
    }

    public Korisnik getKorisnik_id() {
        return korisnik_id;
    }

    public PodesavanjePretrage getPodesavanje_pretrage_id() {
        return podesavanje_pretrage_id;
    }

    public void setPretraga_id(int pretraga_id) {
        this.pretraga_id = pretraga_id;
    }

    public void setPodesavanje_pretrage_id(PodesavanjePretrage podesavanje_pretrage_id) {
        this.podesavanje_pretrage_id = podesavanje_pretrage_id;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pretraga{").append("korisnik_id=").append(korisnik_id)
        .append("podesavanje_pretrage_id=").append(podesavanje_pretrage_id)
        .append("}");
        return sb.toString();
    }
}
