package rs.ac.fink.domaci.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import rs.ac.fink.domaci.data.Proizvod;
import rs.ac.fink.domaci.exception.RacunarskaOpremaException;

public class ProizvodDAO {
    private static final ProizvodDAO instance = new ProizvodDAO();

    private ProizvodDAO() {
    }

    public static ProizvodDAO getInstance() {
        return instance;
    }
    
    public static List<Proizvod> getAllProizvodi() throws RacunarskaOpremaException {
        List<Proizvod> proizvodi = new ArrayList<>();
        String sql = "SELECT * FROM proizvod";  
        try (Connection con = ResourcesManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Proizvod p = new Proizvod();
                p.setProizvodID(rs.getInt("proizvod_id"));
                p.setNaziv(rs.getString("naziv"));
                p.setCena(rs.getInt("cena"));
                p.setVrstaOpreme(rs.getString("vrsta_opreme"));
                p.setStanjeNaLageru(rs.getInt("stanje_na_lageru"));
                
                proizvodi.add(p);
            }
        } catch (SQLException ex) {
            throw new RacunarskaOpremaException("Error while fetching proizvodi", ex);
        }
        return proizvodi;
    }
    
    public Proizvod find(String naziv, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Proizvod product = null;
        try {
            ps = con.prepareStatement("SELECT * FROM proizvod WHERE naziv=?");
            ps.setString(1, naziv);
            rs = ps.executeQuery();
            if (rs.next()) {
                product = new Proizvod(rs.getInt("proizvod_id"), naziv, rs.getInt("cena"),rs.getString("vrsta_opreme"), rs.getInt("stanje_na_lageru"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return product;
    }
    
    public void update(Proizvod proizvod, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE proizvod SET naziv=?, cena=?, vrsta_opreme=?, stanje_na_lageru=? WHERE proizvod_id=?");
            ps.setString(1, proizvod.getNaziv());
            ps.setInt(2, proizvod.getCena());
            ps.setString(3, proizvod.getVrstaOpreme());
            ps.setInt(4, proizvod.getStanjeNaLageru());
            ps.setInt(5, proizvod.getProizvodID());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public List<Proizvod> searchProizvodi(Double minCena, Double maxCena, String vrstaOpreme, String naziv, Connection con) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM proizvod WHERE 1=1");

        if (minCena != null) {
            query.append(" AND cena >= ?");
        }
        if (maxCena != null) {
            query.append(" AND cena <= ?");
        }
        if (vrstaOpreme != null && !vrstaOpreme.isEmpty()) {
            query.append(" AND vrsta_opreme = ?");
        }
        if (naziv != null && !naziv.isEmpty()) {
            query.append(" AND naziv LIKE ?");
        }

        try (PreparedStatement ps = con.prepareStatement(query.toString())) {
            int index = 1;

            if (minCena != null) {
                ps.setDouble(index++, minCena);
            }
            if (maxCena != null) {
                ps.setDouble(index++, maxCena);
            }
            if (vrstaOpreme != null && !vrstaOpreme.isEmpty()) {
                ps.setString(index++, vrstaOpreme);
            }
            if (naziv != null && !naziv.isEmpty()) {
                ps.setString(index++, "%" + naziv + "%");
            }

            try (ResultSet rs = ps.executeQuery()) {
                List<Proizvod> proizvodi = new ArrayList<>();
                while (rs.next()) {
                    proizvodi.add(mapResultSetToProizvod(rs));
                }
                return proizvodi;
            }
        }
    }


    private Proizvod mapResultSetToProizvod(ResultSet rs) throws SQLException {
        return new Proizvod(
            rs.getInt("proizvod_id"), 
            rs.getString("naziv"), 
            rs.getInt("cena"), 
            rs.getString("vrsta_opreme"), 
            rs.getInt("stanje_na_lageru")
        );
    }
   
}
