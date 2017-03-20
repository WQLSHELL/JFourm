package com.service;

import com.dao.BaseDAO;
import com.dao.UserLoginDAO;
import com.exception.UserLoginException;
import com.exception.UserRegisterException;
import com.model.UserLogin;
import com.system.utils.MD5Util;
import com.system.utils.PropertiesUtil;
import com.system.utils.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserLoginService extends BaseService<UserLogin> {

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Resource(name = "userLoginDAO")
    @Override
    public void setDao(BaseDAO<UserLogin> dao) {
        super.setDao(dao);
    }

    /**
     * 验证邮箱是否被注册
     */
    public void validateEmail(UserLogin userLogin) throws UserRegisterException {
        UserLogin dbUserLogin = userLoginDAO.getByEmail(userLogin);
        if (dbUserLogin != null) {
            throw new UserRegisterException("邮箱已经被占用.");
        }
    }

    /**
     * 验证用户登录信息是否正确
     * 不正确: 抛异常
     * 正确: 没反应
     */
    public void validateUserLoginInfo(UserLogin userLogin) throws UserLoginException {
        if (userLogin == null) {
            throw new UserLoginException("参数信息异常.");
        }

        UserLogin dbUserLogin = userLoginDAO.getByEmail(userLogin);
        if (dbUserLogin == null) {
            throw new UserLoginException("用户不存在.");
        } else if (dbUserLogin.getEmailValidated() == 0) {
            throw new UserLoginException("邮箱未通通过验证, 请登录邮箱验证.");
        } else {
            // 验证密码
            String salt = PropertiesUtil.getStringValue(WebConstants.ENCRYPTION_SALT);
            String generateMD5 = MD5Util.generateMD5(userLogin.getPassword() + salt);
            if (!dbUserLogin.getPassword().equals(generateMD5)) {
                throw new UserLoginException("密码不正确.");
            }
        }
    }

    /**
     * 根据邮箱获取一个实体
     */
    public UserLogin getByEmail(UserLogin userLogin) {
        return userLoginDAO.getByEmail(userLogin);
    }

    /**
     * 用户注册
     */
    @Transactional
    public void save(UserLogin userLogin) {
        String salt = PropertiesUtil.getStringValue(WebConstants.ENCRYPTION_SALT);
        String passwordSalt = userLogin.getPassword() + salt;
        String passwordMD5 = MD5Util.generateMD5(passwordSalt);
        userLogin.setPassword(passwordMD5);
        userLogin.setEmailValidated(0);
        userLogin.setUserSource(1);
        super.save(userLogin);
    }

    /**
     * 激活邮箱用户
     */
    @Transactional
    public void activeEmail(String emailSign) throws UserLoginException {
        UserLogin userLogin = userLoginDAO.getByEmailSign(emailSign);
        if (userLogin == null) {
            throw new UserLoginException("邮箱签名不正确!");
        }
        userLogin.setEmailValidated(1);
        super.update(userLogin);
    }

}
