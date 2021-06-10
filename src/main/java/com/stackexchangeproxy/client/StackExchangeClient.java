package com.stackexchangeproxy.client;

import com.stackexchangeproxy.client.model.SearchItem;
import reactor.core.publisher.Flux;

/**
 * Client for work with <a href="https://api.stackexchange.com/docs/search#&order=desc&sort=
 * activity&filter=default&site=stackoverflow&run=true">stackexchange</a>
 */
public interface StackExchangeClient {

  Flux<SearchItem> search(String query);
}
