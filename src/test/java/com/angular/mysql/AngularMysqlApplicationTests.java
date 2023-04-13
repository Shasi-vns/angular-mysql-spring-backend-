package com.angular.mysql;

import com.angular.mysql.Employee.Employee;
import com.angular.mysql.Employee.EmployeeRepository;
import com.angular.mysql.Employee.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class AngularMysqlApplicationTests {
	@Autowired
	private EmployeeService empSer;
	@MockBean
	private EmployeeRepository empRep;

	@Test
	public void getEmployeeTest(){
		when(empRep.findAll()).thenReturn(Stream.of(new Employee(101,"shashi","shashi52@gmail.com","HR"),
				new Employee(102,"Rishi","rishi@gmail.com","Dev"),new Employee(103,"Uday","udai@gmail.com","Fia"))
				.collect(Collectors.toList()));

		assertEquals(3,empSer.getEmployee().size());
	}

	@Test
	public void getEmployeeByIdTest(){
		Employee e = new Employee(101,"shashi","shashi52@gmail.com","HR");
		Integer id = 101;
		when(empRep.getById(id)).thenReturn(e);
		assertEquals(e,empSer.getEmployeebyId(id));
	}
	@Test
	public void insertEmpTest(){
		Employee e = new Employee(103,"Rajesh","raju@email.com","CSE");
		when(empRep.save(e)).thenReturn(e);
		assertEquals(e,empSer.insertEmp(e));

	}

	@Test
	public void deleteempTest(){
		Employee e = new Employee(103,"Rajesh","raju@email.com","CSE");
		empSer.deleteemp(e.getId());
		verify(empRep,times(1)).deleteById(e.getId());


	}


}
