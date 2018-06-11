package com.chenanle.controller.protal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenanle on 2018/6/8.
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "hello")
    @ResponseBody
    public String say() {
        return "hello springboot!";
    }

}