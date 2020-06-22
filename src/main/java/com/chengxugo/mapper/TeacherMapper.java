package com.chengxugo.mapper;

import com.chengxugo.pojo.Teacher;


public interface TeacherMapper {
    Teacher login(String username);
    void update(Teacher teacher);
    void deleteManyTeacher(String chk_value);
    void Upteacer(Teacher teacher);

}
