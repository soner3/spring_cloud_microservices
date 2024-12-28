package net.sonerapp.gatewayserver.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class FilterUtility {

    public static final String CORRELATION_ID = "sonerapp-correlation-id";

    public String getCorrelationId(HttpHeaders httpHeaders) {
        if (httpHeaders.get(CORRELATION_ID) != null) {
            return httpHeaders.get(CORRELATION_ID).stream().findFirst().orElse(null);
        } else {
            return null;
        }

    }

    private ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        return exchange
                .mutate()
                .request(exchange.getRequest().mutate().header(name, value).build())
                .build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return this.setRequestHeader(exchange, CORRELATION_ID, correlationId);
    }

}
