package rs.ac.fink.domaci.data;

import java.io.Serializable;


public class Proizvod implements Serializable{
    private int proizvod_id=-1;
    private String naziv;
    private int cena;
    private String vrsta_opreme;
    private int stanje_na_lageru;

    public Proizvod() {
    }
    
    public Proizvod(int proizvod_id, String naziv, int cena, String vrsta_opreme, int stanje_na_lageru) {
        this.proizvod_id = proizvod_id;
        this.naziv = naziv;
        this.cena = cena;
        this.vrsta_opreme = vrsta_opreme;
        this.stanje_na_lageru = stanje_na_lageru;
    }

    public int getProizvodID() {
        return proizvod_id;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getCena() {
        return cena;
    }

    public String getVrstaOpreme() {
        return vrsta_opreme;
    }

    public int getStanjeNaLageru() {
        return stanje_na_lageru;
    }

    public void setProizvodID(int proizvod_id) {
        this.proizvod_id = proizvod_id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public void setVrstaOpreme(String vrsta_opreme) {
        this.vrsta_opreme = vrsta_opreme;
    }

    public void setStanjeNaLageru(int stanje_na_lageru) {
        this.stanje_na_lageru = stanje_na_lageru;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Proizvod{").append("naziv=").append(naziv).append(", cena=").append(cena)
        .append(", vrsta_opreme=").append(vrsta_opreme).append(", stanje_na_lageru=").append(stanje_na_lageru)
        .append("}");
        return sb.toString();
    }
    
    
}
