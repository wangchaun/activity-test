package com.kyubi.activitytest.modules.kyc.model;


import com.kyubi.activitytest.modules.kyc.model.detail.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @description:KYC申请明细信息
 * @projectName:mysteel-aliyun-mbs-service_202012021341
 * @className:KycApplyInfo.java
 * @author: wangsiming
 * @createTime: 2020年12月10日 下午1:58:14
 */
@ApiModel(value="KycApplyDetailInfo对象",description="大表单明细VO")
public class KycApplyDetailInfo extends AbstractDTO {

	/**
	 * KYC申请id
	 */
	@ApiModelProperty(value="KYC申请id",name="kycApplyId")
	private Long kycApplyId;

	/**
	 * 模块一：首要信息
	 */
	@ApiModelProperty(value="模块一：首要信息",name="preliminary")
	private Preliminary preliminary;
	/**
	 * 模块二：基础信息
	 */
	@ApiModelProperty(value="模块二：基础信息",name="basicInformation")
	private BasicInformation basicInformation;
	/**
	 * 模块三：业务联系人信息
	 */
	@ApiModelProperty(value="模块三：业务联系人信息",name="businessContacts")
	private List<BusinessContacts> businessContacts;
	/**
	 * 模块四：特殊信息
	 */
	@ApiModelProperty(value="模块四：特殊信息",name="specificInformation")
	private SpecificInformation specificInformation;
	/**
	 * 模块五：问卷信息
	 */
	@ApiModelProperty(value="模块五：问卷信息",name="questionaire")
	private List<Questionaire> questionaire;
	/**
	 * 模块六：资质信息
	 */
	@ApiModelProperty(value="模块六：资质信息",name="agreements")
	private List<Agreements> agreements;

	public Long getKycApplyId() {
		return kycApplyId;
	}

	public void setKycApplyId(Long kycApplyId) {
		this.kycApplyId = kycApplyId;
	}

	public Preliminary getPreliminary() {
		return preliminary;
	}

	public void setPreliminary(Preliminary preliminary) {
		this.preliminary = preliminary;
	}

	public BasicInformation getBasicInformation() {
		return basicInformation;
	}

	public void setBasicInformation(BasicInformation basicInformation) {
		this.basicInformation = basicInformation;
	}

	public List<BusinessContacts> getBusinessContacts() {
		return businessContacts;
	}

	public void setBusinessContacts(List<BusinessContacts> businessContacts) {
		this.businessContacts = businessContacts;
	}

	public SpecificInformation getSpecificInformation() {
		return specificInformation;
	}

	public void setSpecificInformation(SpecificInformation specificInformation) {
		this.specificInformation = specificInformation;
	}

	public List<Questionaire> getQuestionaire() {
		return questionaire;
	}

	public void setQuestionaire(List<Questionaire> questionaire) {
		this.questionaire = questionaire;
	}

	public List<Agreements> getAgreements() {
		return agreements;
	}

	public void setAgreements(List<Agreements> agreements) {
		this.agreements = agreements;
	}
}