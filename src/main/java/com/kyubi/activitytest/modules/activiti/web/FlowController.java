package com.kyubi.activitytest.modules.activiti.web;

import com.kyubi.activitytest.modules.activiti.service.IDemoFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author Kyubi
 */
@RestController
@RequestMapping("/act")
@Slf4j
@Lazy
@Api(value = "工作流", tags = {"act流程引擎测试"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class FlowController {
    @Resource
    private IDemoFlowService demoFlowService;

    @GetMapping("/genFlow")
    @ApiOperation(httpMethod = "GET", value = "创建工作流")
    public String genFlow() {
        return demoFlowService.genFlow();
    }

    @GetMapping("/completeFlow/{processId}")
    @ApiOperation(httpMethod = "GET", value = "完成工作流下一个任务")
    public String completeFlow(@PathVariable String processId) {
        return demoFlowService.completeFlow(processId);
    }

    @GetMapping("/getHistory/{processId}")
    @ApiOperation(httpMethod = "GET", value = "获取流程历史记录日志")
    public String getHistory(@PathVariable String processId) {
        return demoFlowService.getHistory(processId);
    }

    @GetMapping("/flow/{processId}")
    @ApiOperation(httpMethod = "GET", value = "获取工作流下一个任务")
    public String getFlowTask(@PathVariable String processId) {
        return demoFlowService.getFlowTask(processId);
    }

    @GetMapping("/flowByName/{userName}")
    @ApiOperation(httpMethod = "GET", value = "获取指定人的任务列表")
    public String getFlowTaskByUserName(@PathVariable String userName) {
        return demoFlowService.getFlowTaskByUserName(userName);
    }

}

