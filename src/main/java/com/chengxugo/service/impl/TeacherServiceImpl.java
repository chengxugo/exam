package com.chengxugo.service.impl;

import com.chengxugo.mapper.TeacherMapper;
import com.chengxugo.pojo.Teacher;
import com.chengxugo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public Teacher login(String username) {
        return teacherMapper.login(username);
    }

    @Override
    public void deleteManyTeacher(String chk_value) {
        teacherMapper.deleteManyTeacher(chk_value);
    }

    @Override
    public void Upteacer(Teacher teacher) {
        teacherMapper.Upteacer(teacher);
    }


}
