package com.aniltonmoraisjr;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.math.BigDecimal;

@Entity
public class Payment extends PanacheEntity {
  public BigDecimal value;
  public Long orderId;
  @Enumerated(EnumType.STRING)
  public PaymentStatus status;

  @Override
  public String toString() {
    return "Payment{" +
        "value=" + value +
        ", orderId=" + orderId +
        ", status=" + status +
        ", id=" + id +
        '}';
  }
}
