package com.nalidao.v2.shoppingcart.domain.dto;

import lombok.Data;

@Data
public class ShoppingCartProductDto {

	private long id;
	private String name;
	private Double price;
	private int prodAmmount;
}
