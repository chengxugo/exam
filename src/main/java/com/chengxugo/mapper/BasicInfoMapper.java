package com.chengxugo.mapper;

import com.chengxugo.pojo.BasicInfo.*;
import com.chengxugo.pojo.BasicInfo.Class;
import com.chengxugo.pojo.Student;

import java.util.List;

public interface BasicInfoMapper {
    List<Semester> findAll();
    List<Major> findMaAll(Long seid);
    List<Class>findCiAll(Long maid);
    java.lang.Class findCiId(Long id);
    void CreateSemester(Semester semester);
    void CreateMajor(Major major);
    void CreateClass(Class aClass);
    void CreateStudent(Student student);

    void UpSemester(Semester semester);
    void UpMajor(Major major);
    void UpClass(Class aClass);

    List<Student>findClass(String classname);
     Integer deleteSemester(int[] id_arr);//批量删除
   void DeleteClass(String cname);
    void DeleteMajor(Long maid);
    void DeleteYear(Long seid);


    List<Sit> AllSit();
    void InsertSit(Sit sit);
    void deleteSit(int id);
    void updateSit(Sit sit);

    List<Major> findMajorAll();


    List<Major> findMajors();
}
