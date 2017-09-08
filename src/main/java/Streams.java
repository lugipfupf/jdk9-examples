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

        System.out.println();
    }

    private void ofNullableExample() {
        System.out.println("\nofNullable");

        System.out.println();
    }
}
