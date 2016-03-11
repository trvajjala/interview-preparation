package stringintern;

public class MemoryLeakString {

    public static void main(String[] args) {

        final String s1 = "helloWorld";

        final String s2 = s1.substring(1, 4);

        System.out.println(s1);

        System.out.println(s2);

        final StringBuilder sb = new StringBuilder("Hello ").append("world. ").append(" isCorrect ? ").append(true);
        System.out.println(sb.toString());

        System.out.println(s1.concat(" ANother world"));

    }
}
