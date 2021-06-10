package com.stackexchangeproxy.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchItem {

  private List<String> tags;
  private Owner owner;
  @JsonProperty("is_answered")
  private Boolean isAnswered;
  @JsonProperty("view_count")
  private Double viewCount;
  @JsonProperty("accepted_answer_id")
  private Double acceptedAnswerId;
  @JsonProperty("answer_count")
  private Double answerCount;
  private Double score;
  @JsonProperty("last_activity_date")
  private Double lastActivityDate;
  @JsonProperty("creation_date")
  private Double creationDate;
  @JsonProperty("last_edit_date")
  private Double lastEditDate;
  @JsonProperty("question_id")
  private Double questionId;
  @JsonProperty("content_license")
  private String contentLicense;
  private String link;
  private String title;
}
