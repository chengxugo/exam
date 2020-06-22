package com.chengxugo.service;

import com.chengxugo.pojo.Teacher;


public interface TeacherService {
    Teacher login(String username);
    void deleteManyTeacher(String chk_value);
    void Upteacer(Teacher teacher);

}
