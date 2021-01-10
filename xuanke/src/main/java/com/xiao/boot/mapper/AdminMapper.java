package com.xiao.boot.mapper;

import com.xiao.boot.bean.Student;
import com.xiao.boot.bean.Teacher;
import org.apache.ibatis.annotations.Mapper;

// @Mapper 如果开启了包扫描就不用写注解了
public interface AdminMapper {

    public Integer addTeacher(Teacher teacher);

    public Integer addStudent(Student student);
}
