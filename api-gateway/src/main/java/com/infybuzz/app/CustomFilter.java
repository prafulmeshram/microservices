/**
 * 
 */
package com.infybuzz.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * @author jack
 *
 */
@Configuration
public class CustomFilter implements GlobalFilter {

	Logger logger = LoggerFactory.getLogger(CustomFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		ServerHttpRequest request = exchange.getRequest();
		logger.info("Authorization = " + request.getHeaders().getFirst("Authorization"));

		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			// Post Filter
			ServerHttpResponse response = exchange.getResponse();
			logger.info("Response = " + response.getStatusCode()); 
		}));
	}

}
