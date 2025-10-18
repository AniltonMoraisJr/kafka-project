package com.aniltonmoraisjr;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class CustomerOrderItem extends PanacheEntity {
  public Long amount;
  public BigDecimal unitPrice;
  public String observation;

  @ManyToOne
  @JsonIgnore
  public CustomerOrder customerOrder;

  @ManyToOne
  public MenuItem menuItem;
}
