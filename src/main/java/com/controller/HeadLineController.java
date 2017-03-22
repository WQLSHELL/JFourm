package com.controller;

import com.model.HeadLine;
import com.service.HeadLineService;
import com.system.web.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/headLine")
@SessionAttributes(names = {"modelType"})
public class HeadLineController extends BaseController {

    @Autowired
    private HeadLineService headLineService;

    /* 分页列出最新的头条 */
    @RequestMapping("/listLast.action")
    public String listLast(@RequestParam(name = "pageNo", required = false) Integer pageNo, ModelMap modelMap) {
        Page<HeadLine> page;
        if (pageNo == null) {
            page = new Page<>(1);
        } else {
            page = new Page<>(pageNo);
        }
        page = headLineService.listLast(page);

        modelMap.addAttribute("page", page);
        modelMap.addAttribute("href", "/headLine/listLast.action");
        modelMap.addAttribute("modelType", "headLineModel");
        modelMap.addAttribute("type", "last");
        return "/list_head_line";
    }

    /* 分页列出最热的头条 */
    @RequestMapping("/listHot.action")
    public String listHot(@RequestParam(name = "pageNo", required = false) Integer pageNo, ModelMap modelMap) {
        Page<HeadLine> page;
        if (pageNo == null) {
            page = new Page<>(1);
        } else {
            page = new Page<>(pageNo);
        }
        page = headLineService.listHot(page);

        modelMap.addAttribute("page", page);
        modelMap.addAttribute("href", "/headLine/listHot.action");
        modelMap.addAttribute("type", "hot");
        return "/list_head_line";
    }

    /* 发布头条 */
    @RequestMapping("/addHeadLine.action")
    public String addHeadLine(HeadLine headLine, ModelMap modelMap) {
        // TODO 保存头条， 默认为未审核
        return "/send_head_line";
    }

    /* 列出待审核的头条 */
    @RequestMapping("/listUnReview.action")
    public String listUnReview(ModelMap modelMap) {
        List<HeadLine> headLines = headLineService.listUnReview();
        modelMap.addAttribute("headLines", headLines);
        return "/back/list_head_line";
    }

    /* 审核通过头条 */
    @RequestMapping("/reviewPassed.action")
    @ResponseBody
    public String reviewPassed(Integer headLineId) {
        HeadLine headLine = headLineService.get(headLineId);
        headLine.setStatus(1);
        headLineService.update(headLine);
        return "success";
    }

}
