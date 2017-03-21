package com.controller;

import com.exception.AdminException;
import com.model.Admin;
import com.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    /* 跳转到登录页面 */
    @RequestMapping("/login.action")
    public String toLoginPage() {
        return "/back/login";
    }

    /* 管理员进行登录 */
    @RequestMapping("/doLogin.action")
    public String login(Admin admin, ModelMap modelMap) {
        try {
            adminService.validateInfo(admin);
        } catch (AdminException e) {
            modelMap.addAttribute("errorMsg", e.getMessage());
            return "/back/login";
        }
        return "/back/main";
    }

}
