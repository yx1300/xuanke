package com.xiao.boot.service;

import com.xiao.boot.bean.Student;
import com.xiao.boot.bean.Teacher;

public interface AdminService {

    public Integer addTeacher(Teacher teacher);

    public Integer addStudent(Student student);
}
