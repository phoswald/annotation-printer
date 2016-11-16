package phoswald.annotation.printer;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import phoswald.annotation.printer.annotations.MyArrayOfAnnotationAnnotation;
import phoswald.annotation.printer.annotations.MyArrayOfCharAnnotation;
import phoswald.annotation.printer.annotations.MyArrayOfClassAnnotation;
import phoswald.annotation.printer.annotations.MyArrayOfEnumAnnotation;
import phoswald.annotation.printer.annotations.MyArrayOfIntAnnotation;
import phoswald.annotation.printer.annotations.MyArrayOfStringAnnotation;
import phoswald.annotation.printer.annotations.MyDefaultedFullAnnotation;
import phoswald.annotation.printer.annotations.MyDefaultedSingleAnnotation;
import phoswald.annotation.printer.annotations.MyEnum;
import phoswald.annotation.printer.annotations.MyFullAnnotation;
import phoswald.annotation.printer.annotations.MyMarkerAnnotation;
import phoswald.annotation.printer.annotations.MyMixedAnnotation;
import phoswald.annotation.printer.annotations.MyNormalAnnotation;
import phoswald.annotation.printer.annotations.MyNormalPrimitivesAnnotation;
import phoswald.annotation.printer.annotations.MySingleAnnotationAnnotation;
import phoswald.annotation.printer.annotations.MySingleClassAnnotation;
import phoswald.annotation.printer.annotations.MySingleEnumAnnotation;
import phoswald.annotation.printer.annotations.MySingleIntAnnotation;
import phoswald.annotation.printer.annotations.MySingleStringAnnotation;

public class AnnotationPrinterTest {

    private AnnotationPrinter testee = new AnnotationPrinter();

    @Test(expected=NullPointerException.class)
    public void format_null_exception() {
        testee.format(null);
    }

