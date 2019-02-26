package org.o7planning.spring.dao;

import java.util.List;

import javax.sql.DataSource;

import org.o7planning.spring.mapper.DepartmentMapper;
import org.o7planning.spring.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class DepartmentDAO extends JdbcDaoSupport {

    @Autowired
    public DepartmentDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    private int getMaxDeptId() {
        String sql = "Select max(d.dept_id) from Department d";

        Integer value = this.getJdbcTemplate().queryForObject(sql, Integer.class);
        if (value == null) {
            return 0;
        }
        return value;
    }

    public Department findDepartment(String deptNo) {
        String sql = DepartmentMapper.BASE_SQL //
                + " where d.dept_no = ?";

        Object[] params = new Object[] { deptNo };

        DepartmentMapper mapper = new DepartmentMapper();

        Department dept = this.getJdbcTemplate().queryForObject(sql, params, mapper);
        return dept;
    }

    public List<Department> listDepartment() {
        String sql = DepartmentMapper.BASE_SQL;

        Object[] params = new Object[] {};
        DepartmentMapper mapper = new DepartmentMapper();

        List<Department> list = this.getJdbcTemplate().query(sql, params, mapper);
        return list;
    }

    public void insertDepartment(String deptName, String location) {
        String sql = "Insert into Department (dept_id,dept_no,dept_name,location) "//
                + " values (?,?,?,?) ";
        int deptId = this.getMaxDeptId() + 1;
        String deptNo = "D" + deptId;
        Object[] params = new Object[] { deptId, deptNo, deptName, location };
        this.getJdbcTemplate().update(sql, params);
    }

}