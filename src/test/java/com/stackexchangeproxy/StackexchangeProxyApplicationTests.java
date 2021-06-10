package com.stackexchangeproxy;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.stackexchangeproxy.StackexchangeProxyApplicationTests.BootConfig;
import com.stackexchangeproxy.controller.StackExchangeController;
import com.stackexchangeproxy.model.SearchItem;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;


@WebFluxTest(StackExchangeController.class)
@Import(BootConfig.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StackexchangeProxyApplicationTests {

  WireMockServer mockServer = new WireMockServer(8888);

  @Autowired
  WebTestClient webTestClient;

  @BeforeAll
  void setup() {
    mockServer.start();
  }

  @AfterAll
  void cleanup() {
    mockServer.shutdown();
  }

  @Test
  void search() {
    mockServer.stubFor(get(urlEqualTo(
        "/search?order=desc&sort=activity&site=stackoverflow&intitle=java"
    ))
        .willReturn(aResponse()
            .withHeader("Content-Type", "application/json")
            .withBodyFile("mockresp.json")));

    webTestClient.get()
        .uri(uriBuilder ->
            uriBuilder
                .host("localhost")
                .port(8080)
                .path("/search")
                .queryParam("query", "java")
                .build()
        )
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$..isAnswered")
        .isEqualTo(false)
        .jsonPath("$..title")
        .isEqualTo("Java 8 collection conversion using Java 8 stream");
  }

  @ComponentScan("com.stackexchangeproxy")
  static class BootConfig {

    @Bean
    WebClient.Builder webClientbuilder() {
      return WebClient.builder();
    }
  }

}
