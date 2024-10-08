package ru.panyukovnn.streamapi.streamapi;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.*;

import static ru.panyukovnn.streamapi.streamapi.Util.printStreamInLine;

public class Creation {

    @Test
    public void empty() {
        Stream<Object> emptyStream = Stream.empty();
    }

    @Test
    public void list() {
        List<Integer> list = List.of(100, 200, 300);

        Stream<Integer> listStream = list.stream();
    }

    @Test
    public void map() {
        Map<Integer, String> map = Map.of(0, "a", 1, "b");

        Stream<Map.Entry<Integer, String>> mapStream = map.entrySet().stream();
        Stream<Integer> mapKeyStream = map.keySet().stream();
    }

    @Test
    public void array() {
        int[] array = {1, 2, 3};

        IntStream arrayStream = Arrays.stream(array);
    }

    @Test
    public void element() {
        Stream<String> elementsStream = Stream.of("a", "b", "c");
    }

    @Test
    public void parallel() {
        List<Integer> list = List.of(100, 200, 300, 400);
        Stream<Integer> integerStream1 = list.parallelStream()
            .filter(x -> x > 100)
            .map(x -> x * 2); // 400 600 800

        printStreamInLine(integerStream1);

        Stream<Integer> integerStream2 = list.stream()
            .parallel()
            .filter(x -> x > 200)
            .map(x -> x * 10); // 3000 4000

        printStreamInLine(integerStream2);
    }

    @Test
    public void primitive() {
        IntStream intStream = IntStream.of(1, 2, 3, 4);
        LongStream longStream = LongStream.of(1, 2, 3, 4);
        DoubleStream doubleStream = DoubleStream.of(1.2, 3.4);
    }

    @Test
    public void files() throws IOException {
        Stream<String> lines = Files.lines(Paths.get("note.txt"));
        Stream<Path> paths = Files.list(Paths.get("./"));
        Stream<Path> walk = Files.walk(Paths.get("./"), 3);
    }

    /* Генерация стримов */
    @Test
    public void range() {
        IntStream range = IntStream.range(10, 100); // 10 .. 99
        LongStream rangeClosed = LongStream.rangeClosed(10, 100); // 10 .. 100
    }

    @Test
    public void generate() {
        Stream<Integer> limit = Stream.generate(() -> 6)
            .limit(6);

        printStreamInLine(limit); // 6, 6, 6, 6, 6, 6
    }

    @Test
    public void iterate() {
        Stream<Integer> iterate = Stream.iterate(2, x -> x + 6)
            .limit(6);

        printStreamInLine(iterate); // 2, 8, 14, 20, 26, 32
    }

    @Test
    public void iterateHasNext() {
        // С Java 9
        Stream<Integer> iterate = Stream.iterate(2, x -> x < 25, x -> x + 6);
        printStreamInLine(iterate); // 2, 8, 14, 20
    }

    @Test
    public void concat() {
        Stream<Integer> concat = Stream.concat(
            Stream.of(1, 2, 3),
            Stream.of(4, 5, 6));

        printStreamInLine(concat); // 1, 2, 3, 4, 5, 6

        List<Integer> list1 = List.of(1, 2, 3);
        List<Integer> list2 = List.of(4, 5, 6);
        Stream<Integer> concat2 = Stream.concat(
            list1.stream(),
            list2.stream()
        );

        printStreamInLine(concat2);
    }

    @Test
    public void build() {
        Stream<String> build = Stream.<String>builder()
            .add("Mike")
            .add("Joe")
            .build();
    }
}
