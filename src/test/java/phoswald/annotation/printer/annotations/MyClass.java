package phoswald.annotation.printer.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class MyClass {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public static @interface InnerAnnotation {
        InnerEnum myEnum();
        Class<?> myClass();
    }

    public static enum InnerEnum {
        VALUE_1, VALUE_2
    }

    public static class InnerClass {

    }
}
