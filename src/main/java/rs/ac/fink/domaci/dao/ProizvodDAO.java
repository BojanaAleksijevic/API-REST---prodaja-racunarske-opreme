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
    
    public static Proizvod find(int proizvod_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Proizvod product = null;
        try {
            ps = con.prepareStatement("SELECT * FROM proizvod WHERE proizvod_id=?");
            ps.setInt(1, proizvod_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                product = new Proizvod(proizvod_id, rs.getString("naziv"), rs.getInt("cena"), rs.getString("vrsta_opreme"), rs.getInt("stanje_na_lageru"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return product;
    }
    
}
