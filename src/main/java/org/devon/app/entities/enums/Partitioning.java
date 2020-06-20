package org.devon.app.entities.enums;

public enum Partitioning {
    DECOMANDAT, SEMIDECOMANDAT, NEDECOMANDAT, CIRCULAR, VAGON, NA;

    public static Partitioning fromString(String partitioningStr) {
        if (partitioningStr == null) {
            partitioningStr = "NA";
        }
        for (Partitioning pt : Partitioning.values()) {
            if (pt.name().equals(partitioningStr.toUpperCase())) {
                return pt;
            }
        }
        return null;
    }

}
