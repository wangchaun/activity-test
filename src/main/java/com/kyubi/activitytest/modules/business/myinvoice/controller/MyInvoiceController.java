package com.kyubi.activitytest.modules.business.myinvoice.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kyubi.activitytest.enums.ReviewStatusCommonEnum;
import com.kyubi.activitytest.modules.activiti.service.impl.InvoiceActService;
import com.kyubi.activitytest.modules.business.myinvoice.entity.MyInvoice;
import com.kyubi.activitytest.modules.business.myinvoice.service.IMyInvoiceService;
import com.kyubi.activitytest.modules.business.myinvoice.vo.ForwardInvoiceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author kyubi
 * @since 2020-10-16
 */
@RestController
@RequestMapping("/act")
@Slf4j
@Lazy
@Api(value = "我的发票", tags = {"发票流程"}, produces = MediaType.APPLICATION_JSON_VALUE)

public class MyInvoiceController {

    @Resource
    private InvoiceActService invoiceActService;

    @Resource
    private IMyInvoiceService myInvoiceService;


    @GetMapping("/initMyInvoice")
    @ApiOperation(httpMethod = "GET", value = "初始化一张发票")
    public String initMyInvoice() {
        MyInvoice myInvoice = myInvoiceService.initMyInvoice();
        return "生成发票成功, 发票号： "
                .concat(myInvoice.getInvoiceNo())
                .concat("\n\n发票具体信息：\n\n")
                .concat(JSON.toJSONString(myInvoice));
    }

    @GetMapping("/forWardInvoice")
    @ApiOperation(httpMethod = "GET", value = "推动发票流程进展")
    public String forWardInvoice(ForwardInvoiceVO forwardInvoiceVO) {
        try {
            //获取发票单
            MyInvoice myInvoice = myInvoiceService.getMyInvoiceByNo(forwardInvoiceVO.getMyInvoiceNo());

            //处理发票单流程
            invoiceActService.handleInvoiceFlow(myInvoice, ReviewStatusCommonEnum
                    .getReviewStatusReviewEnum(forwardInvoiceVO.getReviewStatus()), forwardInvoiceVO.getReviewComment(), forwardInvoiceVO.getUserName());

        } catch (Exception e) {
            log.error("forWardInvoice={}", e);
            return "推动发票流程进展失败: ".concat(e.getMessage());
        }

        return "推动发票流程进展成功: ".concat(JSON.toJSONString(forwardInvoiceVO));
    }

}

