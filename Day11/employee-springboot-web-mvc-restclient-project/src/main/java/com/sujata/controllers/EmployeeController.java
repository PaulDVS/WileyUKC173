package com.sujata.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sujata.entity.Employee;
import com.sujata.model.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/")
	public String getMainPageController() {
		return "index";
	}
	
	@RequestMapping("/searchEmployeePage")
	public String getSearchEmployeePage() {
		return "searchEmpPage";
	}
	
	@RequestMapping("/searchEmployee")
//	public ModelAndView searchEmployeeController(HttpServletRequest request) {
	public ModelAndView searchEmployeeController(@RequestParam("empId") int id) {
		ModelAndView modelAndView=new ModelAndView();
//		int id=Integer.parseInt(request.getParameter("empId"));
		Employee employee=employeeService.getEmployeeById(id);
		modelAndView.addObject("employee", employee);
		modelAndView.setViewName("showEmployee");
		return modelAndView;
	}
	
	@RequestMapping("/insertEmployeePage")
	public String insertEmployeePageController() {
		return "inputEmployeeDetails";
	}
	
	@RequestMapping("/saveEmployee")
	public ModelAndView insertEmployeeController(HttpServletRequest request) {
		ModelAndView modelAndView=new ModelAndView();
		
		Employee employee=new Employee();
		employee.setEmpId(Integer.parseInt(request.getParameter("empId")));
		employee.setEmpName(request.getParameter("empName"));
		employee.setEmpDesignation(request.getParameter("empDesignation"));
		employee.setEmpDepartment(request.getParameter("empDepartment"));
		employee.setEmpSalary(Integer.parseInt(request.getParameter("empSalary")));
		
		String message=null;
		if(employeeService.saveEmployee(employee))
			message="Employee Added Succesfully";
		else
			message="Employee Addition Failed";
		
		modelAndView.addObject("message", message);
		modelAndView.setViewName("output");
		
		return modelAndView;
	}
}
