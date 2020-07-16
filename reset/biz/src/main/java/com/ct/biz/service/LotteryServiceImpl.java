package com.ct.biz.service;

import org.springframework.stereotype.Service;

@Service
public class LotteryServiceImpl extends LotteryService {
    @Override
    protected Object doDraw(String uId) {
        return "主业务处理完消息"+uId;
    }
}
