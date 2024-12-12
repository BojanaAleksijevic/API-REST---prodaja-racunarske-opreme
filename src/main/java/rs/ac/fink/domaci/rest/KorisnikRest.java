package rs.ac.fink.domaci.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import rs.ac.fink.domaci.data.Korisnik;
import rs.ac.fink.domaci.exception.RacunarskaOpremaException;
import rs.ac.fink.domaci.service.KorisnikService;


@Path("korisnik")
public class KorisnikRest {
    private final KorisnikService korisnikService = KorisnikService.getInstance();
    
    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik getCustomerById(@PathParam("username") String username) throws RacunarskaOpremaException {
        return korisnikService.findCustomer(username);
    }
    
    
}
