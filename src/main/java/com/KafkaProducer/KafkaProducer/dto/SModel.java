package com.KafkaProducer.KafkaProducer.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class SModel {
    @JsonProperty("Id")
    private Integer id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("View")
    private String view;
}
