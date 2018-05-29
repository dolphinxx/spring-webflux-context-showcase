package com.whaleread.illustration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Base64;

/**
 * @author Dolphin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FooControllerTest {
    @Autowired
    private WebTestClient webClient;

    @Test
    public void greeting() {
        this.webClient.get().uri("/foo?name={name}", "Dolphin").exchange()
                .expectBody(String.class).isEqualTo("Hello, Dolphin!");
    }

    @Test
    public void greeting_Authorization() throws Exception {
        this.webClient.get().uri("/foo?name={name}", "Dolphin")
                .header("Authorization", "Bearer " + Base64.getUrlEncoder().encodeToString((String.valueOf(10000001L) + '.' + "BraveDolphin").getBytes("UTF-8")))
                .exchange()
                .expectBody(String.class).isEqualTo("Hello, BraveDolphin!");
    }
}