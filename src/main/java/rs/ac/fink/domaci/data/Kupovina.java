package rs.ac.fink.domaci.data;

import java.io.Serializable;

public class Kupovina {
    private int kupovina_id;
    private Korisnik korisnik;
    private Proizvod proizvod;
    
    public Kupovina() {
        
    }

    public Kupovina(Korisnik korisnik, Proizvod proizvod) {
        this.korisnik = korisnik;
        this.proizvod = proizvod;
    }
    
    public Kupovina(int kupovina_id, Korisnik korisnik, Proizvod proizvod) {
        this.kupovina_id = kupovina_id;
        this.korisnik = korisnik;
        this.proizvod = proizvod;
    }

    public int getKupovina_id() {
        return kupovina_id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    

    public void setKupovina_id(int kupovina_id) {
        this.kupovina_id = kupovina_id;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    
}
