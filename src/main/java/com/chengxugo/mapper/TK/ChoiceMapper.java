package com.chengxugo.mapper.TK;

import com.chengxugo.pojo.Tk.Choice;

import java.util.List;

public interface ChoiceMapper {
    List<Choice> AllChoice();

    void CreateChoice(Choice choice);

    void DeleteChoice(Long id);

    void UpdateChoice(Choice choice);

    Choice findChoice(Long id);

    void deleteManyChoice(String chk_value);
}
