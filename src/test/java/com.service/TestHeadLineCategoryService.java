package com.service;

import com.system.config.spring.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class TestHeadLineCategoryService {

    @Autowired
    private HeadLineCategoryService headLineCategoryService;

    @Test
    public void testSave() {

//        for (int i = 1; i <= 7; i++) {
//            HeadLineCategory headLineCategory = new HeadLineCategory();
//            headLineCategory.setName("头条分类 " + i);
//            headLineCategoryService.save(headLineCategory);
//        }

    }

    @Test
    public void testDelete() {
//        HeadLineCategory headLineCategory = headLineCategoryService.get(2);
//        headLineCategoryService.delete(headLineCategory);
    }

}
