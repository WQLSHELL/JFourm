package com.controller;

import com.model.HeadLineCategory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/headlineCategory")
public class HeadLineCategoryController extends BaseController{

    @RequestMapping("/addCategory.action")
    public String addCategory(HeadLineCategory headLineCategory) {

        return "/back/success";
    }

}
