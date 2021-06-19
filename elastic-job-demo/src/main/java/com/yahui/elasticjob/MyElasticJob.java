package com.yahui.elasticjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2021-06-07
 */
@Slf4j
public class MyElasticJob implements SimpleJob {
    @Override
    public void execute(ShardingContext context) {
        switch (context.getShardingItem()) {
            case 0:
                System.out.println("0000");
                break;
            case 1:
                System.out.println("1111");
                break;
            case 2:
                System.out.println("2222");
                break;
            default:
                System.out.println(context.getShardingItem());
        }
    }
}
