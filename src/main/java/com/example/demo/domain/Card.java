package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {

    private Long id;
    private String cost;
    private String title;
    private String text;
    private String color;
    private String collection;
    private String type;
    private String subType;
}
