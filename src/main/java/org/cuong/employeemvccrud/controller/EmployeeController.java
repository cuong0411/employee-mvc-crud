package org.cuong.employeemvccrud.controller;

import jakarta.validation.Valid;
import org.cuong.employeemvccrud.entity.Employee;
import org.cuong.employeemvccrud.service.EmployeeService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String findAll(Model model) {
        var employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees/list";
    }

    @GetMapping("/create")
    public String showFormForCreate(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/create";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("employee") Employee employee,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "employees/create";
        }
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam int id, Model model) {
        var emp = employeeService.getEmployeeById(id);
        model.addAttribute("employee", emp);
        return "employees/update";
    }

    @GetMapping("/delete")
    public String removeEmployeeById(@RequestParam int id, Model model) {
        var emp = employeeService.getEmployeeById(id);
        model.addAttribute("employee", emp);
        return "employees/delete";
    }

    @PostMapping("/remove")
    public String remove(int id) {
        var emp = employeeService.getEmployeeById(id);
        employeeService.deleteEmployeeById(emp.getId());
        return "redirect:/employees";
    }

}
