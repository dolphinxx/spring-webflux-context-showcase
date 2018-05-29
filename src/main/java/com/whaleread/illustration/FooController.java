package com.whaleread.illustration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

/**
 * @author Dolphin
 */
@Controller
public class FooController {
    @GetMapping("/foo")
    @ResponseBody
    public Mono<String> greeting(@RequestParam String name) {
        System.out.println(2);
        return SecurityContextHolder.getContext()
                .map(securityContext -> securityContext.isAuthenticated() ? securityContext.getDisplayName() : name)
                .flatMap(n -> Mono.just("Hello, " + n + "!"));
    }
}
