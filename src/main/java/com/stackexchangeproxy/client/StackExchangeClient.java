package com.stackexchangeproxy.client;

import com.stackexchangeproxy.client.model.SearchItem;
import reactor.core.publisher.Flux;

public interface StackExchangeClient {

  Flux<SearchItem> search(String query);
}
