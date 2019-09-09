package com.zfw.consumer.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "provider")
//@Service(value = "userFeign")
public interface UserFeign {

    @GetMapping("/userController/getName")
    String getName(@RequestParam Integer id);

}