package com.ct.biz.controller;

import java.util.concurrent.ConcurrentHashMap;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ct.biz.bean.VipVO;
import com.ct.biz.dao.impala.ITfcImpalaScheduleDAO;
import com.ct.biz.dao.mysql.TfcEpdDao;
import com.ct.biz.redis.RedisDistributedLock;
import com.ct.biz.redis.RedisTools;
import com.ct.biz.service.LotteryServiceImpl;
import com.ct.biz.strategy.IVipStrategy;
import com.ct.biz.strategy.VipStrategyResolver;
import com.ct.common.annotation.Log;
import com.ct.common.annotation.PassToken;
import com.ct.common.bean.Result;
import com.ct.common.enums.HttpCodeMessage;
import com.ct.common.thread.AsyncService;
import com.ct.common.utils.IdGenerate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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

    @Autowired
    @Qualifier("idGenerate")
    private IdGenerate idGenerate;

    @Autowired
    private RedisTools redisTools;

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
        // 调用service层的任务
        asyncService.executeAsync();
        logger.info("end submit");
        return Result.success("");
    }

    /**
     * Strategy result.
     *
     * @param vipCode the vip code
     * @return the result
     * @throws Exception the exception
     * @author chen.cheng
     */
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

    /**
     * Init redis sku result.
     *
     * @param kuname the kuname
     * @param allnum the allnum
     * @param subnum the subnum
     * @return the result
     * @throws Exception the exception
     * @author chen.cheng
     */
    @ApiOperation(value = "redis 分段锁", notes = "初始化锁库存")
    @RequestMapping(value = "/initRedisSKU", method = RequestMethod.GET)
    @PassToken
    @Log(title = "SampleController", action = "initRedisSKU")
    public Result initRedisSKU(@RequestParam("kuname") String kuname, @RequestParam("allnum") Integer allnum,
        @RequestParam("subnum") Integer subnum) throws Exception {
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap();
        String skuKey;
        for (int i = 0, count = (allnum / subnum); i < count; i++) {
            skuKey = idGenerate.nextCode("sku-key");
            map.put(idGenerate.nextCode("dis-lock-key"), skuKey);
            redisTools.set(skuKey, subnum);
        }
        redisTools.hmset(kuname, map);
        return Result.success();
    }

    @ApiOperation(value = "redis 分段锁", notes = "锁库存")
    @RequestMapping(value = "/lockSku", method = RequestMethod.GET)
    @PassToken
    @Log(title = "SampleController", action = "lockSku")
    public Result lockSku(@RequestParam("kuname") String kuname,
        @RequestParam(value = "timeout", defaultValue = "5") String timeout) {
        // 初始化锁
        RedisDistributedLock lock = new RedisDistributedLock(kuname, idGenerate.nextCode("sku-uuid"), timeout);
        lock.setTools(redisTools);
        Result result;
        try {
            // 获取锁
            if (lock.tryLock()) {
                result = Result.success("恭喜您抢到了！！！:" + lock.decr());
            }
            else {
                result = Result.failure(HttpCodeMessage.PARAM_IS_INVALID, "很遗憾您没有抢到 ╥﹏╥...");
            }
        }
        catch (Exception e) {
            result = Result.failure(HttpCodeMessage.PARAM_IS_INVALID, "很遗憾您没有抢到 ╥﹏╥...");
            logger.info("{}", e);
        }
        finally {
            // 释放锁
            lock.unLock();
        }
        return result;
    }
}
