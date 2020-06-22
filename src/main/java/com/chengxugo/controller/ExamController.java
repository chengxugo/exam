package com.chengxugo.controller;

import com.chengxugo.pojo.Exam.*;
import com.chengxugo.pojo.Student;
import com.chengxugo.pojo.Teacher;
import com.chengxugo.service.ExamService;
import com.chengxugo.service.StudentService;
import com.chengxugo.service.TeacherService;
import com.chengxugo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/exam")
public class ExamController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ExamService examService;
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    //创建新考试跳转页面
    @RequestMapping("/addexam")
    public  String addexam(Model model){
        List<Teacher> teachers = userService.findTeacherAll();
        model.addAttribute("teacher",teachers);
        return "page/teacher/addexam";
    }
    //添加考试
    @RequestMapping("/examadd")
    public  String examadd(ExamInformation examInformation){
        examService.CreateExam(examInformation);
        System.out.print(examInformation.getChaptertwo()+"sf"+examInformation.getDifficulty()+"1234567890");
        List<PaperJudge>paperJudges = examService.RandJudge((long) examInformation.getJudgenum(),examInformation.getChaptertwo(),examInformation.getDifficulty());
        List<PaperChoice>paperChoices = examService.RandChoice((long) examInformation.getChoicenum(),examInformation.getChaptertwo(),examInformation.getDifficulty());

        if(paperJudges.size()==0&&paperJudges.size()==0)
            return "redirect:examall.do";

         for (int i = 0;i<paperChoices.size();i++){
             paperChoices.get(i).setExamid(examInformation.getId());
         }
         examService.insertRandChoice(paperChoices);
        for (int i = 0;i<paperJudges.size();i++){
            paperJudges.get(i).setExamid(examInformation.getId());
        }
        examService.insertRandJudge(paperJudges);
        return "redirect:examall.do";
    }


    //教师查看考试信息
    @RequestMapping("/examall")
    public String ExamAll(Model model){
        List<ExamInformation>examInfos = examService.AllExam();
        model.addAttribute("examInfo",examInfos);
        return "page/teacher/exam_info";
    }
    //管理员查看考试信息
    @RequestMapping("/allexam")
    public String AllExam(Model model){
        List<ExamInformation>examInfos = examService.AllExam();
        model.addAttribute("examInfo",examInfos);
        return "page/admin/exam_info";
    }


    //教师查看考试详情
    @RequestMapping("detail")
    public String ExamDetail(@RequestParam int examid, Model model){
        System.out.println("考试id" +examid);
        List<PaperChoice>paperChoices = examService.AllPaperChoice(examid);
        List<PaperJudge>paperJudges = examService.AllPaperJudge(examid);
       ExamInformation examInformations = examService.findById(examid);
        model.addAttribute("choice",paperChoices);
        model.addAttribute("judge",paperJudges);
        model.addAttribute("examinfo",examInformations);
        return "page/teacher/exam_detail";
    }
    //管理员查看考试详情
    @RequestMapping("admindetail")
    public String AdminExamDetail(@RequestParam int examid, Model model){

        List<PaperChoice>paperChoices = examService.AllPaperChoice(examid);
        List<PaperJudge>paperJudges = examService.AllPaperJudge(examid);
        ExamInformation examInformations = examService.findById(examid);
        model.addAttribute("choice",paperChoices);
        model.addAttribute("judge",paperJudges);
        model.addAttribute("examinfo",examInformations);
        return "page/admin/exam_detail";
    }



    //教师删除考试id删除考试信息
    @RequestMapping("deleteexam")
    public String DeleteExam(@RequestParam int examid){
        examService.DeletePaperChoice(examid);
        examService.DeletePaperJudge(examid);
        examService.DeleteExamInfo(examid);
        return "redirect:examall.do";
    }
    //管理员删除考试id删除考试信息
    @RequestMapping("admindeleteexam")
    public String AdminDeleteExam(@RequestParam int examid){
        System.out.println("删除的考试id"+ examid);
        examService.DeletePaperChoice(examid);
        examService.DeletePaperJudge(examid);
        examService.DeleteExamInfo(examid);
        return "redirect:allexam.do";
    }


    //管理员根据考试id查询所有参加过考试的学生
    @RequestMapping("/allexamstu")
    public String AllExamStu(@RequestParam int examid,Model model){
        Map<Long, Long> map = new HashMap<>();
        List<ExamHistory>examHistories = examService.ByExamId(examid);  //根据考试id得到考试信息
        List<Long>longList = new ArrayList<Long>();
        List<Student>students = new ArrayList<Student>();
        for(int i = 0;i < examHistories.size();i++){
           map.put(examHistories.get(i).getStudentid(),examHistories.get(i).getScore());//key为本场考试学生id，ualue为学生分数
            longList.add(examHistories.get(i).getStudentid());//存入学生id
        }
        if(longList.isEmpty()){    //如果学生id为空，则没有学生参加
          students.add(null);
        }
        else {
            students = studentService.AllStudent(longList);   //负责根据学生id查询学生信息
            for (int i = 0; i < examHistories.size(); i++) {
                students.get(i).setId(map.get(students.get(i).getId())); //封装返回体
            }
            Collections.sort(students, new Comparator<Student>() {//按分数score排序
                public int compare(Student p1, Student p2) {
                    if (p1.getId() < p2.getId()) {
                        return 1;
                    }
                    if (p1.getId() == p2.getId()) {
                        return 0;
                    }
                    return -1;
                }
            });
        }
        model.addAttribute("student",students);
        return "page/admin/exam_allstudent";
    }
    //老师页根据考试id查询所有参加过考试的学生
    @RequestMapping("/allstuexam")
    public String AllStuExam(@RequestParam int examid,Model model){
        Map<Long, Long> map = new HashMap<>();
        List<ExamHistory>examHistories = examService.ByExamId(examid);
        List<Long>longList = new ArrayList<Long>();
        List<Student>students = new ArrayList<Student>();
        for(int i = 0;i < examHistories.size();i++){
            map.put(examHistories.get(i).getStudentid(),examHistories.get(i).getScore());
            longList.add(examHistories.get(i).getStudentid());
        }
        if(longList.isEmpty()){
            students.add(null);
        }
        else {
            students = studentService.AllStudent(longList);
            for (int i = 0; i < examHistories.size(); i++) {
                students.get(i).setId(map.get(students.get(i).getId()));
            }
            Collections.sort(students, new Comparator<Student>() {//按score排序
                public int compare(Student p1, Student p2) {
                    if (p1.getId() < p2.getId()) {
                        return 1;
                    }
                    if (p1.getId() == p2.getId()) {
                        return 0;
                    }
                    return -1;
                }
            });
        }
        model.addAttribute("student",students);
        return "page/teacher/exam_allstudent";
    }



    //学生参加考试
    @RequestMapping("/examallstu")
    public String ExamStuAll(Model model,@RequestParam int studentid){
        List<ExamInformation>examInfos = examService.AllExam();
        List<ExamHistory>examHistories =examService.ByStudentid(studentid);
          for(int i = 0;i < examInfos.size();i++){
              for(int j = 0;j < examHistories.size();j++){
                  if (examHistories.get(j).getExamid() == examInfos.get(i).getId()){  //将考过的试卷不显示
                      examInfos.remove(examInfos.get(i));
                  }
              }
          }
        model.addAttribute("examInfo",examInfos);
        return "page/student/exam_info";
    }
    //学生根据试卷id进入考试
    @RequestMapping("exampaper")
    public String ExamPaper(@RequestParam int examid, Model model){
        List<PaperChoice>paperChoices = examService.AllPaperChoice(examid);//从paperchoice中得到试题
        List<PaperJudge>paperJudges = examService.AllPaperJudge(examid);//从paperjudge中得到试题
        ExamInformation examInformation = examService.findById(examid);//得到考试信息
        model.addAttribute("choice",paperChoices);
        model.addAttribute("judge",paperJudges);
        model.addAttribute("examinfo", examInformation);
        return "page/student/exam_paper";
    }
    //学生提交试卷操作
    @RequestMapping("saveexam")
    public String SaveExam(@RequestParam int examid,@RequestParam long studentid,
                           @RequestParam String[] answerchoice,@RequestParam String[] answerjudge,
                           Model model){
       List<PaperChoice>paperChoices =  examService.AllPaperChoice(examid); //根据试卷id查询全部选择题试卷表
       List<PaperJudge>paperJudges = examService.AllPaperJudge(examid); //根据试卷id查询全部判断题试卷表
       ExamInformation examInformation = examService.findById(examid);  //根据试卷id查询试卷信息

       List<AllAnswer>allAnswers =new ArrayList<AllAnswer>();  //存储学生这张试卷的答案

       ExamHistory examHistory = new ExamHistory();     //存储学生试卷结果信息


        int allscore = 0;  //统计学生本章试卷的分数
        for(int i = 0 ;i < paperChoices.size();i++){
            if(paperChoices.get(i).getAnswer().equals(answerchoice[i])){   //如果单选题试卷表的答案和提交答案一样则正确
                allscore += examInformation.getChoicescore();  //将总分相加选择题的分数
            }
                AllAnswer  allAnswer= new AllAnswer();//构建学生作答的结果表exam_result
                allAnswer.setStudentid(studentid);
                allAnswer.setExamid(examid);
                allAnswer.setStuanswer(answerchoice[i]);
                allAnswers.add(allAnswer);
        }
        for(int i = 0;i < paperJudges.size();i++){
            if (paperJudges.get(i).getAnswer().equals(answerjudge[i]) ) {
                allscore += examInformation.getJudgescore();
            }
                System.out.println(answerjudge[i]);
                AllAnswer  allAnswer= new AllAnswer();
                allAnswer.setStudentid(studentid);
                allAnswer.setExamid(examid);
                allAnswer.setStuanswer(answerjudge[i]);
                allAnswers.add(allAnswer);

        }
       for(int i= 0;i < allAnswers.size();i++){
           System.out.println("错误题目和答案  " + allAnswers.get(i).getStuanswer()+"  "+ allAnswers.get(i).getExamid());
       }
       //添加到错题本
        examService.CreateAllAnswer(allAnswers);
        //添加考试历史记录
       examHistory.setExamid(examid);
       examHistory.setStudentid(studentid);
       examHistory.setScore(allscore);
       examService.CreateExamHistory(examHistory);
        return "redirect:examallstu.do?studentid="+studentid;  //返回到学生参加考试的界面
    }
    //学生试卷回顾
    @RequestMapping("examhistory")
    public String ExamHistory(@RequestParam  int studentid,Model model){
        List<ExamHistory>examHistories =examService.ByStudentid(studentid);  //根据学生id查询学生全部考试信息
        if(examHistories.size()==0){
            return "page/student/exam_end";
        }
        List<Long>longList = new ArrayList<Long>();
        for(int i = 0;i < examHistories.size();i++){
            longList.add(examHistories.get(i).getExamid());
        }
        List<ExamInformation> examInformations = examService.AlreadExam(longList);  //根据考试id得到考试信息
        if(examInformations.size()==0){
        }
       for(int i  =0;i < examInformations.size();i++){
           examInformations.get(i).setAllscore((int) examHistories.get(i).getScore());
       }
       model.addAttribute("examend",examInformations);
       return "page/student/exam_end";
    }


    //学生根据学生id，试卷id进入试题详情页面
    @RequestMapping("examdetial")
    public  String ExamHistory(@RequestParam int studentid,@RequestParam int examid, Model model){
        List<AllAnswer>allAnswers = examService.AllAnswer(studentid,examid);//查询学生的答案
        List<PaperChoice>paperChoices = examService.AllPaperChoice(examid);//根据考试id查询选择题试卷表的答案
        List<PaperJudge>paperJudges = examService.AllPaperJudge(examid);
        ExamHistory examHistory =examService.DetilsExam(studentid,examid);//查询学生id 试卷id查询考试的试卷历史信息
        ExamInformation examInformation = examService.findById(examid);   //根据考试id查询考试信息

        model.addAttribute("examinfo",examInformation);

        int allscore = (int) examHistory.getScore();
        model.addAttribute("allscore",allscore);

        for(int i = 0;i < paperChoices.size();i++){
            paperChoices.get(i).setMyanswer(allAnswers.get(i).getStuanswer());    //封装选择题和题目答案
        }
        for(int i = 0;i < paperJudges.size();i++){
            paperJudges.get(i).setMyanswer(allAnswers.get(i+paperChoices.size()).getStuanswer());   //封装判断题和题目答案
        }
        model.addAttribute("choice",paperChoices);
        model.addAttribute("judge",paperJudges);
        return "page/student/exam_historypaper";
    }






    //教师或管理员根据名字和学生学号查询学生成绩
    @ResponseBody
    @RequestMapping("stuscore")
    public List<ExamInformation> StuScore(@RequestParam String name){
        List<ExamInformation> examInformations = new ArrayList<ExamInformation>();  //试卷信息表，应为需要具体的考试信息，所以不用Examhoistory表传数据。
        Student student = new Student();
        List<Long>longList = new ArrayList<Long>();
        if(name.length() <5){
             student = studentService.login(name);
        }
        else{
           student = studentService.ByNum(name);
        }
        long id = student.getId();
        List<ExamHistory>examHistories = examService.ByStudentid((int) id);  //根据学生id查询学生的所有考试信息
        if(examHistories.size() ==0){
            return examInformations;
        }
        else {
            for (int i = 0; i < examHistories.size(); i++) {
                longList.add(examHistories.get(i).getExamid());  //得到考试id
            }
            examInformations = examService.AlreadExam(longList);//根据考试id得到考试信息
            for (int i = 0; i < examInformations.size(); i++) {
                examInformations.get(i).setAllscore((int) examHistories.get(i).getScore());//填充分数
                examInformations.get(i).setStudentname(student.getUsername());//学生名字
            }
            return examInformations;  //返回一个学生的全部考试信息
        }
   }
}
