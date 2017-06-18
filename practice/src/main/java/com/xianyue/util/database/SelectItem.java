package com.xianyue.util.database;


import org.apache.commons.lang3.StringUtils;

/**
 * @author Xianyue
 */
public class SelectItem {
    private String itemName;
    private String aliasName;

    public SelectItem(String itemName, String aliasName) {
        this.itemName = itemName;
        this.aliasName = aliasName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(" ");
        sb.append(itemName);
        if(!StringUtils.isBlank(aliasName)) {
            sb.append(" AS ").append(aliasName);
        }
        return sb.toString();
    }
}
