package com.dao;

import com.model.Question;
import com.model.User;
import com.system.web.Page;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends BaseDAO<User> {

    public User getByEmail(User user) {
        String hql = "FROM User WHERE email=?";
        return get(hql, user.getEmail());
    }

    public User getByNickName(User user) {
        String hql = "FROM User WHERE nickName=?";
        return get(hql, user.getNickName());
    }

}
