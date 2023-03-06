package com.demiray.zuul;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.context.annotation.Bean;

@NoArgsConstructor
public class RibbonConfig {

    @Bean
    @ConditionalOnMissingBean
    public ServerList<?> ribbonServerList(DiscoveryClient discoveryClient,IClientConfig config) {

        Server[] servers = discoveryClient.getInstances(config.getClientName()).stream()
                .map(i -> new Server(i.getHost(), i.getPort()))
                .toArray(Server[]::new);

        return new StaticServerList<>(servers);
    }
}
