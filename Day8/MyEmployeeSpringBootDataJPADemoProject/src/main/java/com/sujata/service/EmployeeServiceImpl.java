package com.sujata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sujata.entity.Employee;
import com.sujata.entity.EmployeePayslip;
import com.sujata.persistence.EmployeeDao;


//@Component
@Service
public class EmployeeServiceImpl implements EmployeeService {

//	private EmployeeDao employeeDao=new EmployeeDaoImpl();
	@Autowired
	private EmployeeDao employeeDao;
	
	
	public EmployeeServiceImpl(EmployeeDao employeeDao) {
	super();
	this.employeeDao = employeeDao;
}

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeDao.findAll();
		
	}

	@Override
	public boolean saveEmployee(Employee employee) {
		Employee emp=employeeDao.save(employee);
		if(emp!=null)
			return true;
		return false;
	}

	@Override
	public Employee getEmployeeById(int id) {
		
		return employeeDao.getById(id);
	}

	/*
	 * Allowances : 25% of Salary
	 * Deductions :18% of Salary
	 */
	@Override
	public EmployeePayslip generateSalary(int empId) {
		Employee employee=employeeDao.getById(empId);
		if(employee!=null) {
		//Business Logic
		double allowances=employee.getEmpSalary()*.25;
		double deductions=employee.getEmpSalary()*.18;
		double totalSalary=employee.getEmpSalary()+allowances-deductions;
		EmployeePayslip employeePayslip=new EmployeePayslip(employee, allowances, deductions, totalSalary);
			return employeePayslip;
		}
		return null;
	}

}
