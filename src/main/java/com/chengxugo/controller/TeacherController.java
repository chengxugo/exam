package com.chengxugo.controller;

import com.chengxugo.pojo.Teacher;
import com.chengxugo.service.ExamService;
import com.chengxugo.service.TeacherService;
import com.chengxugo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
     private ExamService examService;
    @Autowired
    private UserService userService;

    //教师登录
    @RequestMapping(value = "/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model,HttpSession session ) {
        Teacher teacher = teacherService.login(username);
        if (teacher != null) {
            if (teacher.getPassword().equals(password)) {
                session.setAttribute("teachersession",teacher);
                return "page/teacher/home";
            } else {
                model.addAttribute("message", "你输入的密码有误");
                return "page/loginInfo";
            }
        }
        else {
            model.addAttribute("message", "用户不存在");
            return "page/loginInfo";
        }
    }

    //更改密码
    @RequestMapping("/uppass")
    public String UpPass(@RequestParam String username,Model model){
        Teacher teacher = teacherService.login(username);
        model.addAttribute("teacher",teacher);
         return "page/teacher/uppassword";
    }
    //管理员更新教师
    @RequestMapping("/uppassword")
    public String UpPassord(Teacher teacher){
        teacherService.Upteacer(teacher);
        return "page/teacher/home";
    }
    //退出登录
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("teachersession");
        return "/index";
    }


    //添加考试信息
    @RequestMapping("/exam")
    public  String Exam(){ return "page/teacher/examtest"; }
    //查找考试信息
    @RequestMapping("/findexam")
    public  String findexam(){
        return "redirect:allexam.do";
    }

    //学生成绩查询
    @RequestMapping("findstuscore")
    public String FindStuScore(){
        return "page/teacher/student_score";
    }
    //学生信息查询，并且查询全部学生
    @RequestMapping("/stuinfo")
    public  String stuifnopage(){
        return "page/teacher/stuinformation";
    }
}
