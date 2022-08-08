package com.yao0er4.springcloud.config;

import org.springframework.cloud.gateway.handler.predicate.RoutePredicateFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    /**
     * 配置了一个id 为 path_route_douyin 的 路由规则，
     * 当访问地址 http://localhost:9527/douyin 的 时
     * 会自动转发到 https://www.douyin.com/
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route_douyin", r ->
                r.path("/douyin").uri("https://www.douyin.com/")).build();

        return routes.build();
    }

    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route_juejin", r ->
                r.path("/juejin").uri("https://juejin.cn/")).build();

        return routes.build();
    }

}
