package com.fc.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DockerController
 * @Description
 * @Author fc
 * @Date 2022/8/26 2:34 下午
 * @Version 1.0
 **/
@RestController
@RequestMapping("/docker")
public class DockerController {

    @RequestMapping("/test")
    public String configTest() {
        return "branch-dev: hello docker+jenkins+spring-boot";
    }
}
