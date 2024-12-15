package rs.ac.fink.domaci.data;

import java.io.Serializable;

public class Pretraga {
    private int pretraga_id=-1;
    private Korisnik korisnik;
    private PodesavanjePretrage podesavanje_pretrage;
    
    Pretraga() {  
    }

    public Pretraga(int pretraga_id, Korisnik korisnik, PodesavanjePretrage podesavanje_pretrage) {
        this.pretraga_id = pretraga_id;
        this.korisnik = korisnik;
        this.podesavanje_pretrage = podesavanje_pretrage;
    }

    public int getPretraga_id() {
        return pretraga_id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public PodesavanjePretrage getPodesavanje_pretrage() {
        return podesavanje_pretrage;
    }

    public void setPretraga_id(int pretraga_id) {
        this.pretraga_id = pretraga_id;
    }

    public void setPodesavanje_pretrage(PodesavanjePretrage podesavanje_pretrage) {
        this.podesavanje_pretrage = podesavanje_pretrage;
    }

    
}
