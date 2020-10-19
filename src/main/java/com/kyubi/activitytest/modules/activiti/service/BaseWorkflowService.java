package com.kyubi.activitytest.modules.activiti.service;

import com.alibaba.fastjson.JSON;
import com.kyubi.activitytest.enums.ReviewStatusCommonEnum;
import com.kyubi.activitytest.modules.activiti.model.ActReviewDto;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.Map;


/**
 * @author Kyubi
 */
@Slf4j
public abstract class BaseWorkflowService {

    @Resource
    public RuntimeService runtimeService;

    @Resource
    public TaskService taskService;


    private void checkPermissions(String permisson) {
    }

    /**
     * Author kyubi
     * description 暴露外界方法：完成流程图某个节点
     * date 2019/11/10 0010 下午 9:17
     **/
    public void completeFlow(Map<String, Object> variables) {

        log.info("[完成流程图某个节点 variables={}]", JSON.toJSONString(variables));
        ActReviewDto reviewDto = (ActReviewDto) variables.get("reviewDto");

        ReviewStatusCommonEnum statusEnum = reviewDto.getReviewStatusCommonEnum();

        if (StringUtils.isNotBlank(reviewDto.getReviewStatus())) {
            statusEnum = ReviewStatusCommonEnum.getReviewStatusReviewEnum(reviewDto.getReviewStatus());
        }

        log.info("[完成流程图某个节点 ={}]", JSON.toJSONString(statusEnum.getActName()));

        if (statusEnum != null) {
            log.info("公用流程枚举存在，走公用流程");
            this.completeFlowCommon(variables);
            return;
        }

        throw new RuntimeException("请至少传递一种流程枚举");
    }


    private void completeFlowCommon(Map<String, Object> variables) {

        ActReviewDto reviewDto = (ActReviewDto) variables.get("reviewDto");

        ReviewStatusCommonEnum statusEnum = reviewDto.getReviewStatusCommonEnum();

        ReviewStatusCommonEnum checkEnum = ReviewStatusCommonEnum.getReviewStatusReviewEnum(reviewDto.getReviewStatus());

        if (ReviewStatusCommonEnum.INIT.equals(statusEnum)) {
            createFlow(variables);
            return;
        }

        if (statusEnum != null) {
            variables.put(checkEnum.getRoleVar(), statusEnum.isPassStatus());
        }

        //验证权限
        checkPermissions(reviewDto.getPermission());

        Task task = getFlowTaskByBussId(reviewDto.getReviewNo());

        if (task == null) {
            throw new RuntimeException("流程已终结，操作失败！");
        }
        if (statusEnum != null) {
            log.info("获取当前任务：{}, 是否审核通过?={}", task.getName(), statusEnum.isPassStatus());
        }

        if (task.getAssignee() != null && !task.getAssignee().equals(reviewDto.getUserName())) {
            throw new RuntimeException("当前流程操作人错误！应为：" + task.getAssignee());
        }

        if (!checkEnum.getActName().equals(task.getName())) {
            throw new RuntimeException("流程错误！请先完成任务 : " + task.getName());
        }
        //完成流程
        completeTaskByBussId(reviewDto.getReviewNo(), variables, reviewDto.getComment(), reviewDto.getUserName());
    }


    /**
     * Author kyubi
     * description 创建任务
     * date 2019/11/10 0010 下午 9:17
     **/
    private void createFlow(Map<String, Object> variables) {

        ActReviewDto reviewDto = (ActReviewDto) variables.get("reviewDto");
        try {
            //启动流程定义，返回流程实例
            ProcessInstance pi = runtimeService.startProcessInstanceByKey(reviewDto.getActProcessInstanceEnums().getProcessKey()
                    , reviewDto.getReviewNo(), variables);
            log.info("流程创建成功，当前流程实例ID={}", pi.getId());
        } catch (Exception e) {
            log.error("genFlowError throws an exception:", e);
            throw new RuntimeException("初始化流程失败！请联系管理员");
        }
    }

    /**
     * Author kyubi
     * description 根据业务id获取任务
     * date 2019/11/10 0010 下午 9:18
     **/
    private Task getFlowTaskByBussId(String bussId) {
        return taskService.createTaskQuery().processInstanceBusinessKey(bussId).singleResult();
    }

    /**
     * Author kyubi
     * description 完成任务
     * date 2019/11/10 0010 下午 9:18
     **/
    private void completeTaskByBussId(String bussId, Map<String, Object> map, String comment, String userName) {

        Task task = taskService.createTaskQuery().processInstanceBusinessKey(bussId).singleResult();

        log.info("当前准备任务：{},map:{}", task.getName(), JSON.toJSONString(map));

        if (StringUtils.isNotBlank(comment)) {
            taskService.addComment(task.getId(), task.getProcessInstanceId(), comment);
        }

        taskService.setAssignee(task.getId(), userName);

        taskService.complete(task.getId(), map);

        log.info("完成任务：{},map:{}", task.getName(), JSON.toJSONString(map));
    }


    /**
     * description: 删除任务
     * date: 19/11/7 下午2:23
     */
    public void deleteProcessByBussId(String bussId) {
        Task task = getFlowTaskByBussId(bussId);
        if (task == null) {
            throw new RuntimeException("删除流程实例失败");
        }
        runtimeService.deleteProcessInstance(task.getProcessInstanceId(), "作废删除");
    }

    /**
     * description:
     * Author: kyubi
     * date: 2020/3/9
     * time: 下午8:24
     */
    public void deleteProcessInstance(String processInstanceId) {
        runtimeService.deleteProcessInstance(processInstanceId, "作废删除");
    }

}
