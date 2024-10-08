package ru.panyukovnn.streamapi.streamapi;

import org.junit.Test;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static ru.panyukovnn.streamapi.streamapi.Util.characters;

public class Terminate {

    @Test
    public void count() {
        Stream<Character> stream = characters.stream();
        System.out.println(stream.count());
    }

    @Test
    public void collect() {
        List<Character> list = characters.stream()
            .filter(character -> character.getHouse().equals(House.STARK))
            .collect(Collectors.toList());
    }

    @Test
    public void reduce() {
        IntStream intStream = IntStream.of(100, 200, 300, 400);
        int result = intStream.reduce((acc, value) -> acc + value).orElse(0);

        System.out.println(result);
    }

    @Test
    public void forEach() {
        characters.stream().forEach(character -> System.out.println(character.getAge()));
        characters.forEach(character -> System.out.println(character.getAge()));

        characters.stream().forEachOrdered(character -> System.out.println(character.getAge()));
    }

    @Test
    public void math() {
        // Только для примитивных стримов
        IntStream.of(100, 200, 300, 400)
            .average()
            .ifPresent(System.out::println);

        IntStream.of(100, 200, 300, 400)
            .max()
            .ifPresent(System.out::println);

        IntStream.of(100, 200, 300, 400)
            .min()
            .ifPresent(System.out::println);

        int sum = IntStream.of(100, 200, 300, 400).sum();
        System.out.println(sum);

        IntSummaryStatistics intSummaryStatistics1 = IntStream.of(100, 200, 300, 400).summaryStatistics();
        System.out.println(intSummaryStatistics1);

        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        IntSummaryStatistics intSummaryStatistics2 = integerStream.mapToInt(Integer::valueOf)
            .summaryStatistics();

        System.out.println(intSummaryStatistics2);
    }

    @Test
    public void minMax() {
        characters.stream().min(Comparator.comparingInt(Character::getAge));
        characters.stream().max(Comparator.comparingInt(Character::getAge));
    }

    @Test
    public void toArray() {
        characters.stream().toArray();
    }

    @Test
    public void find() {
        characters.stream().findAny();
        characters.stream().findFirst();
    }

    @Test
    public void match() {
        characters.stream().noneMatch(character -> character.getAge() > 60); // true
        characters.stream().anyMatch(character -> character.getHouse() == House.LANISTER); // true
        characters.stream().allMatch(character -> character.getAge() > 18); // true
    }
}
