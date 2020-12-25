package com.zylex.streamapi;

import com.zylex.streamapi.model.Character;
import com.zylex.streamapi.model.House;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.zylex.streamapi.Util.characters;

public class Collector {

    @Test
    public void toList() {
        //R collect​(Supplier supplier, BiConsumer accumulator, BiConsumer combiner)
        //То же, что и collect(collector), только параметры разбиты для удобства.
        // Если нужно быстро сделать какую-то операцию, нет нужды реализовывать интерфейс Collector,
        // достаточно передать три лямбда-выражения.
        //
        // supplier должен поставлять новые объекты (контейнеры), например new ArrayList(),
        // accumulator добавляет элемент в контейнер,
        // combiner необходим для параллельных стримов и объединяет части стрима воедино.
        List<String> list = Stream.of("a", "b", "c", "d")
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Test
    public void toSet() {
        Set<Character> set = characters.stream()
                .collect(Collectors.toSet());
    }

    @Test
    public void toCollection() {
        List<Character> copyOnWriteArrayList = characters.stream()
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));

        Set<Character> linkedSet = characters.stream()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Test
    public void toMap() {
        Map<Integer, String> map1 = Stream.of(1, 2, 3)
                .collect(Collectors.toMap(
                        Function.identity(),
                        i -> String.format("%d * 2 = %d", i, i * 2)
                ));
        // {1="1 * 2 = 2",
        //  2="2 * 2 = 4",
        //  3="3 * 2 = 6"}


        Map<Integer, String> map2 = Stream.of(50, 55, 69, 20, 19, 52)
                .collect(Collectors.toMap(
                        i -> i % 5,
                        i -> String.format("<%d>", i),
                        (a, b) -> String.join(", ", a, b)
                ));
        // {0="<50>, <55>, <20>",
        //  2="<52>",
        //  4="<64>, <19>"}

        Map<Integer, String> map3 = Stream.of(50, 55, 69, 20, 19, 52)
                .collect(Collectors.toMap(
                        i -> i % 5,
                        i -> String.format("<%d>", i),
                        (a, b) -> String.join(", ", a, b),
                        LinkedHashMap::new
                ));
        // {0=<50>, <55>, <20>,
        //  4=<69>, <19>,
        //  2=<52>}

        /*
        toConcurrentMap​(Function keyMapper, Function valueMapper)
        toConcurrentMap​(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction)
        toConcurrentMap​(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction, Supplier mapFactory)

        Всё то же самое, что и toMap, только работает с ConcurrentMap.
        */
    }

    @Test
    public void joining() {
        String s1 = Stream.of("a", "b", "c", "d")
                .collect(Collectors.joining("-"));
        System.out.println(s1); // a-b-c-d

        String s2 = characters.stream()
                .map(Character::getHouse)
                .distinct()
                .sorted(Comparator.comparing(House::ordinal))
                .map(String::valueOf)
                .collect(Collectors.joining(",", "[", "]"));
        System.out.println(s2);
    }

    @Test
    public void groupingBy() {
        Map<House, List<Character>> map1 = characters.stream()
                .collect(Collectors.groupingBy(Character::getHouse));

        map1.forEach((house, integers) -> System.out.println(house + ": " + integers));

        System.out.println();

        Map<House, List<Integer>> map2 = characters.stream()
                .collect(Collectors.groupingBy(
                        Character::getHouse,
                        Collectors.mapping(Character::getAge, Collectors.toList())));

        map2.forEach((house, integers) -> System.out.println(house + ": " + integers));
    }

    @Test
    public void partitioningBy​() {
        // Разбивает последовательность элементов по какому-либо критерию.
        // В одну часть попадают все элементы, которые удовлетворяют переданному условию,
        // во вторую — все, которые не удовлетворяют.

        Map<Boolean, List<String>> map1 = Stream.of(
                "ab", "c", "def", "gh", "ijk", "l", "mnop")
                .collect(Collectors.partitioningBy(s -> s.length() <= 2));
        map1.entrySet().forEach(System.out::println);
        // false=[def, ijk, mnop]
        // true=[ab, c, gh, l]
    }

    @Test
    public void math() {
//        summingInt​(ToIntFunction mapper)
//        summingLong​(ToLongFunction mapper)
//        summingDouble​(ToDoubleFunction mapper)
//        Коллектор, который преобразовывает объекты в int/long/double и подсчитывает сумму.
//
//        averagingInt​(ToIntFunction mapper)
//        averagingLong​(ToLongFunction mapper)
//        averagingDouble​(ToDoubleFunction mapper)
//
//        summarizingInt​(ToIntFunction mapper)
//        summarizingLong​(ToLongFunction mapper)
//        summarizingDouble​(ToDoubleFunction mapper)

//        filtering​(Predicate predicate, Collector downstream)
//        mapping​(Function mapper, Collector downstream)
//        flatMapping​(Function downstream)
//        reducing​(BinaryOperator op)
//        reducing​(T identity, BinaryOperator op)
//        reducing​(U identity, Function mapper, BinaryOperator op)

//        minBy​(Comparator comparator)
//        maxBy​(Comparator comparator)
    }
}
