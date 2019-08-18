package org.devon.app.entities.enums;

public enum ComfortType {
    LUX("lux"), ONE("1"), TWO("2"), THREE("3");

    private String comfortType;

    ComfortType(String comfType) {
        this.comfortType = comfType;
    }

    public String getComfortType() {
        return comfortType;
    }

    public static ComfortType fromString(String comfTypeStr) {
        for (ComfortType ct : ComfortType.values()) {
            if (ct.comfortType.equalsIgnoreCase(comfTypeStr)) {
                return ct;
            }
        }
        return null;
    }
}
