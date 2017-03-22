package com.controller;

import com.model.Question;
import com.model.QuestionCategory;
import com.model.User;
import com.service.QuestionCategoryService;
import com.service.QuestionService;
import com.system.web.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/question")
@SessionAttributes(names = {"user"})
public class QuestionController extends BaseController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionCategoryService questionCategoryService;

    /* 通过最新提问, 进行翻页 */
    @RequestMapping("/listLast.action")
    public String listLastQuestion(@RequestParam(name = "pageNo", required = false) Integer pageNo, ModelMap modelMap) {
        Page<Question> page;
        if (pageNo == null) {
            page = new Page<>(1);
        } else {
            page = new Page<>(pageNo);
        }
        page = questionService.listLast(page);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("href", "/question/listLast.action");
        modelMap.addAttribute("type", "last");
        return "/index";
    }

    /* 通过热门回答, 进行翻页 */
    @RequestMapping("/listHotAnswer.action")
    public String listHostAnswer(@RequestParam(name = "pageNo", required = false) Integer pageNo, ModelMap modelMap) {
        Page<Question> page;
        if (pageNo == null) {
            page = new Page<>(1);
        } else {
            page = new Page<>(pageNo);
        }
        page = questionService.listHotAnswer(page);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("href", "/question/listHotAnswer.action");
        modelMap.addAttribute("type", "hot");
        return "/index";
    }

    /* 通过等待回答, 进行翻页 */
    @RequestMapping("/listNoAnswer.action")
    public String listNoAnswer(@RequestParam(name = "pageNo", required = false) Integer pageNo, ModelMap modelMap) {
        Page<Question> page;
        if (pageNo == null) {
            page = new Page<>(1);
        } else {
            page = new Page<>(pageNo);
        }
        page = questionService.listNoAnswer(page);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("href", "/question/listNoAnswer.action");
        modelMap.addAttribute("type", "no");
        return "/index";
    }

    /* 获取单个问题的详细内容 */
    @RequestMapping("/questionDetail.action")
    public String questionDetail(Question question, ModelMap modelMap) {
        // 获取问题明细
        question = questionService.get(question.getId());
        modelMap.addAttribute("question", question);
        return "/question_detail";
    }

    /* 获取某个分类下的所有问题 */
    @RequestMapping("/listLastCategoryQuestion.action")
    public String listLastCategoryQuestion(@RequestParam(name = "pageNo", required = false) Integer pageNo, Integer categoryId, ModelMap modelMap) {

        // 获取分类信息
        QuestionCategory questionCategory = questionCategoryService.get(categoryId);
        modelMap.addAttribute("questionCategory", questionCategory);

        // 分页获取问题
        Page<Question> page;
        if (pageNo == null) {
            page = new Page<>(1);
        } else {
            page = new Page<>(pageNo);
        }
        page = questionService.listLastWithCategory(page, questionCategory);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("href", "/question/listLastCategoryQuestion.action");
        modelMap.addAttribute("type", "last");
        return "/list_question";
    }

    /* 获取某个分类下的所有问题 */
    @RequestMapping("/listHotCategoryQuestion.action")
    public String listHotCategoryQuestion(@RequestParam(name = "pageNo", required = false) Integer pageNo, Integer categoryId, ModelMap modelMap) {

        // 获取分类信息
        QuestionCategory questionCategory = questionCategoryService.get(categoryId);
        modelMap.addAttribute("questionCategory", questionCategory);

        // 分页获取问题
        Page<Question> page;
        if (pageNo == null) {
            page = new Page<>(1);
        } else {
            page = new Page<>(pageNo);
        }
        page = questionService.listHotWithCategory(page, questionCategory);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("href", "/question/listHotCategoryQuestion.action");
        modelMap.addAttribute("type", "hot");
        return "/list_question";
    }

    /* 获取某个分类下的所有问题 */
    @RequestMapping("/listNoCategoryQuestion.action")
    public String listNoCategoryQuestion(@RequestParam(name = "pageNo", required = false) Integer pageNo, Integer categoryId, ModelMap modelMap) {

        // 获取分类信息
        QuestionCategory questionCategory = questionCategoryService.get(categoryId);
        modelMap.addAttribute("questionCategory", questionCategory);

        // 分页获取问题
        Page<Question> page;
        if (pageNo == null) {
            page = new Page<>(1);
        } else {
            page = new Page<>(pageNo);
        }
        page = questionService.listNoWithCategory(page, questionCategory);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("href", "/question/listNoCategoryQuestion.action");
        modelMap.addAttribute("type", "no");
        return "/list_question";
    }

    /* 提问题 */
    @RequestMapping("/askQuestion.action")
    public String askQuestion(ModelMap modelMap) {
        List<QuestionCategory> questionCategories = questionCategoryService.listAll();
        modelMap.addAttribute("questionCategories", questionCategories);
        return "/ask_question";
    }

    /* 我的提问 */
    @RequestMapping("/listMyQuestion.action")
    public String listMyQuestion(@RequestParam(name = "pageNo", required = false) Integer pageNo, ModelMap modelMap) {
        Page<Question> page;
        if (pageNo == null) {
            page = new Page<>(1);
        } else {
            page = new Page<>(pageNo);
        }
        User user = (User) modelMap.get("user");
        page = questionService.listMyQuestion(page, user);
        modelMap.addAttribute("page", page);
        return "/user/profile/my_ask";
    }

    /* 关闭问题 */
    @RequestMapping("/closeQuestion.action")
    @ResponseBody
    public String closeQuestion(Integer questionId) {
        Question question = questionService.get(questionId);
        question.setQuestionState(0);
        questionService.update(question);
        return "success";
    }

    /* 删除问题 */
    @RequestMapping("/deleteQuestion.action")
    @ResponseBody
    public String deleteQuestion(Integer questionId) {
        Question question = questionService.get(questionId);
        questionService.delete(question);
        return "success";
    }

    /* 列出所有问题 */
    @RequestMapping("/listAll.action")
    public String listAll(ModelMap modelMap) {
        List<Question> questions = questionService.listAll();
        modelMap.addAttribute("questions", questions);
        return "/back/list_question";
    }

}
