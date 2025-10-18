package com.aniltonmoraisjr;

import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.util.List;

@Path("/orders")
public class CustomerOrderResource {

  @GET
  public Uni<List<CustomerOrderResponseDTO>> listAll() {
    return CustomerOrder
        .find("SELECT DISTINCT p FROM CustomerOrder p LEFT JOIN FETCH p.items")
        .list()
        .onItem()
        .transform(orders -> orders.stream().map(order -> CustomerOrderResponseDTO.fromOrder((CustomerOrder) order)).toList());
  }

  @GET
  @Path("/{id}")
  public Uni<CustomerOrder> byId(Long id) {
    return CustomerOrder.find("FROM CustomerOrder p LEFT JOIN FETCH p.items WHERE p.id = :id",
        Parameters.with("id", id)).firstResult();
  }
}
