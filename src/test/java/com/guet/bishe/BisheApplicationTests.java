package com.guet.bishe;

import com.guet.bishe.Utils.MD5;
import com.guet.bishe.entity.Student;
import com.guet.bishe.entity.Teacher;
import com.guet.bishe.mapper.StudentMapper;
import com.guet.bishe.mapper.TeacherMapper;
import com.guet.bishe.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BisheApplicationTests {

    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    StudentMapper studentMapper;
    @Test
    void contextLoads() {
        List<Student> teachers = studentMapper.selectList(null);
        for (Student data : teachers){
            data.setStudentPassword(MD5.encrypt3ToMD5(data.getStudentPassword()));
            studentMapper.updateById(data);
        }

    }

}
