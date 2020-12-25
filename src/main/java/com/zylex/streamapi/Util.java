package com.zylex.streamapi;

import com.zylex.streamapi.model.Character;
import com.zylex.streamapi.model.House;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {

    public static List<Character> characters = List.of(
            new Character(0L, "John Snow", 17, House.STARK, List.of("Sword")),
            new Character(1L, "Arya", 11, House.STARK, List.of("Needle")),
            new Character(2L, "Sansa", 13, House.STARK, new ArrayList<>()),
            new Character(3L, "Tirion", 30, House.LANISTER, new ArrayList<>()),
            new Character(4L, "Jaime", 35, House.LANISTER, List.of("Sword", "Dagger")),
            new Character(5L, "Cersei", 35, House.LANISTER, new ArrayList<>()),
            new Character(6L, "Daenerys", 15, House.TARGARIEN, List.of("Dragons")),
            new Character(7L, "Theon", 19, House.GREYJOY, List.of("Bow")),
            new Character(8L, "Thunder", 30, House.NONE, List.of("Two-handed sword")),
            new Character(9L, "Jorah", 40, House.MORMONT, List.of("Sword"))
    );

    public static void printStreamInLine(Stream<?> stream) {
        System.out.println(stream
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));

        System.out.println();
    }

    public static void printStream(Stream<?> stream) {
        stream.forEach(System.out::println);

        System.out.println();
    }
}
