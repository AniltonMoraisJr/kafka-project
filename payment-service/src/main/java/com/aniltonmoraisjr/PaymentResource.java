package com.aniltonmoraisjr;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import java.util.List;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path( "/payments")
public class PaymentResource {

  private static final Logger log = LoggerFactory.getLogger(PaymentResource.class);

  @Inject
  @Channel("confirmedPayments")
  Emitter<ConfirmedPaymentEvent> emitter;

  @GET
  public Uni<List<PaymentDTOResponse>> list() {
    return Payment
        .listAll()
        .onItem()
        .transform(payments -> payments.stream().map(payment -> PaymentDTOResponse.fromEntity((Payment) payment)).toList());
  }

  @GET
  @Path("/{id}")
  public Uni<PaymentDTOResponse> byId(Long id) {
    return Payment.findById(id).onItem().transform(payment -> PaymentDTOResponse.fromEntity((Payment) payment));
  }

  @PUT
  @Path("/{id}")
  public Uni<PaymentDTOResponse> confirm(Long id) {
    return Panache.withTransaction(() ->
        Payment.<Payment>findById(id)
            .onItem().ifNotNull().invoke(pagamento -> {
              pagamento.status = PaymentStatus.CONFIRMADO;

              ConfirmedPaymentEvent event = new ConfirmedPaymentEvent();
              event.paymentId = pagamento.id;
              event.orderId = pagamento.orderId;

              log.info("Sending confirmed payment event through KAFKA `confirmedPayments` topic");
              emitter.send(event);
            })).onItem().transform(payment -> PaymentDTOResponse.fromEntity((Payment) payment));
  }

}
