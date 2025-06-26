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
                Korisnik korisnik = KorisnikDAO.getInstance().findID(rs.getInt("korisnik_id"), con);
                Proizvod proizvod = ProizvodDAO.getInstance().findID(rs.getInt("proizvod_id"), con);
                kupovina = new Kupovina(kupovina_id, korisnik, proizvod);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return kupovina;
    }
    
    public int insert(Korisnik korisnik, Proizvod proizvod, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int korisnikId = -1;
        ps = con.prepareStatement("SELECT korisnik_id FROM Korisnik WHERE username = ?");
        ps.setString(1, korisnik.getUsername());
        rs = ps.executeQuery();
        
        if (rs.next()) {
            korisnikId = rs.getInt("korisnik_id");
        }
        try {
            if (korisnikId != -1) {
                ps = con.prepareStatement("INSERT INTO Kupovina(korisnik_id, proizvod_id) VALUES(?,?)");
                ps.setInt(1, korisnikId);
                ps.setInt(2, proizvod.getProizvodID());
                ps.executeUpdate(); 
            }
            else if (korisnikId == -1) {
                throw new SQLException("Username " + korisnik.getUsername() + " is not found.");
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return korisnikId;
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
