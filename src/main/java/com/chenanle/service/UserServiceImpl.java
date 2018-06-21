package com.chenanle.service;

import com.chenanle.common.Const;
import com.chenanle.common.ServerResponse;
import com.chenanle.dao.UserMapper;
import com.chenanle.pojo.User;
import com.chenanle.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenanle on 2018/6/8.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if (resultCount <= 0) {
            return ServerResponse.createByErrorMessage("用户名不存在!");
        }
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username, md5Password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户密码不正确!");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccessMessageAndData("登录成功!", user);
    }

    @Override
    public ServerResponse<String> register(User user) {


        return null;
    }

    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        if (StringUtils.isNotBlank(type) && type !=null) {
            if (Const.USERNAME.equals(type)) {
                int resultCount = userMapper.checkUsername(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("用户名已经存在!");
                }
            }
            if (Const.EMAIL.equals(type)) {
                int resultCount = userMapper.checkEmail(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("邮箱已存在!");
                }
            } else {
                return ServerResponse.createByErrorMessage("参数错误");
            }
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }
}
