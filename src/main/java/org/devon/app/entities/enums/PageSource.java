package org.devon.app.entities.enums;

public enum PageSource {
    M, S;

    public static PageSource fromString(String pgSrc) {
        for (PageSource ps : PageSource.values()) {
            if (ps.name().equalsIgnoreCase(pgSrc)) {
                return ps;
            }
        }
        return null;
    }

}
