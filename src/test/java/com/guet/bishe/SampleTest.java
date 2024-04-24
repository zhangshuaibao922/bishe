package com.guet.bishe;


import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guet.bishe.entity.*;
import com.guet.bishe.mapper.AuthorityMapper;
import com.guet.bishe.mapper.CollegeMapper;
import com.guet.bishe.mapper.LessonMapper;
import com.guet.bishe.mapper.TeacherMapper;
import com.guet.bishe.service.CollegeService;
import com.guet.bishe.service.LoginService;
import com.guet.bishe.service.ScoreService;
import com.guet.bishe.service.TeacherService;
import com.guet.bishe.service.impl.LessonServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static net.sf.jsqlparser.parser.feature.Feature.insert;

@SpringBootTest
public class SampleTest {
    @Autowired
    private CollegeMapper collegeMapper;
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private ScoreService scoreService;
    @Test
    public void testSelect() {
        Exam exam = new Exam();
//        exam.setExamId("17845285671407616");
//        Response<List<StudentScoreDto>> listResponse = scoreService.queryByAllScore(exam);
        System.out.println(123);
//        System.out.println(("----- selectAll method test ------"));
//        Teacher teacher = new Teacher();
//        teacher.setCollegeId("10");
//        teacher.setTeacherId("11111");
//        teacher.setTeacherName("郑培林");
//        teacher.setTeacherPassword("111");
//        teacher.setIdCardNo("411025200109224018");
//        teacher.setMobilePhone("16696838939");
//        teacher.setAuthorityId("2");
//        teacher.setStatus("1");
//        boolean insert1 = teacherService.insert(teacher);
//        System.out.println(insert1);

//        College college = new College();
//        college.collegeId=UUID.randomUUID().toString();
//        college.collegeName="光电工程学院";
//        int insert = collegeMapper.insert(college);
//        System.out.println(insert);

//        LambdaQueryWrapper<College> collegeLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        collegeLambdaQueryWrapper.eq(College::getCollegeId,"1001");
//        college.collegeName="guet2";
//        int update = collegeMapper.update(college, collegeLambdaQueryWrapper);
//        System.out.println(update);
//        Authority authority = new Authority();
//        authority.setAuthorityId("4");
//        authority.setAuthorityRole("STUDENT");
//        int insert = authorityMapper.insert(authority);
//        System.out.println(insert);
    }
    @Autowired
    LoginService loginService;
    @Autowired
    CollegeService collegeService;
    @Autowired
    private LessonMapper lessonMapper;
    @Autowired
    private LessonServiceImpl lessonService;
    @Test
    public void test(){
//        Lesson lesson = lessonService.queryById("123");
//        System.out.println();

    }

}
