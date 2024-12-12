package rs.ac.fink.domaci.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import rs.ac.fink.domaci.data.Korisnik;


public class KorisnikDAO {
    private static final KorisnikDAO instance = new KorisnikDAO();
    
    private KorisnikDAO() {
        
    }
    
    public static KorisnikDAO getInstance() {
        return instance;
    }
    
    public Korisnik find(String username, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Korisnik korisnik = null;
        try {
            ps = con.prepareStatement("SELECT * FROM korisnik where username=?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                korisnik = new Korisnik(rs.getString("ime_i_prezime"), username, rs.getString("e_mail"), rs.getString("datum_rodjenja"), rs.getInt("stanje_racuna"), rs.getInt("kolicina_potrosenog_novca"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return korisnik;
    }
    
    public int insert(Korisnik korisnik, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO korisnik(ime_i_prezime, username, e_mail, datum_rodjenja, stanje_racuna, kolicina_potrosenog_novca) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, korisnik.getImeIPrezime());
            ps.setString(2, korisnik.getUsername());
            ps.setString(3, korisnik.getEmail());
            ps.setString(4, korisnik.getDatumRodjenja());
            ps.setInt(5, korisnik.getStanjeRacuna());
            ps.setInt(6, korisnik.getKolicinaPotrosenogNovca());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }
    
    public void update(Korisnik korisnik, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE korisnik SET ime_i_prezime=?, username=?, e_mail=?, datum_rodjenja=?, stanje_racuna=?, kolicina_potrosenog_novca=?  WHERE korisnik_id=?");
            ps.setString(1, korisnik.getImeIPrezime());
            ps.setString(2, korisnik.getUsername());
            ps.setString(3, korisnik.getEmail());
            ps.setString(4, korisnik.getDatumRodjenja());
            ps.setInt(5, korisnik.getStanjeRacuna());
            ps.setInt(6, korisnik.getKolicinaPotrosenogNovca());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public void delete(String username, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM korisnik WHERE korisnik_id=?");
            ps.setString(1, username);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
}
