package org.devon.app.entities.enums;

public enum Partitioning {
    DECOMANDAT, SEMIDECOMANDAT, NEDECOMANDAT, CIRCULAR, VAGON;

    public static Partitioning fromString(String partitioningStr) {
        for (Partitioning pt : Partitioning.values()) {
            if (pt.name().equals(partitioningStr.toUpperCase())) {
                return pt;
            }
        }
        return null;
    }

}
