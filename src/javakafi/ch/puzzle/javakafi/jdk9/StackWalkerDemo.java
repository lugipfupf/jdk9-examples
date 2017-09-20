package ch.puzzle.javakafi.jdk9;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StackWalkerDemo {
    public static void main(String[] args) {
        new StackWalkerDemo();
    }

    private StackWalkerDemo() {
        basicStackTrace();
        whoIsCallingMe();
        walkTheStack();
        getClassContextClassic();
        getClassContextStackWalker();
    }

    private void walkTheStack() {
        StackWalker stackWalker = StackWalker.getInstance();
        stackWalker.walk(
            s -> s.takeWhile(
                f -> f.getMethodName()
                      .endsWith("walkTheStack")
            )
            .collect(Collectors.toList())
        )
        .forEach(
            el -> System.out.println(el.toString())
        );
    }

    private void getClassContextClassic() {
        System.out.println("Get class names on stack using SecurityManager:");

        ClassContextDemo ccd = new ClassContextDemo();
        ccd.printClassContext();

        System.out.println();
    }

    private void getClassContextStackWalker() {
        System.out.println("Get class names on stack using StackWalker:");

        StackWalker sw = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
        sw.walk(
            s -> s.collect(Collectors.toList())
        )
        .forEach(
            el -> System.out.println(el.getClassName())
        );

        System.out.println();
    }

    private void basicStackTrace() {
        System.out.println("Print a basic stack trace:");

        step1();

        System.out.println();
    }

    private void whoIsCallingMe() {
        System.out.println("Look, who's calling:");

        CallerDemo cd = new CallerDemo();
        cd.whoisCallingMe();

        System.out.println();
    }

    private void step1() {
        step2();
    }

    private void step2() {
        step3();
    }

    private void step3() {
        step4();
    }

    private void step4() {
        StackWalker stackWalker = StackWalker.getInstance();
        stackWalker.forEach(System.out::println);
    }

    private class CallerDemo {
        void whoisCallingMe() {
            StackWalker sw = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
            System.out.println(sw.getCallerClass());
        }
    }

    private class ClassContextDemo extends SecurityManager {
        void printClassContext() {
            Arrays.stream(
                getClassContext()
            )
            .dropWhile(
                el -> el.getCanonicalName()
                        .endsWith(this.getClass().getSimpleName())
            )
            .forEach(
                el -> System.out.println(el.getCanonicalName())
            );
        }
    }
}
