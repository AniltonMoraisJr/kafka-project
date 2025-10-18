package com.aniltonmoraisjr;

import java.math.BigDecimal;

public record CustomerOrderItemResponseDTO(
    Long amount,
    BigDecimal unitPrice,
    String observation) {

  public static CustomerOrderItemResponseDTO fromEntity(CustomerOrderItem item) {
    return new CustomerOrderItemResponseDTO(item.amount, item.unitPrice, item.observation);
  }
}
