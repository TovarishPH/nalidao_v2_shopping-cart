package com.nalidao.v2.shoppingcart.domain.dto;

import java.math.BigInteger;
import java.util.List;

import lombok.Data;

@Data
public class FormCreationShoppingCartDto {

	private BigInteger userId;

	private List<ShoppingCartProductDto> productDtoList;
	
}
