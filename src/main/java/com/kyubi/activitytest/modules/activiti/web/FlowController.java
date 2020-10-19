package com.kyubi.activitytest.modules.activiti.web;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.kyubi.activitytest.enums.ActivityProcessInstanceEnums;
import com.kyubi.activitytest.enums.ReviewStatusCommonEnum;
import com.kyubi.activitytest.modules.activiti.model.ActReviewDto;
import com.kyubi.activitytest.modules.activiti.service.impl.InvoiceActService;
import com.kyubi.activitytest.modules.business.myinvoice.service.IMyInvoiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.*;
import org.activiti.engine.impl.cmd.GetBpmnModelCmd;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @author Kyubi
 */
@RestController
@RequestMapping("/act")
@Slf4j
@Lazy
@Api(value = "工作流", tags = {"act流程引擎测试"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class FlowController {

    @Autowired
    private RepositoryService repositoryService;
    @Resource
    private ProcessEngine processEngine;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Resource
    private HistoryService historyService;


    @GetMapping("/genFlow")
    @ApiOperation(httpMethod = "GET", value = "创建工作流")
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

    @GetMapping("/completeFlow/{processId}")
    @ApiOperation(httpMethod = "GET", value = "完成工作流")
    public String completeFlow(@PathVariable String processId) {
        Task task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        taskService.complete(task.getId());

        return "【" + task.getName() + "】 任务完成！";

    }

    @GetMapping("/getHistory/{processId}")
    @ApiOperation(httpMethod = "GET", value = "获取流程历史记录日志")
    public String getHistory(@PathVariable String processId) {

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
                sb.append("任务ID：").append(hai.getTaskId()).append("\n");
                sb.append("执行人：").append(hai.getAssignee()).append("\n");
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

    public Comment getHisDataByTaskId(List<HistoricData> dataList, String taskId) {

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


    @GetMapping("/flow/{processId}")
    @ApiOperation(httpMethod = "GET", value = "获取工作流下一个任务")
    public String getFlowTask(@PathVariable String processId) {
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(processId).singleResult();

        if (task == null) {
            taskService.createTaskQuery().processInstanceId(processId).singleResult();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(processId.concat(" : 下一个任务：\n\n"));

        taskInfo(task, sb);

        return sb.toString();
    }

    private void taskInfo(Task task, StringBuilder sb) {
        sb.append("代办任务ID:"+task.getId()).append("\n");
        sb.append("代办任务name:"+task.getName()).append("\n");
        sb.append("代办任务创建时间:"+task.getCreateTime()).append("\n");
        sb.append("代办任务办理人:"+task.getAssignee()).append("\n");
        sb.append("流程实例ID:"+task.getProcessInstanceId()).append("\n");
        sb.append("执行对象ID:"+task.getExecutionId()).append("\n");
        sb.append("====================================").append("\n");
    }

    @GetMapping("/flowByName/{userName}")
    @ApiOperation(httpMethod = "GET", value = "获取指定人的工作列表")
    public String getFlowTaskByUserName(@PathVariable String userName) {
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(userName).list();
        StringBuilder sb = new StringBuilder();
        sb.append(userName.concat(" :对应的任务列表：\n\n"));
        if(taskList.size()>0){

            for (Task task : taskList){
                taskInfo(task, sb);
            }

        }


        return sb.toString();
    }

}

