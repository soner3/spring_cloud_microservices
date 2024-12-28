package net.sonerapp.gatewayserver.filters;

import java.util.UUID;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@AllArgsConstructor
@Slf4j
public class RequestTraceFilter implements GlobalFilter {

    private final FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
        if (filterUtility.getCorrelationId(httpHeaders) != null) {
            log.debug("Correlation-id found: {}", filterUtility.getCorrelationId(httpHeaders));
        } else {
            String correlationId = UUID.randomUUID().toString();
            exchange = filterUtility.setCorrelationId(exchange, correlationId);
            log.debug("Correlation-id created:", correlationId);
        }

        return chain.filter(exchange);
    }

}
