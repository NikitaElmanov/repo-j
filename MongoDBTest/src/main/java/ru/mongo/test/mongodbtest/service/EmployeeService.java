package ru.mongo.test.mongodbtest.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.mongo.test.mongodbtest.dto.EmployeeDtoRequest;
import ru.mongo.test.mongodbtest.dto.EmployeeDtoResponse;
import ru.mongo.test.mongodbtest.model.Employee;
import ru.mongo.test.mongodbtest.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeDtoResponse save(EmployeeDtoRequest dto) {
        Employee employee = employeeRepository.save(requestDtoToEmployee(dto));
        return employeeToResponseDto(employee);
    }

    private EmployeeDtoResponse employeeToResponseDto(Employee employee) {
        EmployeeDtoResponse dto = new EmployeeDtoResponse();
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setSalary(employee.getSalary());
        dto.setAddress(employee.getAddress());
        dto.setJoiningDate(LocalDate.now());

        return dto;
    }

    private Employee requestDtoToEmployee(EmployeeDtoRequest dto) {
        Employee employee = new Employee();
        employee.setId(UUID.randomUUID());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setSalary(dto.getSalary());
        employee.setAddress(dto.getAddress());
        employee.setJoiningDate(LocalDate.now());

        return employee;
    }

    public List<EmployeeDtoResponse> getAll() {
        return employeeRepository.getAll().stream()
                .map(this::employeeToResponseDto)
                .collect(Collectors.toList());
    }

    public EmployeeDtoResponse update(EmployeeDtoRequest dto) {
        List<Employee> employees = getEmployeeByName(dto.getFirstName());
        if (employees.size() == 0) {
            return null;
        }
        Employee employee = requestDtoToEmployee(dto);
        employee.setId(employees.get(0).getId());
        return employeeToResponseDto(employeeRepository.save(employee));
    }

    public long delete(EmployeeDtoRequest dto) {
        List<Employee> employees = getEmployeeByName(dto.getFirstName());
        return employees.size() > 0 ? employeeRepository.delete(employees.get(0)) : 0;
    }

    public List<EmployeeDtoResponse> getByName(String firstName) {
        return employeeRepository.getByFirstName(firstName).stream()
                .map(this::employeeToResponseDto)
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeeByName(String firstName) {
        return employeeRepository.getByFirstName(firstName);
    }

    public Map<String, Object> getEmployeesByPage(int pageNo, int pageSize, String sortBy) {
        return employeeRepository.getEmployeesByPage(pageNo, pageSize, sortBy);
    }

    public Map<String, Object> getAggregationSalaryCount() {
        return employeeRepository.getAggregationSalaryCount();
    }
}
