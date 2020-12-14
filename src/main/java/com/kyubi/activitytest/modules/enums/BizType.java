package com.kyubi.activitytest.modules.enums;

/**
 * @author GLNC-ZLH
 * @date 2020/6/16 11:22
 */
public enum BizType {
    /**
     * overseas-test
     */
    PROJECT_MODIFY("overseas-test");

    private String key;

    private BizType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
