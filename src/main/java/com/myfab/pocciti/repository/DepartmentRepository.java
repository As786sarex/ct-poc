package com.myfab.pocciti.repository;

import com.myfab.pocciti.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Department set deptName=:deptName where deptId like :id")
    int updateDepartment(final String id, final String deptName);
}
