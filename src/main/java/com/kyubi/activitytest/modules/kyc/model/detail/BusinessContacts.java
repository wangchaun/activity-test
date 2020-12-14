package com.kyubi.activitytest.modules.kyc.model.detail;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @description: Preliminary 类
 * @projectName: mysteel-mbs-service
 * @className: Preliminary
 * @author: wangsiming
 * @createTime: 2020/12/10 9:36
 */
public class BusinessContacts implements Serializable {


    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;


    /**
     * 姓名
     */
    @ApiModelProperty(value="姓名",name="contactsName",example="")
    private String contactsName;


    /**
     * 头衔
     */
    @ApiModelProperty(value="头衔",name="contactsTitle",example="")
    private String contactsTitle;


    /**
     * 邮箱
     */
    @ApiModelProperty(value="邮箱",name="email",example="")
    private String email;


    /**
     * 电话/联系方式
     */
    @ApiModelProperty(value="电话",name="tel",example="")
    private String tel;

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getContactsTitle() {
        return contactsTitle;
    }

    public void setContactsTitle(String contactsTitle) {
        this.contactsTitle = contactsTitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
