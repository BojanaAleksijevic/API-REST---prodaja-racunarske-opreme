package rs.ac.fink.domaci.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import rs.ac.fink.domaci.dao.ProizvodDAO;
import rs.ac.fink.domaci.dao.ResourcesManager;
import rs.ac.fink.domaci.data.Proizvod;
import rs.ac.fink.domaci.exception.RacunarskaOpremaException;

public class ProizvodService {
    private static final ProizvodService instance = new ProizvodService();

    private ProizvodService() {
    }

    public static ProizvodService getInstance() {
        return instance;
    }
    
    public List<Proizvod> getAllProizvodi() throws RacunarskaOpremaException {
        return ProizvodDAO.getAllProizvodi();
    }
    
    public Proizvod findProduct(String naziv) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return ProizvodDAO.getInstance().find(naziv, con);

        } catch (SQLException ex) {
            throw new RacunarskaOpremaException("Failed to find product with name " + naziv, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Proizvod> searchProizvodi(Double minCena, Double maxCena, String vrstaOpreme, String naziv) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return ProizvodDAO.getInstance().searchProizvodi(minCena, maxCena, vrstaOpreme, naziv, con);
        } catch (SQLException ex) {
            throw new RacunarskaOpremaException("Failed to search products", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
