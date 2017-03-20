package com.service;

import com.model.QuestionCategory;
import com.model.User;
import com.system.config.spring.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class TestUserService {

    @Autowired
    private UserService userService;
    @Autowired
    private QuestionCategoryService questionCategoryService;

    @Test
    public void testSave() {
        QuestionCategory questionCategory = questionCategoryService.get(1);
        User user = new User();
        user.setNickName("Tom");
        user.setEmail("tom@gmail.com");
        user.getQuestionCategories().add(questionCategory);
        userService.save(user);
    }

    @Test
    public void testUpdate() {
        User user = userService.get(1);
        user.setGender("Male");
        userService.update(user);
    }

    @Test
    public void testGet() {
    }

}
