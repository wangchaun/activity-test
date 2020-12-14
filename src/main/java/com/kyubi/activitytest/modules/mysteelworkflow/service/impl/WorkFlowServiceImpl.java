package com.kyubi.activitytest.modules.mysteelworkflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.kyubi.activitytest.modules.enums.AuditStatus;
import com.kyubi.activitytest.modules.enums.BizType;
import com.kyubi.activitytest.modules.exception.BizException;
import com.kyubi.activitytest.modules.exception.CommonError;
import com.kyubi.activitytest.modules.mysteelworkflow.entity.*;
import com.kyubi.activitytest.modules.mysteelworkflow.service.WorkFlowService;
import com.kyubi.activitytest.modules.utils.DateUtils;
import com.mysteel.admin.Admin;
import com.mysteel.admin.AdminService;
import com.mysteel.workflow.api.BlackWorkFlowApi;
import com.mysteel.workflow.api.TaskFlowControlApi;
import com.mysteel.workflow.api.WorkFlowApi;
import com.mysteel.workflow.dto.DoneTaskDTO;
import com.mysteel.workflow.dto.ProcessDefineDTO;
import com.mysteel.workflow.dto.ProcessVarDTO;
import com.mysteel.workflow.dto.TodoTaskDTO;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 调用工作流系统接口
 *
 * @author GLNC-ZLH
 * @date 2020/5/15 10:49
 */
@Service
@Slf4j
public class WorkFlowServiceImpl implements WorkFlowService {

    @Resource
    private WorkFlowApi workFlowApi;
    @Resource
    private TaskFlowControlApi taskFlowControlApi;
    @Resource
    private BlackWorkFlowApi blackWorkFlowApi;

    /**
     * 工作流系统中的平台编号
     */
    private static final String SYSTEM = "D06B028423FBD40C4B48F3635FDC13E3";
    private static final String APPLY_TIME_FIELD = "operateTime";
    private static final String AUDIT_TIME_FIELD = "auditTime";
    @Override
    public WorkflowSubmitVO submit(WorkflowSubmitDTO request) throws Exception {
        if (request == null) {
            throw new BizException(CommonError.REQUEST_PARAMS_ERROR.getErrorMsg());
        }

        WorkflowSubmitVO response = new WorkflowSubmitVO();

        String flowDefId = request.getFlowDefId();
        //业务id
        Long bizId = request.getBizId();
        //业务查询关键字
        String businessKey = request.getTitle();
        //发起人备注
        String remark = request.getRemark();

        Long userId = request.getUserId();
        //流程变量
        Map<String, ProcessVarDTO> processVar = new HashMap<>();
        processVar.put("applyUserId", new ProcessVarDTO(userId));
        try {

            Object[] params = new Object[8];
            params[0] = flowDefId;
            params[1] = bizId;
            params[2] = businessKey;
            params[3] = "";
            params[4] = businessKey;
            params[5] = processVar;
            params[6] = userId;
            params[7] = remark;

            System.out.println(JSON.toJSONString(params));
            String res = HttpUtil.post("http://openapi.mysteel.com//lzworkflow/startProcess", JSON.toJSONString(params));
            System.out.println(res);
            String instansId = JSON.parseObject(res, String.class);

//            String instansId = blackWorkFlowApi.startProcess(flowDefId, bizId, businessKey, "", businessKey, processVar, userId, remark);
            response.setProcInstanceId(instansId);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new Exception("提交工作流失败", e);
        }
        response.setProcDefId(flowDefId);
        return response;
    }

