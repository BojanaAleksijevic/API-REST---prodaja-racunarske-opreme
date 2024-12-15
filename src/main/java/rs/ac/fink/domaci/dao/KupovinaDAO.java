package rs.ac.fink.domaci.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import rs.ac.fink.domaci.data.Korisnik;
import rs.ac.fink.domaci.data.Kupovina;
import rs.ac.fink.domaci.data.Proizvod;

public class KupovinaDAO {
    private static final KupovinaDAO instance = new KupovinaDAO();

    private KupovinaDAO() {
    }

    public static KupovinaDAO getInstance() {
        return instance;
    }
    
    public Kupovina find(int kupovina_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Kupovina kupovina = null;
        try {
            ps = con.prepareStatement("SELECT * FROM kupovina where kupovina_id=?");
            ps.setInt(1, kupovina_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Korisnik korisnik = KorisnikDAO.getInstance().find(rs.getString("korisnik_id"), con);
                Proizvod proizvod = ProizvodDAO.getInstance().find(rs.getString("proizvod_id"), con);
                kupovina = new Kupovina(kupovina_id, korisnik, proizvod);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return kupovina;
    }
    
    public int insert(Kupovina kupovina, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO kupovina(korisnik_id, proizvod_id) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, kupovina.getKorisnik().getUsername());
            ps.setInt(2, kupovina.getProizvod().getProizvodID());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }
    
    public void delete(Korisnik korisnik, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM kupovina WHERE korisnik_id=?");
            ps.setString(1, korisnik.getUsername());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public void delete(Proizvod proizvod, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM kupovina WHERE proizvod_id=?");
            ps.setInt(1, proizvod.getProizvodID());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
