package com.yahui.demo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2021-09-02
 */
@Service
public class ClazzB {
    @Autowired
    private AbstractClazz clazz;

    @PostConstruct
    public void init() {
        clazz.print();
    }
}
