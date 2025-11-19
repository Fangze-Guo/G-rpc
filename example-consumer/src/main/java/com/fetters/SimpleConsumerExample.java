package com.fetters;

import com.fetters.model.User;
import com.fetters.proxy.ServiceProxyFactory;
import com.fetters.service.UserService;

/**
 * 简易服务消费者示例
 */
public class SimpleConsumerExample {

    public static void main(String[] args) {
        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);

        User user = new User();
        user.setName("fetters");

        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}
