package com.zfw.gateway.fallback;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hero良
 * @className FallbackController
 * @description TODO
 * @date 2019/11/30
 */
@RestController
public class HystrixCommandController {

    /**
     * @description 降级方法
     * @author hero良
     * @param
     * @date 2019/12/1 21:47
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @exception
     * @version  1.0
     */
    @RequestMapping("/hystrixTimeout")
    public Map<String,String> hystrixTimeout(){
        Map<String,String> map = new HashMap<>();
        map.put("code","-1");
        map.put("message","服务异常");
        map.put("value","null");
        return map;
    }

}