    @Test
    public void format_marker_success() {
        Annotation annotation = AnnotatedClass.class.getAnnotation(MyMarkerAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MyMarkerAnnotation", result);
    }

    @Test
    public void format_singleInt_success() {
        Annotation annotation = AnnotatedClass.class.getAnnotation(MySingleIntAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MySingleIntAnnotation(42)", result);
    }

    @Test
    public void format_singleString_success() {
        Annotation annotation = AnnotatedClass.class.getAnnotation(MySingleStringAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MySingleStringAnnotation(\"foo\")", result);
    }

    @Test
    public void format_singleEnum_success() {
        Annotation annotation = AnnotatedClass.class.getAnnotation(MySingleEnumAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MySingleEnumAnnotation(phoswald.annotation.printer.annotations.MyEnum.VALUE_1)", result);
    }

    @Test
    public void format_singleClass_success() {
        Annotation annotation = AnnotatedClass.class.getAnnotation(MySingleClassAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MySingleClassAnnotation(java.io.Serializable.class)", result);
    }

    @Test
    public void format_singleAnnotation_success() {
        Annotation annotation = AnnotatedClass.class.getAnnotation(MySingleAnnotationAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MySingleAnnotationAnnotation(@phoswald.annotation.printer.annotations.MyMarkerAnnotation)", result);
    }

    @Test
    public void format_arrayOfInt_success() {
        Annotation annotation = AnnotatedClass.class.getAnnotation(MyArrayOfIntAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MyArrayOfIntAnnotation({ 42, 43 })", result);
    }

    @Test
    public void format_arrayOfString_success() {
        Annotation annotation = AnnotatedClass.class.getAnnotation(MyArrayOfStringAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MyArrayOfStringAnnotation({ \"foo\", \"bar\" })", result);
    }

    @Test
    public void format_arrayOfEnum_success() {
        Annotation annotation = AnnotatedClass.class.getAnnotation(MyArrayOfEnumAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MyArrayOfEnumAnnotation({ phoswald.annotation.printer.annotations.MyEnum.VALUE_1, phoswald.annotation.printer.annotations.MyEnum.VALUE_2 })", result);
    }

    @Test
    public void format_arrayOfClass_success() {
        Annotation annotation = AnnotatedClass.class.getAnnotation(MyArrayOfClassAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MyArrayOfClassAnnotation({ java.io.Serializable.class, java.util.List.class })", result);
    }

    @Test
    public void format_arrayOfAnnotation_success() {
        Annotation annotation = AnnotatedClass.class.getAnnotation(MyArrayOfAnnotationAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MyArrayOfAnnotationAnnotation({ @phoswald.annotation.printer.annotations.MySingleIntAnnotation(-1), @phoswald.annotation.printer.annotations.MySingleIntAnnotation(1) })", result);
    }

    @Test
    public void format_normal_success() {
        Annotation annotation = AnnotatedClass.class.getAnnotation(MyNormalAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MyNormalAnnotation(myInt=42, myString=\"foo\")", result);
    }

    @Test
    public void format_normalPrimitives_success() {
        Annotation annotation = AnnotatedClass.class.getAnnotation(MyNormalPrimitivesAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MyNormalPrimitivesAnnotation(myByte=123, myChar='x', myDouble=3.14, myFlag=true, myFloat=12.34F, myInt=1234567890, myLong=123456789012L, myShort=1234)", result);
    }

    @Test
    public void format_full_success() {
        Annotation annotation = AnnotatedClass.class.getAnnotation(MyFullAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MyFullAnnotation(myClass=java.io.Serializable.class, myClassList={ java.io.Serializable.class }, myEnum=phoswald.annotation.printer.annotations.MyEnum.VALUE_1, myEnumList={ phoswald.annotation.printer.annotations.MyEnum.VALUE_1 }, myInt=42, myIntList={ 42, 43 }, myObject=@phoswald.annotation.printer.annotations.MySingleIntAnnotation(42), myObjectList={ @phoswald.annotation.printer.annotations.MySingleIntAnnotation(43) }, myString=\"foo\", myStringList={ \"bar\" })", result);
    }

    @Test
    public void format_mixed_success() {
        Annotation annotation = AnnotatedClass.class.getAnnotation(MyMixedAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MyMixedAnnotation(value=\"value\", value2=\"value2\")", result);
    }

    @Test
    public void format_innerClass_success() {
        Annotation annotation = AnnotatedClass.class.getAnnotation(MyMarkerAnnotation.InnerAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MyMarkerAnnotation.InnerAnnotation", result);
    }

    @Test
    public void format_escapeStrings_success() {
        Annotation annotation = AnnotatedEscapingClass.class.getAnnotation(MyArrayOfStringAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MyArrayOfStringAnnotation({ \"\", \"x\", \" \\t \\r \\n \\' \\\" \\\\ \" })", result);
    }

    @Test
    public void format_escapeChars_success() {
        Annotation annotation = AnnotatedEscapingClass.class.getAnnotation(MyArrayOfCharAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MyArrayOfCharAnnotation({ 'a', 'b', '\\t', '\\r', '\\n', '\\'', '\\\"', '\\\\' })", result);
    }

    @Test
    public void format_emptyArray_success() {
        Annotation annotation = AnnotatedEmptyClass.class.getAnnotation(MyArrayOfIntAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MyArrayOfIntAnnotation({ })", result);
    }

    @Test
    public void format_defaultedSingle_success() {
        Annotation annotation = AnnotatedDefaultedClass.class.getAnnotation(MyDefaultedSingleAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MyDefaultedSingleAnnotation", result);
    }

    @Test
    public void format_defaultedFull_success() {
        Annotation annotation = AnnotatedDefaultedClass.class.getAnnotation(MyDefaultedFullAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MyDefaultedFullAnnotation", result);
    }

    @Test
    public void format_defaultOverriddenSingle_success() {
        Annotation annotation = AnnotatedDefaultOverriddenClass.class.getAnnotation(MyDefaultedSingleAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MyDefaultedSingleAnnotation(\"HI\")", result);
    }

    @Test
    public void format_defaultOverriddenFull_success() {
        Annotation annotation = AnnotatedDefaultOverriddenClass.class.getAnnotation(MyDefaultedFullAnnotation.class);
        String result = testee.format(annotation);
        assertEquals("@phoswald.annotation.printer.annotations.MyDefaultedFullAnnotation(myClass=java.util.Collection.class, myClassList={ }, myEmptyClassList={ java.lang.Object.class }, myInt=43, myString=\"HI\")", result);
    }

    @MyMarkerAnnotation
    @MySingleIntAnnotation(42)
    @MySingleStringAnnotation("foo")
    @MySingleEnumAnnotation(MyEnum.VALUE_1)
    @MySingleClassAnnotation(Serializable.class)
    @MySingleAnnotationAnnotation(@MyMarkerAnnotation)
    @MyArrayOfIntAnnotation({ 42, 43 })
    @MyArrayOfStringAnnotation({ "foo", "bar" })
    @MyArrayOfEnumAnnotation({ MyEnum.VALUE_1, MyEnum.VALUE_2 })
    @MyArrayOfClassAnnotation({ Serializable.class, List.class })
    @MyArrayOfAnnotationAnnotation({ @MySingleIntAnnotation(-1), @MySingleIntAnnotation(1) })
    @MyNormalAnnotation(myInt=42, myString="foo")
    @MyNormalPrimitivesAnnotation(myFlag=true, myByte=123, myShort=1234, myChar='x', myInt=1234567890, myLong=123456789012L, myFloat=12.34F, myDouble=3.14)
    @MyFullAnnotation(myClass=Serializable.class, myClassList={ Serializable.class }, myEnum=MyEnum.VALUE_1, myEnumList={ MyEnum.VALUE_1 }, myInt=42, myIntList={42, 43}, myObject=@MySingleIntAnnotation(42), myObjectList={ @MySingleIntAnnotation(43) }, myString="foo", myStringList={ "bar" })
    @MyMixedAnnotation(value="value", value2="value2")
    @MyMarkerAnnotation.InnerAnnotation
    private static class AnnotatedClass { }

    @MyArrayOfStringAnnotation({ "", "x", " \t \r \n \' \" \\ " })
    @MyArrayOfCharAnnotation({ 'a', 'b', '\t', '\r', '\n', '\'', '\"', '\\' })
    private static class AnnotatedEscapingClass { }

    @MyArrayOfIntAnnotation({ })
    private static class AnnotatedEmptyClass { }

    @MyDefaultedSingleAnnotation
    @MyDefaultedFullAnnotation
    private static class AnnotatedDefaultedClass { }

    @MyDefaultedSingleAnnotation("HI")
    @MyDefaultedFullAnnotation(myClass=Collection.class, myClassList={}, myEmptyClassList={Object.class}, myInt=43, myString="HI")
    private static class AnnotatedDefaultOverriddenClass { }
}
