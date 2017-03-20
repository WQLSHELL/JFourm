package com.service;

import com.dao.BaseDAO;
import com.dao.QuestionDAO;
import com.model.Comment;
import com.model.Question;
import com.model.QuestionCategory;
import com.model.User;
import com.system.web.Page;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionService extends BaseService<Question> {

    @Autowired
    private QuestionDAO questionDAO;

    @Resource(name = "questionDAO")
    @Override
    public void setDao(BaseDAO<Question> dao) {
        super.setDao(dao);
    }

    @Override
    public Question get(Integer id) {
        Question question = super.get(id);
        Hibernate.initialize(question.getCategory());
        Hibernate.initialize(question.getUser());
        Hibernate.initialize(question.getComments());
        Hibernate.initialize(question.getComments());
        for (Comment comment : question.getComments())
            Hibernate.initialize(comment.getUser());
        return question;
    }

    /**
     * 根据提问时间排序 (最新提问)
     */
    public Page<Question> listLast(Page<Question> page) {
        // 1. 计算总条数
        Integer totalItem = questionDAO.countAll();
        page.setTotalItem(totalItem);

        // 2. 分页获取数据
        List<Question> list = questionDAO.listPageSortByLast(page);
        for (Question question : list) {
            Hibernate.initialize(question.getUser());
            Hibernate.initialize(question.getCategory());
        }
        page.setList(list);
        return page;
    }

    /**
     * 根据回答数排序 (热门回答)
     */
    public Page<Question> listHotAnswer(Page<Question> page) {
        // 1. 计算总条数
        Integer totalItem = questionDAO.countHotAnswer();
        page.setTotalItem(totalItem);

        // 2. 分页获取数据
        List<Question> list = questionDAO.listPageSortByAnswer(page);
        for (Question question : list) {
            Hibernate.initialize(question.getUser());
            Hibernate.initialize(question.getCategory());
        }
        page.setList(list);
        return page;
    }

    /**
     * 没有被回答的问题 (时间倒叙)
     */
    public Page<Question> listNoAnswer(Page<Question> page) {
        // 1. 计算总条数
        Integer totalItem = questionDAO.countNoAnswer();
        page.setTotalItem(totalItem);

        // 2. 分页获取数据
        List<Question> list = questionDAO.listPageNoAnswer(page);
        for (Question question : list) {
            Hibernate.initialize(question.getUser());
            Hibernate.initialize(question.getCategory());
        }
        page.setList(list);
        return page;
    }

    /**
     * 我的提问
     */
    public Page<Question> listMyQuestion(Page<Question> page, User user) {
        Integer totalItem = questionDAO.countMyQuestion(user.getId());
        page.setTotalItem(totalItem);
        List<Question> list = questionDAO.listPageMyQuestion(page, user);
        page.setList(list);
        return page;
    }

    /* 列出该分类下的所有问题, 按时间排序 */
    public Page<Question> listLastWithCategory(Page<Question> page, QuestionCategory questionCategory) {
        Integer totalItem = questionDAO.countLastWithCategory(questionCategory);
        page.setTotalItem(totalItem);
        List<Question> list = questionDAO.listPageWithCategorySortByLast(page, questionCategory);
        page.setList(list);
        for (Question question : list) {
            Hibernate.initialize(question.getUser());
            Hibernate.initialize(question.getCategory());
        }
        return page;
    }

    /* 列出该分类下的所有问题, 按热度倒叙 */
    public Page<Question> listHotWithCategory(Page<Question> page, QuestionCategory questionCategory) {
        Integer totalItem = questionDAO.countHotWithCategory(questionCategory);
        page.setTotalItem(totalItem);
        List<Question> list = questionDAO.listPageWithCategorySortByHot(page, questionCategory);
        page.setList(list);
        for (Question question : list) {
            Hibernate.initialize(question.getUser());
            Hibernate.initialize(question.getCategory());
        }
        return page;
    }

    /* 列出该分类下的所有问题, 按时间排序 */
    public Page<Question> listNoWithCategory(Page<Question> page, QuestionCategory questionCategory) {
        Integer totalItem = questionDAO.countNoWithCategory(questionCategory);
        page.setTotalItem(totalItem);
        List<Question> list = questionDAO.listPageWithCategorySortByNo(page, questionCategory);
        page.setList(list);
        for (Question question : list) {
            Hibernate.initialize(question.getUser());
            Hibernate.initialize(question.getCategory());
        }
        return page;
    }
}
