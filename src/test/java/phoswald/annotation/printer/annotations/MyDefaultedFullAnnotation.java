package phoswald.annotation.printer.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Set;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyDefaultedFullAnnotation {

    public Class<?> myClass() default String.class;
    public Class<?>[] myClassList() default { List.class, Set.class };
    public Class<?>[] myEmptyClassList() default { };

    public MyEnum myEnum() default MyEnum.VALUE_1;
    public MyEnum[] myEnumList() default { MyEnum.VALUE_1 };

    public int myInt() default 42;
    public int[] myIntList() default { 42, 43 };

    public MySingleIntAnnotation myObject() default @MySingleIntAnnotation(42);
    public MySingleIntAnnotation[] myObjectList() default { @MySingleIntAnnotation(42), @MySingleIntAnnotation(43) };
    public MySingleIntAnnotation[] myEmptyObjectList() default { };

    public String myString() default "hello";
    public String[] myStringList() default { "hello", "world" };
    public String[] myEmptyStringList() default { };
}
