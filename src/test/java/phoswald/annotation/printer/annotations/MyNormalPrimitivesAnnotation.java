package phoswald.annotation.printer.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyNormalPrimitivesAnnotation {

    public boolean myFlag();

    public byte myByte();

    public short myShort();

    public char myChar();

    public int myInt();

    public long myLong();

    public float myFloat();

    public double myDouble();
}
