package org.cuong.employeemvccrud.repositoty;

import org.cuong.employeemvccrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    @Query("from Employee order by lastName asc")
    public List<Employee> findAllByOrderByLastNameAsc();
}
