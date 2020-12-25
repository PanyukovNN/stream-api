package com.zylex.streamapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Character {

    private Long id;
    private String name;
    private int age;
    private House house;
    private List<String> weapons;
}
