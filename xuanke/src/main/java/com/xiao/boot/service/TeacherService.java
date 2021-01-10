package com.xiao.boot.service;

import com.xiao.boot.bean.Course;
import com.xiao.boot.bean.Student;
import com.xiao.boot.bean.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherService {

    public int addCourse(Course course);

    public Teacher findTeacherById(String id);

    public int updateSelfInfo(Teacher teacher);

    public List<Map<String,Object>> findAllCourseByTeacherId(String id);

    public List<Student> findStudents(String courseId, String teacherId);
}
