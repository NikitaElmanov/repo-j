package ru.mongo.test.mongodbtest.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mongo.test.mongodbtest.dto.EmployeeDtoRequest;
import ru.mongo.test.mongodbtest.dto.EmployeeDtoResponse;
import ru.mongo.test.mongodbtest.service.EmployeeService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {

    EmployeeService employeeService;

    @PostMapping
    public EmployeeDtoResponse save(@RequestBody EmployeeDtoRequest dto) {
        return employeeService.save(dto);
    }

    @GetMapping("/all")
    public List<EmployeeDtoResponse> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{firstName}")
    public List<EmployeeDtoResponse> getByName(@PathVariable String firstName) {
        return employeeService.getByName(firstName);
    }

    @PutMapping
    public EmployeeDtoResponse update(@RequestBody EmployeeDtoRequest dto) {
        return employeeService.update(dto);
    }

    @DeleteMapping
    public long delete(@RequestBody EmployeeDtoRequest dto) {
        return employeeService.delete(dto);
    }

    @GetMapping
    public Map<String, Object> getEmployeesByPage(
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2") int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "_id", required = false) String sortBy
    ) {
        return employeeService.getEmployeesByPage(pageNo, pageSize, sortBy);
    }

    @GetMapping("/sum/salary")
    public Map<String, Object> getAggregationSalaryCount() {
        return employeeService.getAggregationSalaryCount();
    }
}
