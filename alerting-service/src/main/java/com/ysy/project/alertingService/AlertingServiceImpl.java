package com.ysy.project.alertingService;


import akka.Done;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

public class AlertingServiceImpl implements ServiceGuiceSupport {

    @Inject
    public AlertingServiceImpl() {
    }

    public ServiceCall<NotUsed, Done> sendAlert() {
        return request -> {
            // 发送警报逻辑
            return CompletableFuture.completedFuture(Done.getInstance());
        };
    }
}
