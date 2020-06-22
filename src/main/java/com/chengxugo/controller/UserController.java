package com.chengxugo.controller;


import com.chengxugo.pojo.Point1;
import com.chengxugo.pojo.Teacher;

import com.chengxugo.service.BasicInfoService;
import com.chengxugo.service.TeacherService;
import com.chengxugo.service.UserService;
import com.chengxugo.pojo.Point2;
import com.chengxugo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户的控制层
 *
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
    //注入service
    @Autowired
    private UserService userService;
     @Autowired
     TeacherService teacherService;
     @Autowired
    BasicInfoService basicInfoService;
    // 用户登录
    @RequestMapping(value = "/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        User user = userService.login(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                //登录成功
               session.setAttribute("usersession",user);
                 return "page/admin/home";
            } else {
                model.addAttribute("message", "密码错误");
                return "page/loginInfo";
            }
        }else {
            model.addAttribute("message", "不存在此用户");
            return "page/loginInfo";
        }
    }
    @RequestMapping(value="/validate",method = RequestMethod.POST)
    @ResponseBody
    public  boolean validate(@RequestParam String username) {
        User user=userService.findByUsername(username);
        if(null!=user) {
            return true;
        }
        else {
            return false;
        }
    }

    @RequestMapping("uppass")
    public String UpPass(@RequestParam String username, Model model){
        User user = userService.login(username);
        model.addAttribute("user",user);
        return "page/admin/uppassword";
    }
    @RequestMapping("newpass")
    public String NewPass(User user,Model model){
       userService.updateuser(user);
        model.addAttribute("message", "密码更新成功");
        return "page/admin/home";
    }

   //显示所有老师
    @RequestMapping("/findallteacher")
    public String findTeacherAll(Model model){
               List<Teacher>teachers  = userService.findTeacherAll();
        for(int i = 0 ; i < teachers.size() ; i++) {
            System.out.println(teachers.get(i));
        }
        model.addAttribute("teachers",teachers);
        return  "page/admin/teacher";
    }
    @RequestMapping("/deletemanyteacher")
    public String ManyTeacher(String chk_value){
        teacherService.deleteManyTeacher(chk_value);
        return "redirect:findallteacher.do";
    }
    @ResponseBody
    @RequestMapping("/listteacher")
    public List<Teacher> Listteacher(){
        List<Teacher>teachers = userService.findTeacherAll();
        System.out.println("老师" + teachers.get(0).getUsername());
        return teachers;
    }
    //保存老师
    @RequestMapping(value = "/save")
    public String create(Teacher teacher, Model model) {
        try {
            userService.create(teacher);
            model.addAttribute("message", "保存客户信息系成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
           return "redirect:findallteacher.do";
    }
    //老师删除的方法
    @RequestMapping(value = "/delete")
    public String delete(@RequestParam Long id, Model model) {
        try {
            userService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:findallteacher.do";
    }
    //根据id找老师
    @ResponseBody
    @RequestMapping("/findById")
    public Teacher findById(@RequestBody Teacher teacher) {
        Teacher teacher_info = userService.findById(teacher.getId());
        if (teacher_info != null) {
            return teacher_info;
        } else {
            return null;
        }
    }
    //更新老师
    @RequestMapping(value = "/update")
    public String update(Teacher teacher, Model model) {
        try {
            userService.update(teacher);
            model.addAttribute("message", "更新老师信息成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:findallteacher.do";
    }





    //查找知识点
  @RequestMapping("/findpoint1")
    public String findPoint1All(Model model){
        List<Point1>point1  = userService.findPoint1All();
      model.addAttribute("point1",point1);
      return "page/admin/point";
    }

    //保存point1
    @RequestMapping("/addpoint1")
    public String createpoint1(Point1 point1, Model model) {
        try {
            userService.createpoint1(point1);
            model.addAttribute("message", "保存章节成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:findpoint1.do";
    }
  //删除point1
    @RequestMapping("/delep1")
    public  String DeleteP1(@RequestParam int id){
        userService.deletep1((long) id);
        userService.deletepp1((long) id);
        return "redirect:findpoint1.do";
    }
    //删除point2
    @RequestMapping("/delep2")
    public  String DeleteP2(@RequestParam Long chaptertwo){
       userService.deletep2(chaptertwo);
        return "redirect:findpoint1.do";
    }

    //保存point2
    @RequestMapping("/addpoint2")
    public String createpoint2(Point2 point2, Model model) {
        try {
            userService.createpoint2(point2);
            model.addAttribute("message", "保存章节成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:findpoint1.do";
    }

    //*下面是2个方法是为了对知识点的列表显示*/
    //显示point1
    @RequestMapping("/allpoint1")
   @ResponseBody
    public List<Point1> point1All(Model model){
        List<Point1>pt1  = userService.Point1All();
    	return pt1;
    }
    //查找所有知识点
    @RequestMapping("/pointall")
    @ResponseBody
    public List<Point1> pointAll(Model model){
        List<Point1>point  = userService.findPoint1All();
        model.addAttribute("point",point);
        return point;
    }







    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("usersession");
        return "/index";
    }
    //跳转到老师列表页面
    @RequestMapping(value = "/ListTeacher")
    public String toListPage(Model model) {
        return "redirect:findallteacher.do";
    }
    //跳转到老师
    @RequestMapping("/toteacher")
    public String toSavePage() {
        return "page/admin/adteacher";
    }
    //跳转到题库
    @RequestMapping("/ListTk")
    public String Tk() {
        return "page/admin/adtk";
    }

    //回到学生信息页面
    @RequestMapping("/stuinfo")
    public  String stuifnopage(){
        return "page/teacher/studentinfo";
    }

    //管理员学生成绩查询
    @RequestMapping("/findstuscore")
    public String FindStuScore(){
        return "page/admin/student_score";
    }


    @RequestMapping("/index")
    public String   index(){
        return "/index";
    }
}
