package com.apigcc.example.spring.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 欢迎使用Apiggs
 *
 * @index 1
 */
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    /**
     * 示例接口
     * 自定义错误编码
     *
     * @param name 名称
     * @return greeting
     * @errorCode ERROR_CODE_1 错误编码1 很长很长的描述
     * @errorCode ERROR_CODE_2 错误编码2 很长很长的描述
     * @errorCode ERROR_CODE_3 错误编码3 很长很长的描述很长很长的描述很长很长的描述很长很长的描述很长很长的描述很长很长的描述
     * @errorCode ERROR_CODE_4 错误编码4 很长很长的描述很长很长的描述很长很长的描述很长很长的描述很长很长的描述很长很长的描述很长很长的描述很长很长的描述很长很长的描述
     */
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "apigcc") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}
