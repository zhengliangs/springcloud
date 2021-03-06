package com.zfw.ribbon.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author hero良
 * @className RibbonController
 * @description
 * @date 2019/11/15 15:52
 */
@Slf4j
@DefaultProperties
@RestController
public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getProductMsg")
    public String getProductMsg(){
        return restTemplate.getForObject("http://provider-service/appController/getProductMsg", String.class);
    }

    /**
     * 负载均衡
     * @return
     */
    @GetMapping("/getRibbonBalancer")
    public String getRibbonBalancer(String name){
        return restTemplate.getForObject("http://provider-service/appController/getRibbonBalancer?name="+name, String.class);
    }

    /**
     * @description ribbon的超时与重试
     * 超时需要在创建RestTemplate的时候指定时间，配置在配置文件中不生效，重试需要引入Spring-retry依赖
     * @author hero良
     * @return java.lang.String
     * @exception
     * @version  1.0
     */
    @GetMapping("/getRibbonTimeout")
    public String getRibbonTimeout(){
        log.info("      ribbon   接收到请求      ");
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return restTemplate.getForObject("http://provider-service/appController/getRibbonTimeout", String.class);
    }

    /**
     * @description 降级
     * @author hero良
     * @return
     */
    @GetMapping("/getRibbonHystrix")
    @HystrixCommand(defaultFallback = "getHystrixFall")
    public String getRibbonHystrix(){
        return restTemplate.getForObject("http://provider-service/appController/getRibbonHystrix", String.class);
    }

    /**
     * 回退方法需要和执行它的方法在一个类中
     * @description 降级回退
     * @author hero良
     * @param
     * @return
     */
    String getHystrixFall() {
        return "我是回退方法 getRibbonHystrix";
    }

    @GetMapping("/testDocker")
    public String testDocker(String name){
        return name;
    }
}
