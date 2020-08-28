package com.nalidao.v2.shoppingcart.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartProductDto {

	private long id;
	private String name;
	private Double price;
	private int prodAmmount;
}
