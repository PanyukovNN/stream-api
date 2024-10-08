package ru.panyukovnn.streamapi.streamapi;

import org.junit.Test;

import java.util.Comparator;
import java.util.stream.*;

import static ru.panyukovnn.streamapi.streamapi.Util.*;

public class Intermediate {

    @Test
    public void filter() {
        Stream<Integer> filterStream1 = Stream.of(1, 2, 3)
            .filter(x -> x == 10);

        printStreamInLine(filterStream1); // Вывода нет, так как после фильтрации стрим станет пустым

        Stream<Integer> filterStream2 = Stream.of(120, 410, 85, 32, 314, 12)
            .filter(x -> x > 100);

        printStreamInLine(filterStream2); // 120, 410, 314

        characters.stream()
            .filter(character -> character.getHouse() == House.STARK)
            .forEach(System.out::println);
    }

    @Test
    public void map() {
        LongStream longStream = IntStream.of(100, 200, 300, 400)
            .mapToLong(Long::valueOf);

        Stream<Integer> houses = characters.stream()
            .map(Character::getAge);

        printStreamInLine(houses);
    }

    @Test
    public void flatMap() {
        Stream.of(2, 3, 0, 1, 3)
            .flatMapToInt(x -> IntStream.range(0, x))
            .forEach(System.out::println); // 0, 1, 0, 1, 2, 0, 0, 1, 2

        System.out.println();

        IntStream.of(100, 200, 300, 400)
            .flatMap(value -> IntStream.of(value - 50, value))
            .forEach(System.out::println);

        Stream<String> weaponsStream = characters.stream()
            .map(character -> character.getWeapons())
            .flatMap(weapons -> weapons.stream());

        printStreamInLine(weaponsStream);
    }

    @Test
    public void limit() {
        Stream<Integer> stream = Stream.of(120, 410, 85, 32, 314, 12)
            .limit(4);

        printStreamInLine(stream); // 120, 410, 85, 32
    }

    @Test
    public void skip() {
        Stream<Integer> skip = Stream.of(120, 410, 85, 32, 314, 12)
            .peek(System.out::println)
            .skip(2);

        printStreamInLine(skip); // 85, 32, 314, 12

        characters.stream()
            .skip(3)
            .limit(5)
            .forEach(System.out::println);
    }

    @Test
    public void sorted() {
        characters.stream()
            .sorted(Comparator.comparing(Character::getAge))
            .forEach(System.out::println);
    }

    @Test
    public void distinct() {
        Stream<Integer> distinct = Stream.of(2, 1, 8, 1, 3, 2)
            .distinct();

        printStreamInLine(distinct); // 2, 1, 8, 3
    }

    @Test
    public void peek() {
        Stream.of("one", "two", "three", "four")
            .peek(e -> System.out.println("Raw value: " + e))
            .filter(e -> e.length() > 3)
            .peek(e -> System.out.println("Filtered value: " + e))
            .map(String::toUpperCase)
            .peek(e -> System.out.println("Mapped value: " + e))
            .collect(Collectors.toList());

        System.out.println();

        characters.stream()
            .sorted(Comparator.comparingInt(Character::getAge))
            .peek(character -> character.setAge(18))
            .map(character -> String.format("%s %s %s", character.getName(), character.getHouse(), character.getAge()))
            .forEach(System.out::println);
    }

    @Test
    public void takeWhile() {
        Stream.of(1, 2, 3, 4, 2, 5)
            .takeWhile(x -> x < 3)
            .forEach(System.out::println); // 1, 2

        System.out.println();

        Stream<Character> takeWhileStream = characters.stream()
            .takeWhile(character -> character.getAge() < 31);

        printStream(takeWhileStream);
    }

    @Test
    public void dropWhile() {
        Stream.of(1, 2, 3, 4, 2, 5)
            .dropWhile(x -> x < 3)
            .forEach(System.out::println);// 3, 4, 2, 5

        System.out.println();

        Stream<Character> dropWhileStream = characters.stream()
            .dropWhile(character -> character.getAge() < 31);

        printStream(dropWhileStream);
    }

    @Test
    public void boxed() {
        Stream<Double> boxed = DoubleStream.of(0.1, Math.PI).boxed();
    }
}
