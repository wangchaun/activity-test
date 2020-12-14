package com.kyubi.activitytest.modules.kyc.web;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.kyubi.activitytest.modules.kyc.KycDetailInfo;
import com.kyubi.activitytest.modules.kyc.model.AuditNodeEnum;
import com.kyubi.activitytest.modules.kyc.model.KycApplyDetailInfo;
import com.kyubi.activitytest.modules.kyc.model.WorkFlowShowVo;
import com.kyubi.activitytest.modules.kyc.model.WorkLog;
import com.kyubi.activitytest.modules.kyc.model.detail.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @description: WelcomeController 类
 * @projectName: activity-test
 * @className: WelcomeController
 * @author: wangsiming
 * @createTime: 2020/11/23 11:32
 */
@RestController
@RequestMapping("/mbs/kyc")
@Slf4j
@Lazy
@Api(value = "kyc流程", tags = {"钢联KYC大表单"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class KYCController {

    @GetMapping("/apply_detail_info")
    @ApiOperation(httpMethod = "GET", value = "KYC申请单明细获取")
    public KycDetailInfo genFlow(Long kycApplyId) {
        KycApplyDetailInfo kycApplyDetailInfo = new KycApplyDetailInfo();
        kycApplyDetailInfo.setAgreements(Lists.newArrayList(new Agreements()));
        kycApplyDetailInfo.setPreliminary(new Preliminary());
        kycApplyDetailInfo.setBasicInformation(new BasicInformation());
        kycApplyDetailInfo.setBusinessContacts(Lists.newArrayList(new BusinessContacts()));
        kycApplyDetailInfo.setQuestionaire(Lists.newArrayList(new Questionaire()));
        kycApplyDetailInfo.setSpecificInformation(new SpecificInformation());

        KycDetailInfo kycDetailInfo = new KycDetailInfo();
        kycDetailInfo.setKycDetailInfo(kycApplyDetailInfo);

        return kycDetailInfo;
    }

    @GetMapping("/work_log")
    @ApiOperation(httpMethod = "GET", value = "KYC申请单日志获取")
    public List<WorkLog> genLog(Long kycApplyId) {
        WorkLog workLog = new WorkLog();
        workLog.setId(1L);
        workLog.setKycApplyId(kycApplyId);
        List<WorkLog> workLogs = new ArrayList<>();
        workLogs.add(workLog);
        workLog.setId(2L);
        workLogs.add(workLog);

        return workLogs;
    }
    @PostMapping("/apply_detail_info")
    @ApiOperation(httpMethod = "POST", value = "KYC申请单明细SUBMIT")
    public String postFlow(@RequestBody KycApplyDetailInfo kycDetailInfo) {
        System.out.println(JSON.toJSONString(kycDetailInfo));
        if (kycDetailInfo == null) {
            return "失败";
        }
        return "成功";
    }

    @GetMapping("/get_work_show")
    @ApiOperation(httpMethod = "GET", value = "KYC审核流程图")
    public List<WorkFlowShowVo> genshow(Long kycApplyId) {
        List<WorkFlowShowVo> workFlowShowVos = new ArrayList<>();
        for (AuditNodeEnum auditNodeEnum : AuditNodeEnum.values()) {
            WorkFlowShowVo workFlowShowVo = new WorkFlowShowVo();
            workFlowShowVo.setCreateName("wang qiang");
            workFlowShowVo.setCreateTime(System.currentTimeMillis());
            workFlowShowVo.setComment("材料齐全，通过！");

            if (kycApplyId == 1L) {
                workFlowShowVo.setAuditNode(auditNodeEnum.getDesc());
                workFlowShowVo.setColor(WorkFlowShowVo.GREEN);
                workFlowShowVos.add(workFlowShowVo);
            }else {
                workFlowShowVo.setAuditNode(auditNodeEnum.getDesc());
                workFlowShowVo.setColor(WorkFlowShowVo.RED);
                workFlowShowVo.setComment("材料没有齐全，驳回！");
                if (auditNodeEnum.equals(AuditNodeEnum.BROKER) ||auditNodeEnum.equals(AuditNodeEnum.APPROVER)) {
                    workFlowShowVo.setColor(WorkFlowShowVo.GREY);
                    workFlowShowVo.setComment("");
                }
                workFlowShowVos.add(workFlowShowVo);
            }


        }
        return workFlowShowVos;
    }

}
