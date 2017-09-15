import java.util.List;
import java.util.stream.Stream;

public class Streams {

    public static void main(String[] args) {
        new Streams();
    }

    private Streams() {
        takeWhileExample();
        dropWhileExample();
        iterateExample();
        ofNullableExample();
    }

    private void takeWhileExample() {
        System.out.println("\ntakeWhile()");

        Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
            .takeWhile(i -> i < 5)
            .forEach(System.out::print);

        System.out.println();
    }

    private void dropWhileExample() {
        System.out.println("\ndropWhile()");

        Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
            .dropWhile(i -> i < 5)
            .forEach(System.out::print);

        System.out.println();
    }

    private void iterateExample() {
        System.out.println("\niterate()");

        // Overload to existing iterate(), taking a function to make the previously infinte stream finite
        // 1st argument tells the stream where to start
        // 2nd argument tells the stream when to stop
        // 3rd argument tells the stream how to iterate
        Stream.iterate(0, i -> i < 10, i -> i + 1)
            .forEach(System.out::print);

        System.out.println();
    }

    private void ofNullableExample() {
        System.out.println("\nofNullable()");

        System.out.println("Instance is NOT null. Prints names.");
        Puzzle puzzlerNonNull = new Puzzle();
        Stream.ofNullable(puzzlerNonNull).flatMap(Puzzle::getEmployees).forEach(System.out::println);

        System.out.println("\nInstance is null. Nothing gets printed.");
        Puzzle puzzlerNull = null;
        Stream.ofNullable(puzzlerNull).flatMap(Puzzle::getEmployees).forEach(System.out::println);

        System.out.println();
    }

    class Puzzle {
        private List<String> employees = List.of("Lugi", "Max", "Lara", "Andy", "Ramon");

        Stream<String> getEmployees() {
            return this.employees.stream();
        }
    }
}
