package com.dingxiang.controller;

import com.dingxiang.dao.DepartmentDao;
import com.dingxiang.dao.EmployeeDao;
import com.dingxiang.pojo.Department;
import com.dingxiang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddpage(Model model){
        // 查出所有部门的信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("---save---"+employee);
        // 调用底层业务方法保存员工信息
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    // 去员工的修改页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id")Integer id,Model model){
        // 查出原来的数据
        Employee employee = employeeDao.getEmployeeById(id);
        System.out.println("这里的id类型2：" + id.getClass().getName());
        model.addAttribute("emp",employee);

        // 查出所有部门的信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        System.out.println("返回的数据是"+employee);
        return "emp/update";
    }

    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    // 删除员工
    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id")int id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
