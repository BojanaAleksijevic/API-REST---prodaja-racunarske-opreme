package rs.ac.fink.domaci.data;

import java.io.Serializable;

public class Kupovina {
    private int kupovina_id;
    private Korisnik korisnik_id;
    private Proizvod proizvod_id;
    
    public Kupovina() {
        
    }

    public Kupovina(int kupovina_id, Korisnik korisnik_id, Proizvod proizvod_id) {
        this.kupovina_id = kupovina_id;
        this.korisnik_id = korisnik_id;
        this.proizvod_id = proizvod_id;
    }

    public int getKupovina_id() {
        return kupovina_id;
    }

    public Korisnik getKorisnik_id() {
        return korisnik_id;
    }

    public Proizvod getProizvod_id() {
        return proizvod_id;
    }

    public void setKupovina_id(int kupovina_id) {
        this.kupovina_id = kupovina_id;
    }

    public void setKorisnik_id(Korisnik korisnik_id) {
        this.korisnik_id = korisnik_id;
    }

    public void setProizvod_id(Proizvod proizvod_id) {
        this.proizvod_id = proizvod_id;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Kupovina{").append("korisnik_id=").append(korisnik_id)
        .append("proizvod_id=").append(proizvod_id)
        .append("}");
        return sb.toString();
    }
    
}
