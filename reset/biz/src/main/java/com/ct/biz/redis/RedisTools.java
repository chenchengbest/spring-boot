package com.ct.biz.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

@Service
public class RedisTools {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @param key 键
     * @param value 值
     * @return true成功 false失败 普通缓存放入
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败 HashSet
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Map<String, Object> clear() {
        Map<String, Object> map = new HashMap<>();
        try {
            // 获取所有key
            Set<String> keys = redisTemplate.keys("*");
            assert keys != null;
            // 迭代
            Iterator<String> it1 = keys.iterator();
            while (it1.hasNext()) {
                // 循环删除
                redisTemplate.delete(it1.next());
            }
            map.put("code", 1);
            map.put("msg", "清理全局缓存成功");
            return map;
        }
        catch (Exception e) {
            map.put("code", -1);
            map.put("msg", "清理全局缓存失败");
            return map;
        }
    }

    public Object luaScript(String script, List<String> keys, List<String> args) {
        return redisTemplate.execute((RedisCallback<Object>) connection -> {
            Object nativeConnection = connection.getNativeConnection();
            // 集群模式
            if (nativeConnection instanceof JedisCluster) {
                return ((JedisCluster) nativeConnection).eval(script, keys, args);
            }
            // 单机模式
            else if (nativeConnection instanceof Jedis) {
                return ((Jedis) nativeConnection).eval(script, keys, args);
            }
            return false;
        });
    }
}
