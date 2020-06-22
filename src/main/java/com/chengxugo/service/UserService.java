package com.chengxugo.service;

import com.chengxugo.pojo.*;
import com.chengxugo.pojo.Point1;
import com.chengxugo.pojo.Teacher;
import com.chengxugo.pojo.Tk.Choice;
import com.chengxugo.pojo.Point2;
import com.chengxugo.pojo.User;

import java.util.List;

/**
 * @author ds
 * @date 业务层
 */
public interface UserService extends BaseService<User> {
    void updateuser(User user);
    //void CreateAdmin(User user);
    User login(String username);
    List<Teacher> findTeacherAll();
    void update(Teacher teacher);
    void create(Teacher teacher);
    List<Point1> findPoint1All();
    void createpoint1(Point1 point1 );
    void createpoint2(Point2 point2);
    void deletep1(Long id);
    void deletepp1(Long id);
    void deletep2(Long bid);
    List<Point1>Point1All();
    List<Choice> findChoiceAll();
    void createchoice(Choice choice);
    Choice findBychId(Long id);
    Teacher findById(Long id);
    void updatech(Choice choice);
    void deletech(Long id);
    User findByUsername(String username);

    void deleteManyChoice(String chk_value);

}
