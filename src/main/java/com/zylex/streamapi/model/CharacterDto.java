package com.zylex.streamapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CharacterDto {
    private Long id;
    private String name;
    private int age;
    private String house;

    public CharacterDto(Character character) {
        this.id = character.getId();
        this.name = character.getName();
        this.age = character.getAge();
        this.house = character.getHouse().toString();
    }
}
