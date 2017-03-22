package com.dao;

import com.model.HeadLine;
import com.system.web.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HeadLineDAO extends BaseDAO<HeadLine> {

    /* 获取最新的头条 */
    public List<HeadLine> listPageSortByLast(Page<HeadLine> page) {
        String hql = "FROM HeadLine ORDER BY submitTime DESC";
        return listPage(hql, page.getPageNo(), page.getPageSize());
    }

    /* 统计最热头条的数量 */
    public Integer countHot() {
        String hql = "SELECT COUNT(*) FROM HeadLine WHERE viewNum>5";
        return countWithCondition(hql);
    }

    /* 获取最热的头条 */
    public List<HeadLine> listPageSortByHot(Page<HeadLine> page) {
        String hql = "FROM HeadLine ORDER BY viewNum DESC";
        return listPage(hql, page.getPageNo(), page.getPageSize());
    }

    /* 获取前 10 条头条 */
    public List<HeadLine> listTopTen() {
        String hql = "FROM HeadLine ORDER BY viewNum DESC";
        return listPage(hql, 1, 10);
    }

    /* 列出未审核的头条 */
    public List<HeadLine> listUnReview() {
        String hql = "FROM HeadLine WHERE status=0";
        return listWithCustom(hql);
    }
}
