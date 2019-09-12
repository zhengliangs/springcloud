package com.zfw.provider.conteoller;

import com.zfw.provider.entity.JacksonEntity;
import com.zfw.provider.service.JacksonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hero良
 * @className HelloController
 * @description TODO
 * @date 2019/9/10
 */
@RestController
@RequestMapping("/helloController")
@Slf4j
public class HelloController {

    @Autowired
    private JacksonService jacksonService;

    @GetMapping("/getJacksonInfo")
    public JacksonEntity getJacksonInfo(){
        return jacksonService.getJacksonInfo();
    }

    /**
     * 负载均衡测试
     * 分别使用application和application-dev来启动两个不同的port,返回不同的端口号（8763和8764）
     * @return
     */
    @GetMapping(value = "/getRibbon")
    public String getRibbon(){
        return "hi ribbon, from port 8764";
    }

    @GetMapping(value = "/verifyHystrix")
    public String verifyHystrix(){
        log.info("******接收到请求******");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello verifyHystrix";
    }
}
