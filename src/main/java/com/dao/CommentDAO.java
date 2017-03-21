package com.dao;

import com.model.Comment;
import com.model.User;
import com.system.web.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAO extends BaseDAO<Comment> {

    public List<Comment> listMyAnswer(User user, Page<Comment> page) {
        String hql = "FROM Comment WHERE user.id=? ORDER BY submitTime";
        return listWithCustom(hql, user.getId());
    }

}
