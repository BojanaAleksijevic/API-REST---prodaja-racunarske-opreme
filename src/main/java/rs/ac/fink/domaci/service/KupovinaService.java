package rs.ac.fink.domaci.service;

import java.sql.Connection;
import java.sql.SQLException;
import rs.ac.fink.domaci.dao.KorisnikDAO;
import rs.ac.fink.domaci.dao.KupovinaDAO;
import rs.ac.fink.domaci.dao.ProizvodDAO;
import rs.ac.fink.domaci.dao.ResourcesManager;
import rs.ac.fink.domaci.data.Korisnik;
import rs.ac.fink.domaci.data.Kupovina;
import rs.ac.fink.domaci.data.Proizvod;
import rs.ac.fink.domaci.exception.RacunarskaOpremaException;


public class KupovinaService {
    
    private static final KupovinaService instance = new KupovinaService();

    private KupovinaService() {
    }

    public static KupovinaService getInstance() {
        return instance;
    }
    
    public void makeKupovina(Korisnik korisnik, Proizvod proizvod) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            
            Korisnik k = KorisnikDAO.getInstance().findID(korisnik.getKorisnikID(), con);
            if (k == null) {
                throw new RacunarskaOpremaException("Korisnik sa ID-jem " + korisnik.getKorisnikID() + " ne postoji.");
            }

            Proizvod p = ProizvodDAO.getInstance().findID(proizvod.getProizvodID(), con);
            if (p == null) {
                throw new RacunarskaOpremaException("Proizvod sa ID-jem " + proizvod.getProizvodID() + " ne postoji.");
            }
            
            System.out.println("Stanje korisnika: " + k.getStanjeRacuna());
            System.out.println("Cena proizvoda: " + p.getCena());
            System.out.println("Stanje na lageru: " + p.getStanjeNaLageru());



            // Provera lagera i stanja racuna
            if (p.getStanjeNaLageru() == 0) {
                throw new RacunarskaOpremaException("There are no more products " + p.getNaziv() + " in the store.");
            }

            if (k.getStanjeRacuna() < p.getCena()) {
                throw new RacunarskaOpremaException("Customer " + k.getUsername() + " doesn't have enough credit to make a purchase. Customer's credit is " + k.getStanjeRacuna() + ", price of the product is " + p.getCena());
            }

            
            //umanji stanje na racunu kupca i povecaj kolicinu potrosenog novca
            int novoStanjeRacuna = k.getStanjeRacuna() - p.getCena();
            int novaKolicinaPotrosenog = k.getKolicinaPotrosenogNovca() + p.getCena();
            
            k.setStanjeRacuna(novoStanjeRacuna);
            k.setKolicinaPotrosenogNovca(novaKolicinaPotrosenog);
            KorisnikDAO.getInstance().update(k, con);

            //smanjiti kolicinu proizvoda koji je prodat
            p.setStanjeNaLageru(p.getStanjeNaLageru() - 1);
            ProizvodDAO.getInstance().update(p, con);

            KupovinaDAO.getInstance().insert(k, p, con);

            con.commit();

        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new RacunarskaOpremaException("Failed to make a purchase.", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    
    
    
    public Kupovina findKupovina(int kupovina_id) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return KupovinaDAO.getInstance().find(kupovina_id, con);

        } catch (SQLException ex) {
            throw new RacunarskaOpremaException("Failed to find " + kupovina_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
