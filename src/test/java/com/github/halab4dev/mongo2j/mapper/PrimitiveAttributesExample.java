package com.github.halab4dev.mongo2j.mapper;

/*
 *
 * @author halab
 */
public class PrimitiveAttributesExample {

    public static final String STATIC_FINAL = "static final";

    private boolean booleanAttribute;
    private byte byteAttribute;
    private short shortAttribute;
    private int integerAttribute;
    private long longAttribute;
    private float floatAttribute;
    private double doubleAttribute;
    private char charAttribute;

    public boolean isBooleanAttribute() {
        return booleanAttribute;
    }

    public void setBooleanAttribute(boolean booleanAttribute) {
        this.booleanAttribute = booleanAttribute;
    }

    public byte getByteAttribute() {
        return byteAttribute;
    }

    public void setByteAttribute(byte byteAttribute) {
        this.byteAttribute = byteAttribute;
    }

    public short getShortAttribute() {
        return shortAttribute;
    }

    public void setShortAttribute(short shortAttribute) {
        this.shortAttribute = shortAttribute;
    }

    public int getIntegerAttribute() {
        return integerAttribute;
    }

    public void setIntegerAttribute(int integerAttribute) {
        this.integerAttribute = integerAttribute;
    }

    public long getLongAttribute() {
        return longAttribute;
    }

    public void setLongAttribute(long longAttribute) {
        this.longAttribute = longAttribute;
    }

    public float getFloatAttribute() {
        return floatAttribute;
    }

    public void setFloatAttribute(float floatAttribute) {
        this.floatAttribute = floatAttribute;
    }

    public double getDoubleAttribute() {
        return doubleAttribute;
    }

    public void setDoubleAttribute(double doubleAttribute) {
        this.doubleAttribute = doubleAttribute;
    }

    public char getCharAttribute() {
        return charAttribute;
    }

    public void setCharAttribute(char charAttribute) {
        this.charAttribute = charAttribute;
    }

    @Override
    public String toString() {
        return "PrimitiveAttributesExample{" +
                "booleanAttribute=" + booleanAttribute +
                ", byteAttribute=" + byteAttribute +
                ", shortAttribute=" + shortAttribute +
                ", integerAttribute=" + integerAttribute +
                ", longAttribute=" + longAttribute +
                ", floatAttribute=" + floatAttribute +
                ", doubleAttribute=" + doubleAttribute +
                ", charAttribute=" + charAttribute +
                '}';
    }
}
