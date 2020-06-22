package com.chengxugo.service.impl;

import com.chengxugo.mapper.TK.ChoiceMapper;
import com.chengxugo.mapper.TK.JudgeMapper;
import com.chengxugo.pojo.Tk.Choice;
import com.chengxugo.pojo.Tk.Judge;
import com.chengxugo.service.TkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TkServiceImpl implements TkService {

    @Autowired
    private JudgeMapper judgeMapper;
    @Autowired
    private ChoiceMapper choiceMapper;
    @Override
    public List<Choice> AllChice() {
        return choiceMapper.AllChoice();
    }

    @Override
    public  void CreateChoice(Choice choice) {
        choiceMapper.CreateChoice(choice);
    }

    @Override
    public void DeleteChoice(Long id) {
        choiceMapper.DeleteChoice(id);
    }

    @Override
    public void UpdateChoice(Choice choice) {
        choiceMapper.UpdateChoice(choice);
    }

    @Override
    public Choice findChoice(Long id) {
        return choiceMapper.findChoice(id);
    }
    @Override
    public void deleteManyChoice(String chk_value) {
        choiceMapper.deleteManyChoice(chk_value);
    }

    @Override
    public List<Judge> AllJudge() {

        return judgeMapper.AllJudge();
    }
    @Override
    public Judge findJudgeId(Long id) {
        return judgeMapper.findJudgeId(id);
    }
    @Override
    public void DeleteJudge(Long id) {
        judgeMapper.DeleteJudge(id);
    }
    @Override
    public void CreateJudge(Judge judge) {
        judgeMapper.CreateJudge(judge);
    }
    @Override
    public void UpdateJudge(Judge judge) {
        judgeMapper.UpdateJudge(judge);
    }
    @Override
    public void deleteManyJudge(String chk_value) {
        judgeMapper.deleteManyJudge(chk_value);
    }
}
