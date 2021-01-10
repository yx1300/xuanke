package com.xiao.boot.service.impl;

import com.xiao.boot.bean.Student;
import com.xiao.boot.bean.Teacher;
import com.xiao.boot.mapper.AdminMapper;
import com.xiao.boot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;
    @Override
    public Integer addTeacher(Teacher teacher) {
        return adminMapper.addTeacher(teacher);
    }

    @Override
    public Integer addStudent(Student student) {
        return adminMapper.addStudent(student);
    }
}
