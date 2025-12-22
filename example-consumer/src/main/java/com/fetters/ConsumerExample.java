package com.fetters;

import com.fetters.model.User;
import com.fetters.proxy.ServiceProxyFactory;
import com.fetters.service.UserService;

/**
 * 消费者示例
 */
public class ConsumerExample {

    public static void main(String[] args) {
        // 获取 UserService 代理对象
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("fetters");

        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println("用户名：" + newUser.getName());
        } else {
            System.out.println("用户不存在");
        }
        long number = userService.getNumber();
        System.out.println("number：" + number);
    }
}
