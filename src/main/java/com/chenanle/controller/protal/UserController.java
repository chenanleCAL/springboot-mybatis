package com.chenanle.controller.protal;

import com.chenanle.common.Const;
import com.chenanle.common.ServerResponse;
import com.chenanle.common.TokenCache;
import com.chenanle.pojo.User;
import com.chenanle.service.IUserService;
import com.chenanle.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by chenanle on 2018/6/8.
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService iUserService;


    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @GetMapping(value = "login")
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }


    /**
     * 用户退出
     *
     * @param session
     * @return
     */
    @GetMapping(value = "logout")
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }


    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @PostMapping(value = "register")
    @ResponseBody
    public ServerResponse<String> register(User user) {
        return iUserService.register(user);
    }


    /**
     * 用户邮箱和用户名的校验
     *
     * @param str
     * @param type
     * @return
     */
    @GetMapping(value = "check_valid")
    @ResponseBody
    public ServerResponse<String> checkValid(String str, String type) {
        return iUserService.checkValid(str, type);
    }


    /**
     * 获取登录用户信息
     *
     * @param session
     * @return
     */
    @GetMapping(value = "get_user_info")
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
        }
        return ServerResponse.createBySuccessData(user);
    }


    /**
     * 忘记密码
     *
     * @param username
     * @return
     */
    @GetMapping(value = "forget_get_question")
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username) {
        return iUserService.forgetGetQuestion(username);
    }


    /**
     * 提交问题答案获取token
     *
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @GetMapping(value = "check_question_and_answer")
    @ResponseBody
    public ServerResponse<String> checkQuestionAndAnswer(String username, String question, String answer) {
        return iUserService.checkQuestionAndAnswer(username, question, answer);
    }


    /**
     * 忘记密码的重置密码
     *
     * @param username
     * @param newPassword
     * @param forgetToken
     * @return
     */
    @PutMapping(value = "forget_reset_password")
    @ResponseBody
    public ServerResponse<String> forgetResetPassword(String username, String newPassword, String forgetToken) {
        return iUserService.forgetResetPassword(username, newPassword, forgetToken);
    }


    /**
     * 登录中的重置密码
     *
     * @param oldPassword
     * @param newPassword
     * @param session
     * @return
     */
    @PutMapping(value = "reset_password")
    @ResponseBody
    public ServerResponse<String> resetPassword(String oldPassword, String newPassword, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iUserService.resetPassword(oldPassword, newPassword, user);
    }


}