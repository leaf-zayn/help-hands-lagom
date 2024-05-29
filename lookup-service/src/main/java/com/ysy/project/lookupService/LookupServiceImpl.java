package com.ysy.project.lookupService;
import akka.Done;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

public class LookupServiceImpl implements ServiceGuiceSupport {

    @Inject
    public LookupServiceImpl() {
    }

    public ServiceCall<NotUsed, Done> lookupConsumerDetails() {
        return request -> {
            // 查询消费者详情逻辑
            return CompletableFuture.completedFuture(Done.getInstance());
        };
    }
}
