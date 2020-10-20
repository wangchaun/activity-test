package com.kyubi.activitytest.modules.enums;


import lombok.Getter;


/**
 * @author Kyubi
 */

@Getter
public enum ActivityProcessInstanceEnums {

    /**
     * DEMO流程
     */
    DEMO("DEMO","DEMO流程"),
    /**
     * 发票流程
     */
    MY_INVOICE("MY_INVOICE","发票流程"),

    ;

    /**
     *     流程图ID
     */
    private String processKey;
    /**
     *     业务单据流程KEY
     */
    private String businessKey;
    /**
     *     说明备注信息
     */
    private String desc;

    ActivityProcessInstanceEnums(String processKey, String desc) {
        this.processKey = processKey;
        this.businessKey = processKey.concat(":");
        this.desc = desc;
    }
}
