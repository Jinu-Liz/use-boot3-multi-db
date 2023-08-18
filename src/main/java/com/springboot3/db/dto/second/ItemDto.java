package com.springboot3.db.dto.second;

import lombok.Data;

@Data
public class ItemDto {

  private int itemId;

  private String dtype;

  private int price;

  private int stockQuantity;

}
