package com.chenanle.service;

import com.chenanle.common.ServerResponse;
import com.chenanle.pojo.User;

/**
 * Created by chenanle on 2018/6/8.
 */
public interface IUserService {

    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str, String type);

}
