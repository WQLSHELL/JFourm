package com.service;

import com.dao.BaseDAO;
import com.dao.CommentDAO;
import com.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CommentService extends BaseService<Comment> {

    @Autowired
    private CommentDAO commentDAO;

    @Resource(name = "commentDAO")
    @Override
    public void setDao(BaseDAO<Comment> dao) {
        super.setDao(dao);
    }

    /* 评论点赞 */
    @Transactional
    public void addLikeNum(Comment comment) {
        comment = commentDAO.get(comment.getId());
        comment.setLikeNum(comment.getLikeNum() + 1);
        commentDAO.update(comment);
    }

}