    @Override
    public WorkflowExchangeVO exchangeWorkFlow(WorkflowExchangeDTO request) throws Exception {

        if (request == null) {
            throw new BizException(CommonError.REQUEST_PARAMS_ERROR.getErrorMsg());
        }

        Map<String, Number> processVar = new HashMap<>();
        boolean isPass = request.getIsPass().equals(1);
        processVar.put("pass", isPass ? 1 : 0);
        String remark = request.getRemark();
        Long auditUserId = request.getUserId();
        WorkflowExchangeVO response = new WorkflowExchangeVO();
        TodoTaskDTO todoTask = null;
        try {
            Object[] params = new Object[3];
            params[0] = BizType.PROJECT_MODIFY.getKey();
            params[1] = request.getBizId();
//            params[2] = auditUserId;

            System.out.println(JSON.toJSONString(params));
            String res = HttpUtil.post("http://openapi.mysteel.com//lzworkflow/findTodoTask", JSON.toJSONString(params));
            System.out.println(res);
            if (res.equals("\"dubbo has not return value!\"")) {
                response.setStatus("1");
                response.setMessage("审批任务不存在");
                return response;
            }
            todoTask = JSON.parseObject(res, TodoTaskDTO.class);


//            todoTask = blackWorkFlowApi.findTodoTask(BizType.PROJECT_MODIFY.getKey(), request.getBizId(),auditUserId);
        } catch (Exception e) {
            log.error("查询任务信息失败",e);
            throw new Exception("查询任务信息失败", e);
        }

        if (todoTask == null) {
            response.setStatus("1");
            response.setMessage("审批任务不存在");
            return response;
        }

        boolean result = false;
        try {
            Object[] params = new Object[5];
            params[0] = todoTask.getId();
            params[1] = auditUserId;
            params[2] = isPass;
            params[3] = remark;
            params[4] = processVar;

            System.out.println(JSON.toJSONString(params));
            result = workFlowApi.completeTask(todoTask.getId(), auditUserId, isPass, remark, processVar);
//            String res = HttpUtil.post("http://openapi.mysteel.com//lzworkflow/completeTask", JSON.toJSONString(params));
            System.out.println(result);

//            result = blackWorkFlowApi.completeTask(todoTask.getId(), auditUserId, isPass, remark, processVar);
        } catch (Exception e) {
            log.error("流转工作流失败",e);
            throw new Exception("流转工作流失败", e);
        }
        //表示完成
        if (result) {
            if (isPass) {
                response.setStatus("1");
                response.setMessage("审核通过成功");
            } else {
                response.setStatus("0");
                response.setMessage("驳回成功");
            }
        } else {
            if (isPass) {
                response.setStatus("1");
                response.setMessage("审核通过失败");
            } else {
                response.setStatus("0");
                response.setMessage("驳回失败");
            }
        }

        return response;
    }

    @Override
    public List<WorkflowQueryVO> queryWorkFlowByAuditor(WorkflowQueryDTO request) throws Exception {
        if (request == null) {
            throw new BizException(CommonError.REQUEST_PARAMS_ERROR.getErrorMsg());
        }

        int pageNum = request.getPageNum();
        int pageSize = request.getPageSize();
        Long bizId = request.getBizId();
        String businessKey = request.getKeyword();
        Long adminId = request.getUserId();

        //, String SYSTEM, Long businessId, String businessKey, Long adminId
        PageInfo<TodoTaskDTO> todoTasks = blackWorkFlowApi.queryPageTodoTask(pageNum, pageSize, SYSTEM, bizId, businessKey, adminId);

        List<WorkflowQueryVO> result = new ArrayList<>();
        for (TodoTaskDTO task : todoTasks.getList()) {
            WorkflowQueryVO res = new WorkflowQueryVO();
            res.setTaskId(task.getId());
            res.setAssign(task.getAssignee());
            res.setProcessDefinitionId(task.getProcessKey());
            res.setProcessInstanceId(task.getInstanceId());
            res.setTitle(task.getName());
            res.setBusinessKey(task.getBusinessKey());
            res.setCreateTime(task.getCreateTime());
            res.setBusinessId(task.getBusinessId());
            result.add(res);
        }

        return result;
    }

