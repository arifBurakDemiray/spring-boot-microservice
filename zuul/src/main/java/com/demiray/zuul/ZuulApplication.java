package com.demiray.zuul;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;


@SpringBootApplication
@EnableDiscoveryClient
@Configuration
@EnableZuulProxy
@AutoConfigureAfter(RibbonAutoConfiguration.class)
@RibbonClients(defaultConfiguration = RibbonConfig.class)
@Slf4j
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @PostConstruct
    public void init(DiscoveryClient client) {
        log.info("Services: {}", client.getServices());

        client.getServices().forEach(svc -> {
            try {
                List<ServiceInstance> its = client.getInstances(svc);
                for (ServiceInstance it : its) {
                    log.info("Instance: url={}:{}, id={}, service={}", it.getHost(), it.getPort(), it.getInstanceId(), it.getServiceId());
                }
            } catch (Exception ex) {
                log.warn("Failed to lookup instance due to endpoint not specifying port for service {}. Exception: {}" + svc, ex.toString());
            }
        });

    }

}
