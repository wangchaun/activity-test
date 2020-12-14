package com.kyubi.activitytest.modules.activiti.listener;

import com.kyubi.activitytest.modules.enums.ReviewStatusCommonEnum;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


/**
 * @author Kyubi
 * @date 2020-10-19 19:04
 */
@Component
@Slf4j
@Lazy
public class TestListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        if (delegateTask.getName().equals(ReviewStatusCommonEnum.PURCHASE_REVIEWING.getActName())) {
            log.info("设置该步审核人3333-wangsm");
            log.info("设置该步审核人3333-wangsm");
            log.info("设置该步审核人3333-wangsm");
            log.info("设置该步审核人3333-wangsm");
            log.info("设置该步审核人3333-wangsm");
            log.info("设置该步审核人3333-wangsm");

        }
    }
}
