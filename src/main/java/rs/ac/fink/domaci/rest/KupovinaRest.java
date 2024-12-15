package rs.ac.fink.domaci.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rs.ac.fink.domaci.data.Kupovina;
import rs.ac.fink.domaci.exception.RacunarskaOpremaException;
import rs.ac.fink.domaci.service.KupovinaService;

@Path("kupovina")
public class KupovinaRest {
    private final KupovinaService kupovinaService = KupovinaService.getInstance();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeKupovina(Kupovina kupovina) throws RacunarskaOpremaException{
        kupovinaService.makeKupovina(kupovina.getKorisnik(), kupovina.getProizvod());
        return Response.ok().build();
    }
}
