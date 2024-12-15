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

            if (proizvod.getStanjeNaLageru() == 0) {
                throw new RacunarskaOpremaException("There are no more products " + proizvod.getNaziv() + " in the store.");
            }

            if (korisnik.getStanjeRacuna() < proizvod.getCena()) {
                throw new RacunarskaOpremaException("Customer doesn't have enough credit to make a purchase. Customer's credit is " + korisnik.getStanjeRacuna() + ", price of the product is " + proizvod.getCena());
            }

            //umanji stanje na racunu kupca 
            int novoStanjeRacuna = korisnik.getStanjeRacuna() - proizvod.getCena();
            korisnik.setStanjeRacuna(novoStanjeRacuna);
            KorisnikDAO.getInstance().update(korisnik, con);

            //smanjiti kolicinu proizvoda koji je prodat
            proizvod.setStanjeNaLageru(proizvod.getStanjeNaLageru() - 1);
            ProizvodDAO.getInstance().update(proizvod, con);

            //track of purchase in database
            Kupovina kupovina = new Kupovina(korisnik, proizvod);
            KupovinaDAO.getInstance().insert(kupovina, con);

            con.commit();

            System.out.println("Korisnik " + korisnik.getUsername() + " je kupio proizvod " + proizvod.getNaziv() + " koji kosta " + proizvod.getCena());
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new RacunarskaOpremaException("Failed to make a purchase.", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
