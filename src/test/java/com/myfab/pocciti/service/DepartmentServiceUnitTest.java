package com.myfab.pocciti.service;


import com.myfab.pocciti.exception.DepartmentNotFoundException;
import com.myfab.pocciti.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DepartmentServiceUnitTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    public DepartmentServiceUnitTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void throwsExceptionWhenDepartmentNotExists() {
        when(departmentRepository.existsById("U003"))
                .thenReturn(false);
        assertThatCode(() -> departmentService.updateDepartment("U003", "IT"))
                .isInstanceOf(DepartmentNotFoundException.class)
                .hasMessage("Department does not exist!");
        verify(departmentRepository).existsById("U003");
    }

    @Test
    void shouldUpdateWhenDepartmentExists(){
        when(departmentRepository.existsById("U004"))
                .thenReturn(true);
        when(departmentRepository.updateDepartment("U004","CSE"))
                .thenReturn(1);
        assertThat(departmentService.updateDepartment("U004","CSE"))
                .isEqualTo("Update successful!");
        verify(departmentRepository).updateDepartment("U004","CSE");
    }
    @Test
    void shouldThrowErrorIfDatabaseOperationFails(){
        when(departmentRepository.existsById("U005"))
                .thenReturn(true);
        when(departmentRepository.updateDepartment("U005","EE"))
                .thenReturn(0);
        assertThatCode(() -> departmentService.updateDepartment("U005", "EE"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Update operation failed!");
        verify(departmentRepository).updateDepartment("U005","EE");
    }
}