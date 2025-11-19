package com.fetters.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.fetters.model.RpcRequest;
import com.fetters.model.RpcResponse;
import com.fetters.serializer.JdkSerializer;
import com.fetters.serializer.Serializer;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 服务代理（JDK 动态代理）
 */
public class ServiceProxy implements InvocationHandler {

    /**
     * 调用代理
     *
     * @param proxy  代理对象
     * @param method 被调用的方法
     * @param args   方法参数
     * @return 远程调用结果
     * @throws Throwable 调用过程中可能抛出的异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        Serializer serializer = new JdkSerializer();

        // 构造RPC请求对象，包含服务名、方法名、参数类型和参数
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            // 序列化
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            // 发送HTTP POST请求到指定地址，并获取响应结果
            // todo 注意，这里地址被硬编码了（需要使用注册中心和服务发现机制解决）
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()) {
                byte[] result = httpResponse.bodyBytes();
                // 反序列化响应数据为RpcResponse对象，并返回其中的数据
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return rpcResponse.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
