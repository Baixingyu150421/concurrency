package com.java.concurrency.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/redis")
@Slf4j
public class RedisController {
    @Autowired
    RedisClient redisClient;

    @RequestMapping("/get")
    @ResponseBody
    public String get(@RequestParam("k") String  key) throws Exception{
        return redisClient.get(key);
    }
    @RequestMapping("/set")
    @ResponseBody
    public String set(@RequestParam("k") String key , @RequestParam("v") String value){
        try {
            redisClient.set(key,value);
        }catch (Exception e){
            log.warn("Exception",e);
        }
        return "success";
    }
}
