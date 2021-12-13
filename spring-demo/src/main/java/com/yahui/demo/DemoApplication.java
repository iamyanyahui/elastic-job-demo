package com.yahui.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yahui.demo.springscope.PrototypeScope;
import com.yahui.demo.springscope.SingleScope;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    private PrototypeScope prototypeScope;
    @Autowired
    private SingleScope singleScope;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
