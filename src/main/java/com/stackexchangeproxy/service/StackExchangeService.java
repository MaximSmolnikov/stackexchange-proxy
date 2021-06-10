package com.stackexchangeproxy.service;

import com.stackexchangeproxy.model.SearchItem;
import reactor.core.publisher.Flux;

public interface StackExchangeService {

  Flux<SearchItem> search(String query);
}
