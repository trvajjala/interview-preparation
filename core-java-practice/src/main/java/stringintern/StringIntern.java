package stringintern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ThirupathiReddy V
 *
 */
public class StringIntern {

    public static void main(String[] args) {

        final String s1 = "Hello";
        System.out.println(s1.hashCode());
        final String s2 = "Hello";
        System.out.println(s2.hashCode());

        final String s3 = new String("Hello");

        System.out.println(s3.hashCode());

        System.out.println("s1==s2  " + (s1 == s2));
        System.out.println("s2==s3 " + (s2 == s3));

        System.out.println("s2==s3.intern() " + (s2 == s3.intern()));

        System.out.println("--------------------");

        final String str = "Hello world";

        System.out.println(str.matches("(.*) world"));

        final String input = "hellosthhelloaahelloksfkdsjfkdhello";

        final Pattern pattern = Pattern.compile("hello");
        final Matcher matcher = pattern.matcher(input);

        int counter = 0;

        while (matcher.find()) {
            counter++;
        }
        System.out.println(counter);
        System.out.println(input.substring(5, 8));

        System.out.println("SubString :  " + input.substring(input.length()));

    }
}
