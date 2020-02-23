package com.ct.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type User.
 *
 * @author chen.cheng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * The Id.
     *
     * @author chen.cheng
     */
    String Id;
    /**
     * The Username.
     *
     * @author chen.cheng
     */
    String userName;
    /**
     * The Password.
     *
     * @author chen.cheng
     */
    String password;
}
