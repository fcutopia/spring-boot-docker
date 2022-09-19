package com.fc.springboot;

import com.alibaba.csp.sentinel.cluster.flow.rule.ClusterFlowRuleManager;
import com.alibaba.csp.sentinel.cluster.server.config.ClusterServerConfigManager;
import com.alibaba.csp.sentinel.cluster.server.config.ServerTransportConfig;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;

/**
 * @ClassName TokenServerConfig
 * @Description
 * @Author fc
 * @Date 2022/9/8 4:45 下午
 * @Version 1.0
 **/
@Component
public class TokenServerConfig {


    public static String APP_NAME= "appA";
    public static final int CLUSTER_SERVER_PORT = 8080;

    private static final String REMOTE_ADDRESS = "106.15.179.151:8848";
    private static final String GROUP_ID = "SENTINEL_GROUP";
    private static final String FLOW_POSTFIX = "-flow-rules";


    /**
     * 载入 namespace 和 ServerTransportConfig 的配置到 ClusterServerConfigManager 中
     */
    protected void loadServerConfig() {
        // 加载namespace
        ClusterServerConfigManager.loadServerNamespaceSet(Collections.singleton(APP_NAME));
        // 加载ServerTransportConfig
        ServerTransportConfig transportConfig = new ServerTransportConfig().setIdleSeconds(60000).setPort(CLUSTER_SERVER_PORT);
        ClusterServerConfigManager.loadGlobalTransportConfig(transportConfig);
    }


    /**
     * 加载集群限流规则
     */
    protected void initClusterFlowSupplier() {
        // 为集群流控注册一个Supplier，该Supplier会根据namespace动态创建数据源
        ClusterFlowRuleManager.setPropertySupplier(namespace -> {
            ReadableDataSource<String, List<FlowRule>> ds =
                    new NacosDataSource<>(REMOTE_ADDRESS,GROUP_ID,namespace+FLOW_POSTFIX, source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {}));
            return ds.getProperty();
        });

    }


}
