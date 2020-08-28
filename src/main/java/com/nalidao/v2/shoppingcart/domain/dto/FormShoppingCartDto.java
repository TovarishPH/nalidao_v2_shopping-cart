package com.nalidao.v2.shoppingcart.domain.dto;

import java.math.BigInteger;

import lombok.Data;

@Data
public class FormShoppingCartDto {

	private BigInteger userId;
	private long productId;
	private int productAmmount;
	
}
