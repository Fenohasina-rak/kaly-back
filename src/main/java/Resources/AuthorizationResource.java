package Resources;

import Services.SessionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/api")
public class AuthorizationResource {
    private SessionService sessionService;
    @Inject
    public AuthorizationResource(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GET
    @Path("/auth")
    public void authenticate() {

    }
}
