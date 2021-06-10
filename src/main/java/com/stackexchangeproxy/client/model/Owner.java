package com.stackexchangeproxy.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Owner {

  private Double reputation;
  @JsonProperty("user_id")
  private Double userId;
  @JsonProperty("user_type")
  private String userType;
  @JsonProperty("accept_rate")
  private Double acceptRate;
  @JsonProperty("profile_image")
  private String profileImage;
  @JsonProperty("display_name")
  private String displayName;
  private String link;
}
