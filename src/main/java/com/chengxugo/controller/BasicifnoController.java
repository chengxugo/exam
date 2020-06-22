package com.chengxugo.controller;

import com.chengxugo.pojo.BasicInfo.*;
import com.chengxugo.pojo.BasicInfo.Class;
import com.chengxugo.pojo.Student;
import com.chengxugo.service.BasicInfoService;
import com.chengxugo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/basic")
public class BasicifnoController {
    @Autowired
    private BasicInfoService basicInfoService;
    @Autowired
    private StudentService studentService;


    //所有学期,这个方法在添加学生时列表显示
    @ResponseBody
    @RequestMapping("/findall")
    public List<Semester> findAll(){
        List<Semester>semesters=basicInfoService.findAll();
        System.out.println(semesters);
        return  semesters;
    }

    @RequestMapping("/allsemester")
    public String AllSemester(Model model){
        List<Semester>semesters = basicInfoService.findAll();
        model.addAttribute("semester",semesters);
        return "page/admin/base_year";
    }
    //添加学期
    @RequestMapping("/insertse")
    public String CreatSe(Semester semester){
        basicInfoService.CreateSemester(semester);
        return "redirect:allsemester.do";
    }
    //批量删除学期
    @RequestMapping("/deletesemester")
    public String deletesemester(int[] chk_value){
        for(int i = 0;i < chk_value.length;i++){
            System.out.println(chk_value[i]);
        }
         System.out.println(chk_value.length);
         if (chk_value == null){
             return "redirect:allsemester.do";
         }
         else{
             basicInfoService.deleteSemester(chk_value);
             return "redirect:allsemester.do";
         }
    }

    //更新的学期
    @RequestMapping("upse")
    public String UpSe(Semester semester){
        System.out.println("更新的学年" + semester.getSeid());
        basicInfoService.UpSemester(semester);
        return "redirect:allsemester.do";
    }
    //删除学年
    @RequestMapping("/deleteyear")
    public void DeleteYear(@RequestParam Long seid){
        System.out.println("删除的学年" + seid);
        basicInfoService.DeleteYear(seid);

    }





    //查找所有专业
    @ResponseBody
    @RequestMapping("/findmaall")
    public List<Major> findMaall(@RequestParam Long seid){
        List<Major>majors= basicInfoService.findMaAll(seid);
        System.out.println("专业数量" +seid);
        return majors;
    }
    //查找所有专业,不带条件的
    @ResponseBody
    @RequestMapping("/findmajorall")
    public List<Major> findMajorAll(){
        List<Major>majors= basicInfoService.findMajorAll();
        return majors;
    }

    @RequestMapping("/allmajor")
    public  String AllMajor(Model model){
        List<Major>majors = basicInfoService.findMajors();
        System.out.println("return \"page/admin/base_major\";");
        model.addAttribute("major",majors);
        return "page/admin/base_major";
    }
    //插入专业
    @RequestMapping("/insertma")
    public  String InsertMa(Major major, Model model){
           basicInfoService.CreateMajor(major);
           System.out.println("return \"page/admin/base_major\";");
           model.addAttribute("seid",major.getSeid());
          return "redirect:allmajor.do";
    }
    //删除专业
    @RequestMapping("/deletemajor")
    public void DeleteMajor(@RequestParam Long maid){
        System.out.println("删除的专业" + maid);
        basicInfoService.DeleteMajor(maid);

    }
    //更新专业
    @RequestMapping("upma")
    public String UpMa(Major major,Model model){
        System.out.println("更新专业" + major.getSeid());
        basicInfoService.UpMajor(major);
        model.addAttribute("seid",major.getSeid());
        return "redirect:allmajor.do";
    }


    //查找班级
    @ResponseBody
    @RequestMapping("/findciall")
    public List<Class> findCiall(@RequestParam Long maid){//查找班级
        List<Class>classes = basicInfoService.findCiAll(maid);
        System.out.println("班级数量" + classes.size());
        return classes;
    }
    @RequestMapping("/allclass")
    public String AllClass(@RequestParam long maid, Model model){
        System.out.println(maid);
        List<Class>classes = basicInfoService.findCiAll(maid);
        model.addAttribute("cla",classes);
        model.addAttribute("maid",maid);
        return  "page/admin/base_class";
    }
    //插入班级
    @RequestMapping("/insertclass")
    public String Isertcl(Class aClass, Model model){
        basicInfoService.CreateClass(aClass);
        model.addAttribute("maid", aClass.getMaid());
        return  "redirect:allclass.do";
    }
    //更新班级
    @RequestMapping("upcl")
    public String UpMa(Class aClass, Model model){
        System.out.println("更新的班级" + aClass.getMaid());
        basicInfoService.UpClass(aClass);
        model.addAttribute("maid", aClass.getMaid());
        return "redirect:allclass.do";
    }
    //删除班级
    @RequestMapping("/deleteclass")
    public void DeleteClass(@RequestParam String cname){
        System.out.println("删除的班级" + cname);
        basicInfoService.DeleteClass(cname);

    }




    //查询全部学生
    @RequestMapping("/allstudent")
    public  String FindStudent(@RequestParam String cname,Model model){
        System.out.println("班级名称"+ cname);
        List<Student>students = basicInfoService.findClass(cname);
        model.addAttribute("student",students);
        model.addAttribute("cname",cname);
        return "page/admin/base_student";
    }
    @RequestMapping("/studentall")
    @ResponseBody
    public List<Student> FindStudentAll(@RequestParam String cname){
        System.out.println("班级名称"+ cname);
        List<Student>students = basicInfoService.findClass(cname);
        System.out.println("学生数量" + students.size());
        return students;
    }
    //添加学生
    @RequestMapping("/creatstu")
    public String CreatStu(Student student){
        student.setPassword(student.getNum());
        studentService.CreatStu(student);
        return "redirect:allstudent.do?cname="+student.getClassname();
    }






    //查询所有职称
    @RequestMapping("allsit")
    public  String AllSit(Model model){
        List<Sit>sits = basicInfoService.AllSit();
        model.addAttribute("sit",sits);
        return "page/admin/base_sit";
    }
    @ResponseBody
    @RequestMapping("sit")
    public List<Sit> Sit(){
        List<Sit>sits = basicInfoService.AllSit();
        return sits;
    }
    //添加职称
    @RequestMapping("addsit")
    public String AddSit(Sit sit){
        System.out.println("添加职称" + sit);
        basicInfoService.InsertSit(sit);
        return "redirect:allsit.do";
    }
    //删除职称
    @RequestMapping("deletesit")
    public String DeleteSit(@RequestParam int id){
        System.out.println("删除" + id);
        basicInfoService.deleteSit(id);
        return "redirect:allsit.do";
    }
    //更新职称
    @RequestMapping("upsit")
    public String UpSit(Sit sit){
        System.out.println("更新职称" + sit.getId());
        basicInfoService.updateSit(sit);
        return "redirect:allsit.do";
    }
}

