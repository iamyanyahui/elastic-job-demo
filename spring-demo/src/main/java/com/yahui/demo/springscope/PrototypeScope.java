package com.yahui.demo.springscope;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2021-09-02
 */
@Component
@Scope("prototype")
public class PrototypeScope {
    private static int instanceCnt;

    @PostConstruct
    public void init() {
        System.out.println("initPrototypeScopeInstanceCnt. cnt:" + ++instanceCnt);
    }
}
