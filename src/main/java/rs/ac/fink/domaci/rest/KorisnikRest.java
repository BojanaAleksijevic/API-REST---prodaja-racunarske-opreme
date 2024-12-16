package rs.ac.fink.domaci.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rs.ac.fink.domaci.data.Korisnik;
import rs.ac.fink.domaci.exception.RacunarskaOpremaException;
import rs.ac.fink.domaci.service.KorisnikService;


@Path("korisnik")
public class KorisnikRest {
    private final KorisnikService korisnikService = KorisnikService.getInstance();
    
    // detalji o korisniku
    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik getKorisnik(@PathParam("username") String username) throws RacunarskaOpremaException {
        return korisnikService.findKorisnik(username);
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addKorisnik(Korisnik korisnik) throws RacunarskaOpremaException{
        korisnikService.addNewKorisnik(korisnik);
        return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateKorisnika(Korisnik korisnik) throws RacunarskaOpremaException {
            korisnikService.updateKorisnik(korisnik);
            return Response.ok().build();
    }
    
    
    // login
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Korisnik korisnik) throws RacunarskaOpremaException {
        String result = korisnikService.login(korisnik.getUsername(), korisnik.getPassword());
        if (result != null) {
            return Response.ok(result).build(); 
        }
        return null;
    }
}
