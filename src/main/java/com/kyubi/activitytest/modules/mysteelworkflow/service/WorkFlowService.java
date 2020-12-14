package com.kyubi.activitytest.modules.mysteelworkflow.service;


import com.kyubi.activitytest.modules.mysteelworkflow.entity.*;

import java.util.List;

/**
 * @author GLNC-ZLH
 * @date 2020/6/9 9:00
 */
public interface WorkFlowService {
    /**
     * 提交工作流
     * @param request 工作流提交request对象
     * @return 工作流提交response对象
     * @throws Exception
     */
    WorkflowSubmitVO submit(WorkflowSubmitDTO request) throws Exception;


    /**
     * 流转工作流
     * @param request 工作流流转request对象
     * @return 工作流流转response对象
     * @throws Exception
     */
    WorkflowExchangeVO exchangeWorkFlow(WorkflowExchangeDTO request) throws Exception;

    /**
     * 查询待我审核的流程
     * @param request 工作流查询request对象
     * @return
     * @throws Exception
     */
    List<WorkflowQueryVO> queryWorkFlowByAuditor(WorkflowQueryDTO request) throws Exception;

    /**
     * 查询我已办任务
     * @param request
     * @return
     */
    List<InputDataOperateRecord> queryAlreadyWorkFlow(AlreadyWorkflowQueryDTO request);

    /**
     * 查询流程节点信息
     * @return 工作流状态映射Response集合
     * @throws Exception
     */
    List<WorkflowNode> queryNodes(String procInstId) throws Exception;

    /**
     * 取消正在流转的工作流
     * @param processDefId 流程定义id
     * @param businessId 业务id
     * @param reason 取消原因
     * @throws Exception
     */
    void cancelWorkFlow(String processDefId, Long businessId, String reason) throws Exception;

    /**
     * 统计用户待办数
     * @param adminId
     * @return
     */
    long countToWorkFlow(Long adminId);
}
