package rs.ac.fink.domaci.rest;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import rs.ac.fink.domaci.data.Proizvod;
import rs.ac.fink.domaci.exception.RacunarskaOpremaException;
import rs.ac.fink.domaci.service.ProizvodService;


@Path("proizvod")
public class ProizvodRest {
    private final ProizvodService customerService = ProizvodService.getInstance();
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Proizvod> getAllProizvodi() throws RacunarskaOpremaException {
        return ProizvodService.getAllProizvodi();  
    }
    
    @GET
    @Path("/{proizvod_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Proizvod getProductByName(@PathParam("proizvod_id") int proizvod_id) throws RacunarskaOpremaException {
        return ProizvodService.findProduct(proizvod_id);
    }
    
}
