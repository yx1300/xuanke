package com.xiao.boot.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xiao.boot.bean.Course;
import com.xiao.boot.bean.Student;
import com.xiao.boot.bean.Teacher;
import com.xiao.boot.bean.User;
import com.xiao.boot.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    // 添加课程信息
    @GetMapping("/add_course_page")
    public String add_course_page(){
        return "teacher/addCoursePage";
    }

    @PostMapping("/teacher/add_course")
    public String addCourse(Course course, RedirectAttributes ra){
        course.setNum(0);
        teacherService.addCourse(course);
        ra.addAttribute("message","添加课程成功");
        return "redirect:/add_course_page";
    }

    // 修改个人信息
    @GetMapping("/update_self_page")
    public String update_self_page(HttpSession session, Model model){
        String id = ((User)session.getAttribute("user")).getUsername();
        Teacher teacher = teacherService.findTeacherById(id);
        model.addAttribute("teacher",teacher);
        return "teacher/updateSelfInfoPage";
    }

    @PostMapping("/teacher/update_selfInfo")
    public String updateSelfInfo(Teacher teacher,RedirectAttributes ra){
        teacherService.updateSelfInfo(teacher);
        ra.addAttribute("message","修改成功");
        return "redirect:/update_self_page";
    }

    //浏览选课学生信息
    @GetMapping("/course_info_page")
    public String course_info_page(HttpSession session, Model model){
        String id = ((User)session.getAttribute("user")).getUsername();
        List<Map<String,Object>> courses = teacherService.findAllCourseByTeacherId(id);
        if (courses.size() == 0){
            model.addAttribute("message","暂无课程信息");
            return "main";
        }
        model.addAttribute("courses",courses);
        return "teacher/courseInfoPage";
    }

    @GetMapping("/findStudents")
    public String findStudents(@RequestParam("courseId") String courseId,HttpSession session,Model model){
        String teacherId = ((User)session.getAttribute("user")).getUsername();
        List<Student> students = teacherService.findStudents(courseId,teacherId);
        if (students.size() == 0){
            model.addAttribute("message","暂无学生选本文课");
            return "main";
        }
        model.addAttribute("students",students);
        return "teacher/studentInfoPage";
    }
}
