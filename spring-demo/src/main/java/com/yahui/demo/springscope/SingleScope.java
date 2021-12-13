package com.yahui.demo.springscope;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2021-09-02
 */
@Component
public class SingleScope {
    @Autowired
    private PrototypeScope prototypeScope;

    @PostConstruct
    public void init() {
        System.out.println("initSingleScope.");
    }
}
