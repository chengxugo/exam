package com.chengxugo.service;

import com.chengxugo.pojo.BasicInfo.*;
import com.chengxugo.pojo.BasicInfo.Class;
import com.chengxugo.pojo.Student;

import java.util.List;

public interface BasicInfoService {
    List<Semester> findAll();
    List<Major> findMaAll(Long seid);

    List<Class>findCiAll(Long maid);
    void CreateSemester(Semester semester);
    Integer deleteSemester(int[] id_arr);//批量删除
    void CreateMajor(Major major);
    void CreateClass(Class aClass);
    void UpSemester(Semester semester);
    void UpMajor(Major major);
    void UpClass(Class aClass);
    void CreateStudent(Student student);

    List<Student>findClass(String classname);
    void DeleteClass(String cname);
    void DeleteMajor(Long maid);

    void DeleteYear(Long seid);
    List<Sit> AllSit();
    void InsertSit(Sit sit);
    void deleteSit(int id);
    void updateSit(Sit sit);

    List<Major> findMajors();
    List<Major> findMajorAll();


}
