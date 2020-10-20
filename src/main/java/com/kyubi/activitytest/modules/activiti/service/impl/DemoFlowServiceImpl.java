package com.kyubi.activitytest.modules.activiti.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.kyubi.activitytest.modules.activiti.model.ActReviewDto;
import com.kyubi.activitytest.modules.activiti.service.IDemoFlowService;
import com.kyubi.activitytest.modules.enums.ActivityProcessInstanceEnums;
import com.kyubi.activitytest.modules.enums.ReviewStatusCommonEnum;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricData;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.ProcessInstanceHistoryLog;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.date.DatePattern.CHINESE_DATE_TIME_PATTERN;

/**
 * @author Kyubi
 * @date 2020-10-20 14:49
 */
@Service
@Slf4j
@Lazy
public class DemoFlowServiceImpl implements IDemoFlowService {

    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;
    @Resource
    private HistoryService historyService;

    @Override
    public String genFlow() {
        //流程所需参数
        ActReviewDto reviewDto = ActReviewDto.builder()
                .actProcessInstanceEnums(ActivityProcessInstanceEnums.DEMO)
                .reviewStatus(ReviewStatusCommonEnum.INIT.getCode())
                .reviewNo("TEST-ACTIVITI")
                .build();
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("reviewDto", reviewDto);

        //调用acti API 生成 流程
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(reviewDto.getActProcessInstanceEnums().getProcessKey()
                , reviewDto.getReviewNo(), variables);
        String processId = pi.getId();

        return "DEMO流程创建成功，当前流程实例ID：    " + processId;
    }

    @Override
    public String completeFlow(String processId) {
        Task task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        if (task == null) {
            return "操作失败!流程已终将，无任务完成";
        }
        taskService.setAssignee(task.getId(), "system-api");
        taskService.complete(task.getId());

        return "【" + task.getName() + "】 任务完成！";
    }

    @Override
    public String getHistory(String processId) {

        HistoricProcessInstance processInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey(processId).singleResult();

        if (processInstance == null) {
            processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processId).singleResult();
        }
        StringBuilder sb = new StringBuilder();
        List<HistoricActivityInstance> instances = historyService
                .createHistoricActivityInstanceQuery()
                .processInstanceId(processInstance.getId())
                .list();

        ProcessInstanceHistoryLog processInstanceHistoryLog = historyService
                .createProcessInstanceHistoryLogQuery(processInstance.getId())
                .includeComments()
                .includeVariables()
                .singleResult();

        log.info(JSON.toJSONString(processInstanceHistoryLog.getHistoricData()));

        sb.append("当前流程ID：")
                .append(processInstance.getId())
                .append(", 业务单据ID：")
                .append(processInstance.getBusinessKey())
                .append("\n").append("\n");
        if (instances != null && instances.size() > 0) {
            for (HistoricActivityInstance hai : instances) {
                sb.append("步骤ID：").append(hai.getActivityId()).append("\n");
                sb.append("步骤名称：").append(hai.getActivityName()).append("\n");
                sb.append("任务ID：").append(hai.getTaskId() == null ? "无" : hai.getTaskId()).append("\n");
                sb.append("执行人：").append(hai.getAssignee() == null ? "未指定" : hai.getAssignee()).append("\n");
                sb.append("执行时间：").append(DateUtil.format(hai.getEndTime(), CHINESE_DATE_TIME_PATTERN)).append("\n");
                if (hai.getTaskId() != null) {
                    Comment comment = getHisDataByTaskId(processInstanceHistoryLog.getHistoricData(), hai.getTaskId());
                    if (comment != null) {
                        sb.append("执行备注：").append(comment.getFullMessage()).append("\n");
                    }
                }
                sb.append("====================================").append("\n");
            }


        }


        return sb.toString();
    }

    private Comment getHisDataByTaskId(List<HistoricData> dataList, String taskId) {

        for (HistoricData historicData : dataList) {
            if (historicData instanceof Comment) {
                Comment comment = (Comment) historicData;
                if (taskId.equals(comment.getTaskId())) {
                    return comment;
                }
            }
        }
        return null;
    }
    @Override
    public String getFlowTask(String processId) {
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(processId).singleResult();

        if (task == null) {
            task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("流程ID：").append(processId.concat(" 的下一个任务：\n\n"));

        taskInfo(task, sb);

        return sb.toString();
    }


    private void taskInfo(Task task, StringBuilder sb) {
        if (task == null) {
            sb.append("无任务");
            return;
        }
        sb.append("代办任务ID:").append(task.getId()).append("\n");
        sb.append("代办任务名称:").append(task.getName()).append("\n");
        sb.append("代办任务创建时间:").append(task.getCreateTime()).append("\n");
        sb.append("代办任务办理人:").append(task.getAssignee() == null ? "未指定" : task.getAssignee()).append("\n");
        sb.append("流程实例ID:").append(task.getProcessInstanceId()).append("\n");
        sb.append("执行对象ID:").append(task.getExecutionId()).append("\n");
        sb.append("====================================").append("\n");
    }

    @Override
    public String getFlowTaskByUserName(String userName) {
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(userName).list();
        StringBuilder sb = new StringBuilder();
        sb.append(userName.concat(" :对应的任务列表：\n\n"));
        if (taskList.size() > 0) {

            for (Task task : taskList) {
                taskInfo(task, sb);
            }

        }


        return sb.toString();
    }
}
