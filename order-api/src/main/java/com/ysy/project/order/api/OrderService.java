package com.ysy.project.order.api;

import static com.lightbend.lagom.javadsl.api.Service.*;

import akka.Done;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import org.pcollections.PSequence;

import java.util.List;

import static com.lightbend.lagom.javadsl.api.transport.Method.POST;

public interface OrderService extends Service {

    ServiceCall<NotUsed, String> createOrder();
    ServiceCall<NotUsed, PSequence<OrderInfo>> getOrders();

    @Override
    default Descriptor descriptor() {
        return named("order")
                .withCalls(
                        restCall(POST, "/api/order/create", this::createOrder),
                        pathCall("/api/orders", this::getOrders)
                )
                .withAutoAcl(true);
    }
}
