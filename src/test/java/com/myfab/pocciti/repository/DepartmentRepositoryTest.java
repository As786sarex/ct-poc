package com.myfab.pocciti.repository;

import com.myfab.pocciti.model.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DataJpaTest
class DepartmentRepositoryTest {


    private final DepartmentRepository departmentRepository;

    @Autowired
    DepartmentRepositoryTest(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
        departmentRepository.save(new Department("D005", "CSE"));
    }

    @Test
    void updateDepartmentSuccessful() {
        final int count = this.departmentRepository.updateDepartment("D005", "IT");
        assertThat(count).isEqualTo(1);
        Optional<Department> department = this.departmentRepository.findById("D005");
        assertThatCode(department::get).doesNotThrowAnyException();
        assertThat(department.get().getDeptName()).isEqualTo("IT");
    }

    @Test
    void updateUnsuccessfulWhenNotAvailable() {
        final int count = this.departmentRepository.updateDepartment("D003", "IT");
        assertThat(count).isEqualTo(0);
        final Optional<Department> department = this.departmentRepository.findById("D003");
        assertThat(department).isEqualTo(Optional.empty());
    }
}