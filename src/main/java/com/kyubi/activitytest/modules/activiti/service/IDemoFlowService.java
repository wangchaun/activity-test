package com.kyubi.activitytest.modules.activiti.service;

/**
 * @author Kyubi
 * @date 2020-10-20 14:49
 */
public interface IDemoFlowService {

    /**
     *  生成DEMO流程
     * @return
     */
    String genFlow();

    /**
     * 完成DEMO某个步骤
     * @param processId 流程ID
     * @return 前端返回值
     */
    String completeFlow(String processId);

    /**
     * 获取历史流程详细信息
     * @param processId 流程ID
     * @return 前端返回值
     */
    String getHistory(String processId);

    /**
     * 获取流程下一步任务
     * @param processId 流程ID
     * @return 前端返回值
     */
    String getFlowTask(String processId);

    /**
     * 获取指定人的任务列表
     * @param userName 指定人ID
     * @return 前端返回值
     */
    String getFlowTaskByUserName(String userName);
    
    
}
