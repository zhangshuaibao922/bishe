package com.guet.bishe;

import com.guet.bishe.Utils.EmailUtil;
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

    @Autowired
    private StudentService studentService;
    @Autowired
    private EmailUtil emailUtil;

    @Test
    void sendSuccess() {
        // Given
        String[] emails =new String[]{"2742520302@qq.com","3425178647@qq.com"};
        // When
//        emailUtil.sendSuccess(emails);
    }

    @Test
    void testInsert() {
//        // Arrange (mocking not needed since we're using Spring Test with real beans)
//
//        // Act
//        boolean result = studentService.insert(student);
//
//        // Assert
//        assertTrue(result, "Insert should return true");
    }
}
