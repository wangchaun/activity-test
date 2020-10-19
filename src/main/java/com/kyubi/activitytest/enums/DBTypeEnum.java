package com.kyubi.activitytest.enums;

import lombok.Getter;

/**
 * @author Kyubi
 * @date 2020-09-29 14:52
 */
@Getter
public enum DBTypeEnum {

    /**
     * 测试
     */
    LOCAL("ACTIVITI"),
    /**
     * 预发
     */
    ONLINE("MYBUSINESS");
    private String value;

    DBTypeEnum(String value) {
        this.value = value;
    }

}
