package com.kyubi.activitytest.config;

import com.kyubi.activitytest.enums.DBTypeEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Kyubi
 * @date 2020-09-29 14:54
 */
@Slf4j
public class DataSourceContextHolder {

    /**
     * 实际上就是开启多个线程，每个线程进行初始化一个数据源
     */
    private static final ThreadLocal CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源
     * @param dbTypeEnum
     */
    public static void setDbType(DBTypeEnum dbTypeEnum) {
        CONTEXT_HOLDER.set(dbTypeEnum.getValue());
    }

    /**
     * 取得当前数据源
     * @return
     */
    public static String getDbType() {
        return (String) CONTEXT_HOLDER.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clearDbType() {
        CONTEXT_HOLDER.remove();
    }
}
