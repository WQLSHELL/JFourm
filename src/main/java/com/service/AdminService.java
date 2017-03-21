package com.service;

import com.dao.AdminDAO;
import com.dao.BaseDAO;
import com.exception.AdminException;
import com.model.Admin;
import com.system.utils.MD5Util;
import com.system.utils.PropertiesUtil;
import com.system.utils.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminService extends BaseService<Admin> {

    @Autowired
    private AdminDAO adminDAO;

    @Resource(name = "adminDAO")
    @Override
    public void setDao(BaseDAO<Admin> dao) {
        super.setDao(dao);
    }

    /* 验证管理员登录信息 */
    public void validateInfo(Admin admin) throws AdminException {
        Admin dbAdmin = adminDAO.getByUserName(admin.getUserName());
        String salt = PropertiesUtil.getStringValue(WebConstants.ENCRYPTION_SALT);
        String generateMD5 = MD5Util.generateMD5(admin.getPassword() + salt);
        if (!dbAdmin.getPassword().equals(generateMD5)) {
            throw new AdminException("密码不正确");
        }
    }
}
