package com.fetters.server;

import io.vertx.core.Vertx;

public class VertxHttpServer implements HttpServer {

    @Override
    public void doStart(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 http 服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        // 设置请求处理程序
        server.requestHandler(new HttpServerHandler());

        // 启动 HTTP 服务器并监听指定端口
        server.listen(port, res -> {
            if (res.succeeded()) {
                System.out.println("服务器正在监听端口" + port);
            } else {
                System.out.println("无法启动服务器：" + res.cause());
            }
        });
    }
}
