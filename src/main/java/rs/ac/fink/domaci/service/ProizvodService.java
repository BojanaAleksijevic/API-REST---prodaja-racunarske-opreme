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
    
    public static List<Proizvod> getAllProizvodi() throws RacunarskaOpremaException {
        return ProizvodDAO.getAllProizvodi();  // Pozivanje metode iz DAO klase
    }
    
    public static Proizvod findProduct(int proizvod_id) throws RacunarskaOpremaException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return ProizvodDAO.getInstance().find(proizvod_id, con);

        } catch (SQLException ex) {
            throw new RacunarskaOpremaException("Failed to find product with name " + proizvod_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
