package com.java.concurrency.cache;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

//标注这个类被spring管理  redis客户端
@Component
public class RedisClient {

    @Resource(name = "redisPool")
    private JedisPool jedisPool;
    //对string类型的get/set
    public void set(String key , String value)throws Exception {
        Jedis jedis = null;
        try{
            Jedis resource = jedisPool.getResource();
            resource.set(key,value);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    public String get(String key) throws Exception{
        Jedis jedis = null;
        String s;
        try{
            Jedis resource = jedisPool.getResource();
             s = resource.get(key);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return s;
    }
}
