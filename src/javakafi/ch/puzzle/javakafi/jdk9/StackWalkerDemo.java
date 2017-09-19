package ch.puzzle.javakafi.jdk9;

public class StackWalkerDemo {
    public static void main(String[] args) {
        new StackWalkerDemo();

    }

    private StackWalkerDemo() {
        basicStackTrace();
        whoIsCallingMe();
    }

    private void whoIsCallingMe() {
        System.out.println("Find out, who's calling:");

        CallerDemo cd = new CallerDemo();
        cd.whoisCallingMe();

        System.out.println();
    }

    private void basicStackTrace() {
        System.out.println("Print a basic stack trace:");

        step1();

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
}
