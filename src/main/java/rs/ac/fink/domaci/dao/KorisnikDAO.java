package rs.ac.fink.domaci.dao;


public class KorisnikDAO {
    private static final KorisnikDAO instance = new KorisnikDAO();
    
    private KorisnikDAO() {
        
    }
    
    public static KorisnikDAO getInstance() {
        return instance;
    }
    
    
    
}
