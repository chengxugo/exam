package com.chengxugo.service.impl;

import com.chengxugo.mapper.BasicInfoMapper;
import com.chengxugo.pojo.BasicInfo.*;
import com.chengxugo.pojo.BasicInfo.Class;
import com.chengxugo.pojo.Student;
import com.chengxugo.service.BasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicInfoImpl implements BasicInfoService {
    @Autowired
    private BasicInfoMapper basicInfoMapper;

    @Override
    public List<Semester> findAll() {
        return basicInfoMapper.findAll();
    }

    @Override
    public List<Major> findMaAll(Long seid) {
        return basicInfoMapper.findMaAll(seid);
    }

    @Override
    public List<Major> findMajors() {
        return basicInfoMapper.findMajors();
    }

    @Override
    public List<Class> findCiAll(Long maid) {
        return basicInfoMapper.findCiAll(maid);
    }

    @Override
    public void CreateSemester(Semester semester) {
        basicInfoMapper.CreateSemester(semester);
    }
 //批量删除
    @Override
    public Integer deleteSemester(int[] id_arr) {
        return basicInfoMapper.deleteSemester(id_arr);
    }

    @Override
    public void CreateMajor(Major major) {
        basicInfoMapper.CreateMajor(major);
    }

    @Override
    public void CreateClass(Class aClass) {
          basicInfoMapper.CreateClass(aClass);
    }

    @Override
    public void UpSemester(Semester semester) {
        basicInfoMapper.UpSemester(semester);
    }

    @Override
    public void UpMajor(Major major) {
basicInfoMapper.UpMajor(major);
    }

    @Override
    public void UpClass(Class aClass) {
basicInfoMapper.UpClass(aClass);
    }

    @Override
    public void CreateStudent(Student student) {
        basicInfoMapper.CreateStudent(student);
    }


    @Override
    public List<Student> findClass(String classname) {
        return basicInfoMapper.findClass(classname);
    }

    @Override
    public void DeleteClass(String cname) {
        basicInfoMapper.DeleteClass(cname);
    }

    @Override
    public void DeleteMajor(Long maid) {
        basicInfoMapper.DeleteMajor(maid);
    }

    @Override
    public void DeleteYear(Long seid) {
        basicInfoMapper.DeleteYear(seid);
    }

    @Override
    public List<Sit> AllSit() {
        return basicInfoMapper.AllSit();
    }

    @Override
    public void InsertSit(Sit sit) {
     basicInfoMapper.InsertSit(sit);
    }

    @Override
    public void deleteSit(int id) {
 basicInfoMapper.deleteSit(id);
    }

    @Override
    public void updateSit(Sit sit) {
basicInfoMapper.updateSit(sit);
    }

    @Override
    public List<Major> findMajorAll() {
        return basicInfoMapper.findMajorAll();
    }


}
