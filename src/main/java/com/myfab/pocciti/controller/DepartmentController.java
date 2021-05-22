package com.myfab.pocciti.controller;

import com.myfab.pocciti.exception.DepartmentNotFoundException;
import com.myfab.pocciti.model.Department;
import com.myfab.pocciti.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/getAllDepartments")
    public Collection<Department> getAllDepartments() {
        return this.departmentService.getAllDepartments();
    }

    @GetMapping(path = "/getDepartment/{id}")
    public Department getDepartmentById(@PathVariable String id) {
        return this.departmentService.getAllDepartmentsById(id).orElseThrow(DepartmentNotFoundException::new);
    }

    @PostMapping(path = "/update/{id}")
    public String updateDepartmentById(@PathVariable String id,
                                           @RequestBody Map<String, String> body) {
        final String deptName = body.get("deptName");
        if (deptName == null || id.isEmpty())
            throw new RuntimeException("Id or deptName is missing!");
        return departmentService.updateDepartment(id, deptName);
    }
}
