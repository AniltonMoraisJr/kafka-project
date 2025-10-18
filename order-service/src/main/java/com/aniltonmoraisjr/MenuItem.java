package com.aniltonmoraisjr;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.math.BigDecimal;

@Entity
public class MenuItem extends PanacheEntity {
  public String name;
  public String description;
  @Enumerated(EnumType.STRING)
  public MenuCategory category;
  public BigDecimal price;
  public BigDecimal promotionalPrice;

}
