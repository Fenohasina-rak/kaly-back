package Services;

import Models.Output.AuthenticationOutput;
import Models.Output.LogoutOutput;
import Models.Output.UserOutput;
import Models.Entities.User;
import Repositories.UserRepository;
import Utils.HashingUtils;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.Optional;

@ApplicationScoped
public class SessionService {
    private static final String ISSUER = ConfigProvider.getConfig().getValue("mp.jwt.verify.issuer", String.class);
    private static final String TOKEN_TYPE = ConfigProvider.getConfig().getValue("jwt.type", String.class);
    private static final Integer TOKEN_EXPIRATION = ConfigProvider.getConfig().getValue("jwt.expiration", Integer.class);
    private UserRepository userRepository;
    private HashingUtils hashingUtils;
    @Inject
    public SessionService(UserRepository userRepository, HashingUtils hashingUtils) {
        this.userRepository = userRepository;
        this.hashingUtils = hashingUtils;
    }

    public AuthenticationOutput authenticateUser(String username, String password){
        AuthenticationOutput authenticationOutput = new AuthenticationOutput();
        Optional<User> userOptional = userRepository.findUserByUserName(username);

        if(userOptional.isPresent()) {
            User user = userOptional.get();
            if(HashingUtils.checkPassword(password, user.password)){
                setAuthResponse(userOptional, authenticationOutput);
            } else {
                authenticationOutput.setAuthenticated(false);
                authenticationOutput.setComment("Authenticated failed");
            }
        } else {
            authenticationOutput.setAuthenticated(false);
            authenticationOutput.setComment("Authenticated failed");
        }

        return authenticationOutput;
    }

    public AuthenticationOutput authenticateByRefreshToken(String refreshToken){
        AuthenticationOutput authenticationOutput = new AuthenticationOutput();
        Optional<User> userOptional = userRepository.findUserByrefreshToken(refreshToken);
        JsonWebToken decodedToken = hashingUtils.getTokenObject(refreshToken);

        if(userOptional.isPresent() && decodedToken != null) {
            setAuthResponse(userOptional, authenticationOutput);
        } else {
            authenticationOutput.setAuthenticated(false);
            authenticationOutput.setComment("Authenticated failed");
        }
        return authenticationOutput;
    }

    public LogoutOutput logout(String username){
        LogoutOutput logoutOutput = new LogoutOutput();
        logoutOutput.setIsLoggedOut("false");
        Optional<User> userOptional = userRepository.findUserByUserName(username);
        if(userOptional.isPresent()){
            userRepository.deleteRefreshToken(userOptional.get());
            logoutOutput.setComment("User " + userOptional.get().username + "logged out successfully");
            logoutOutput.setIsLoggedOut("true");
        }
        return  logoutOutput;
    }

    private void setAuthResponse(Optional<User> userOptional, AuthenticationOutput authenticationOutput) {
        User user = userOptional.get();
        authenticationOutput.setAuthenticated(true);
        authenticationOutput.setComment("Authenticated");
        String token = Jwt.issuer(ISSUER)
                .upn(user.username)
                .groups(user.role)
                .expiresIn(TOKEN_EXPIRATION)
                .claim("userId", user.id)
                .sign();
        UserOutput userOutput = new UserOutput();
        userOutput.setToken(token);
        userOutput.setType(TOKEN_TYPE);
        userOutput.setUsername(user.username);
        userOutput.setExpirationTime(TOKEN_EXPIRATION);
        String newRefreshToken = Jwt.issuer(ISSUER)
                .upn(user.username)
                .groups(user.role)
                .expiresIn(3600)
                .claim("userId", user.id)
                .sign();
        userOutput.setRefreshToken(newRefreshToken);
        userRepository.updateRefreshToken(user, newRefreshToken);
        authenticationOutput.setUser(userOutput);
    }
}
