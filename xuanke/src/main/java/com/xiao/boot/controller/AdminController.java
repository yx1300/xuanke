package com.xiao.boot.controller;

import com.xiao.boot.bean.Student;
import com.xiao.boot.bean.Teacher;
import com.xiao.boot.mapper.AdminMapper;
import com.xiao.boot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;
    @GetMapping("/add_teacher_page")
    public String add_teacher_page(){
        return "admin/addTeacherPage";
    }

    @GetMapping("/add_student_page")
    public String add_student_page(){
        return "admin/addStudentPage";
    }

    @PostMapping("/admin/add_teacher")
    public String addTeacher(Teacher teacher, RedirectAttributes ra){
        Integer row = adminService.addTeacher(teacher);
        if (row == 1){
            ra.addAttribute("message","添加成功");
        }else {
            ra.addAttribute("message","添加失败");
        }
        return "redirect:/add_teacher_page";
    }

    @PostMapping("/admin/add_student")
    public String addStudent(Student student, RedirectAttributes ra){
        Integer row = adminService.addStudent(student);
        if (row == 1){
            ra.addAttribute("message","添加成功");
        }else {
            ra.addAttribute("message","添加失败");
        }
        return "redirect:/add_student_page";
    }
}
