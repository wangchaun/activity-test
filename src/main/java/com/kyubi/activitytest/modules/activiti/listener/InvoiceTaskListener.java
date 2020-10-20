package com.kyubi.activitytest.modules.activiti.listener;

import com.kyubi.activitytest.modules.enums.ReviewStatusCommonEnum;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Kyubi
 * @date 2020-10-19 19:04
 */
@Component
@Slf4j
@Lazy
public class InvoiceTaskListener implements TaskListener {
    @Resource
    private TaskService taskService;
    @Override
    public void notify(DelegateTask delegateTask) {
        if (delegateTask.getName().equals(ReviewStatusCommonEnum.PURCHASE_REVIEWING.getActName())) {

            log.info("设置该步审核人-wangsm");
            taskService.setAssignee(delegateTask.getId(), "wangsm");
        }
    }
}
