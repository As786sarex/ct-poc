package com.myfab.pocciti.service;

import com.myfab.pocciti.exception.DepartmentNotFoundException;
import com.myfab.pocciti.model.Department;
import com.myfab.pocciti.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return this.departmentRepository.findAll();
    }

    public Optional<Department> getAllDepartmentsById(final String id) {
        return this.departmentRepository.findById(id);
    }

    public String updateDepartment(final String id, final String deptName) {
        boolean isDeptExists = this.departmentRepository.existsById(id);
        if (!isDeptExists) {
            throw new DepartmentNotFoundException();
        }
        if (this.departmentRepository.updateDepartment(id, deptName) > 0)
            return "Update successful!";
        else
            throw new RuntimeException("Update operation failed!");
    }
}
