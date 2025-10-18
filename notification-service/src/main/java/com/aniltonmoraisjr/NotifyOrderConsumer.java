package com.aniltonmoraisjr;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class NotifyOrderConsumer {

  private static final Logger log = LoggerFactory.getLogger(NotifyOrderConsumer.class);

  @Inject
  PrintInvoiceOrder printInvoiceOrderStrategy;

  @Incoming("notifyOrders")
  public Uni<Void> notifyOrder(OrderEvent order){
    log.info("Receiving notification event");
    return Uni.createFrom().item(order).onItem().invoke(() -> {
      NotifyContext<OrderEvent> notifyContext = new NotifyContext<>(printInvoiceOrderStrategy);
      notifyContext.handleNotification(order);
    }).replaceWithVoid();
  }
}
