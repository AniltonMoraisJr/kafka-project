package com.aniltonmoraisjr;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.Instant;
import java.util.List;

@JsonPropertyOrder({"id", "orderDateTime", "status", "customer", "items"})
public record CustomerOrderResponseDTO(Long id, Instant orderDateTime, OrderStatus status, CustomerResponseDTO customer, List<CustomerOrderItemResponseDTO> items) {

  public static CustomerOrderResponseDTO fromOrder(CustomerOrder order){
    final List<CustomerOrderItemResponseDTO> itemResponseDTOS = order.items.stream()
        .map(CustomerOrderItemResponseDTO::fromEntity).toList();
    return new CustomerOrderResponseDTO(order.id, order.orderDateTime, order.status, CustomerResponseDTO.fromOrder(order), itemResponseDTOS);
  }
}
