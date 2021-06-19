package com.yahui.elasticjob;

import static com.yahui.elasticjob.MyElasticJobConfiguration.createJobConfiguration;
import static com.yahui.elasticjob.MyElasticJobConfiguration.createRegistryCenter;

import com.dangdang.ddframe.job.lite.api.JobScheduler;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2021-06-07
 */
public class JavaMain {
    public static void main(String[] args) {
        new JobScheduler(createRegistryCenter(), createJobConfiguration()).init();
    }
}