    @Override
    public List<InputDataOperateRecord> queryAlreadyWorkFlow(AlreadyWorkflowQueryDTO request) {
        if (request == null) {
            throw new BizException(CommonError.REQUEST_PARAMS_ERROR.getErrorMsg());
        }
        //查询工作流系统中所有已办，默认查10000
        int pageNum = 1;
        int pageSize = 10000;
        Long bizId = request.getBizId();
        String businessKey = request.getKeyword();
        Long adminId = request.getUserId();

        //, String SYSTEM, Long businessId, String businessKey, Long adminId
        PageInfo<DoneTaskDTO> doneTasks = blackWorkFlowApi.queryPageDoneTask(pageNum, pageSize, SYSTEM, bizId, businessKey, adminId);
        List<InputDataOperateRecord> operateRecords = Lists.newArrayList();
        if (doneTasks != null && !doneTasks.getList().isEmpty()) {
            List<WorkflowQueryVO> list = new ArrayList<>();
            for (DoneTaskDTO task : doneTasks.getList()) {
                WorkflowQueryVO res = new WorkflowQueryVO();
                res.setTaskId(task.getTaskId());
                res.setAssign(task.getAssignee());
                res.setProcessDefinitionId(task.getProcessDefinitionId());
                res.setProcessInstanceId(task.getProcessDefinitionId());
                res.setTitle(task.getName());
                res.setBusinessKey(task.getBusinessKey());
                res.setBusinessId(task.getBusinessId());
                list.add(res);
            }

            Map<String, Object> params = BeanUtil.beanToMap(request);
            if (APPLY_TIME_FIELD.equals(request.getDateField())) {
                if (request.getStartDate() != null) {
                    params.put("operateTimeStart", DateUtils.getDateWithMinTime(request.getStartDate()).getTime());
                }
                if (request.getEndDate() != null) {
                    params.put("operateTimeEnd", DateUtils.getDateWithMaxTime(request.getEndDate()).getTime());
                }
            }
            if (AUDIT_TIME_FIELD.equals(request.getDateField())) {
                if (request.getStartDate() != null) {
                    params.put("auditTimeStart", DateUtils.getDateWithMinTime(request.getStartDate()).getTime());
                }
                if (request.getEndDate() != null) {
                    params.put("auditTimeEnd", DateUtils.getDateWithMaxTime(request.getEndDate()).getTime());
                }
            }
            params.put("needDept", 1);
            params.put("auditorId",request.getUserId());
            params.put("ids", list.stream().map(WorkflowQueryVO::getBusinessId).collect(Collectors.toList()));
            PageHelper.startPage(request.getPageNum(), request.getPageSize());
        }
        return operateRecords;
    }

    @Override
    public List<WorkflowNode> queryNodes(String procInstId) throws Exception {
        if (StringUtils.isBlank(procInstId)) {
            throw new BizException(CommonError.REQUEST_PARAMS_ERROR.getErrorMsg());
        }
        ProcessDefineDTO processDefine = blackWorkFlowApi.findAuditProcess(procInstId);

        if (processDefine == null) {
            throw new Exception(CommonError.REQUEST_PARAMS_ERROR.getErrorMsg());
        }

        ProcessDefineDTO.Task start = processDefine.getTaskMap().get("start");

        List<WorkflowNode> resultList = new ArrayList<>();

        while (start.getNexts() != null && !start.getNexts().isEmpty()) {
            ProcessDefineDTO.Task task = processDefine.getTaskMap().get(start.getNexts().get(0).getId());
            if (task == null) {
                break;
            }
            WorkflowNode nodeResponse = new WorkflowNode();
            nodeResponse.setAdminIds(task.getAdminIds());
            nodeResponse.setAudited(task.getAudited());
            nodeResponse.setAdmin(task.getAdmin());
            nodeResponse.setKey(task.getKey());
            nodeResponse.setRole(task.getRole());
            resultList.add(nodeResponse);

            start = task;
        }

        return resultList;
    }

    @Override
    public void cancelWorkFlow(String processDefId, Long businessId, String reason) throws Exception {
        if (StringUtils.isBlank(processDefId) || businessId == null) {
            throw new BizException(CommonError.REQUEST_PARAMS_ERROR.getErrorMsg());
        }
        try {
            workFlowApi.cancelProcessInstance(processDefId, businessId, reason);
        } catch (Exception e) {
            throw new Exception("取消工作流失败," + e.getLocalizedMessage());
        }
    }

    @Override
    public long countToWorkFlow(Long adminId) {
        return workFlowApi.countTodoTask(Lists.newArrayList(BizType.PROJECT_MODIFY.getKey()), adminId);
    }
}
