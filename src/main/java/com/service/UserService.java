package com.service;

import com.dao.BaseDAO;
import com.dao.UserDAO;
import com.exception.UserRegisterException;
import com.model.QuestionCategory;
import com.model.User;
import com.model.UserLogin;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
