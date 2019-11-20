package pl.coderslab.app.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByFirstName(String firstName);
    Optional<User> findByLogin(String login);
    Optional<User> findByMail(String mail);

    @Modifying
    @Query("update User u set u.firstName=:newFirstName, u.lastName=:newLastName where u.id =:id")
    void customUpdate(@Param("newFirstName") String newFirstName, @Param("newLastName") String newLastName, @Param("id") Long id);

    void deleteById(Long id);
}
