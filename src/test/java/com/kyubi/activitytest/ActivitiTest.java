package com.kyubi.activitytest;

import com.alibaba.fastjson.JSON;
import com.kyubi.activitytest.modules.enums.ActivityProcessInstanceEnums;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @program: cosmos-poseidon
 * @description: activiti工作流
 * @author: siming.wang
 * @create: 2019-06-24 10:19
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ActivitiTest {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;


    @Test
    public void testGenFlow() {

        String bussKey = ActivityProcessInstanceEnums.MY_INVOICE.getBusinessKey().concat("33");

        genFlow(bussKey);
//        getFlowTaskByBuss(bussKey);
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put(ReSupplierActiConstans.adminFlag, true);
//        map.put(ReSupplierActiConstans.chargerFlag, true);
//        map.put(ReSupplierActiConstans.ccFlag, true);
//        map.put(ReSupplierActiConstans.finanFlag, true);
//        map.put(ReSupplierActiConstans.finanAdjustFlag, true);
//        map.put(ReSupplierActiConstans.maxFlag, true);
//        map.put(ReSupplierActiConstans.operationMode, 1);
//        completeTaskByBuss(bussKey, map);
//        log.info("下一个任务：");
//        getFlowTaskByBuss(bussKey);


    }


    public String genFlow(String bussKey) {
        //启动流程定义，返回流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(ActivityProcessInstanceEnums.MY_INVOICE.getProcessKey(), bussKey);

        String processId = pi.getId();
        System.out.println("流程创建成功，当前流程实例ID：" + processId);

        return processId;
    }

    @Test
    public void completeFlow() {

        Task task = taskService.createTaskQuery().processInstanceId("630001").singleResult();
        System.out.println("执行前，任务名称：" + task.getName());
        taskService.complete(task.getId());


    }


    public Task getFlowTaskByBuss(String bussId) {
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(bussId).singleResult();
        log.info("当前任务：{}", JSON.toJSONString(task == null ? "" : task.getName()));
        return task;
    }


    public Task completeTaskByBuss(String bussId, Map<String, Object> map) {
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(bussId).singleResult();
        log.info("完成任务：{}", JSON.toJSONString(task.getName()));
        taskService.addComment(task.getId(), task.getProcessInstanceId(), "123213123");
        taskService.setAssignee(task.getId(), "wangsmssssmmmm");
        taskService.complete(task.getId(), map);
        return task;
    }
}
