package com.guet.bishe;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guet.bishe.entity.College;
import com.guet.bishe.mapper.CollegeMapper;
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
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        College college = new College();
        college.collegeId="1001";
//        college.collegeName="guet";
//        int insert = collegeMapper.insert(college);
//        System.out.println(insert);

        LambdaQueryWrapper<College> collegeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        collegeLambdaQueryWrapper.eq(College::getCollegeId,"1001");
        college.collegeName="guet2";
        int update = collegeMapper.update(college, collegeLambdaQueryWrapper);
        System.out.println(update);
    }
    @Test
    public void test(){
        System.out.println(Date.valueOf(LocalDateTime.now().toLocalDate()));
        System.out.println();
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
