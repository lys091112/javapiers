package com.xianyue.packageScan;

/**
 * @author XianYue
 */
public enum EnumFileType {
    NONE(".none"),
    CLASS(".class");

    private String type;

    EnumFileType(String type) {
        this.type = type;
    }

    public static EnumFileType getFileType(String type) {
        EnumFileType[] types = EnumFileType.values();
        for (EnumFileType enumType : types) {
            if (enumType.getType().equals(type)) {
                return enumType;
            }
        }
        return NONE;
    }

    public String getType() {
        return type;
    }
}
