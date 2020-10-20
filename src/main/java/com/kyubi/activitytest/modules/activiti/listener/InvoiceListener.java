package com.kyubi.activitytest.modules.activiti.listener;

import com.kyubi.activitytest.modules.enums.ReviewStatusCommonEnum;
import com.kyubi.activitytest.modules.business.myinvoice.service.IMyInvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.Expression;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;


/**
 * @program: cosmos-poseidon
 * @description:
 * @author: siming.wang
 * @create: 2019-06-24 10:51
 **/
@Component
@Slf4j
@Lazy
public class InvoiceListener implements Serializable, ExecutionListener {

    @Resource
    IMyInvoiceService myInvoiceService;

    private static final long serialVersionUID = 8513750196548027535L;
    private Expression message;

    public Expression getMessage() {
        return message;
    }

    public void setMessage(Expression message) {
        this.message = message;
    }

    @Override
    public void notify(DelegateExecution execution) {

        String msg = (String) message.getValue(execution);

        log.info("流程监听器" + msg);
        log.info("监听发票({})流程", execution.getProcessInstanceBusinessKey());

        ReviewStatusCommonEnum reviewStatus = ReviewStatusCommonEnum.getReviewStatusReviewEnumByDesc(msg);

        //更新状态
        myInvoiceService.listenerByFlow(execution.getProcessInstanceBusinessKey(), reviewStatus);
    }


}
