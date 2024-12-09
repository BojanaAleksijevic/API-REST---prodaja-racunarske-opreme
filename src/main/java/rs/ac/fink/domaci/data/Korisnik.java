package rs.ac.fink.domaci.data;

import java.io.Serializable;


public class Korisnik implements Serializable{
    private int korisnik_id=-1;
    private String ime_i_prezime;
    private String username;
    private String e_mail;
    private String datum_rodjenja;
    
    private int stanje_racuna;
    private int kolicina_potrosenog_novca;

    public Korisnik() {  
    }

    public Korisnik(int korisnik_id, String ime_i_prezime, String username, String e_mail, String datum_rodjenja, int stanje_racuna, int kolicina_potrosenog_novca) {
        this.korisnik_id = korisnik_id;
        this.ime_i_prezime = ime_i_prezime;
        this.username = username;
        this.e_mail = e_mail;
        this.datum_rodjenja = datum_rodjenja;
        this.stanje_racuna = stanje_racuna;
        this.kolicina_potrosenog_novca = kolicina_potrosenog_novca;
    }

    
    
    public int getKorisnikID() {
        return korisnik_id;
    }

    public String getImeIPrezime() {
        return ime_i_prezime;
    }
    
    
    public void setImeIPrezime(String ime_i_prezime) {
        this.ime_i_prezime = ime_i_prezime;
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return e_mail;
    }

    public void setEmail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getDatumRodjenja() {
        return datum_rodjenja;
    }

    public void setDatum_rodjenja(String datum_rodjenja) {
        this.datum_rodjenja = datum_rodjenja;
    }

    public int getStanjeRacuna() {
        return stanje_racuna;
    }

    public int getKolicinaPotrosenogNovca() {
        return kolicina_potrosenog_novca;
    }
    
    // da li treba i setter za stanjeRacuna i KolicinaPotrosenogNovca
 
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Korisnik{").append("ime_i_prezime=").append(ime_i_prezime).append(", username=").append(username)
        .append(", e_mail=").append(e_mail).append(", datum_rodjenja=").append(datum_rodjenja)
        .append(", stanje_racuna=").append(stanje_racuna).append(", kolicina_potrosenog_novca=").append(kolicina_potrosenog_novca)
        .append("}");
        return sb.toString();
    }
}
