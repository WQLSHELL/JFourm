package com.service;

import com.dao.BaseDAO;
import com.dao.UserDAO;
import com.exception.UserRegisterException;
import com.model.Question;
import com.model.QuestionCategory;
import com.model.User;
import com.model.UserLogin;
import com.system.web.Page;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Set;

@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserDAO userDAO;

    @Resource(name = "userDAO")
    @Override
    public void setDao(BaseDAO<User> dao) {
        super.setDao(dao);
    }

    /* 检测昵称是否被占用 */
    public void validateNickName(User user) throws UserRegisterException {
        User dbUser = userDAO.getByNickName(user);
        if (dbUser != null) {
            throw new UserRegisterException("昵称已经被占用.");
        }
    }

    /* 通过邮箱获取一个实体 */
    public User getByEmail(UserLogin userLogin) {
        User user = new User();
        user.setEmail(userLogin.getEmail());
        return userDAO.getByEmail(user);
    }

    /* 获取用户收藏的问题分类 */
    public Set<QuestionCategory> getUserQuestionCategories(User user) {
        User dbUser = userDAO.get(user.getId());
        // 实例化代理对象
        Hibernate.initialize(dbUser.getQuestionCategories());
        return dbUser.getQuestionCategories();
    }

    /* 分页获取用户关注的问题 */
    public Page<Question> getUserAttentionQuestion(User user, Page<Question> page) {
        user = userDAO.get(user.getId());
        Set<Question> attentionQuestions = user.getAttentionQuestions();
        for (Question question : attentionQuestions)
            Hibernate.initialize(question);
        page.setList(new ArrayList<>(attentionQuestions));
        // TODO 将要分页
        return page;
    }

    @Transactional
    @Override
    public void update(User user) {
        User dbUser = userDAO.get(user.getId());
        dbUser.setGender(user.getGender());
        dbUser.setBirthDay(user.getBirthDay());
        dbUser.setPersonUrl(user.getPersonUrl());
        dbUser.setMobile(user.getMobile());
        dbUser.setCurrentTown(user.getCurrentTown());
        dbUser.setSchool(user.getSchool());
        dbUser.setMajor(user.getMajor());
        dbUser.setComments(user.getComments());
        dbUser.setJob(user.getJob());
        super.update(dbUser);
    }
}
