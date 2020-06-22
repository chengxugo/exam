package com.chengxugo.mapper;

import com.chengxugo.pojo.*;
import com.chengxugo.pojo.Point1;
import com.chengxugo.pojo.Point2;
import com.chengxugo.pojo.Teacher;
import com.chengxugo.pojo.Tk.Choice;
//import com.chengxugo.pojo.Tk.ReadProgram;
import com.chengxugo.pojo.User;

import java.util.List;

/**
 * @author ds
 */
public interface UserMapper {
   void CreateAdmin(User user);
    void updateuser(User user);
    User login(String username);
    void create(Teacher teacher);
    void delete(Long id);
    void update(Teacher teacher);
    Teacher findById(Long id);
    List<Teacher> findTeacherAll();
    List<Point1> findPoint1All();
    void createpoint1(Point1 point1);
    void createpoint2(Point2 point2);
    void deletep1(Long id);
    void deletepp1(Long id);
    void deletep2(Long bid);
    List<Point1>Point1All();
    void createchoice(Choice choice);
     Choice findBychId(Long id);
    List<Choice> findChoiceAll();
     void updatech(Choice choice);
    void deletech(Long id);
 User findUsername (String username);
    //  List<Point1> findPoint();
    void deleteManyChoice(String chk_value);
}
