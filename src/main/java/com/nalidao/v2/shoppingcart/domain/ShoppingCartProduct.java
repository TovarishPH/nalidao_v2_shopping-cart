package com.nalidao.v2.shoppingcart.domain;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartProduct {

	private long id;
	private String name;
	private Double price;
	@JsonAlias(value = "amount")
	private int prodAmmount;
}
