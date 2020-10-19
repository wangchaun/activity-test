package com.kyubi.activitytest.modules.business.myinvoice.service;

import com.kyubi.activitytest.enums.ReviewStatusCommonEnum;
import com.kyubi.activitytest.modules.business.myinvoice.entity.MyInvoice;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kyubi
 * @since 2020-10-15
 */
public interface IMyInvoiceService extends IService<MyInvoice> {


    /**
     * 初始化一张发票
     * @return 发票
     */
    MyInvoice initMyInvoice();

    /**
     *  获取发票
     * @param invoiceNo 发票号
     * @return
     */
    MyInvoice getMyInvoiceByNo(String invoiceNo);

    /**
     *  监听状态变化
     * @param invoiceNo  发票号
     * @param reviewStatus 监听更新状态
     */
    void listenerByFlow(String invoiceNo, ReviewStatusCommonEnum reviewStatus);

}
