package com.myfab.pocciti.service;

import com.myfab.pocciti.exception.UserNotFoundException;
import com.myfab.pocciti.model.User;
import com.myfab.pocciti.model.UserRole;
import com.myfab.pocciti.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserServiceUnitTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void updateSuccessfulWhenUserExists() {
        when(userRepository.updateUserName(anyString(), anyString(), anyString()))
                .thenReturn(1);
        when(userRepository.existsById(anyString()))
                .thenReturn(true);
        assertThat(userService.updateUser("U1001", "John", "Doe"))
                .isEqualTo("Update successful!");
        verify(userRepository).updateUserName("U1001", "John", "Doe");
        verify(userRepository).existsById("U1001");
    }

    @Test
    void throwsExceptionWhenUserDoesNotExist() {
        when(userRepository.existsById(anyString()))
                .thenReturn(false);
        assertThatCode(() -> userService.updateUser("U1002", "Jane", "Doe"))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User does not exists");
        verify(userRepository).existsById("U1002");
        verify(userRepository, never()).updateUserName("U1002", "Jane", "Doe");
    }

    @Test
    void throwsExceptionIfDBFailsToUpdate() {
        when(userRepository.updateUserName(anyString(), anyString(), anyString()))
                .thenReturn(0);
        when(userRepository.existsById(anyString()))
                .thenReturn(true);
        assertThatCode(() -> userService.updateUser("U1002", "Jane", "Doe"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Update operation failed!");
    }

    @Test
    void shouldGetAllUsers() {
        List<User> mockReturns = Arrays.asList(
                new User("U1001", "Jane", "Doe", UserRole.TEACHER, "D002"),
                new User("U1001", "John", "Doe", UserRole.STUDENT, "D001"));
        when(userRepository.findAll())
                .thenReturn(mockReturns);
        assertThat(userService.getAllUsers()).isEqualTo(mockReturns);
        verify(userRepository).findAll();
    }

    @Test
    void shouldGetUserByIdWhenUserExists() {
        User user = new User("U1001", "Jane", "Doe", UserRole.TEACHER, "D002");
        when(userRepository.findById("U1001"))
                .thenReturn(Optional.of(user));
        assertThat(userService.getAllUsersById("U1001"))
                .isEqualTo(Optional.of(user));
        verify(userRepository).findById("U1001");
    }

    @Test
    void shouldGetEmptyWhenUserDoesNotExists() {
        when(userRepository.findById("U1001"))
                .thenReturn(Optional.empty());
        assertThat(userService.getAllUsersById("U1001"))
                .isEqualTo(Optional.empty());
        verify(userRepository).findById("U1001");
    }
}