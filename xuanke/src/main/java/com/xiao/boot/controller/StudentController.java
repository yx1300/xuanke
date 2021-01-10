package com.xiao.boot.controller;

import com.xiao.boot.bean.Course;
import com.xiao.boot.bean.Student;
import com.xiao.boot.bean.Teacher;
import com.xiao.boot.bean.User;
import com.xiao.boot.service.StudentService;
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
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    // 修改个人信息
    @GetMapping("/update_stu_page")
    public String update_self_page(HttpSession session, Model model){
        String id = ((User)session.getAttribute("user")).getUsername();
        Student student = studentService.findStudentById(id);
        model.addAttribute("student",student);
        return "student/updateStuInfoPage";
    }

    @PostMapping("/student/update_selfInfo")
    public String updateSelfInfo(Student student, RedirectAttributes ra){
        studentService.updateSelfInfo(student);
        ra.addAttribute("message","修改成功");
        return "redirect:/update_stu_page";
    }


    //浏览选课学生信息
    @GetMapping("/all_course_info_page")
    public String course_info_page(HttpSession session, Model model){

        List<Map<String,Object>> courses = studentService.findAllCourse();
        if (courses.size() == 0){
            model.addAttribute("message","暂无课程信息");
            return "main";
        }
        model.addAttribute("courses",courses);
        return "student/allCourseInfoPage";
    }

    // 查看老师信息
    @GetMapping("/findTeacher")
    public String findTeacher(@RequestParam("teacherId") String teacherId,Model model){
        Teacher teacher = teacherService.findTeacherById(teacherId);
        model.addAttribute("teacher",teacher);
        return "student/teacherInfoPage";
    }

    // 获得所有可以选课的课程信息
    @GetMapping("/all_chosen_course_info_page")
    public String all_chosen_course_info_page(Model model,HttpSession session){
        String studentId = ((User)session.getAttribute("user")).getUsername();
        List<Map<String,Object>> courses = studentService.findChosenCourse(studentId);
        if (courses.size() == 0){
            model.addAttribute("message","暂无课程信息");
            return "main";
        }
        model.addAttribute("courses",courses);
        return "student/allChosenCourseInfoPage";
    }

    // 选课
    @GetMapping("/choose_course")
    public String all_chosen_course_info_page(Model model,HttpSession session,
                                              @RequestParam("courseId") String courseId,
                                              @RequestParam("teacherId") String teacherId,
                                              RedirectAttributes ra){
        String studentId = ((User)session.getAttribute("user")).getUsername();
        Integer b1 = studentService.chooseCourse(courseId,teacherId,studentId);
        Integer b2 = studentService.updateCourseNum(courseId);
        if (b1+b2 == 2){
            ra.addAttribute("message","选课成功！");
        }else {
            ra.addAttribute("message","选课失败！");
        }
        return "redirect:/all_chosen_course_info_page";
    }
}
