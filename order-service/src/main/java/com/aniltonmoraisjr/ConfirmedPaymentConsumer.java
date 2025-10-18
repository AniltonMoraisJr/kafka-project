package com.aniltonmoraisjr;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.math.BigDecimal;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.hibernate.reactive.mutiny.Mutiny;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ConfirmedPaymentConsumer {
  private final Logger log = LoggerFactory.getLogger(ConfirmedPaymentConsumer.class);

  @Inject
  @Channel("notifyOrders")
  Emitter<NotifyOrderEvent> emitter;

  @Incoming("confirmedPayments")
  public Uni<Void> consume(ConfirmedPaymentEvent event) {
    log.info("Consuming confirmed payment event: {}", event);
    return Panache.withTransaction(() ->
      CustomerOrder.<CustomerOrder>findById(event.orderId)
          .call(entity -> Mutiny.fetch(entity.items))
          .onItem().ifNotNull()
          .invoke(customerOrder -> {
            customerOrder.status = OrderStatus.PAGO;
          }))
        .invoke(customerOrder -> {
          final double totalOrderValue = customerOrder.items.stream()
              .mapToDouble(item -> item.unitPrice.multiply(
                  BigDecimal.valueOf(item.amount)).doubleValue()).sum();
          final NotifyOrderEvent orderNotification = new NotifyOrderEvent(customerOrder.customer.name,
              customerOrder.customer.cpf, customerOrder.customer.phone,
              customerOrder.customer.address, BigDecimal.valueOf(totalOrderValue));
          emitter.send(orderNotification);
        }).replaceWithVoid();
  }
}
