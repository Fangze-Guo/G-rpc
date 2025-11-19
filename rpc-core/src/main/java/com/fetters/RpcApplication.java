package com.fetters;

import com.fetters.config.RpcConfig;
import com.fetters.constant.RpcConstant;
import com.fetters.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * RPC 框架应用
 * 相当于 holder，存放了项目全局用到的变量
 */
@Slf4j
public class RpcApplication {

    private static volatile RpcConfig rpcConfig;

    /**
     * 框架初始化，支持传入自定义配置
     *
     * @param newRpcConfig 自定义RPC配置对象
     */
    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init, config = {}", newRpcConfig.toString());
    }

    /**
     * 初始化框架配置，加载默认配置文件
     * 如果配置文件加载失败，则使用默认配置
     */
    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            // 配置加载失败，使用默认值
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    /**
     * 获取RPC框架配置
     * 使用双检锁单例模式确保线程安全，如果配置未初始化则自动初始化
     *
     * @return RPC配置对象
     */
    public static RpcConfig getRpcConfig() {
        if (rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}

