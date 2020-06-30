package com.ct.biz.bean;

import com.ct.common.validate.IsMobile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The type Vip vo.
 *
 * @author chen.cheng
 */
public class VipVO {
    /**
     * The Vip code.
     *
     * @author chen.cheng
     */
    @NotNull(message = "vipCode 不得为空")
    @Size(min = 1,message = "vipCode 不得为空")
    private String vipCode;

    /**
     * The Tel no.
     *
     * @author chen.cheng
     */
    @IsMobile
    private String telNo;

    /**
     * Gets vip code.
     *
     * @return the vip code
     * @author chen.cheng
     */
    public String getVipCode() {
        return vipCode;
    }

    /**
     * Sets vip code.
     *
     * @param vipCode the vip code
     * @author chen.cheng
     */
    public void setVipCode(String vipCode) {
        this.vipCode = vipCode;
    }

    /**
     * Gets tel no.
     *
     * @return the tel no
     * @author chen.cheng
     */
    public String getTelNo() {
        return telNo;
    }

    /**
     * Sets tel no.
     *
     * @param telNo the tel no
     * @author chen.cheng
     */
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }
}
