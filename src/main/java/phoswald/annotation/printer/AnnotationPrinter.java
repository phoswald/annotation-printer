package phoswald.annotation.printer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * Utility class for formatting annotations.
 */
public class AnnotationPrinter {

    /**
     * Formats the given annotation.
     *
     * @param annotation the annotation to be formatted, must not be null.
     * @return the annotation formatted using Java Syntax, never null or empty.
     */
    public String format(Annotation annotation) {
        StringBuilder sb = new StringBuilder();
        formatAnnotation(sb, annotation);
        return sb.toString();
    }

    private static void formatAnnotation(StringBuilder sb, Annotation annotation) {
        sb.append("@");
        sb.append(annotation.annotationType().getName().replace('$', '.'));
        Method[] methods = annotation.annotationType().getDeclaredMethods();
        Arrays.sort(methods, Comparator.comparing(Method::getName));
        int count = 0;
        for(Method method : methods) {
            Object value = getElement(annotation, method);
            if(value == null) {
                continue;
            }
            if(count == 0) {
                sb.append('(');
            } else {
                sb.append(", ");
            }
            if(!isSingleElement(methods)) {
                sb.append(method.getName()).append('=');
            }
            format(sb, value);
            count++;
        }
        if(count > 0) {
            sb.append(')');
        }
    }

    private static void formatArray(StringBuilder sb, Object array) {
        sb.append("{ ");
        int length = Array.getLength(array);
        for(int index = 0; index < length; index++) {
            Object element = Array.get(array, index);
            if(index > 0) {
                sb.append(", ");
            }
            format(sb, element);
        }
        if(length > 0) {
            sb.append(' ');
        }
        sb.append('}');
    }

    private static void formatClass(StringBuilder sb, Class<?> value) {
        sb.append(value.getName().replace('$', '.') + ".class");
    }

    private static void formatEnum(StringBuilder sb, Enum<?> value) {
        sb.append(value.getDeclaringClass().getName().replace('$', '.') + "." + value.name());
    }

    private static void format(StringBuilder sb, Object value) {
        if(value == null) {
            throw new IllegalArgumentException("Cannot handle null annotation element.");
        } else if(value instanceof Annotation) {
            formatAnnotation(sb, (Annotation) value);
        } else if(value.getClass().isArray()) {
            formatArray(sb, value);
        } else if(value instanceof Class) {
            formatClass(sb, (Class<?>) value);
        } else if(value instanceof Enum) {
            formatEnum(sb, (Enum<?>) value);
        } else if(value instanceof Boolean || value instanceof Byte || value instanceof Short || value instanceof Integer || value instanceof Double) {
            sb.append(value);
        } else if(value instanceof Character) {
            sb.append('\'');
            appendChar(sb, (char) value);
            sb.append('\'');
        } else if(value instanceof Long) {
            sb.append(value + "L");
        } else if(value instanceof Float) {
            sb.append(value + "F");
        } else if(value instanceof String) {
            sb.append('"');
            for(char c : value.toString().toCharArray()) {
                appendChar(sb, c);
            }
            sb.append('"');
        } else {
            throw new IllegalArgumentException("Unsupported annotation element: " + value.getClass().getName());
        }
    }

    private static void appendChar(StringBuilder sb, char c) {
        switch(c) {
            case '\t':
                sb.append("\\t");
                break;
            case '\r':
                sb.append("\\r");
                break;
            case '\n':
                sb.append("\\n");
                break;
            case '\'':
            case '\"':
            case '\\':
                sb.append('\\').append(c);
                break;
            default:
                sb.append(c);
        }
    }

    private static Object getElement(Annotation annotation, Method method) {
        try {
            Object defaultValue = method.getDefaultValue();
            Object value = method.invoke(annotation);
            if(Objects.deepEquals(defaultValue, value)) {
                return null;
            }
            return value;
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new IllegalArgumentException("Failed to inspect annotation.", e);
        }
    }

    private static boolean isSingleElement(Method[] methods) {
        return methods.length == 1 && methods[0].getName().equals("value");
    }
}
