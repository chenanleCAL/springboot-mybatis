package com.chenanle.common;

/**
 * Created by chenanle on 2018/6/13.
 */
public class Const {

    public static final String CURRENT_USER = "current_user";

    public static final String EMAIL = "email";

    public static final String USERNAME = "username";

    /**
     * 角色
     */
    public interface Role {

        int ROLE_CUSTOMER = 0;//普通用户

        int ROLE_ADMIN = 1;//管理员
    }


}
