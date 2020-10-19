package com.kyubi.activitytest.modules.activiti.service.impl;

import com.google.common.collect.Maps;
import com.kyubi.activitytest.enums.ActivityProcessInstanceEnums;
import com.kyubi.activitytest.enums.ReviewStatusCommonEnum;
import com.kyubi.activitytest.modules.activiti.model.ActReviewDto;
import com.kyubi.activitytest.modules.activiti.service.BaseWorkflowService;
import com.kyubi.activitytest.modules.business.myinvoice.entity.MyInvoice;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Kyubi
 * @date 2020-10-16 13:55
 */
@Service
@Lazy
@Transactional(value = "activitiTransactionManager", readOnly = false, rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
public class InvoiceActService extends BaseWorkflowService {

    /**
     *  操作发票流程
     * @param myInvoice 发票
     * @param reviewStatusCommonEnum 审核状态
     * @param comment 备注
     */
    public void handleInvoiceFlow(MyInvoice myInvoice, ReviewStatusCommonEnum reviewStatusCommonEnum, String comment, String userName) {


        ActReviewDto reviewDto = ActReviewDto.builder()
                .actProcessInstanceEnums(ActivityProcessInstanceEnums.MY_INVOICE)
                .reviewStatus(myInvoice.getInvoiceStatus())
                .sysNo(myInvoice.getId())
                .reviewNo(myInvoice.getInvoiceNo())
                .userName(userName)
                .comment(comment)
                .reviewStatusCommonEnum(reviewStatusCommonEnum)
                .build();
        // 流程参数
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("reviewDto", reviewDto);
        this.completeFlow(variables);
    }

}
