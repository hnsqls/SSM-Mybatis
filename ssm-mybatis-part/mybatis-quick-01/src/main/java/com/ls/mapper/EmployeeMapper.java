package com.ls.mapper;

import com.ls.pojo.Employee;

/**
 * 操作employee数据库的接口
 */
public interface EmployeeMapper {
    //根据id查询员工的信息
    Employee findById(int id);
}
