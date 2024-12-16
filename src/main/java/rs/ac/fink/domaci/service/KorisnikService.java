package rs.ac.fink.domaci.service;

import java.sql.Connection;
import java.sql.SQLException;
import rs.ac.fink.domaci.dao.KorisnikDAO;
import rs.ac.fink.domaci.dao.ResourcesManager;
import rs.ac.fink.domaci.data.Korisnik;
import rs.ac.fink.domaci.exception.RacunarskaOpremaException;

public class KorisnikService {
    private static final KorisnikService instance = new KorisnikService();
    
    private KorisnikService(){
        
    }
    
    public static KorisnikService getInstance() {
        return instance;
    }
    
    public void addNewKorisnik(Korisnik korisnik) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            //more than one SQL statement to execute, needs to be a single transaction
            con.setAutoCommit(false);

            KorisnikDAO.getInstance().insert(korisnik, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new RacunarskaOpremaException("Failed to add new customer " + korisnik, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
  
    public Korisnik findKorisnik(String username) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return KorisnikDAO.getInstance().find(username, con);

        } catch (SQLException ex) {
            throw new RacunarskaOpremaException("Failed to find customer with username " + username, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteKorisnik(String username) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Korisnik korisnik = KorisnikDAO.getInstance().find(username, con);
            if (korisnik != null) {
                KorisnikDAO.getInstance().delete(username, con);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new RacunarskaOpremaException("Failed to delete korisnik with username " + username, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    
    /*proba*/
    public void updateKorisnik(Korisnik korisnik) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            KorisnikDAO.getInstance().update(korisnik, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new RacunarskaOpremaException("Failed to update korisnik " + korisnik, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public String login(String username, String password) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return KorisnikDAO.getInstance().login(username, password, con);
        } catch (SQLException ex) {
            throw new RacunarskaOpremaException("Login failed for username " + username, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
