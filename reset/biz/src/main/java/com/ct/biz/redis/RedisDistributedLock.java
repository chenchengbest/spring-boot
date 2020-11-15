package com.ct.biz.redis;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Redis distributed lock.
 *
 * @author chen.cheng
 */
public class RedisDistributedLock {
    /**
     * The Log.
     *
     * @author chen.cheng
     */
    Logger log = LoggerFactory.getLogger(RedisDistributedLock.class);

    /**
     * The Tools.
     *
     * @author chen.cheng
     */
    @Resource
    private RedisTools tools;

    /**
     * The constant TRY_LOCK.
     * if redis.call("EXISTS",KEYS[1]) > 0 then
     *  local kus = redis.call("HKEYS",KEYS[1]);
     *  for i = 1, #kus do
     *    if redis.call('setnx',kus[i],ARGV[1]) > 0 then
     *      redis.call('expire', kus[i], ARGV[2])
     *      return kus[i];
     *    end
     *  end
     * else
     *  return nil;
     * end
     * @author chen.cheng
     */
    private final String TRY_LOCK = "if redis.call(\"EXISTS\",KEYS[1]) > 0 then "
        + " local kus = redis.call(\"HKEYS\",KEYS[1]);   for i = 1, #kus do "
        + " if redis.call('setnx',kus[i],ARGV[1]) > 0 then  redis.call('expire', kus[i], ARGV[2]) "
        + " return kus[i];   end   end  else   return nil;  end ";

    /**
     * The constant DECR.
     * local kusName = KEYS[1];
     * local key = KEYS[2];
     * local uid = redis.call('get',key);
     * local kuName = redis.call('HGET',kusName,key);
     * local kuNum = redis.call('get',kuName);
     * if redis.call("EXISTS",kusName) > 0 then
     *  if KEYS[3] == uid then
     *      if kuNum and tonumber(kuNum) >= 1 then
     *        return redis.call('DECR',kuName);
     *      else
     *        return nil; // 返回null会脚本错误
     *      end
     *  end
     * else
     *  return -1;
     * end
     *
     * @author chen.cheng
     */
    private final String DECR = "local kusName = KEYS[1]; local key = KEYS[2]; "
        + "local uid = redis.call('get',key); " + "local kuName = redis.call('HGET',kusName,key); "
        + "local kuNum = redis.call('get',kuName);" + "if redis.call(\"EXISTS\",kusName) > 0 then "
        + " if KEYS[3] == uid then " + " if kuNum and tonumber(kuNum) >= 1 then "
        + " return redis.call('DECR',kuName); " + " else " + " return nil; " + " end " + " end " + "else "
        + " return -1; " + "end";

    /**
     * The constant UN_LOCK.
     *local kusName = KEYS[1];
     * local key = KEYS[2];
     * local uid = redis.call('get',key);
     * local kuName = redis.call('HGET',kusName,key);
     * local kuNum = redis.call('get',kuName);
     * if uid == KEYS[3] then
     *  if kuNum and tonumber(kuNum) <= 0 then
     *  redis.call('hdel',kusName,key);
     *  redis.call('del',kuName);
     *  end
     *  return redis.call('del',key);
     * end
     * return false;
     * @author chen.cheng
     */
    private final String UN_LOCK = "local kusName = KEYS[1]; " + "local key = KEYS[2]; "
        + "local uid = redis.call('get',key); " + "local kuName = redis.call('HGET',kusName,key); "
        + "local kuNum = redis.call('get',kuName); " + "if uid == KEYS[3] then "
        + " if kuNum and tonumber(kuNum) <= 0 then " + " redis.call('hdel',kusName,key); "
        + " redis.call('del',kuName); " + " end " + " return redis.call('del',key); " + "end " + "return false;";

    /**
     * The Kus name.
     *
     * @author chen.cheng
     */
    private String KUS_NAME = null;

    /**
     * The Unique id.
     *
     * @author chen.cheng
     */
    private String UNIQUE_ID = null;

    /**
     * The Expire.
     *
     * @author chen.cheng
     */
    private String EXPIRE = "60";

    /**
     * The Key id.
     *
     * @author chen.cheng
     */
    private String KEY_ID = null;

    /**
     * @param KUS_NAME 分段库名称
     * @param UNIQUE_ID 唯一标识
     * @param EXPIRE 过期时间 以秒为单位
     */
    public RedisDistributedLock(String KUS_NAME, String UNIQUE_ID, String EXPIRE) {
        this.KUS_NAME = KUS_NAME;
        this.UNIQUE_ID = UNIQUE_ID;
        this.EXPIRE = EXPIRE;
        log.info("参数初始化:===>总仓库名称: {}, 唯一标识: {}, 过期时间: {}", KUS_NAME, UNIQUE_ID, EXPIRE);
    }

    /**
     * 初始化工具类
     *
     * @param tools
     */
    public void setTools(RedisTools tools) {
        this.tools = tools;
    }

    /**
     * 获取锁 前面设置的时间就是锁得过期时间
     *
     * @return
     */
    public boolean tryLock() {
        List<String> keys = Arrays.asList(KUS_NAME);
        List<String> args = Arrays.asList(UNIQUE_ID, EXPIRE);
        Object result = tools.luaScript(TRY_LOCK, keys, args);
        if (result != null && (result.toString().length() > 3)) {
            this.KEY_ID = result.toString();
            // log.info("仓库ID: {} 返回结果: {} 获取锁成功",KEY_ID,result);
            return true;
        }
        else {
            // log.info("仓库ID: {} 返回结果: {} 获取锁失败",KEY_ID,result);
            return false;
        }
    }

    /**
     * 原子性扣减
     *
     * @return
     */
    public Integer decr() {
        Object result = null;
        if (KEY_ID != null) {
            List<String> keys = Arrays.asList(KUS_NAME, KEY_ID, UNIQUE_ID);
            List<String> args = Arrays.asList();
            result = tools.luaScript(DECR, keys, args);
            if (result != null && !result.toString().isEmpty()) {
                log.info("消费成功 返回结果: {}", result);
                return 1;
            }
            else {
                // log.info("消费失败 返回结果: {}",result);
                return 0;
            }
        }
        log.info("已售空...{}" + result);
        return -1;
    }

    /**
     * 释放锁
     */
    public void unLock() {
        if (KEY_ID != null) {
            List<String> keys = Arrays.asList(KUS_NAME, KEY_ID, UNIQUE_ID);
            List<String> args = Arrays.asList();
            Object result = tools.luaScript(UN_LOCK, keys, args);
            // log.info("释放锁 返回结果: {}",result);
        }
    }
}
