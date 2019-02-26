package org.o7planning.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.o7planning.spring.model.Department;
import org.springframework.jdbc.core.RowMapper;

public class DepartmentMapper implements RowMapper<Department> {

    public static final String BASE_SQL =
            "Select d.dept_id,d.dept_no,d.dept_name,d.location "//
                    + " from Department d ";

    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer deptId = rs.getInt("dept_id");
        String deptNo = rs.getString("dept_no");
        String deptName = rs.getString("dept_name");
        String location = rs.getString("location");

        return new Department(deptId, deptNo, deptName, location);
    }

}