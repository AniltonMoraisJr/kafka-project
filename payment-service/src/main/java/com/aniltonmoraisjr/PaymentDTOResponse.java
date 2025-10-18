package com.aniltonmoraisjr;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Payment}
 */
public record PaymentDTOResponse(Long id, BigDecimal value, Long orderId,
                                 PaymentStatus status) implements
    Serializable {

  public static PaymentDTOResponse fromEntity(Payment payment){
    return new PaymentDTOResponse(payment.id, payment.value, payment.orderId, payment.status);
  }

}