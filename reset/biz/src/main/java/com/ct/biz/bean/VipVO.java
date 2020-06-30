package com.ct.biz.bean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
    private String vipCode;

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
}
