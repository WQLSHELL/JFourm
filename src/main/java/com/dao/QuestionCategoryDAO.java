package com.dao;

import com.model.QuestionCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionCategoryDAO extends BaseDAO<QuestionCategory> {

    public List<QuestionCategory> listTopTen() {
        String hql = "FROM QuestionCategory";
        return listPage(hql, 1, 10);
    }

}
