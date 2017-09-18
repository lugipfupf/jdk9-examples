package ch.puzzle.javakafi.jdk9;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Collections {
    private Dog dog1;
    private Dog dog2;
    private Dog dog3;
    private Dog dog4;

    public static void main(String[] args) {
        System.out.println("Collection Interface with JDK 9");

        new Collections();
    }

    private Collections() {
        this.dog1 = new Dog("Waldi");
        this.dog2 = new Dog("Hasso");
        this.dog3 = new Dog("Fifi");
        this.dog4 = new Dog("Rantaplan");

        // Literal approach is still missing:
        // List<Dog> = #[dog1, dog2, dog3]; // JEP-186 http://openjdk.java.net/jeps/186
        // Factory methods aim to provide a similarly slim syntax

        // Factory examples
        // there are always 10 factory methods. This seems awful at first. Why not just have a method in the form of
        // List.of(E... elements)?
        // It is assumed, that in many cases no more than 10 elements are needed when populating collections manually.
        // Having appropriate methods reduces array allocation and garbage collection overhead
        // For all other cases you still have List.of(E... elements)
        listExample();
        setExample();
        mapExample();
    }

    /**
     * Java 9 Collection using factory methods
     */
    private void listExample() {
        List<Dog> listOfDogs = List.of(dog1, dog2, dog3);
        System.out.println("Listing dogs names in list");
        listOfDogs.forEach(dog -> System.out.println(dog.getName()));

        // Factory methods return unmodifiable collections!
        try {
            listOfDogs.add(dog4);
        } catch (UnsupportedOperationException e) {
            System.out.println(String.format("Don't modify me!! %s has to stay outside!", dog4.getName()));
        }

        System.out.println();

        // using streams Java 8 style
        List<String> blah = Stream.of("foo", "bar", "baz")
                .collect(Collectors.collectingAndThen(toList(), java.util.Collections::unmodifiableList));
    }

    /**
     * Java 9 Set using factory methods
     */
    private void setExample() {
        Set<Dog> setOfDogs1 = Set.of(dog1, dog2, dog3);
        System.out.println("Listing dogs names in set");
        setOfDogs1.forEach(dog -> System.out.println(dog.getName()));

        // Unlike with a normal set, you cannot add duplicate elements. Consider this code:
        //        Set<Dog> setOfDogs2 = new HashSet<>();
        //        setOfDogs2.add(dog1);
        //        setOfDogs2.add(dog2);
        //        setOfDogs2.add(dog1);
        // this will work
        try {
            // howeever, this will not
            Set.of(dog1, dog2, dog3, dog1, dog2, dog1, dog1, dog3);
        } catch (IllegalArgumentException e) {
            System.out.println("Only one bone per dog! Check for uniqueness beforehand.");
        }

        System.out.println();

    }

    private void mapExample() {
        System.out.println("Listing dogs names in map using key/value pairs");

        Map<Integer, Dog> mapOfDogs1 = Map.of(1, dog1, 2, dog2, 3, dog3);
        mapOfDogs1.forEach((k, v) -> System.out.println(String.format("Dog's name at pos %d is %s", k, v.getName())));

        System.out.println();

        System.out.println("Listing dogs names in map using entries");
        Map<Integer, Dog> mapOfDogs2 = Map.ofEntries(Map.entry(1, dog1), Map.entry(2, dog2), Map.entry(3, dog3));
        mapOfDogs2.forEach((k, v) -> System.out.println(String.format("Dog's name at pos %d is %s", k, v.getName())));

        System.out.println();
    }

    class Dog {
        private String name;

        Dog(String name) {
            this.name = name;
        }

        String getName() {
            return this.name;
        }
    }
}
