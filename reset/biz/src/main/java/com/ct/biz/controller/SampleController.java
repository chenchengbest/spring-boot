package com.ct.biz.controller;

import com.ct.common.annotation.Log;
import com.ct.common.thread.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Sample controller.
 *
 * @author chen.cheng
 */
@RestController
@CrossOrigin
@RequestMapping("/api/ct")
public class SampleController {
    /**
     * The Logger.
     *
     * @author chen.cheng
     */
    private final Logger logger = LoggerFactory.getLogger(SampleController.class);
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
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @Log(title = "SampleController",action = "test")
    public String submit() throws Exception {
        logger.info("start submit");
        //调用service层的任务
        asyncService.executeAsync();
        logger.info("end submit");
        return "success";
    }
}
