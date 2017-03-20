package com.controller;

import com.alibaba.fastjson.JSON;
import com.exception.UserLoginException;
import com.exception.UserRegisterException;
import com.model.*;
import com.service.*;
import com.system.utils.EmailUtil;
import com.system.web.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
@SessionAttributes(names = {"user", "questionCategories", "headLines"})
public class UserController extends BaseController {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private HeadLineService headLineService;

    @Autowired
    private QuestionCategoryService questionCategoryService;

    /* 用户注册 */
    @RequestMapping(value = "/login/doRegister.action", method = RequestMethod.POST)
    public String userRegister(UserLogin userLogin, Model model) {

        try {
            // 1. 验证邮箱是否可用
            userLoginService.validateEmail(userLogin);

            // 2. 验证昵称是否已经被占用
            User user = userLogin.getUser();
            userService.validateNickName(user);

            // 3. 保存 User
            user.setEmail(userLogin.getEmail());
            userService.save(user);

            // 4. 保存 UserLogin
            String emailSign = EmailUtil.generateEmailSign();
            userLogin.setEmailSign(emailSign); // 设置邮箱签名
            userLoginService.save(userLogin);

            // 5. 发送邮箱验证邮件, 签名经过加密发送出去, 同密码一样, 使用 salt盐进行加密
            EmailUtil.sendAccountActivateEmail(userLogin.getEmail(), EmailUtil.encodeEmailSign(emailSign));
        } catch (UserRegisterException e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "/user/login/register";
        }
        return "redirect:/user/login/register_success";
    }

    /* 用户登录 */
    @RequestMapping(value = "/login/doLogin.action", method = RequestMethod.POST)
    public String userLogin(UserLogin userLogin, ModelMap model) {
        try {
            // 验证邮箱用户信息
            userLoginService.validateUserLoginInfo(userLogin);

            // 获取用户信息, 存入 Session
            User user = userService.getByEmail(userLogin);
            model.addAttribute("user", user);

        } catch (UserLoginException e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "/user/login/login";
        }
        return "redirect:/user/home.action";
    }

    /* 用户退出 */
    @RequestMapping("/logout.action")
    public String logout(HttpSession session, SessionStatus sessionStatus) {
        session.invalidate();;
        sessionStatus.setComplete();
        return "redirect:/site/index.action";
    }

    /* 网站主页 */
    @RequestMapping("/home.action")
    public String home(ModelMap modelMap) {
        // 获取最新的提问
        Page<Question> page = new Page<>(1);
        page = questionService.listLast(page);

        // 获取用户收藏的问题分类
        User user = (User) modelMap.get("user");
        Set<QuestionCategory> questionCategories = userService.getUserQuestionCategories(user);
        modelMap.addAttribute("questionCategories", questionCategories);

        // 获取头条 top 10
        List<HeadLine> headLines = headLineService.listTopTen();
        modelMap.addAttribute("headLines", headLines);

        modelMap.addAttribute("modelType", "headLineModel");
        modelMap.addAttribute("href", "/question/listLast.action");
        return "/index";
    }

    /* 用户关注一个问题分类 */
    @RequestMapping("/attention/addQuestionCategory.action")
    @ResponseBody
    public String attentionAddQuestionCategory(QuestionCategory questionCategory, ModelMap modelMap) {
        // 获取这个问题
        questionCategory = questionCategoryService.get(questionCategory.getId());

        // 添加用户收藏的问题分类
        User user = (User) modelMap.get("user");
        user.getQuestionCategories().add(questionCategory);
        userService.update(user);

        // 回复客户端
        response.setStatus(true);
        return JSON.toJSONString(response);
    }

    /* 用户取消关注一个问题分类 */
    @RequestMapping("/attention/deleteQuestionCategory.action")
    @ResponseBody
    public String attentionDeleteQuestionCategory(QuestionCategory questionCategory, ModelMap modelMap) {
        // 获取这个问题
        questionCategory = questionCategoryService.get(questionCategory.getId());

        // 添加用户收藏的问题分类
        User user = (User) modelMap.get("user");
        user.getQuestionCategories().remove(questionCategory);
        userService.update(user);

        // 回复客户端
        response.setStatus(true);
        return JSON.toJSONString(response);
    }

    /* 用户收藏一个问题 */
    @RequestMapping("/favoriteQuestion.action")
    @ResponseBody
    public String favoriteQuestion(Question question, ModelMap modelMap) {
        User user = (User) modelMap.get("user");
        user = userService.get(user.getId());
        user.getAttentionQuestions().add(question);
        userService.update(user);
        response.setStatus(true);
        response.setMessage("收藏成功.");
        return JSON.toJSONString(response);
    }


    // -------------------------- 页面跳转方法
    /* 跳转到登录页面 */
    @RequestMapping("/login/login.action")
    public String toRegisterPage() {
        return "/user/login/login";
    }

    /* 跳转到注册页面 */
    @RequestMapping("/login/register.action")
    public String toLoginPage() {
        return "/user/login/register";
    }

}
