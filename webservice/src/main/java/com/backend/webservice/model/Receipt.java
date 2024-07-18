package com.backend.webservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Data;


@Data
public class Receipt {
	private String id;
	private String retailer;
    private LocalDate purchaseDate;
    private LocalTime purchaseTime;
    private List<Item> items;
    private Double total;
    private Integer points;
}
