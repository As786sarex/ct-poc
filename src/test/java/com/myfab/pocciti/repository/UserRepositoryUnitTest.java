package com.myfab.pocciti.repository;


import com.myfab.pocciti.model.Department;
import com.myfab.pocciti.model.User;
import com.myfab.pocciti.model.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DataJpaTest
class UserRepositoryUnitTest {

    private final UserRepository userRepository;

    @Autowired
    UserRepositoryUnitTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void shouldUpdateWhenCorrectInputProvided() {
        User user = new User("U1001", "Asif", "Mondal", UserRole.STUDENT, "D004");
        this.userRepository.save(user);
        final int count = this.userRepository.updateUserName("U1001", "John", "Doe");
        assertThat(count).isEqualTo(1);
        Optional<User> department = this.userRepository.findById("U1001");
        assertThatCode(department::get).doesNotThrowAnyException();
        assertThat(department.get().getFirstName()).isEqualTo("John");
    }

    @Test
    void throwExceptionWhenUserDoesNotExists(){
        final int count = this.userRepository.updateUserName("U1002", "Jane","Doe");
        assertThat(count).isEqualTo(0);
        final Optional<User> department = this.userRepository.findById("D003");
        assertThat(department).isEqualTo(Optional.empty());
    }
}