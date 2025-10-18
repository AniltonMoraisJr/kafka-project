package com.aniltonmoraisjr;

public class ConfirmedPaymentEvent {
  public Long paymentId;
  public Long orderId;

  @Override
  public String toString() {
    return "ConfirmedPaymentEvent{" +
        "orderId=" + orderId +
        ", paymentId=" + paymentId +
        '}';
  }
}
