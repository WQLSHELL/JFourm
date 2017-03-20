package com.dao;

import com.model.Question;
import com.model.QuestionCategory;
import com.model.User;
import com.system.web.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDAO extends BaseDAO<Question> {

    /**
     * 根据提问时间排序 (最新提问)
     */
    public List<Question> listPageSortByLast(Page<Question> page) {
        String hql = "FROM Question ORDER BY submitTime DESC";
        return listPage(hql, page.getPageNo(), page.getPageSize());
    }

    /**
     * 统计热门回答问题的数量
     */
    public Integer countHotAnswer() {
        String hql = "SELECT COUNT(*) FROM Question WHERE answerNum>10";
        return countWithCondition(hql);
    }

    /**
     * 根据回答数排序 (热门回答)
     */
    public List<Question> listPageSortByAnswer(Page<Question> page) {
        String hql = "FROM Question WHERE answerNum>5 ORDER BY answerNum DESC";
        return listPage(hql, page.getPageNo(), page.getPageSize());
    }

    /**
     * 统计没有回答问题的数量
     */
    public Integer countNoAnswer() {
        String hql = "SELECT COUNT(*) FROM Question WHERE answerNum=0";
        return countWithCondition(hql);
    }

    /**
     * 没有被回答的问题 (时间倒叙)
     */
    public List<Question> listPageNoAnswer(Page<Question> page) {
        String hql = "FROM Question WHERE answerNum=0 ORDER BY submitTime DESC";
        return listPage(hql, page.getPageNo(), page.getPageSize());
    }

    /**
     * 统计我的问题数量
     */
    public Integer countMyQuestion(Integer userId) {
        String hql = "SELECT COUNT(*) FROM Question WHERE user.id=?";
        return countWithCondition(hql, userId);
    }

    /**
     * 我的问题
     */
    public List<Question> listPageMyQuestion(Page<Question> page, User user) {
        String hql = "FROM Question WHERE user.id=? ORDER BY submitTime DESC";
        return listPageWithCondition(hql, page.getPageNo(), page.getPageSize(), user.getId());
    }

    /**
     * 统计某个类别下的问题数量, 按时间倒叙
     */
    public Integer countLastWithCategory(QuestionCategory questionCategory) {
        String hql = "SELECT COUNT(*) FROM Question WHERE category.id=?";
        return countWithCondition(hql, questionCategory.getId());
    }

    /**
     * 分页获取该类别下的问题, 按时间倒序
     */
    public List<Question> listPageWithCategorySortByLast(Page<Question> page, QuestionCategory questionCategory) {
        String hql = "FROM Question WHERE category.id=? ORDER BY submitTime DESC";
        return listPageWithCondition(hql, page.getPageNo(), page.getPageSize(), questionCategory.getId());
    }

    /**
     * 统计某个类别下的热门问题的数量
     */
    public Integer countHotWithCategory(QuestionCategory questionCategory) {
        String hql = "SELECT COUNT(*) FROM Question WHERE category.id=? AND answerNum>5";
        return countWithCondition(hql, questionCategory.getId());
    }

    /**
     * 分页获取该类别下的问题
     */
    public List<Question> listPageWithCategorySortByHot(Page<Question> page, QuestionCategory questionCategory) {
        String hql = "FROM Question WHERE category.id=? AND answerNum>5 ORDER BY answerNum DESC";
        return listPageWithCondition(hql, page.getPageNo(), page.getPageSize(), questionCategory.getId());
    }

    /**
     * 统计某个类别下的热门问题的数量
     */
    public Integer countNoWithCategory(QuestionCategory questionCategory) {
        String hql = "SELECT COUNT(*) FROM Question WHERE category.id=? AND answerNum=0";
        return countWithCondition(hql, questionCategory.getId());
    }

    /**
     * 分页获取该类别下的问题
     */
    public List<Question> listPageWithCategorySortByNo(Page<Question> page, QuestionCategory questionCategory) {
        String hql = "FROM Question WHERE category.id=? AND answerNum=0 ORDER BY submitTime DESC";
        return listPageWithCondition(hql, page.getPageNo(), page.getPageSize(), questionCategory.getId());
    }

}
