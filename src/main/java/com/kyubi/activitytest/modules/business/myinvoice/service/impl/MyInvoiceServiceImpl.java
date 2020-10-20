package com.kyubi.activitytest.modules.business.myinvoice.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kyubi.activitytest.modules.enums.ReviewStatusCommonEnum;
import com.kyubi.activitytest.modules.activiti.service.impl.InvoiceActService;
import com.kyubi.activitytest.modules.business.myinvoice.entity.MyInvoice;
import com.kyubi.activitytest.modules.business.myinvoice.mapper.MyInvoiceMapper;
import com.kyubi.activitytest.modules.business.myinvoice.service.IMyInvoiceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

import static com.kyubi.activitytest.modules.enums.ReviewStatusCommonEnum.INIT;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kyubi
 * @since 2020-10-15
 */
@Service
@Lazy
@Slf4j
//我使用的是开启新事务
@Transactional(value = "businessTransactionManager", readOnly = false, rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
public class MyInvoiceServiceImpl extends ServiceImpl<MyInvoiceMapper, MyInvoice> implements IMyInvoiceService {

    @Resource
    private InvoiceActService invoiceActService;

    @Override
    public MyInvoice initMyInvoice() {
        MyInvoice myInvoice = new MyInvoice();
        myInvoice.setInvoiceNo("IN-" + DateUtil.format(new Date(), DatePattern.PURE_TIME_FORMAT) + RandomUtil.randomStringUpper(2));
        myInvoice.setInvoiceInfo(String.format("发票总金额 : %s 元", RandomUtil.randomNumbers(5)));
        myInvoice.setInvoiceStatus(INIT.getCode());
        this.save(myInvoice);

        //调用流处理，推动工作流的进程
        invoiceActService.handleInvoiceFlow(myInvoice, INIT, "初始化发票：".concat(myInvoice.getInvoiceNo()), "system-init");
        //查询这个发票并返回
        return getMyInvoiceByNo(myInvoice.getInvoiceNo());

    }

    @Override
    public MyInvoice getMyInvoiceByNo(String invoiceNo) {
        return getOne(Wrappers.<MyInvoice>lambdaQuery()
                .eq(MyInvoice::getInvoiceNo, invoiceNo), false);
    }

    @Override
    public void listenerByFlow(String invoiceNo, ReviewStatusCommonEnum reviewStatus) {

        MyInvoice myInvoice = this.getMyInvoiceByNo(invoiceNo);

        if (myInvoice != null) {
            log.info("流程监听器更新发票状态: {} -> {}",
                    ReviewStatusCommonEnum.getReviewStatusReviewEnum(myInvoice.getInvoiceStatus()).getActName(),
                    reviewStatus.getActName());
            myInvoice.setInvoiceStatus(reviewStatus.getCode());
            this.updateById(myInvoice);
        }


    }
}
