package com.stackexchangeproxy.client.impl;

import com.stackexchangeproxy.client.StackExchangeClient;
import com.stackexchangeproxy.client.model.SearchItem;
import com.stackexchangeproxy.client.model.SearchResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class StackExchangeClientImpl implements StackExchangeClient {

  private static final String ORDER_PARAM = "order";
  private static final String SORT_PARAM = "sort";
  private static final String SITE_PARAM = "site";
  private static final String INTITLE_PARAM = "intitle";

  private final WebClient webClient;

  public StackExchangeClientImpl(
      @Value("${stackexchange.baseurl}") String url, WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl(url)
        .build();
  }

  @Override
  public Flux<SearchItem> search(String query) {
    return webClient.get()
        .uri(uriBuilder ->
            uriBuilder.path("/search")
                .queryParam(ORDER_PARAM, "desc")
                .queryParam(SORT_PARAM, "activity")
                .queryParam(SITE_PARAM, "stackoverflow")
                .queryParam(INTITLE_PARAM, query)
                .build()
        )
        .retrieve()
        .bodyToMono(SearchResult.class)
        .flatMapMany(searchResult -> Flux.fromIterable(searchResult.getItems()));
  }
}
