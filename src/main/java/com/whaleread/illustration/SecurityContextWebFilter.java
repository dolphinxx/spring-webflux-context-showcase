package com.whaleread.illustration;

import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author Dolphin
 */
public class SecurityContextWebFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        System.out.println(1);
        return chain.filter(exchange).subscriberContext(SecurityContextHolder.withSecurityContext(Mono.fromSupplier(() -> {
            System.out.println(3);
            HttpHeaders headers = exchange.getRequest().getHeaders();
            String auth = headers.getFirst("Authorization");
            return parse(auth);
        })));
    }

    private static SecurityContext parse(String auth) {
        if (auth == null || auth.length() == 0) {
            return new SecurityContext();
        }
        int space = auth.indexOf(' ');
        String type = auth.substring(0, space);
        String principal = auth.substring(space + 1);
        if (!type.equalsIgnoreCase("Bearer")) {
            return new SecurityContext();
        }
        String payload = null;
        try {
            payload = new String(Base64.getUrlDecoder().decode(principal), "UTF-8");
        } catch (UnsupportedEncodingException ignore) {
        }
        int dot = payload.indexOf('.');
        long id = Long.parseLong(payload.substring(0, dot));
        String name = payload.substring(dot + 1);
        return new SecurityContext(id, name);
    }
}
