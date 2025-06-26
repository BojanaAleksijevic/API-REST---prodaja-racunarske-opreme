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
                korisnik = new Korisnik(rs.getInt("korisnik_id"), rs.getString("ime_i_prezime"), username, rs.getString("password"), rs.getString("e_mail"), rs.getString("datum_rodjenja"), rs.getInt("stanje_racuna"), rs.getInt("kolicina_potrosenog_novca"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return korisnik;
    }
    
    public Korisnik findID(int korisnik_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Korisnik korisnik = null;
        try {
            ps = con.prepareStatement("SELECT * FROM korisnik where korisnik_id=?");
            ps.setInt(1, korisnik_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                korisnik = new Korisnik(rs.getInt("korisnik_id"), rs.getString("ime_i_prezime"),rs.getString("username"), rs.getString("password"), rs.getString("e_mail"), rs.getString("datum_rodjenja"), rs.getInt("stanje_racuna"), rs.getInt("kolicina_potrosenog_novca"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return korisnik;
    }
    
    
    
    public void insert(Korisnik korisnik, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        //int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO korisnik(ime_i_prezime, username, password, e_mail, datum_rodjenja, stanje_racuna, kolicina_potrosenog_novca) VALUES(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, korisnik.getImeIPrezime());
            ps.setString(2, korisnik.getUsername());
            ps.setString(3, korisnik.getPassword());
            ps.setString(4, korisnik.getEmail());
            ps.setString(5, korisnik.getDatumRodjenja());
            ps.setInt(6, korisnik.getStanjeRacuna());
            ps.setInt(7, korisnik.getKolicinaPotrosenogNovca());
            
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                korisnik.setKorisnikID(rs.getInt(1));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        //return id;
    }

    
    public void update(Korisnik korisnik, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(
                "UPDATE korisnik SET stanje_racuna=?, kolicina_potrosenog_novca=? WHERE korisnik_id=?"
            );
            ps.setInt(1, korisnik.getStanjeRacuna());
            ps.setInt(2, korisnik.getKolicinaPotrosenogNovca());
            ps.setInt(3, korisnik.getKorisnikID());
            
            ps.executeUpdate();
            
            int updated = ps.executeUpdate();
            System.out.println("Redova ažurirano: " + updated);

        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    
    
    public String login(String username, String password, Connection con) throws SQLException {
        String sql = "SELECT COUNT(*) FROM korisnik WHERE username = ? AND password = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    if (count > 0) {
                        return "true"; // Pronadjen korisnik
                    }
                }
            }
        }
        return "false"; // Korisnik nije pronadjen
    }
    
}
