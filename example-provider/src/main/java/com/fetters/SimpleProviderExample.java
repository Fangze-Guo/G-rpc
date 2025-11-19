package com.fetters;

import com.fetters.registry.LocalRegistry;
import com.fetters.server.HttpServer;
import com.fetters.server.VertxHttpServer;
import com.fetters.service.UserService;

/**
 * 简易服务提供者示例
 */
public class SimpleProviderExample {

    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动服务
        VertxHttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
