package com.chengxugo.service;

import com.chengxugo.pojo.Tk.*;

import java.util.List;

public interface TkService {


    List<Judge>AllJudge();
    Judge findJudgeId(Long id);
    void  DeleteJudge(Long id);
    void  CreateJudge(Judge judge);
    void UpdateJudge(Judge judge);
    void deleteManyJudge(String chk_value);


    List<Choice> AllChice();
    void CreateChoice(Choice choice);
    void DeleteChoice(Long id);
    void UpdateChoice(Choice choice);
    Choice findChoice(Long id);
    void deleteManyChoice(String chk_value);
}
