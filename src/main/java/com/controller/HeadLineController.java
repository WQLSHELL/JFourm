package com.controller;

import com.model.HeadLine;
import com.service.HeadLineService;
import com.system.web.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

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
        return "/send_head_line";
    }

}
