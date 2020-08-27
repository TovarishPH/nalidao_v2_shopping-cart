package com.nalidao.v2.shoppingcart.domain.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ShoppingCartDto {

	private BigInteger id;

	private BigInteger userId;

	private List<ShoppingCartProductDto> productDtoList;
	
	private Double totalPrice;

	private LocalDateTime creationDate;

	private LocalDateTime updateDate;
}
