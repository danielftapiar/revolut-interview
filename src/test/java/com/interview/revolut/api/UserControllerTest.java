package com.interview.revolut.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;


//@SpringBootTest(classes = UserController.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.config.additional-location=classpath:application.yaml")

public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

}