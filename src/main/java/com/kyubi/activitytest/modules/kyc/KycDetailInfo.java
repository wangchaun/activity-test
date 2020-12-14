package com.kyubi.activitytest.modules.kyc;

import com.kyubi.activitytest.modules.kyc.model.AbstractDTO;
import com.kyubi.activitytest.modules.kyc.model.KycApplyDetailInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: KycDetailInfo 类
 * @projectName: activity-test
 * @className: KycDetailInfo
 * @author: wangsiming
 * @createTime: 2020/12/10 10:44
 */
@ApiModel(value="KycDetailInfo对象",description="大表单明细返回VO")
public class KycDetailInfo  extends AbstractDTO {
    @ApiModelProperty(value="模块信息入口",name="入口")
    private KycApplyDetailInfo kycDetailInfo;

    public KycApplyDetailInfo getKycDetailInfo() {
        return kycDetailInfo;
    }

    public void setKycDetailInfo(KycApplyDetailInfo kycDetailInfo) {
        this.kycDetailInfo = kycDetailInfo;
    }
}
