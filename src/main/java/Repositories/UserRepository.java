package Repositories;


import Models.Session.User;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserRepository {
    public List<User> listAllUsers(){
        return User.listAll();
    }
    public Optional<User> findUserByUserName(String username){
        return User.find("username", username).firstResultOptional();
    }
}
