package com.github.halab4dev.mongo2j.mapper;

/*
 *
 * @author halab
 */
public class WrappedAttributesExample {

    private Boolean booleanAttribute;
    private Byte byteAttribute;
    private Short shortAttribute;
    private Integer integerAttribute;
    private Long longAttribute;
    private Float floatAttribute;
    private Double doubleAttribute;
    private Character charAttribute;

    public WrappedAttributesExample() {
    }

    public WrappedAttributesExample(Character charAttribute) {
        this.charAttribute = charAttribute;
    }

    public Boolean getBooleanAttribute() {
        return booleanAttribute;
    }

    public void setBooleanAttribute(Boolean booleanAttribute) {
        this.booleanAttribute = booleanAttribute;
    }

    public Byte getByteAttribute() {
        return byteAttribute;
    }

    public void setByteAttribute(Byte byteAttribute) {
        this.byteAttribute = byteAttribute;
    }

    public Short getShortAttribute() {
        return shortAttribute;
    }

    public void setShortAttribute(Short shortAttribute) {
        this.shortAttribute = shortAttribute;
    }

    public Integer getIntegerAttribute() {
        return integerAttribute;
    }

    public void setIntegerAttribute(Integer integerAttribute) {
        this.integerAttribute = integerAttribute;
    }

    public Long getLongAttribute() {
        return longAttribute;
    }

    public void setLongAttribute(Long longAttribute) {
        this.longAttribute = longAttribute;
    }

    public Float getFloatAttribute() {
        return floatAttribute;
    }

    public void setFloatAttribute(Float floatAttribute) {
        this.floatAttribute = floatAttribute;
    }

    public Double getDoubleAttribute() {
        return doubleAttribute;
    }

    public void setDoubleAttribute(Double doubleAttribute) {
        this.doubleAttribute = doubleAttribute;
    }

    public Character getCharAttribute() {
        return charAttribute;
    }

    public void setCharAttribute(Character charAttribute) {
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
