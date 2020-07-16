package com.ct.biz.controller;

import com.ct.biz.bean.VipVO;
import com.ct.biz.dao.impala.ITfcImpalaScheduleDAO;
import com.ct.biz.dao.mysql.TfcEpdDao;
import com.ct.biz.service.LotteryServiceImpl;
import com.ct.biz.strategy.IVipStrategy;
import com.ct.biz.strategy.VipStrategyResolver;
import com.ct.common.annotation.Log;
import com.ct.common.annotation.PassToken;
import com.ct.common.bean.Result;
import com.ct.common.thread.AsyncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * The type Sample controller.
 *
 * @author chen.cheng
 */
@RestController
@CrossOrigin
@RequestMapping("/api/ct")
@Api(value = "测试控制器")
public class SampleController {
    /**
     * The Logger.
     *
     * @author chen.cheng
     */
    private final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @Autowired
    private VipStrategyResolver vipStrategyResolver;

    @Autowired
    private ITfcImpalaScheduleDAO iTfcImpalaScheduleDAO;

    @Autowired
    private LotteryServiceImpl lotteryService;

    @Autowired
    private TfcEpdDao tfcEpdDao;
    /**
     * The Async service.
     *
     * @author chen.cheng
     */
    @Autowired
    private AsyncService asyncService;

    /**
     * Submit string.
     *
     * @return the string
     * @author chen.cheng
     */
    @ApiOperation(value = "get", notes = "get")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @PassToken
    @Log(title = "SampleController", action = "test")
    public Result submit() throws Exception {
        logger.info("start submit");
        //调用service层的任务
        asyncService.executeAsync();
        logger.info("end submit");
        return Result.success("");
    }

    @ApiOperation(value = "get", notes = "测试策略模式，结合spring 容器特性")
    @RequestMapping(value = "/strategy", method = RequestMethod.GET)
    @PassToken
    @Log(title = "SampleController", action = "test")
    public Result strategy(@Valid VipVO vipCode) throws Exception {
        IVipStrategy iVipStrategy = vipStrategyResolver.getHandler(vipCode.getVipCode());
        iVipStrategy.compute(1000L);
        return Result.success("");
    }

    /**
     * Submit string.
     *
     * @return the string
     * @author chen.cheng
     */
    @ApiOperation(value = "map", notes = "map")
    @RequestMapping(value = "/map", method = RequestMethod.GET)
    @PassToken
    @Log(title = "SampleController", action = "map")
    public Result map() throws Exception {
        return Result.success(tfcEpdDao.queryMaxTimeStampOfSatur());
    }
    /**
     * Submit string.
     *
     * @return the string
     * @author chen.cheng
     */
    @ApiOperation(value = "observer pattern", notes = "observer pattern")
    @RequestMapping(value = "/observer", method = RequestMethod.GET)
    @PassToken
    @Log(title = "SampleController", action = "observer")
    public Result observer() throws Exception {
        return Result.success(lotteryService.draw("4567"));
    }

}
