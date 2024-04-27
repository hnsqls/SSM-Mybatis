package com.ls.mapper;

import com.ls.pojo.Employee;

import java.util.List;

/**
 * crud接口
 */
public interface EmployeeMapper {
    /**
     * 根据id查看员工信息
     * @param id
     * @return
     */
    Employee findOneBy(Integer id);

    //根据id删除员工信息
    int deleteById(Integer id);

    //新增员工信息
    int insertEmployee(Employee employee);

    //根据姓名和薪水查询员工信息


}
