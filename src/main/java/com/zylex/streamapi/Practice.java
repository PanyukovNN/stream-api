package com.zylex.streamapi;

import com.zylex.streamapi.model.Character;
import com.zylex.streamapi.model.CharacterDto;
import com.zylex.streamapi.model.House;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.zylex.streamapi.Util.characters;

public class Practice {

    @Test
    public void youngestOldest() {
        // Вывести четырёх самых старых персонажей
        characters.stream()
                .sorted(Comparator.comparingInt(Character::getAge).reversed())
                .limit(4)
                .forEach(System.out::println);

        System.out.println();
    }

    @Test
    public void noWeapons() {
        // Вывести всех безоружных персонажей из дома Ланистеров
        characters.stream()
                .filter(character -> character.getWeapons().isEmpty() && character.getHouse().equals(House.LANISTER))
                .sorted(Comparator.comparing(Character::getName))
                .forEach(System.out::println);
    }

    @Test
    public void swordsNumber() {
        // Посчитать количество мечей
        long swordsNumber = characters.stream()
                .map(character -> character.getWeapons())
                .flatMap(weapons -> weapons.stream())
                .filter(weapon -> weapon.toLowerCase().contains("sword"))
                .count();

        System.out.println(swordsNumber);
    }

    @Test
    public void ageStatistics() {
        // Вывести возрастную статистику персонажей
        System.out.println(characters.stream().mapToInt(Character::getAge).summaryStatistics());
    }

    @Test
    public void dto() {
        // Получить список dto-персонажей из дома Старков
        List<CharacterDto> list = characters.stream()
                .filter(character -> character.getHouse().equals(House.STARK))
                .map(CharacterDto::new)
                .collect(Collectors.toList());

        list.forEach(System.out::println);
    }
}
