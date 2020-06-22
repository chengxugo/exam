package com.chengxugo.controller;

import com.chengxugo.pojo.Tk.Choice;
import com.chengxugo.pojo.Tk.Judge;
import com.chengxugo.service.TkService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/tk")
public class TkController {
    @Autowired
    TkService tkService;

    /*选择题的操作*/
    //管理员查询判断题题库
    @RequestMapping("/allchoice")
    public String AllChoice(Model model,@RequestParam(defaultValue = "1") int pn) {
        PageHelper.startPage(pn, 6);//显示个数
        List<Choice>  choices =tkService.AllChice();
        System.out.print(choices);
        PageInfo pageInfo = new PageInfo(choices, 5);//显示前后页数
        model.addAttribute("pageInfo", pageInfo);
        return "page/admin/tk_choice";
    }
    @RequestMapping("/allchoice1")
    public String AllChoice1(Model model,@RequestParam(defaultValue = "1") int pn) {
        PageHelper.startPage(pn, 6);//显示个数
        List<Choice>  choices =tkService.AllChice();
        System.out.print(choices);
        PageInfo pageInfo = new PageInfo(choices, 5);//显示前后页数
        model.addAttribute("pageInfo", pageInfo);
        return "page/teacher/tea_choice";
    }

    @RequestMapping("/createchoice")
    public String CreateChoice(Choice choice){
        tkService.CreateChoice(choice);/**/
        return "redirect:allchoice.do";
    }
    @RequestMapping("/createchoice1")
    public String CreateChoice1(Choice choice){
        tkService.CreateChoice(choice);/**/
        return "redirect:allchoice1.do";
    }

    @RequestMapping("/deletechoice")
    public String DeleteChoice(@RequestParam Long id){
        tkService.DeleteChoice(id);
        return "redirect:allchoice.do";
    }
    @RequestMapping("/deletechoice1")
    public String DeleteChoice1(@RequestParam Long id){
        tkService.DeleteChoice(id);
        return "redirect:allchoice1.do";
    }

    @RequestMapping("/upchoice")
    public String UpJudge(Choice choice){
        tkService.UpdateChoice(choice);
        return "redirect:allchoice.do";
    }
    @RequestMapping("/upchoice1")
    public String UpJudge1(Choice choice){
        tkService.UpdateChoice(choice);
        return "redirect:allchoice1.do";
    }

    @ResponseBody
    @RequestMapping("/choiceId")
    public Choice findChoiceId(@RequestBody Choice choice){
        Choice choices = tkService.findChoice(choice.getId());
        if(choices != null){
            return choices;
        }else {
            return null;
        }
    }
    @ResponseBody
    @RequestMapping("/choiceId1")
    public Choice findChoiceId1(@RequestBody Choice choice){
        Choice choices = tkService.findChoice(choice.getId());
        if(choices != null){
            return choices;
        }else {
            return null;
        }
    }

    @RequestMapping("/deletemanychoice")
    public String ManyChoice(String chk_value){
        tkService.deleteManyChoice(chk_value);
        return "redirect:allchoice.do";
    }
    @RequestMapping("/deletemanychoice1")
    public String ManyChoice1(String chk_value){
        tkService.deleteManyChoice(chk_value);
        return "redirect:allchoice1.do";
    }






    /* 判断题的操作*/
    //管理员查询判断题题库
    @RequestMapping("/alljudge")
    public String AllJudge(Model model,@RequestParam(defaultValue = "1") int pn){
        PageHelper.startPage(pn,6);//显示个数
        List<Judge>judges = tkService.AllJudge();
        System.out.print(judges);
        PageInfo pageInfo = new PageInfo(judges,5);//显示前后页数
        System.out.print(pageInfo.getTotal());
        model.addAttribute("pageInfo",pageInfo);
        return "page/admin/tk_judge";
    }
    @RequestMapping("/alljudge1")
    public String AllJudge1(Model model,@RequestParam(defaultValue = "1") int pn){
        PageHelper.startPage(pn,6);//显示个数
        List<Judge>judges = tkService.AllJudge();
        System.out.print(judges);
        PageInfo pageInfo = new PageInfo(judges,5);//显示前后页数
        model.addAttribute("pageInfo",pageInfo);
        return "page/teacher/tea_judge";
    }


    @RequestMapping("/createjudge")
    public String CreateJudge(Judge judge){
        tkService.CreateJudge(judge);
        return "redirect:alljudge.do";
    }
    @RequestMapping("/createjudge1")
    public String CreateJudge1(Judge judge){
        tkService.CreateJudge(judge);
        return "redirect:alljudge1.do";
    }

    @RequestMapping("/deletejudge")
    public String DeleteJudge(@RequestParam Long id){
        tkService.DeleteJudge(id);
        return "redirect:alljudge.do";
    }
    @RequestMapping("/deletejudge1")
    public String DeleteJudge1(@RequestParam Long id){
        tkService.DeleteJudge(id);
        return "redirect:alljudge1.do";
    }

    @RequestMapping("/upjudge")
    public String UpJudge(Judge judge){
        tkService.UpdateJudge(judge);
        System.out.print("gfsrgtehytk");
        return "redirect:alljudge.do";
    }
    @RequestMapping("/upjudge1")
    public String UpJudge1(Judge judge){
        tkService.UpdateJudge(judge);
        System.out.print("gfsrgtehytk");
        return "redirect:alljudge1.do";
    }

    @ResponseBody
    @RequestMapping("judgeId")
    public Judge findJudgeId(@RequestBody Judge judge){
        Judge judges = tkService.findJudgeId(judge.getId());
        System.out.print("gfsrgtehytkgfsrgtehytkgfsrgtehytkgfsrgtehytkgfsrgtehytkgfsrgtehytk");
        if(judges != null){
            return judges;
        }else {
            return null;
        }
    }
    @ResponseBody
    @RequestMapping("judgeId1")
    public Judge findJudgeId1(@RequestBody Judge judge){
        Judge judges = tkService.findJudgeId(judge.getId());
        if(judges != null){
            return judges;
        }else {
            return null;
        }
    }

    @RequestMapping("/deletemanyjudge")
    public String ManyJudge(String chk_value){
         tkService.deleteManyJudge(chk_value);
         return "redirect:alljudge.do";
    }
    @RequestMapping("/deletemanyjudge1")
    public String ManyJudge1(String chk_value){
        tkService.deleteManyJudge(chk_value);
        return "redirect:alljudge1.do";
    }

}
