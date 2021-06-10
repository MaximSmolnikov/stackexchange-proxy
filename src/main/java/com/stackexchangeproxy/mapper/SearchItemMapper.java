package com.stackexchangeproxy.mapper;

import com.stackexchangeproxy.model.SearchItem;
import org.mapstruct.Mapper;

@Mapper
public interface SearchItemMapper {

  SearchItem toSearchItem(com.stackexchangeproxy.client.model.SearchItem searchItem);
}
