package pattern.behavioral.interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@formatter:off
/**
 *
 * java.util.Pattern
 * java.text.Format
 *
 * @author ThirupathiReddy V
 *
 */
public class InterpreterPattrnExample {


    public static void main( String[] args ) {
        final String input ="Lions, and tigers, and bears! oh, my!";

        final Pattern p=Pattern.compile( "(lion|cat|dog|wolf|bear|human|tiger|liger)" );

        final Matcher m=p.matcher( input );

        while(m.find()){

            System.out.println("Found  a "+ m.group() );

        }

    }
}
