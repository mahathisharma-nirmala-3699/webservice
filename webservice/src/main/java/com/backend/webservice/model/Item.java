package com.backend.webservice.model;

import java.math.BigDecimal;

import lombok.Data;

@Data

public class Item {
  
private String shortDescription;
   private Double price; 
   
   public Item(String shortDescription, Double price) {
		super();
		this.shortDescription = shortDescription;
		this.price = price;
	}
}
