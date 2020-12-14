package com.kyubi.activitytest.modules.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommonError {

    USER_AUTH_ERROR("10001", "用户认证失败"),
    UNAUTHORIZED_ACCESS("10003", "无权访问"),
    BUSINESS_ERROR("30000", "业务错误"),
    REQUEST_PARAMS_ERROR("30001", "请求参数异常"),
    UPLOAD_FILE_ERROR("20000", "文件上传失败"),
    SYSTEM_ERROR("40000", "系统异常"),
    SYSTEM_DISABLED("50000", "系统已被禁用"),
    LOCAL_LOGIN_EXCEPTION("60000", "请切换本地登录模式！"),
    LOCAL_LOGIN_USER_NOT_NULL("60001", "登录入不能为空！"),
    LOCAL_LOCAL_USER_NOT_EXIST("60002", "用户不存在!"),
    COMMIT_FAIL("60003", "对不起，提交失败，请刷新后重试！"),
    LONG_TIME_NOT_OPERATE("60004", "对不起，您长时间没有操作，请刷新后重试！"),
    COMMON_END_DATE_NOT_LESS_END_DATE("60005","结束时间不能小于开始时间"),
    EXPORT_SIZE_TOO_MANY("60006", "导出数据量最高只支持5万条！"),
	// --------------------------绩效考核-部门分类
	EXAMPLE_DEPARTMENT_QUERY_PARAMS_NULL("70001","查询参数不能为空!"),

    /* --------------------------绩效考核-项目分类  */
    EXAMPLE_PROJECT_QUERY_NO_AUTHORITY("80001","无权限查看该页面"),
    EXAMPLE_PROJECT_EXPORT_NO_AUTHORITY("80002","没有查到拥有导出的项目"),
    EXAMPLE_PROJECT_DATE_FORMAT("80003","汇总时间格式不正确"),
    EXAMPLE_PROJECT_SUMMARY_TYPE_ISNULL("80004","汇总类别为空"),
    EXAMPLE_PROJECT_URL_ERROR("80004","项目汇总跳转URL错误"),
    EXAMPLE_PROJECT_EXCEL_EXPORT_ERROR("80005","excel导出类型错误"),
    DAY_FORMAT_ERROR("80006","结束时间小于当前时间"),
    EXCEL_DATA_TOO_LONG("80007","对不起,已超过最多50000行的限制，请重新选择过滤条件"),
    SELECT_FULL_WEEK("80008","请选择一个完整的自然周"),
    SELECT_FULL_MONTH("80009","请选择一个完整的自然月"),
    BEGIN_END_TIME_LESS_HALF_YEAR("80010","起始日期与终止日期不能超过半年");

    private final String errorCode;
    private final String errorMsg;

}
