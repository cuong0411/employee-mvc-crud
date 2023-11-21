package org.cuong.employeemvccrud.service;

import org.cuong.employeemvccrud.entity.Employee;
import org.cuong.employeemvccrud.exception.EmployeeNotFound;
import org.cuong.employeemvccrud.repositoty.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepo repo;

    public EmployeeServiceImpl(EmployeeRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<Employee> getAllEmployees() {
//        return repo.findAll();
        return repo.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee getEmployeeById(int id) {
        var emp = repo.findById(id);
        if (emp.isEmpty()) {
            throw new EmployeeNotFound("Employee with id " + id + " not found!");
        }
        return emp.get();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return repo.save(employee);
    }

    @Override
    public void deleteEmployeeById(int id) {
        repo.deleteById(id);
    }
}
