package org.nttdata.features.streams.person;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> people = List.of(
            new Person("John", 20, 179, Gender.MALE),
            new Person("Yusuf", 23, 179, Gender.MALE),
            new Person("Mike", 10, 179, Gender.FEMALE),
            new Person("Doe", 40, 179, Gender.FEMALE),
            new Person("Fred", 25, 179, Gender.FEMALE),
            new Person("Isawi", 30, 179, Gender.MALE)
        );

        double averageAge = people
                .stream()
                .filter(p -> p.getSex() == Gender.MALE)
                .mapToInt(Person::getAge)
                .average()
                .orElse(0);

        System.out.println("Average age of males = " + averageAge);
    }
}
