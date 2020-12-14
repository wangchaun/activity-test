package com.kyubi.activitytest.modules.mysteelworkflow.web;

import com.alibaba.fastjson.JSON;
import com.kyubi.activitytest.modules.enums.BizType;
import com.kyubi.activitytest.modules.mysteelworkflow.entity.*;
import com.kyubi.activitytest.modules.mysteelworkflow.service.WorkFlowService;
import com.mysteel.workflow.api.BlackWorkFlowApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @description: WelcomeController 类
 * @projectName: activity-test
 * @className: WelcomeController
 * @author: wangsiming
 * @createTime: 2020/11/23 11:32
 */
@RestController
@RequestMapping("/gl")
@Slf4j
@Lazy
@Api(value = "gl工作流", tags = {"钢联隆众工作流"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkFlowController {

    @Resource
    private WorkFlowService workFlowService;

    @GetMapping("/genFlow")
    @ApiOperation(httpMethod = "GET", value = "创建工作流")
    public ResultInfo<WorkflowSubmitVO> genFlow(WorkflowSubmitDTO submitDTO) {
        submitDTO.setBizType(BizType.PROJECT_MODIFY.name());
        ResultInfo<WorkflowSubmitVO> resultInfo = new ResultInfo<>();
        WorkflowSubmitVO submitVO = null;
        try {
            submitVO = workFlowService.submit(submitDTO);
        } catch (Exception e) {
            log.error("创建工作流={}", e);
            return resultInfo.error(e.getMessage());
        }


        return resultInfo.success(submitVO);
    }

    @GetMapping("/completeFlow/")
    @ApiOperation(httpMethod = "GET", value = "完成工作流下一个任务")
    public String completeFlow(WorkflowExchangeDTO exchangeDTO) {
        WorkflowExchangeVO exchangeVO = null;
        try {
            exchangeVO = workFlowService.exchangeWorkFlow(exchangeDTO);
        } catch (Exception e) {
            log.error("completeFlow={}", e);
        }

        return JSON.toJSONString(exchangeVO);
    }

//    @GetMapping("/getHistory/{processId}")
//    @ApiOperation(httpMethod = "GET", value = "获取流程历史记录日志")
//    public String getHistory(@PathVariable String processId) {
//        return demoFlowService.getHistory(processId);
//    }

//    @GetMapping("/flow/{processId}")
//    @ApiOperation(httpMethod = "GET", value = "获取工作流下一个任务")
//    public String getFlowTask(@PathVariable String processId) throws Exception {
//        List<WorkflowNode> nodes = workFlowService.queryNodes(processId);
//        return JSON.toJSONString(nodes);
//    }

    @GetMapping("/flowByName/{userId}")
    @ApiOperation(httpMethod = "GET", value = "获取指定人的任务列表")
    public String getFlowTaskByUserName(@PathVariable Long userId) throws Exception {
        WorkflowQueryDTO workflowQueryDTO = new WorkflowQueryDTO();
        workflowQueryDTO.setPageNum(1);
        workflowQueryDTO.setPageSize(10000);
        workflowQueryDTO.setUserId(userId);
        List<WorkflowQueryVO> list = workFlowService.queryWorkFlowByAuditor(workflowQueryDTO);

        return JSON.toJSONString(list);
    }



}
