package pl.coderslab.app.user;

import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pl.coderslab.app.role.Role;
import pl.coderslab.app.role.RoleRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public User findByUserName(String name) {
        return userRepository.findByFirstName(name);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        Role userRole = roleRepository.findByRoleName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
    public User findByLogin(String login) throws UserNotFoundException {
        return userRepository.findByLogin(login).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Optional<User> findByMail(String mail) {
        return userRepository.findByMail(mail);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void update(String newFirstName, String newLastName, Long id) {
        userRepository.customUpdate(newFirstName, newLastName, id);
    }


    public void update2(User user) throws UserNotFoundException {
        User userDB = userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new);
        userDB.setFirstName(user.getFirstName());
        userDB.setLastName(user.getLastName());
    }
    public void delete(User user) {
        userRepository.deleteById(user.getId());
    }
    public void deleteByUserId(Long id) {
        userRepository.deleteById(id);
    }
}
