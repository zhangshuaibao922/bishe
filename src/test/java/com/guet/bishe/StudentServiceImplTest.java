package com.guet.bishe;

import com.guet.bishe.entity.Student;
import com.guet.bishe.mapper.StudentMapper;
import com.guet.bishe.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StudentServiceImplTest {

    @Autowired
    private StudentMapper studentMapper;

    private Student student;

    @Autowired
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        student = new Student();

        student.setStudentId("1111111111");
        student.setStudentPassword("123456");
        student.setStudentName("小三");
        student.setIdCardNo("330103199901010000");
        student.setMobilePhone("13000000000");
        student.setGender("1");
        student.setStatus("1");
        student.setAuthorityId("1");
        student.setDescription("好人一生平安");

        // Set other required fields for the Student entity
    }

    @Test
    void testInsert() {
        // Arrange (mocking not needed since we're using Spring Test with real beans)

        // Act
        boolean result = studentService.insert(student);

        // Assert
        assertTrue(result, "Insert should return true");
    }
}
