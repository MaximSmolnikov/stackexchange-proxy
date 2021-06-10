package com.stackexchangeproxy.controller;

import com.stackexchangeproxy.model.SearchItem;
import com.stackexchangeproxy.service.StackExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class StackExchangeController {

  private final StackExchangeService stackExchangeService;

  @GetMapping("/search")
  public Flux<SearchItem> search(@RequestParam String query) {
    return stackExchangeService.search(query);
  }
}
