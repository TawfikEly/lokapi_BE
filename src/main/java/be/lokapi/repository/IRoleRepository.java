package be.lokapi.repository;

import be.lokapi.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<UserRoles, Long> {

    //Optional<UserRoles> findByName(Role role);

}
