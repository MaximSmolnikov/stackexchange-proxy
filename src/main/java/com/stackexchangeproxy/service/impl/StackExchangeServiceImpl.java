package com.stackexchangeproxy.service.impl;

import com.stackexchangeproxy.client.StackExchangeClient;
import com.stackexchangeproxy.mapper.SearchItemMapper;
import com.stackexchangeproxy.model.SearchItem;
import com.stackexchangeproxy.service.StackExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class StackExchangeServiceImpl implements StackExchangeService {

  private final StackExchangeClient stackExchangeClient;
  private final SearchItemMapper searchItemMapper;

  @Override
  public Flux<SearchItem> search(String query) {
    return stackExchangeClient.search(query)
        .map(searchItemMapper::toSearchItem);
  }
}
