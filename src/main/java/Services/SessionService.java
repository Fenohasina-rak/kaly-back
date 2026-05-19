package Services;

import Models.Session.Output.AuthenticationOutput;
import Models.Session.Output.UserOutput;
import Models.Session.User;
import Repositories.UserRepository;
import Utils.HashingUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class SessionService {
    private UserRepository userRepository;
    @Inject
    public SessionService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserOutput authenticateUser(String username, String password){
        AuthenticationOutput authenticationOutput = new AuthenticationOutput();
        Optional<User> userOptional = userRepository.findUserByUserName(username);

        if(userOptional.isPresent()) {
            User user = userOptional.get();
            if(HashingUtils.checkPassword(password, user.password)){
                authenticationOutput.setAuthenticated(true);
                authenticationOutput.setComment("Authenticated");
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
}
