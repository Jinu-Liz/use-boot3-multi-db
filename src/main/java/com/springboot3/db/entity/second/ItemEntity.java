package com.springboot3.db.entity.second;

import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.GenerationType.*;

@Getter
@Entity
@Table(name = "item")
public class ItemEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private int itemId;

  private String dtype;

  private int price;

  private int stockQuantity;

}
