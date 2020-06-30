package com.ct.biz.strategy;

import com.ct.common.annotation.VipStrategy;
import org.springframework.stereotype.Service;

/**
 * The type Silver strategy.
 *
 * @author chen.cheng
 */
@VipStrategy(name = "silver")
@Service
public class SilverStrategy implements IVipStrategy {
    @Override
    public double compute(long money) {
        System.out.println("白银会员 优惠50元");
        return money - 50;
    }
}
