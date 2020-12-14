package com.kyubi.activitytest.modules.enums;

/**
 * @author GLNC-ZLH
 * @date 2020/6/29
 */
public enum AuditStatus {
    /**
     * 正常,无需审核
     */
    NORMAL(0, "正常"),
    /**
     * 未审核
     */
    NO_AUDIT(1, "未审核"),
    /**
     * 审核通过
     */
    PASSED(2, "审核通过"),
    /**
     * 已拒绝
     */
    REJECTED(3, "审核不通过"),
    /**
     * 已撤销
     */
    CANCELED(-1, "已撤销");

    private AuditStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    private int status;
    private String desc;

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
