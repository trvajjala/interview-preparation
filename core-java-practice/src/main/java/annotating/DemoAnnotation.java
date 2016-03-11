package annotating;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class DemoAnnotation {

    public static void main(String[] args) {

        final Class anno = AnnoExample.class;

        for (final Method method : anno.getMethods()) {
            for (final Annotation ann : method.getAnnotations()) {

                if (ann instanceof Comment) {
                    final Comment comment = (Comment) ann;
                    System.out.println("Author:  " + comment.author());
                    System.out.println("Description :  " + comment.description());
                    System.out.println("Priority :  " + comment.priority());
                }
            }
        }

    }
}
