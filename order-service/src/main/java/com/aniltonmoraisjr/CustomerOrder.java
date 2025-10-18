package com.aniltonmoraisjr;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@Entity
public class CustomerOrder extends PanacheEntity {
  public Instant orderDateTime;
  @Enumerated(EnumType.STRING)
  public OrderStatus status;
  @Embedded
  public Customer customer;
  @OneToMany(mappedBy = "customerOrder")
  public List<CustomerOrderItem> items;

  @Override
  public String toString() {
    return "Order{" +
        "id=" + id +
        ", status=" + status +
        '}';
  }
}
