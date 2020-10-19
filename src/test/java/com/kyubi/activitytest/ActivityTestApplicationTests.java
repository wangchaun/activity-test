package com.kyubi.activitytest;

import com.kyubi.activitytest.modules.business.myinvoice.entity.MyInvoice;
import com.kyubi.activitytest.modules.business.myinvoice.service.IMyInvoiceService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ActivityTestApplicationTests {

    @Resource
    IMyInvoiceService myInvoiceService;

    @Test
    void initMyInvoice() {
        myInvoiceService.initMyInvoice();
    }

    @Test
    void updateInvoice() {
        MyInvoice myInvoice = myInvoiceService.getById(1L);
        myInvoice.setInvoiceStatus("222");
        myInvoice.setVersion(2L);
        myInvoiceService.updateById(myInvoice);

    }

}
