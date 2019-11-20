package pl.coderslab.app.user;

import java.util.Optional;
import java.util.List;

public interface UserService {

    User findByUserName(String name);
    void saveUser(User user);
     void update2(User user) throws UserNotFoundException;
     void deleteByUserId(Long id);
    User findByLogin(String login) throws UserNotFoundException;
    Optional<User> findByMail(String mail);
    List<User> findAll();
}
