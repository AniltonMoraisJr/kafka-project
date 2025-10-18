package com.aniltonmoraisjr;

public record CustomerResponseDTO(String name) {
  public static CustomerResponseDTO fromOrder(CustomerOrder order){
    return new CustomerResponseDTO(order.customer.name);
  }

}
