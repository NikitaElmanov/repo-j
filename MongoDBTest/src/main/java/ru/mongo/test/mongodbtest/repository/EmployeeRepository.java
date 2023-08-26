package ru.mongo.test.mongodbtest.repository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import ru.mongo.test.mongodbtest.model.Employee;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeRepository {

    MongoTemplate mongoTemplate;

    public Employee save(Employee employee) {
        return mongoTemplate.save(employee);
    }

    public List<Employee> getAll() {
        return mongoTemplate.findAll(Employee.class);
    }

    public List<Employee> getByFirstName(String firstName) {
        return mongoTemplate.find(Query.query(Criteria.where("firstName").is(firstName)), Employee.class);
    }

    public long delete(Employee employee) {
        return mongoTemplate.remove(employee).getDeletedCount();
    }

    public Map<String, Object> getEmployeesByPage(int pageNo, int pageSize, String sortBy) {

        // add sorting by sortBy param
        Sort sort = Sort.by(sortBy);

        // add pagination by all retrieved params
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // add query for acumulate all settings
        Query query = new Query();
        query.with(pageable);

        // make request with query and pagination settings
        List<Employee> employees = mongoTemplate.find(query, Employee.class);
        Page<Employee> employeePage = PageableExecutionUtils.getPage(employees, pageable, employees::size); // TODO

        return Map.of(
                "data", employeePage.getContent(),
                "total", employeePage.getTotalPages(),
                "current page", pageNo
        );
    }

    public Map<String, Object> getAggregationSalaryCount() {
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.group().sum("$salary").as("salaryCount"));
        return mongoTemplate.aggregate(aggregation, "employee", Employee.class).getRawResults();
    }
}
