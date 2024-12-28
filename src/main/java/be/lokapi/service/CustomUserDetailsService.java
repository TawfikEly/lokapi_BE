package be.lokapi.service;

import be.lokapi.entity.User;
import be.lokapi.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.findByUsernameOrEmail(usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + usernameOrEmail));
         return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());

    }
}
