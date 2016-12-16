package com.xianyue.packageScan;

import java.net.URL;

/**
 * @author  XianYue
 */
public enum ResourceType {
    NONE("none"),
    JAR("jar"),
    FILE("file");

    private String type;

    ResourceType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static ResourceType getResourceType(URL url) {
        for (ResourceType t: ResourceType.values() ) {
           if(t.getType().equals(url.getProtocol())) {
               return t;
           }
        }
        return NONE;
    }

}
