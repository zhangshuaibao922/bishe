package com.guet.bishe;


import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guet.bishe.entity.*;
import com.guet.bishe.mapper.AuthorityMapper;
import com.guet.bishe.mapper.CollegeMapper;
import com.guet.bishe.mapper.TeacherMapper;
import com.guet.bishe.service.LoginService;
import com.guet.bishe.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        Teacher teacher = new Teacher();
        teacher.setCollegeId("10");
        teacher.setTeacherId("20100001");
        teacher.setTeacherName("杜春城");
        teacher.setTeacherPassword("root");
        teacher.setIdCardNo("411025200109224018");
        teacher.setMobilePhone("16696838939");
        teacher.setAuthorityId("3");
        teacher.setStatus("1");
//        Teacher insert = teacherService.insert(teacher);
//        System.out.println(insert);

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
    @Test
    public void test(){
        LoginDto loginDto = new LoginDto();
        loginDto.setIdentity("teacher");
        loginDto.setUsername("20030100001");
        loginDto.setPassword("root");
        User login = loginService.login(loginDto);
        System.out.println(login);
    }

}
