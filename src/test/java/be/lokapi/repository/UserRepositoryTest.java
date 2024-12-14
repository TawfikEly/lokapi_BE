
package be.lokapi.repository;

import be.lokapi.entity.User;
import be.lokapi.model.UserDTO;
import be.lokapi.service.impl.UserServiceImpl;
import be.lokapi.utils.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UserRepositoryTest {
    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("password123");
       // user.setRoles(UserDTO.RolesEnum.TENANT);
        user.setCreationDate(DateUtil.toLocalDate(new Date()));
    }

    @Test
    void testGetUserByEmail() {
        // Arrange
        when(userRepository.findUserByEmail("test@example.com")).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.getUserByEmail("test@example.com");

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo("test@example.com");
        verify(userRepository, times(1)).findUserByEmail("test@example.com");
    }

}

