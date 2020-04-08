package com.dingxiang.dao;

import com.dingxiang.pojo.Department;
import com.dingxiang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


// 员工dao
@Repository
public class EmployeeDao {

    // 模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;
    // 员工有所属的部门
    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<>();

        employees.put(1001,new Employee(1001,"AA","Axxxx@qq.com",0,new Department(101,"软件部")));
        employees.put(1002,new Employee(1002,"BB","Bxxxx@qq.com",1,new Department(102,"咨询部")));
        employees.put(1003,new Employee(1003,"CC","Cxxxx@qq.com",0,new Department(103,"销售部")));
        employees.put(1004,new Employee(1004,"DD","Dxxxx@qq.com",1,new Department(104,"财务部")));
        employees.put(1005,new Employee(1005,"EE","Exxxx@qq.com",0,new Department(105,"运营部")));
    }

    // 主键自增
    private static Integer initId = 1006;

    // 增加一个员工
    public void save(Employee employee){
        if (employee.getId() == null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }

    // 查询所有员工信息
    public Collection<Employee> getAll(){
//        System.out.println(employees.);
        return employees.values();
    }

    // 通过id查询员工
    public Employee getEmployeeById(Integer id){
        System.out.println("这里的id类型1：" + id.getClass().getName());
        return employees.get(id);
    }

    // 通过id删除员工
    public void delete(Integer id){
        employees.remove(id);
    }
}
