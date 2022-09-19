package com.fc.springboot;


import com.alibaba.csp.sentinel.cluster.server.SentinelDefaultTokenServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * @author wangmeng
 * @date created in 11:07 上午 2019/9/19
 * @modified by
 */

@Slf4j
@Order()
@Component
public class SentinelCommandLineRunner implements CommandLineRunner {


    @Autowired
    private TokenServerConfig tokenServer;

    @Override
    public void run(String... args) throws Exception {
        log.info("SentinelCommandLineRunner start");
        tokenServer.loadServerConfig();
        tokenServer.initClusterFlowSupplier();
        new SentinelDefaultTokenServer().start();
    }
}
