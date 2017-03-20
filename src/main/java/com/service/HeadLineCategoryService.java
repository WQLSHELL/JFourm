package com.service;

import com.dao.BaseDAO;
import com.model.HeadLineCategory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HeadLineCategoryService extends BaseService<HeadLineCategory> {

    @Resource(name = "headLineCategoryDAO")
    @Override
    public void setDao(BaseDAO<HeadLineCategory> dao) {
        super.setDao(dao);
    }
}
