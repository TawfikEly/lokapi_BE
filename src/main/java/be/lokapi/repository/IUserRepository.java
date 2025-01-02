package be.lokapi.repository;

import be.lokapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u.email FROM User u WHERE u.email = :email AND u.deleteDate is null")
    Optional<User> findUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.deleteDate is null")
    Optional<User> findUserByName(@Param("username") String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :email")// check si il exist deja une adresse mail
    boolean existsByEmail(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username")// check si il exist deja un username
    boolean existsByUsername(@Param("username") String username);


    @Query("SELECT u FROM User u WHERE u.username = :identifier OR u.email= :identifier AND u.deleteDate is null")
    Optional<User> findByUsernameOrEmail(@Param("identifier") String identifier);
}
