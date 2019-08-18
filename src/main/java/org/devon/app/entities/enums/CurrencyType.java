package org.devon.app.entities.enums;

public enum CurrencyType {
    EUR, USD, RON;

    public static CurrencyType fromString(String currencyTypeStr) {
        for (CurrencyType ct : CurrencyType.values()) {
            if (ct.name().equalsIgnoreCase(currencyTypeStr)) {
                return ct;
            }
        }
        return null;
    }
}
