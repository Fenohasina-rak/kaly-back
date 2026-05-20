package Repositories;


import Models.Session.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

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

    public Optional<User> findUserByrefreshToken(String token){
        return User.find("refreshToken = ?1", token).firstResultOptional();
    }

    @Transactional
    public void updateRefreshToken(User user, String refreshToken){
        User managedUser = User.getEntityManager().merge(user);
        managedUser.refreshToken = refreshToken;
    }

    @Transactional
    public void deleteRefreshToken(User user){
        User managedUser = User.getEntityManager().merge(user);
        managedUser.refreshToken = null;
    }
}
