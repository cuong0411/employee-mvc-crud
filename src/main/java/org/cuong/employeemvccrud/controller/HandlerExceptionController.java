package org.cuong.employeemvccrud.controller;


import org.cuong.employeemvccrud.exception.EmployeeNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@ControllerAdvice
public class HandlerExceptionController {

//    @ExceptionHandler(EmployeeNotFound.class)
//    public ResponseEntity<String> findEmployeeById(EmployeeNotFound ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }

    @ExceptionHandler(EmployeeNotFound.class)
    @GetMapping("/employee-not-found")
    public String employeeNotFoundById(EmployeeNotFound ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "errors/employee-not-found";
    }
}
