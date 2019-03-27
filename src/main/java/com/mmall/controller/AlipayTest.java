package com.mmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello/")
public class AlipayTest {
    @RequestMapping("test.do")
    @ResponseBody
    public void test() {
        System.out.println("nihao------------------------------");
    }
}
