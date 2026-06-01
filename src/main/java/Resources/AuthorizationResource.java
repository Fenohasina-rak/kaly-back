package Resources;

import Models.Input.AuthenticationInput;
import Models.Input.RefreshTokenInput;
import Models.Output.AuthenticationOutput;
import Models.Output.CheckAuthenticationOutput;
import Models.Output.LogoutOutput;
import Services.SessionService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.security.Principal;

@Path("/api/auth")
public class AuthorizationResource {
    private JsonWebToken jwt;

    private SessionService sessionService;

    @Inject
    public AuthorizationResource(SessionService sessionService,JsonWebToken jwt ) {
        this.jwt = jwt;
        this.sessionService = sessionService;
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationOutput authenticate(AuthenticationInput inputAuth) {
        return sessionService.authenticateUser(inputAuth.getUsername(), inputAuth.getPassword());
    }

    @GET
    @Path("/check")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public CheckAuthenticationOutput checkUser(@Context SecurityContext securityContext) {
        CheckAuthenticationOutput output = new CheckAuthenticationOutput();
        Principal principal = securityContext.getUserPrincipal();
        output.setUsername(principal.getName());
        output.setIsAuthenticated(Boolean.TRUE);
        return output;
    }

    @POST
    @Path("/refreshtoken")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationOutput authenticateWithToken(RefreshTokenInput refreshToken) {
     return sessionService.authenticateByRefreshToken(refreshToken.getRefreshToken());
    }


    @POST
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public LogoutOutput logout() {
        return sessionService.logout(jwt.getName());
    }


}
