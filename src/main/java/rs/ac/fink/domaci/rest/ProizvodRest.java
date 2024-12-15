package rs.ac.fink.domaci.rest;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import rs.ac.fink.domaci.dao.ProizvodDAO;
import rs.ac.fink.domaci.data.Proizvod;
import rs.ac.fink.domaci.exception.RacunarskaOpremaException;
import rs.ac.fink.domaci.service.ProizvodService;


@Path("proizvod")
public class ProizvodRest {
    private final ProizvodService proizvodService = ProizvodService.getInstance();
    
    // lista svih proizvoda
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Proizvod> getAllProizvodi() throws RacunarskaOpremaException {
        return ProizvodDAO.getAllProizvodi();  
    }
    
    // detalji o proizvodu
    @GET
    @Path("/{naziv}")
    @Produces(MediaType.APPLICATION_JSON)
    public Proizvod getProizvodByName(@PathParam("naziv") String naziv) throws RacunarskaOpremaException {
        return proizvodService.findProduct(naziv);
    }
    
    // Pretraga proizvoda sa filterima
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Proizvod> searchProizvodi(
        @javax.ws.rs.QueryParam("minCena") Double minCena,
        @javax.ws.rs.QueryParam("maxCena") Double maxCena,
        @javax.ws.rs.QueryParam("vrstaOpreme") String vrstaOpreme,
        @javax.ws.rs.QueryParam("naziv") String naziv
    ) throws RacunarskaOpremaException {
        return proizvodService.searchProizvodi(minCena, maxCena, vrstaOpreme, naziv);
    }

}
