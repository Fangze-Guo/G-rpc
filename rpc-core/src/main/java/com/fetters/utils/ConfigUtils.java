package com.fetters.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/**
 * 配置工具类
 */
public class ConfigUtils {

    /**
     * 加载配置对象
     *
     * @param tClass 配置对象的类类型
     * @param prefix 配置项前缀
     * @param <T>    配置对象泛型类型
     * @return 配置对象实例
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix) {
        return loadConfig(tClass, prefix, "");
    }

    /**
     * 加载配置对象，支持区分环境
     *
     * @param tClass      配置对象的类类型
     * @param prefix      配置属性前缀
     * @param environment 环境标识符，用于加载不同环境的配置文件
     * @param <T>         配置对象泛型类型
     * @return 返回指定类型的配置对象实例
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        // 构建配置文件名，根据环境标识符决定是否添加环境后缀
        StringBuilder configFileBuilder = new StringBuilder("application");
        if (StrUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }
        configFileBuilder.append(".properties");

        // 加载配置文件并转换为指定类型的对象
        Props props = new Props(configFileBuilder.toString());
        return props.toBean(tClass, prefix);
    }

}
