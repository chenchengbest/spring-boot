package com.ct.biz.strategy;

import com.ct.common.annotation.VipStrategy;
import org.springframework.stereotype.Service;

/**
 * The type Ordinary strategy.
 *
 * @author chen.cheng
 */
@VipStrategy(name = "ordinary")
@Service
public class OrdinaryStrategy implements IVipStrategy {
    @Override
    public double compute(long money) {
        System.out.println("普通会员 不打折");
        return money;
    }
}
