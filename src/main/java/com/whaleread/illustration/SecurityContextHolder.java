package com.whaleread.illustration;

import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.function.Function;

/**
 * @author Dolphin
 */
public class SecurityContextHolder {
    private static final Class<?> SECURITY_CONTEXT_KEY = SecurityContext.class;

    public static Mono<SecurityContext> getContext() {
        return Mono.subscriberContext()
                .filter(c -> c.hasKey(SECURITY_CONTEXT_KEY))
                .flatMap(c -> c.<Mono<SecurityContext>>get(SECURITY_CONTEXT_KEY));
    }

    public static Function<Context, Context> clearContext() {
        return context -> context.delete(SECURITY_CONTEXT_KEY);
    }

    public static Context withSecurityContext(Mono<SecurityContext> context) {
        return Context.of(SECURITY_CONTEXT_KEY, context);
    }
}
