/**
 * 供应商相关审核表业务
 */
package com.kyubi.activitytest.modules.enums;


import lombok.Getter;

/**
 * 供应商相关审核表枚举
 *
 * @author 日向宁次
 */
@Getter
public enum ReviewStatusCommonEnum {

    /**
     * 初始
     */
    INIT("0","初始",""),
    WAIT_FOR_DELIVERY("50", "待寄出",""),

    /**
     * 待审核状态
     */
    PURCHASE_REVIEWING("250", "待采购初审","needPass"),
    MANAGER_REVIEWING("260", "待经理复审","needMaPass"),
    FINANCE_REVIEWING("270", "待财务审核","needFinancePass"),
    REVIEW_PASSED("800", "审核通过",""),

    /**
     * 驳回状态 > 900
     */
    REVIEW_REJECTED("900", "审核驳回",""),
    REVIEW_REJECTED_BY_PURCHASE("900", "采购初审驳回",""),
    REVIEW_REJECTED_BY_MANAGER("925", "经理复审驳回",""),
    REVIEW_REJECTED_BY_FINANCE("935", "财务审核驳回","")
    ;

    /**
     * 数据库状态编码
     */
    private String code;

    /**
     * //对应流程图节点名称
     */
    private String actName;

    /**
     *  //对应流程图角色变量
     */
    private String roleVar;

    ReviewStatusCommonEnum(String code, String actName, String roleVar) {
        this.code = code;
        this.actName = actName;
        this.roleVar = roleVar;
    }

    public static ReviewStatusCommonEnum getReviewStatusReviewEnum(String code) {
        for (ReviewStatusCommonEnum c : ReviewStatusCommonEnum.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }

    public static ReviewStatusCommonEnum getReviewStatusReviewEnumByDesc(String actName) {
        for (ReviewStatusCommonEnum c : ReviewStatusCommonEnum.values()) {
            if (c.getActName().equals(actName)) {
                return c;
            }
        }
        return null;
    }


    /**
     *  //对应状态的审核状态 true 审核通过 false 审核失败
     */
    public boolean isPassStatus() {

        if (REVIEW_REJECTED.getCode().equals(code)
                || REVIEW_REJECTED_BY_PURCHASE.getCode().equals(code)
                || REVIEW_REJECTED_BY_MANAGER.getCode().equals(code)
                || REVIEW_REJECTED_BY_FINANCE.getCode().equals(code)) {

            return false;
        }

        return true;
    }

    /**
     *  获取 log string
     * @return
     */
    public String getOperationLogMessage(){

        boolean flag = isPassStatus();

        String str = "";
        if (this.getActName().contains("审")){
            if(flag){
                str = "->通过";
            }
            if(!flag){
                str = "->拒绝";
            }
        }else {
            str = "->完成";
        }

        return this.getActName() + str;
    }
}
