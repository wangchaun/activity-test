package com.kyubi.activitytest.modules.business.myinvoice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Kyubi
 * @date 2020-10-16 15:33
 */
@Data
@ApiModel(value="ForwardInvoiceVO对象",description="发票流程VO")
public class ForwardInvoiceVO {
    /**
     * 发票号
     */
    @ApiModelProperty(value="发票号",name="myInvoiceNo",example="IN-876612")
    private String myInvoiceNo;

    /**
     * 状态码
     */
    @ApiModelProperty(value="审核状态   (\"800\", \"审核通过\")" +
            "    (\"900\", \"审核驳回\")",name="reviewStatus",example= "800"
            )
    private String reviewStatus;


    /**
     * 备注
     */
    @ApiModelProperty(value="备注",name="reviewComment",example="单据完整，予以通过！")
    private String reviewComment;

    /**
     * 流程操作人
     */
    @ApiModelProperty(value="流程操作人",name="userName",example="kyubi")
    private String userName;



}
