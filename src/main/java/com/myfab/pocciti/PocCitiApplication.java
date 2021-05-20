package com.myfab.pocciti;

import com.myfab.pocciti.model.Department;
import com.myfab.pocciti.model.User;
import com.myfab.pocciti.model.UserRole;
import com.myfab.pocciti.repository.DepartmentRepository;
import com.myfab.pocciti.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@SpringBootApplication
@EnableSwagger2
public class PocCitiApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    public PocCitiApplication(DepartmentRepository departmentRepository, UserRepository userRepository) {
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PocCitiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("U001", "Asif", "Mondal", UserRole.STUDENT, "D1001");
        User user2 = new User("U002", "Jane", "Doe", UserRole.STUDENT, "D1002");
        User user3 = new User("U003", "John", "Doe", UserRole.TEACHER, "D1001");
        User user4 = new User("U004", "Indiana", "Jones", UserRole.ADMIN, "D1002");

        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4));

        Department department1 = new Department("D1001", "CSE");
        Department department2 = new Department("D1002", "ECE");
        Department department3 = new Department("D1003", "EE");

        departmentRepository.saveAll(Arrays.asList(department1, department3, department2));
    }
}
