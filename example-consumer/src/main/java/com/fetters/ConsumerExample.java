package com.fetters;

import com.fetters.config.RpcConfig;
import com.fetters.utils.ConfigUtils;

/**
 * 消费者示例
 */
public class ConsumerExample {

    public static void main(String[] args) {
        RpcConfig rpcConfig = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpcConfig);
    }
}
