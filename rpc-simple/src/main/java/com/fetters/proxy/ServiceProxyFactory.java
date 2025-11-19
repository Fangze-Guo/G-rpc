package com.fetters.proxy;

import java.lang.reflect.Proxy;

/**
 * 服务代理工厂（用于创建代理对象）
 */
public class ServiceProxyFactory {

    /**
     * 根据服务类获取代理对象
     *
     * @param serviceClass 服务接口类，用于创建代理对象
     * @param <T>          泛型类型，表示服务接口的类型
     * @return 返回指定服务类的代理对象实例
     */
    public static <T> T getProxy(Class<T> serviceClass) {
        // 使用JDK动态代理创建服务代理对象
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy());
    }
}
